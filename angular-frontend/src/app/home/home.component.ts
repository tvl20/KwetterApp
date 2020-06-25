import { Component, OnInit } from '@angular/core';
import { UserStats } from '../models/UserStats';
import { Tweet } from '../models/Tweet';
import { UsersService } from '../services/users.service';
import { TweetsService } from '../services/tweets.service';
import { User } from '../models/User';
import { Router } from '@angular/router';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {
  public users: User[];
  public timeline: Tweet[];
  public stats: UserStats = new UserStats();

  constructor(
    private userService: UsersService,
    private tweetService: TweetsService,
    private router: Router
  ) {
   }

  ngOnInit(): void {
    this.userService.getUserStats().subscribe(
      data => {
        this.stats = data as UserStats;
        if (this.stats == null)
        {
          this.stats = new UserStats();
          this.stats.followers = -1;
          this.stats.following = -1;
        }
      }
    );

    this.userService.getUserData().subscribe(
      data => {
        console.log("GETTING TIMELINE FOR " + (data as User)?.username);
        console.log("GETTING TIMELINE WITH " + (data as User)?.following?.length);
        console.log("GETTING TIMELINE WITH USER " + (data as User)?.following[0]?.username);

        this.tweetService.getTimeline((data as User).following).subscribe(
          timelineData => {
            console.log("LOGGING TIMELINE " + JSON.stringify(timelineData));
            this.timeline = timelineData as Tweet[];
            if (this.timeline == null)
            {
              console.log("error parsing timeline");
              this.timeline = [];
            }
            console.log("Tweets Timeline: " + this.timeline);
          }
        );
    });

    this.userService.getAllUsers().subscribe(
      data => {
        this.users = data as User[];
        console.log(this.users);
      });
  }

  onClickProfile(user: User)
  {
    this.router.navigate(['/profile', user.username]);
  }

}
