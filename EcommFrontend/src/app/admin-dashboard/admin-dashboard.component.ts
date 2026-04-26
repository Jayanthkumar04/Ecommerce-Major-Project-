import { Component, OnInit } from '@angular/core';
import { AdminDashboardService } from '../services/admin-dashboard.service';
import { AdminDashboardResponse } from '../common/admin-dashboard-response';
import { CommonModule, CurrencyPipe } from '@angular/common';
import { ToastrService } from 'ngx-toastr';

@Component({
  selector: 'app-admin-dashboard',
  standalone: true,
  imports: [CurrencyPipe,CommonModule],
  templateUrl: './admin-dashboard.component.html',
  styleUrl: './admin-dashboard.component.css'
})
export class AdminDashboardComponent implements OnInit{


  dashboardDetail!:AdminDashboardResponse;

  constructor(private dashboardService:AdminDashboardService,private toaster:ToastrService)
  {
      
  }

  ngOnInit(): void {
      this.getAdminDashboardDetails();
  }

  getAdminDashboardDetails()
  {
         this.dashboardService.getAdminDashboardDetails().subscribe({
          next:(data)=>{
            console.log(data);
            this.dashboardDetail = data;
          },
          error:(error)=>{
              this.toaster.error("Error fetching admin dashboard details");
          }

         })
  }



}
