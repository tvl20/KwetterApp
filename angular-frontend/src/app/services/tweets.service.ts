import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { environment } from 'src/environments/environment';
import { User } from '../models/User';
import { UsernameWrapper } from '../models/UsernameWrapper';

@Injectable({
  providedIn: 'root'
})
export class TweetsService {

  constructor(
    private http: HttpClient
  ) { }

  getTimeline(names: User[]) {
    let followingUsernames: string[] = [];
    for (let i = 0; i < names.length; i++) {
      let username: string = names[i].username;
      console.log("Adding name to the list: " + username)
      followingUsernames.push(username);
    }

    console.log("Completed list with: " + followingUsernames[0]);

    let usernameWrapper: UsernameWrapper = new UsernameWrapper();
    usernameWrapper.usernames = followingUsernames;

    let jsonString: string = JSON.stringify(usernameWrapper);
    console.log("CALLING FOR TIMELINE WITH BODY: " + jsonString);

    return this.http.post(`${environment.tweetUrl}/api/timeline`, jsonString);

    // let observable = Observable.create((observer) =>{
    //   this.usersService.getUserData().subscribe(
    //     data => { 
    //       console.log("Data: " + data);
    //       let user: User = data as User; 
    //       console.log("User: " + user);
    //       console.log("JSON User: " + JSON.stringify(user));

    //       if (user == null) { console.error("User is null or error converting"); }
    //       else if (user.following == null) { console.log("User following not found"); }
    //       else {
    //         let followingUsernames: Set<string> = new Set<string>();
    //         for (let i = 0; i < user.following.length; i++) {
    //           const username: string = user.following[i].username;
    //           followingUsernames.add(username);
    //         }
    //         console.log("following:");
    //         console.log(followingUsernames);

            
    //       }
    //     }
    //   );
    // });
    // // let user: User = JSON.parse(localStorage.getItem('user')); // need follow data
    
    // return observable;
  }

  getTweets(username: string = "") {
    if (username == "")
    {
      return this.http.get(`${environment.tweetUrl}/api/posts`);
    }
    else
    {
      return this.http.post(`${environment.tweetUrl}/api/posts`, username);
    }
  }

  postTweet(message: string) {
    return this.http.post(`${environment.tweetUrl}/api/tweet`, message);
  }

}
