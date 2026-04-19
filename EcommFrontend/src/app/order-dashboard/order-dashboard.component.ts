import { Component } from '@angular/core';
import { OrderService } from '../services/order.service';
import { ToastrService } from 'ngx-toastr';
import { Router } from '@angular/router';
declare var Razorpay:any;

@Component({
  selector: 'app-order-dashboard',
  standalone: true,
  imports: [],
  templateUrl: './order-dashboard.component.html',
  styleUrl: './order-dashboard.component.css'
})
export class OrderDashboardComponent {

  constructor(private orderService:OrderService,private toaster:ToastrService,private router:Router)
  {

  }

  retryPayment(id:number)
  {this.orderService.retryPayment(id).subscribe({
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
                 this.orderService.verifyPayment(paymentResponse);
              });
        },
        theme: {
          color: '#3399cc'
        }
    

        };
        const rzp = new Razorpay(options);
        rzp.open();
        


      },
      error:(error)=>{
        this.toaster.error("Transaction is failed please enter correct details");
      }
    })
    
  }
}
