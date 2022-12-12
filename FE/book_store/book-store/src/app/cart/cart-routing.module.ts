import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {CartComponent} from './cart/cart.component';
import {BookDetailComponent} from './book-detail/book-detail.component';


const routes: Routes = [
  {path: 'cart',component: CartComponent},
  {path: 'book-detail/:id', component: BookDetailComponent}
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class CartRoutingModule { }
