import { Injectable } from '@angular/core';
import { BehaviorSubject } from 'rxjs';
import { CartItems } from '../common/cart-items';

@Injectable({
  providedIn: 'root'
})
export class CartService {

  // store cart items
  cartItems: CartItems[] = [];

  // observable values for header
  totalQuantity: BehaviorSubject<number> = new BehaviorSubject<number>(0);
  totalPrice: BehaviorSubject<number> = new BehaviorSubject<number>(0);


  // ---------------------------
  // ADD TO CART
  // ---------------------------
  addToCart(item: CartItems) {

    let existingItem = this.cartItems.find(
      temp => temp.name === item.name
    );

    if (existingItem) {
      existingItem.quantity++;
    } else {
      this.cartItems.push(item);
    }

    this.computeCartTotals();
  }


  // ---------------------------
  // REMOVE FROM CART (Simple)
  // ---------------------------
  removeCart(index: number) {

    this.cartItems.splice(index, 1);

    this.computeCartTotals();
  }


  // ---------------------------
  // INCREMENT
  // ---------------------------
  incrementQuantity(item: CartItems) {
    item.quantity++;
    this.computeCartTotals();
  }


  // ---------------------------
  // DECREMENT
  // ---------------------------
  decrementQuantity(item: CartItems) {

    if (item.quantity > 1) {
      item.quantity--;
    }

    this.computeCartTotals();
  }


  // ---------------------------
  // CALCULATE TOTALS
  // ---------------------------
  computeCartTotals() {

    let totalPriceValue = 0;
    let totalQuantityValue = 0;

    for (let currentItem of this.cartItems) {

      totalPriceValue += currentItem.unitPrice * currentItem.quantity;
      totalQuantityValue += currentItem.quantity;
    }

    // send values to subscribers (Header & Cart Page)
    this.totalPrice.next(totalPriceValue);
    this.totalQuantity.next(totalQuantityValue);
  }

}
