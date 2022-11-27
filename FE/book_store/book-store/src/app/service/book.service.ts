import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {Category} from "../model/category";
import {Book} from '../model/book';

@Injectable({
  providedIn: 'root'
})
export class BookService {

  constructor(private httpClient: HttpClient) { }

  getCategory() : Observable<Category>{
    return this.httpClient.get<Category>('http://localhost:8080/api/public/category')
  }

  getBestseller(): Observable<Book[]>{
    return  this.httpClient.get<Book[]>("http://localhost:8080/api/public/book/bestseller")
  }

  getList(page: number) : Observable<Book[]>{
    return this.httpClient.get<Book[]>("http://localhost:8080/api/public/book?page=" + page)
  }
}
