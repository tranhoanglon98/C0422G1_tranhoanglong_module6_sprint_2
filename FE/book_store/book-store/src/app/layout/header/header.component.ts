import { Component, OnInit } from '@angular/core';
import {ShareDataService} from "../../service/share-data.service";
import {TokenStorageService} from "../../service/token-storage.service";

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {

  isLogin: boolean;
  name: string;

  constructor(private shareDataService: ShareDataService,
              private tokenStorageService: TokenStorageService) {
    shareDataService.currentLoginStatus.subscribe(status => {
      this.isLogin = status;
    })
    this.name = tokenStorageService.getName();
  }

  ngOnInit(): void {
  }

  logOut() {
    localStorage.clear();
    sessionStorage.clear();
    this.shareDataService.changeLoginStatus(false);
  }
}
