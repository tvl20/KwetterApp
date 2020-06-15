import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class TweetsService {

  constructor(
    private http: HttpClient
  ) { }

  
}
