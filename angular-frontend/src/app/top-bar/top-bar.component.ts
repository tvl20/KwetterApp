import { Component, OnInit } from '@angular/core';
import { AuthService } from '../services/auth.service';
import { User } from '../models/User';
import { UsersService } from '../services/users.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-top-bar',
  templateUrl: './top-bar.component.html',
  styleUrls: ['./top-bar.component.css']
})
export class TopBarComponent implements OnInit {
  user: User;

  constructor(
    public auth: AuthService,
    private userService: UsersService,
    private router: Router
    ) 
  { }

  ngOnInit(): void {
    if (this.auth.isLoggedIn())
    {
      this.userService.getUserData().subscribe( 
        data => {
          this.user = data as User;
      });
    }
  }

  loadProfile(user: User)
  {
    this.router.routeReuseStrategy.shouldReuseRoute = () =>{return false};
    this.router.onSameUrlNavigation = "reload";
    this.router.navigate(["/profile", user.username]);
  }
}
