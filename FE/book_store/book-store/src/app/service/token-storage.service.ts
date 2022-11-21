import { Injectable } from '@angular/core';
import {LoginResponse} from "../model/login-response";

@Injectable({
  providedIn: 'root'
})
export class TokenStorageService {

  constructor() { }

  localStorageSave(loginResponse: LoginResponse) {
    localStorage.clear();
    sessionStorage.clear();
    localStorage.setItem("TOKEN",loginResponse.jwt);
    localStorage.setItem("USERNAME",loginResponse.username);
    localStorage.setItem("ROLE",JSON.stringify(loginResponse.roles));
    localStorage.setItem("NAME",loginResponse.name);
  }

  sessionStorageSave(loginResponse: LoginResponse) {
    localStorage.clear();
    sessionStorage.clear();
    sessionStorage.setItem("TOKEN",loginResponse.jwt);
    sessionStorage.setItem("USERNAME",loginResponse.username);
    sessionStorage.setItem("ROLE",JSON.stringify(loginResponse.roles));
    sessionStorage.setItem("NAME",loginResponse.name);
  }

  getToken(){
    if (localStorage.getItem("TOKEN") != undefined){
      return localStorage.getItem('TOKEN');
    }else {
      return sessionStorage.getItem('TOKEN');
    }
  }

  getUsername(){
    if (localStorage.getItem("USERNAME") != undefined){
      return localStorage.getItem('USERNAME');
    }else {
      return sessionStorage.getItem('USERNAME');
    }
  }

  getName(){
    if (localStorage.getItem("NAME") != undefined){
      return localStorage.getItem('NAME');
    }else {
      return sessionStorage.getItem('NAME');
    }
  }

  getRole(){
    if (localStorage.getItem("TOKEN") != undefined){
      return localStorage.getItem('TOKEN');
    }else {
      return sessionStorage.getItem('TOKEN');
    }
  }
}
