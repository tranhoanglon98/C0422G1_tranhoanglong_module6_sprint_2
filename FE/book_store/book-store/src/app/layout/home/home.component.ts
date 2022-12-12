import {Component, OnInit} from '@angular/core';
import {BookService} from '../../service/book.service';
import {Book} from '../../model/book';
import {ActivatedRoute, ParamMap} from '@angular/router';
import {CartService} from '../../service/cart.service';
import {ToastrService} from 'ngx-toastr';
import {ShareDataService} from '../../service/share-data.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {
  flip: number = 0;
  bestseller: Book[];
  pages = [];
  page: number = -1;
  totalPages: number = 0;
  searchValue: string = "";
  category: string = "";

  constructor(private bookService: BookService,
              private activatedRoute: ActivatedRoute,
              private cartService: CartService,
              private toastrService: ToastrService,
              private shareDataService: ShareDataService) {
    activatedRoute.paramMap.subscribe((paramMap: ParamMap) => {
      if (paramMap.get("searchValue") != null){
        this.searchValue = paramMap.get("searchValue")
        this.page = -1;
        this.pages = []
      }

      if (paramMap.get("category") != null){
        this.category = paramMap.get("category")
        this.page = -1;
        this.pages = []
      }
      this.getList();
    });
  }

  ngOnInit(): void {
    this.getBestseller();
  }

  getBestseller() {
    this.bookService.getBestseller().subscribe(data => {
      this.bestseller = data;
    });
  }

  getList() {
    this.page++;
    // @ts-ignore
    this.bookService.getList(this.page,this.searchValue, this.category).subscribe(data => {
      if (data != null){
        // @ts-ignore
        this.totalPages = data.totalPages
        if (this.pages.length == 0){
          this.pages = new Array(this.totalPages)
        }
        // @ts-ignore
        this.pages[this.page] = data.content;
      }else {
        this.totalPages = 0;
      }
      if (this.searchValue != "" || this.category != ""){
        window.scroll({
          top: document.getElementById('listBook').offsetTop,
          left: 0,
          behavior: 'smooth'
        });
      }
    });

  }

  left() {
    this.flip = 1;
  }

  right() {
    this.flip = 2;
  }

  mouseOut() {
    this.flip = 0;
  }

  addToCart(id: number) {
    this.cartService.addToCart(id).subscribe(data => {
      this.toastrService.success('đã thêm vào giỏ hàng')
      this.cartService.getCartItems().subscribe(items => {
        this.shareDataService.changeCartItemsAmount(items.length)
      })
    })
  }
}
