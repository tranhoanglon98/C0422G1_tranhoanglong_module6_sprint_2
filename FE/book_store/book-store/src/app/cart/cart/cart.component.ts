import {Component, OnInit} from '@angular/core';
import {CartService} from '../../service/cart.service';
import {CartDetail} from '../../model/cart-detail';
import {ToastrService} from 'ngx-toastr';
import Swal from 'sweetalert2';
import {ShareDataService} from '../../service/share-data.service';
import {render, RenderParams} from 'creditcardpayments/creditCardPayments';

@Component({
  selector: 'app-cart',
  templateUrl: './cart.component.html',
  styleUrls: ['./cart.component.css']
})
export class CartComponent implements OnInit {
  items: CartDetail[];
  selected: CartDetail[] = [];
  totalPrice: number = 0;
  renderParam?: RenderParams;

  constructor(private cartService: CartService,
              private toastrService: ToastrService,
              private shareDataService: ShareDataService) {
    this.renderParam = {
      id: '#paypalButton',
      currency: 'VND',
      value: '0',
      onApprove: details => {
        alert("ok")
      }
    }
  }

  ngOnInit(): void {
    this.getCartItems();
    render(this.renderParam)
  }

  getCartItems() {
    this.cartService.getCartItems().subscribe(items => {
      this.items = items;
    });
  }

  select(item: CartDetail) {
    if (this.selected.includes(item)) {
      this.selected.splice(this.selected.indexOf(item), 1);
    } else {
      this.selected.push(item);
    }
    this.totalPrice = 0;
    this.selected.forEach(book => {
      this.totalPrice += book.quantity * book.bookPrice;
    });
    this.renderParam.value = String(this.totalPrice*0.000042);
  }

  increase(i: number) {
    this.items[i].quantity += 1;
    this.totalPrice = 0;
    this.selected.forEach(book => {
      this.totalPrice += book.quantity * book.bookPrice;
    });
    this.renderParam.value = String(this.totalPrice*0.000042);
  }

  decrease(i: number) {
    if (this.items[i].quantity > 1) {
      this.items[i].quantity -= 1;
    }
    this.totalPrice = 0;
    this.selected.forEach(book => {
      this.totalPrice += book.quantity * book.bookPrice;
    });
    this.renderParam.value = String(this.totalPrice*0.000042);
  }

  callToast(item: CartDetail) {
    Swal.fire({
      title: 'Xóa',
      text: 'Bạn có chắc chắn muốn xóa sách này không',
      showConfirmButton: true,
      confirmButtonColor: '#d33',
      confirmButtonText: 'Có',
      showCancelButton: true,
    }).then(result => {
      this.cartService.delete(item).subscribe(ok => {
        this.cartService.getCartItems().subscribe(items => {
          this.items = items;
        });
        this.shareDataService.changeCartItemsAmount(this.items.length);
        this.toastrService.success('Xóa thành công');
      });
    });
  }
}
