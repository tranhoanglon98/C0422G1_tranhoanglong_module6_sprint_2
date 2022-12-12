import {Component, OnInit} from '@angular/core';
import {ShareDataService} from '../../service/share-data.service';
import {TokenStorageService} from '../../service/token-storage.service';
import {BookService} from '../../service/book.service';
import {Router} from '@angular/router';
import {CartService} from "../../service/cart.service";

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {

  isLogin: boolean;
  name: string;
  categoryList: any;
  searchValue: string;
  cartItemsAmount: number = 0;

  constructor(private shareDataService: ShareDataService,
              private tokenStorageService: TokenStorageService,
              private bookService: BookService,
              private router: Router,
              private cartService: CartService) {
    this.cartService.getCartItems().subscribe(amount => {
      this.shareDataService.changeCartItemsAmount(amount.length)
    })
    shareDataService.currentLoginStatus.subscribe(status => {
      this.isLogin = status;
    })
    shareDataService.currentCartItemsAmount.subscribe(amount => {
      this.cartItemsAmount = amount;
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
    this.router.navigateByUrl('')
  }
}
