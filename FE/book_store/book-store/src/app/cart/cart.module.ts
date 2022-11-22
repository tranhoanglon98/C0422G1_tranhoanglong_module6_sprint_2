import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { CartRoutingModule } from './cart-routing.module';
import { CartComponent } from './cart/cart.component';
import { BookDetailComponent } from './book-detail/book-detail.component';


@NgModule({
    declarations: [CartComponent, BookDetailComponent],
    exports: [
        CartComponent
    ],
    imports: [
        CommonModule,
        CartRoutingModule
    ]
})
export class CartModule { }
