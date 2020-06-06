import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { HomeComponent } from "./home/home.component";
import { ProfileComponent } from "./profile/profile.component";
import { LoginComponent } from './login/login.component';
import { RouteGuardService } from './services/route-guard.service';
import { LogoutComponent } from './logout/logout.component';

const routes: Routes = [
  {path: "", component: HomeComponent, canActivate: [RouteGuardService]},
  {path: "profile", component: ProfileComponent, canActivate: [RouteGuardService]},
  {path: "login", component: LoginComponent},
  {path: "logout", component: LogoutComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
