import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { AdminDashboardResponse } from '../common/admin-dashboard-response';

@Injectable({
  providedIn: 'root'
})
export class AdminDashboardService {

  constructor(private http:HttpClient) { }
      
  private apiUrl = "http://localhost:8084/admin"

  getAdminDashboardDetails():Observable<AdminDashboardResponse>
  {
          return this.http.get<AdminDashboardResponse>(this.apiUrl);
  }


  
  

}
