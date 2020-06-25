import { Component, OnInit } from '@angular/core';
import { AuthService } from '../services/auth.service';
import { FormBuilder, FormGroup, Validators } from "@angular/forms";
import { Router } from '@angular/router';
import { first } from "rxjs/operators";
import { HttpResponse } from '@angular/common/http';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit 
{
  loginForm: FormGroup;
  loading = false;
  submitted = false;

  // convenience getter for easy access to form fields
  get f() { return this.loginForm.controls; }

  constructor(
    private auth: AuthService,
    private formBuilder: FormBuilder,
    private router: Router
    ) { 
      // redirect to home if already logged in
      if (this.auth.userToken) {
        this.router.navigate(['/']);
      }
    }

  ngOnInit() 
  {
    this.loginForm = this.formBuilder.group({
      username: ['', Validators.required],
      password: ['', Validators.required]
    });
  }

  onSubmit()
  {
    this.submitted = true;

    // stop here if form is invalid
    if (this.loginForm.invalid) {
      return;
    }

    this.loading = true;
    this.auth.login(this.f.username.value, this.f.password.value)
      .pipe(first())
      .subscribe(
        (data: HttpResponse<any>) => {
          // this.router.navigate([this.returnUrl]);
          this.router.navigate(['/']);
        },
        error => {
          // this.alertService.error(error);
          this.loading = false;
        });
  }

}
