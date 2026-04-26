import { Component, OnInit } from '@angular/core';
import { OrderService } from '../services/order.service';
import { ToastrService } from 'ngx-toastr';
import { Router, RouterLink } from '@angular/router';
import { FilterResponse } from '../common/filter-response';
import { FilterRequest } from '../common/filter-request';
import { CommonModule } from '@angular/common';
import { AuthService } from '../services/auth.service';

declare var Razorpay:any;

@Component({
  selector: 'app-order-dashboard',
  standalone: true,
  imports: [CommonModule,RouterLink],
  templateUrl: './order-dashboard.component.html',
  styleUrl: './order-dashboard.component.css'
})
export class OrderDashboardComponent implements OnInit{



  userOrders!:FilterResponse[];


  constructor(private orderService:OrderService,private toaster:ToastrService,private router:Router,private authService:AuthService)
  {

  }
  ngOnInit(): void {
  

     
      const email = this.authService.getUserEmail();
      console.log(email);
      if(email == "")
      {
        this.toaster.error("please login to view orders")
        return;
        
      }

      const request = new FilterRequest(email,null as any,null as any,null as any);

    this.orderService.getUserOrders(request).subscribe({
      next:(data)=>{
        this.userOrders = data;
        console.log("user orders",data);
      },
      error:(error)=>{
        this.toaster.error("failed to fetch user orders");
      }
    })
    
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
