import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Register } from '../common/register';
import { Observable } from 'rxjs';
import { Login } from '../common/login';
import { ForgotPassword } from '../common/forgot-password';
import { ChangePassword } from '../common/change-password';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  public baseUrl = "http://localhost:8083/users"
  constructor(private http:HttpClient) { }

  registerUser(data:Register):Observable<Register>{
    return this.http.post<Register>(`${this.baseUrl}/register`,data);
  }

  loginUser(data:Login){
    return this.http.post(`${this.baseUrl}/login`,data);
  }

  forgotPassword(data:ForgotPassword):Observable<ForgotPassword>{
    return this.http.post<ForgotPassword>(`${this.baseUrl}/forgot-password`,data);
  }

  changePassword(data:ChangePassword):Observable<ChangePassword>{
    return this.http.post<ChangePassword>(`${this.baseUrl}/change-password`,data);
  }

}
