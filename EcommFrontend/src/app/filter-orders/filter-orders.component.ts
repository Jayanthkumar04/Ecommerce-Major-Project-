import { Component, OnInit } from '@angular/core';
import { OrderService } from '../services/order.service';
import { FilterResponse } from '../common/filter-response';
import { FilterRequest } from '../common/filter-request';
import { CommonModule } from '@angular/common';
import { RouterLink } from '@angular/router';
import { FormBuilder, FormGroup, FormsModule, ReactiveFormsModule, Validators } from '@angular/forms';
import { ToastrService } from 'ngx-toastr';


@Component({
  selector: 'app-filter-orders',
  standalone: true,
  imports: [CommonModule,RouterLink,ReactiveFormsModule,FormsModule],
  templateUrl: './filter-orders.component.html',
  styleUrl: './filter-orders.component.css'
})
export class FilterOrdersComponent implements OnInit{
  orders!:FilterResponse[];
  filterRequest!:FormGroup;
  isFilled:boolean=false;
  constructor(private orderService:OrderService,private fb:FormBuilder,private toaster:ToastrService){
    this.filterRequest = this.fb.group({
      email:["",[Validators.required]],
      startDate:["",[Validators.required]],
      endDate:["",[Validators.required]]
    })
  }

  ngOnInit(): void {
    const request = new FilterRequest(null as any,null as any,null as any,null as any);
    this.orderService.getUserOrders(request).subscribe(
      data=>{
        this.orders = data;
        console.log(data);
      }
    )
  }


filterOrders() {
  this.isFilled = true;
  const form = this.filterRequest.value;


const request = new FilterRequest(
    this.clean(form.email),
    this.formatDate(form.startDate),
    this.formatDate(form.endDate),
    this.clean(form.orderId)
  );




  this.orderService.filterOrders(request).subscribe({
    next:(data)=>{
      this.orders = data;
    }
})

  console.log("Final Request:", request);
}


formatDate(date: any): string | null {
  if (!date) return null;

  const d = new Date(date);
  return d.toISOString().split('T')[0]; 
}


clean(value: any) {
  return value === '' || value === undefined ? null : value;
}





  }

  


