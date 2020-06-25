import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { User } from '../models/User';
import { environment } from 'src/environments/environment';
import { stringify } from 'querystring';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class UsersService {

  constructor(
    private http: HttpClient
  ) { }

  getUserData(username: string = "")
  {
    if (username == "" || username == null) 
    {
      // get data of the logged in user
      return this.http.get(`${environment.userUrl}/api/details`);
    }
    else
    {
      // get data of dedicated username
      return this.http.post(`${environment.userUrl}/api/details`, username);
    }
  }

  getUserStats(username: string = "")
  {
    if (username == "" || username == null) 
    {
      return this.http.get(`${environment.userUrl}/api/stats`);
    }
    else
    {
      return this.http.post(`${environment.userUrl}/api/stats`, username);
    }
  }

  getAllUsers()
  {
    return this.http.get(`${environment.userUrl}/api/allusers`);
  }
  
  isFollowing(user: string)
  {
    return this.http.post(`${environment.userUrl}/api/isfollowing`, user);
  }

  followUser(user: string)
  {
    this.http.post(`${environment.userUrl}/api/follow`, user).subscribe();
  }
}
