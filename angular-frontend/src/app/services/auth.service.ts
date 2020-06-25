import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { environment } from 'src/environments/environment';
import { map } from 'rxjs/operators';
import { BehaviorSubject } from 'rxjs';
import { Router } from '@angular/router';
import { User } from '../models/User';

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  public userTokenSubject: BehaviorSubject<User>;

  constructor(
    private router: Router,
    private http: HttpClient
    ) {
    this.userTokenSubject = new BehaviorSubject<User>(JSON.parse(localStorage.getItem('user')));
    // this.userSubject.next(-1); // becuase my userid's start at 0, not an issue when switching to tokens
   }

  public get userToken(): User
  {
    return this.userTokenSubject.value;
  }

  login (username, password)
  {
    let loginUser: User = new User();
    loginUser.username = username;
    loginUser.password = password;

    return this.http.post<any>(`${environment.authUrl}/auth/login`, JSON.stringify(loginUser))
      .pipe(map(user => {
        // store user details and jwt token in local storage to keep user logged in between page refreshes
        localStorage.setItem('user', JSON.stringify(user));
        this.userTokenSubject.next(user);
        return user;
      }));
  }

  logout() {
    // remove user from local storage and set current user to null
    localStorage.removeItem('user');
    this.userTokenSubject.next(null);
    this.router.navigate(['/login']);
  }

  register(user: User)
  {
    let json = JSON.stringify(user);
    return this.http.post(`${environment.authUrl}/auth/register`, json);
  }
  
  isLoggedIn(): boolean {
    return !!this.userTokenSubject.value; // check if it does not not exist
    // return this.userSubject.value > -1;
  }
}
