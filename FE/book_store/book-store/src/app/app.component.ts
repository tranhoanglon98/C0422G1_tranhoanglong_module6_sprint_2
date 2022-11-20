import {Component, OnInit} from '@angular/core';
import {ShareDataService} from "./service/share-data.service";
import {filter} from "rxjs/operators";
import {NavigationEnd, Router} from "@angular/router";
import {TokenStorageService} from "./service/token-storage.service";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit{
  title = 'book-store';
  isLoginModule = false;
  previousUrl: string = null;
  currentUrl: string = null;

  constructor(private shareDataService: ShareDataService,
              private router: Router,
              private tokenStorageService: TokenStorageService) {
    if (tokenStorageService.getUsername() != undefined){
      shareDataService.changeLoginStatus(true)
    }
    shareDataService.currentLoginModuleStatus.subscribe(status => {
      this.isLoginModule = status;
    })
  }

  ngOnInit(): void {
    this.router.events.pipe(
      filter((event) => event instanceof NavigationEnd)
    ).subscribe((event: NavigationEnd) => {
      this.shareDataService.changePreviousUrl(this.currentUrl);
      this.currentUrl = event.url;
    });
  }
}
