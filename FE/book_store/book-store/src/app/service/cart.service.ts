import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {TokenStorageService} from './token-storage.service';
import {AuthenticationService} from './authentication.service';
import {Observable} from 'rxjs';
import {CartDetail} from '../model/cart-detail';

@Injectable({
  providedIn: 'root'
})
export class CartService {
  httpOption: any

  constructor(private httpClient: HttpClient,
              private tokenStorageService: TokenStorageService,
              private authenticationService: AuthenticationService) {
  }

  getCartItems(): Observable<CartDetail[]> {
    this.httpOption = this.authenticationService.getHttpOption();

    // @ts-ignore
    return this.httpClient.get<CartDetail[]>('http://localhost:8080/api/user/cart?username='
      + this.tokenStorageService.getUsername(), this.httpOption);
  }

  addToCart(id: number) {
    const cartItem = {
      book: {
        id: id
      },
      account: {
        id: this.tokenStorageService.getId()
      }
    }
    return this.httpClient.post('http://localhost:8080/api/user/cart/save', cartItem,
      this.authenticationService.getHttpOption())
  }


  delete(item: CartDetail) {
    return this.httpClient.post('http://localhost:8080/api/user/cart/delete', item,
      this.authenticationService.getHttpOption())
  }

  updateAll(items: CartDetail[]) {
    return this.httpClient.post('http://localhost:8080/api/user/cart/update-all', items,
      this.authenticationService.getHttpOption())
  }

  pay(selected: CartDetail[]) {
    return this.httpClient.post('http://localhost:8080/api/user/cart/pay', selected,
      this.authenticationService.getHttpOption())
  }
}
