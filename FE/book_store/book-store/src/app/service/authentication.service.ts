import { Injectable } from '@angular/core';
import {TokenStorageService} from './token-storage.service';
import {HttpHeaders} from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class AuthenticationService {
  token: string;
  httpOptions: any;


  constructor(private tokenStorageService:TokenStorageService) {
  }

  getHttpOption(){
    console.log(this.tokenStorageService.getToken());
    this.token = this.tokenStorageService.getToken();
    this.httpOptions = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json',
        'Authorization': 'Bearer ' + this.token
      })
    }
    return this.httpOptions
  }
}
