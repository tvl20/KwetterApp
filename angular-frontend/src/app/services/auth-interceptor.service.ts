import { Injectable, Injector } from '@angular/core';
import { HttpInterceptor, HttpRequest, HttpHandler, HttpEvent } from '@angular/common/http';
import { Observable } from 'rxjs';
import { AuthService } from './auth.service';

@Injectable({
  providedIn: 'root'
})
export class AuthInterceptorService implements HttpInterceptor {

  constructor(
    private injector: Injector
  ) { }

  intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    let headers = req.headers;
    headers = headers.append("Content-Type", "application/json");
    
    let user = this.injector.get(AuthService).userToken;
    if (user != null && user.access_token != "") 
    {
      headers = headers.append("Authorization", "Bearer " + user.access_token);
    }
    
    let request = req.clone({
      headers: headers
    });
    
    return next.handle(request);
  }
}
