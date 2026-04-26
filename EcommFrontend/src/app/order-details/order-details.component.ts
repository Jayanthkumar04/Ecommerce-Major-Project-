import { CommonModule } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, RouterLink } from '@angular/router';
import { AuthService } from '../services/auth.service';
import { ToastrService } from 'ngx-toastr';
import { FilterRequest } from '../common/filter-request';
import { OrderService } from '../services/order.service';
import { FilterResponse } from '../common/filter-response';

@Component({
  selector: 'app-order-details',
  standalone: true,
  imports: [CommonModule, RouterLink],
  templateUrl: './order-details.component.html',
  styleUrl: './order-details.component.css'
})
export class OrderDetailsComponent implements OnInit {
  
  
  userOrders!:FilterResponse;

  constructor(private authService:AuthService,private toaster:ToastrService,private orderService:OrderService,private activatedRoute:ActivatedRoute)
  {

  }
  
  
  ngOnInit(): void {

    const orderId =Number(this.activatedRoute.snapshot.paramMap.get("id"));
    const email = this.authService.getUserEmail();
          console.log(email);
          if(email == "")
          {
            this.toaster.error("please login to view orders")
            return;
            
          }
          if(email.includes("admin"))
          {
const request = new FilterRequest(null as any,null as any,null as any,orderId);
    
        console.log("request ",request)
        this.orderService.getUserOrders(request).subscribe({
          next:(data)=>{
            this.userOrders = data[0];
            console.log("order-services",data);
          },
          error:(error)=>{
            this.toaster.error("failed to fetch user orders");
          }
        })

          }
    
        const request = new FilterRequest(email,null as any,null as any,orderId);
    
        console.log("request ",request)
        this.orderService.getUserOrders(request).subscribe({
          next:(data)=>{
            this.userOrders = data[0];
            console.log("order-services",data);
          },
          error:(error)=>{
            this.toaster.error("failed to fetch user orders");
          }
        })

     }


  

}
