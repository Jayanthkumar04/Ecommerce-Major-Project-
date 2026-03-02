import { CurrencyPipe } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, FormsModule, ReactiveFormsModule, Validators } from '@angular/forms';
import { ShippingAddress } from '../common/shipping-address';
import { OrderRequest } from '../common/order-request';
import { CartItems } from '../common/cart-items';
import { CartService } from '../services/cart.service';
import { OrderService } from '../services/order.service';
import { Router } from '@angular/router';


declare var Razorpay:any;
@Component({
  selector: 'app-checkout',
  standalone: true,
  imports: [CurrencyPipe,ReactiveFormsModule,FormsModule],
  templateUrl: './checkout.component.html',
  styleUrl: './checkout.component.css'
})
export class CheckoutComponent implements OnInit{

  checkoutForm!:FormGroup;
  totalQuantity:number=0;
  totalPrice:number=0;
  cartItems:CartItems[]=[];

  constructor(private fb:FormBuilder,private cartService:CartService,private orderService:OrderService,private router:Router){

  }

  ngOnInit(): void {
      
    this.cartItems = this.cartService.cartItems;

    this.cartService.totalQuantity.subscribe(
      data=>this.totalQuantity=data
    );

    this.cartService.totalPrice.subscribe(
      data=>this.totalPrice = data
    )


      this.checkoutForm = this.fb.group({
        user:this.fb.group({
          name:['',Validators.required],
          email:['',Validators.required],
          phno:['',Validators.required]
        }),
        shippingAddress:this.fb.group({
          houseNum:['',Validators.required],
          street: ['', Validators.required],
        city: ['', Validators.required],
        state: ['', Validators.required],
        zipcode: ['', Validators.required],
        country: ['', Validators.required]
        })
      })
  }

  placeOrder()
  {
    console.log("cmng inside placeorder")

    const checkoutData:OrderRequest={
      ...this.checkoutForm.value,
      orderItems:this.cartItems,
      totalQuantity:this.totalQuantity,
      totalPrice:this.totalPrice
      
    };

    console.log("checkout payload ",checkoutData);

    this.orderService.createOrder(checkoutData).subscribe({
      next:(response)=>{

        const options={
        key: 'rzp_test_SGsIHYSLIBF1ry',   // same as backend key
        amount: response.amount * 100,
        currency: 'INR',
        name: 'Jays Ecommerce Payment',
        description: response.orderTrackingNum,
        order_id: response.razorpayId,

        handler: (paymentResponse: any) => {
          console.log("Payment Success", paymentResponse);

          this.orderService.verifyPayment(paymentResponse)
              .subscribe(res => {
                 alert("Payment Successful ✅");
                 this.router.navigate(['/order-success', res.orderTrackingNum]);
              });
        },
        theme: {
          color: '#3399cc'
        }
    

        };
        const rzp = new Razorpay(options);
      rzp.open();
        


      }
    })
    

  }

}
