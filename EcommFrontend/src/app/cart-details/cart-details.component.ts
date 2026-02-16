import { Component, OnInit } from '@angular/core';
import { CartItems } from '../common/cart-items';
import { CommonModule, CurrencyPipe } from '@angular/common';
import { CartService } from '../services/cart.service';

@Component({
  selector: 'app-cart-details',
  standalone: true,
  imports: [CommonModule, CurrencyPipe],
  templateUrl: './cart-details.component.html',
  styleUrl: './cart-details.component.css'
})
export class CartDetailsComponent implements OnInit{


  totalQuantity: number = 0;
  totalPrice: number = 0;

  cartItems: CartItems[] =[];


  constructor(private cartService:CartService)
  {
      
  }

  ngOnInit(): void {
      // get cart items
    this.cartItems = this.cartService.cartItems;

    // subscribe to total quantity
    this.cartService.totalQuantity.subscribe(
      data => this.totalQuantity = data
    );

    // subscribe to total price
    this.cartService.totalPrice.subscribe(
      data => this.totalPrice = data
    );
  }

  increment(item: CartItems) {
    this.cartService.incrementQuantity(item);
  }

  decrement(item: CartItems) {
    if (item.quantity > 1) {
      this.cartService.decrementQuantity(item);
    }
  }

  remove(item: CartItems) {
   const itemIndex = this.cartItems.findIndex(
      tempItem => tempItem === item
    );

    if (itemIndex > -1) {
      this.cartItems.splice(itemIndex, 1);
      this.cartService.computeCartTotals();
    }
  }
}
 
