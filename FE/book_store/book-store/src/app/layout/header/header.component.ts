import { Component, OnInit } from '@angular/core';
import {ShareDataService} from "../../service/share-data.service";
import {TokenStorageService} from "../../service/token-storage.service";
import {BookService} from "../../service/book.service";
import {Category} from "../../model/category";

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {

  isLogin: boolean;
  name: string;
  categoryList: any;

  constructor(private shareDataService: ShareDataService,
              private tokenStorageService: TokenStorageService,
              private bookService: BookService) {
    shareDataService.currentLoginStatus.subscribe(status => {
      this.isLogin = status;
    })
    this.name = tokenStorageService.getName();
  }

  ngOnInit(): void {
    this.bookService.getCategory().subscribe(data => {
      this.categoryList = data;
    })
  }

  logOut() {
    localStorage.clear();
    sessionStorage.clear();
    this.shareDataService.changeLoginStatus(false);
  }
}
