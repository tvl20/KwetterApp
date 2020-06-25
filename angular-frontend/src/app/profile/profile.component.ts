import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router} from '@angular/router';
import { User } from '../models/User';
import { UsersService } from '../services/users.service';
import { Tweet } from '../models/Tweet';
import { TweetsService } from '../services/tweets.service';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css']
})
export class ProfileComponent implements OnInit 
{
  public user: User = new User();
  public tweets: Tweet[];
  public isFollowing: number = 2; //0 not following, 1 following, 2 self

  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private userService: UsersService,
    private tweetService: TweetsService
  ) { }

  ngOnInit(): void {
    this.userService.getUserData(this.route.snapshot.paramMap.get('username'))
    .subscribe(
      data =>{
        this.user = data as User;
      });

    this.tweetService.getTweets(this.route.snapshot.paramMap.get('username'))
    .subscribe(
      data =>{
        this.tweets = data as Tweet[];
        if (this.tweets == null)
        {
          console.log("account has no tweets");
          this.tweets = new Tweet[0]()
        }
      });

      this.userService.isFollowing(this.route.snapshot.paramMap.get('username'))
      .subscribe(
        data => {
          this.isFollowing = data as number;
        });
  }

  followUser()
  {
    console.log(this.user.username + " Clicked follow; " + this.isFollowing);

    this.userService.followUser(this.user.username);
    
    this.router.routeReuseStrategy.shouldReuseRoute = () => {return false};
    this.router.onSameUrlNavigation = "reload";
    this.router.navigate(["/profile", this.user.username]);
    
  }

}
