import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { environment } from 'src/environments/environment';
import { map } from 'rxjs/operators';
import { BehaviorSubject } from 'rxjs';
import { Router } from '@angular/router';

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  public userSubject: BehaviorSubject<number>;

  constructor(
    private router: Router,
    private http: HttpClient
    ) {
    this.userSubject = new BehaviorSubject<number>(JSON.parse(localStorage.getItem('user')));
    this.userSubject.next(-1); // becuase my userid's start at 0, not an issue when switching to tokens
   }

  public get user(): number{
    return this.userSubject.value;
  }

  login (username, password)
  {
    return this.http.post<any>(`${environment.authUrl}/login`, null, {headers: {username: username, password: password}})
      .pipe(map(user => {
        // store user details and jwt token in local storage to keep user logged in between page refreshes
        localStorage.setItem('user', JSON.stringify(user));
        console.log(user);
        this.userSubject.next(user);
        return user;
      }));
  }

  logout() {
    // remove user from local storage and set current user to null
    localStorage.removeItem('user');
    this.userSubject.next(null);
    this.router.navigate(['/login']);
  }
  
  isLoggedIn(): boolean {
    // return !!this.userSubject.value; // check if it does not not exist
    return this.userSubject.value > -1;
  }
}
