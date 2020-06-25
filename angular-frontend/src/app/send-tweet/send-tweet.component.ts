import { Component, OnInit } from '@angular/core';
import { UsersService } from '../services/users.service';
import { TweetsService } from '../services/tweets.service';
import { User } from '../models/User';
import { Tweet } from '../models/Tweet';

@Component({
  selector: 'app-send-tweet',
  templateUrl: './send-tweet.component.html',
  styleUrls: ['./send-tweet.component.css']
})
export class SendTweetComponent implements OnInit 
{
  text: string;

  constructor(
    private userService: UsersService,
    private tweetService: TweetsService
  ) { }

  ngOnInit(): void {
  }

  onSubmit()
  {
    this.tweetService.postTweet(this.text).subscribe(
      data => {
        console.log("tweet posted");
      },
      err => {
        console.log("error posting tweet");
        console.log(err);
      },
      () => {
        console.log("tweet post completed");
      }
    );
  }
}
