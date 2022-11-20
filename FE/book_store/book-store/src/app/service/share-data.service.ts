import { Injectable } from '@angular/core';
import {BehaviorSubject} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class ShareDataService {

  private isLogin = new BehaviorSubject(false);
  private isLoginModule = new BehaviorSubject(false);
  private previousUrl = new BehaviorSubject("");

  constructor() { }

  currentLoginStatus = this.isLogin.asObservable();
  currentLoginModuleStatus = this.isLoginModule.asObservable();
  currentPreviousUrl = this.previousUrl.asObservable();

  changeLoginStatus(status: boolean){
    this.isLogin.next(status);
  }

  changeLoginModuleStatus(status: boolean){
    this.isLoginModule.next(status);
  }

  changePreviousUrl(url: string){
    this.previousUrl.next(url);
  }
}
