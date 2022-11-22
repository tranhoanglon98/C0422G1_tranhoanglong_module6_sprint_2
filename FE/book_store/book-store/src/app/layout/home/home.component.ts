import {Component, HostListener, OnInit} from '@angular/core';
import {BookService} from "../../service/book.service";
import {Category} from "../../model/category";

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {
  flip: number = 0;
  categoryList : Category[];

  constructor(private bookService: BookService) {
  }

  ngOnInit(): void {
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

}
