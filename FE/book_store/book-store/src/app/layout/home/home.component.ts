import {Component, OnInit} from '@angular/core';
import {BookService} from '../../service/book.service';
import {Book} from '../../model/book';

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
  totalPages: number;

  constructor(private bookService: BookService) {
  }

  ngOnInit(): void {
    this.getBestseller();
    this.getList();
  }

  getBestseller() {
    this.bookService.getBestseller().subscribe(data => {
      this.bestseller = data;
    });
  }

  getList() {
    this.page++;
    // @ts-ignore
    this.bookService.getList(this.page).subscribe(data => {
      // @ts-ignore
      this.totalPages = data.totalPages;
      console.log(this.page);
      // @ts-ignore
      this.pages[this.page] = data.content;
      console.log(this.pages);
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

}
