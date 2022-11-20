import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {LoginRequest} from "../model/login-request";
import {Observable} from "rxjs";
import {LoginResponse} from "../model/login-response";

@Injectable({
  providedIn: 'root'
})
export class SecurityService {

  constructor(private httpClient: HttpClient) { }


  login(loginRequest: LoginRequest): Observable<LoginResponse>{
    // @ts-ignore
    return this.httpClient.post<Observable<LoginResponse>>('http://localhost:8080/api/public/login',loginRequest)
  }


}
