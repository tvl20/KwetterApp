import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { ProfileComponent } from './profile/profile.component';
import { HomeComponent } from './home/home.component';
import { LoginComponent } from './login/login.component';
import { LogoutComponent } from './logout/logout.component';
import { TopBarComponent } from './top-bar/top-bar.component';

import { HttpClientModule, HTTP_INTERCEPTORS } from "@angular/common/http";

import { UsersService } from './services/users.service';
import { TweetsService } from './services/tweets.service';
import { AuthService } from "./services/auth.service";
import { RouteGuardService } from "./services/route-guard.service";
import { AuthInterceptorService } from './services/auth-interceptor.service';
import { RegisterComponent } from './register/register.component';
import { SendTweetComponent } from './send-tweet/send-tweet.component';

@NgModule({
  declarations: [
    AppComponent,
    ProfileComponent,
    HomeComponent,
    LoginComponent,
    LogoutComponent,
    TopBarComponent,
    RegisterComponent,
    SendTweetComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule,
    ReactiveFormsModule
  ],
  providers: [UsersService, TweetsService, AuthService, RouteGuardService,
  {
    provide: HTTP_INTERCEPTORS,
    useClass: AuthInterceptorService,
    multi: true
  }],
  bootstrap: [AppComponent]
})
export class AppModule { }
