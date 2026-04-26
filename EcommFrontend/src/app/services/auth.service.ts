import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { BehaviorSubject, Observable } from 'rxjs';
import { LoginSuccess } from '../common/login-success';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  private baseUrl = "http://localhost:8083/users";

  constructor(private http: HttpClient) {}

  // 🔥 LOGIN STATE MANAGEMENT
  private loginStatus = new BehaviorSubject<boolean>(this.isLoggedIn());
  loginStatus$ = this.loginStatus.asObservable();

  // ---------------- API CALLS ----------------

  registerUser(data: any): Observable<any> {
    return this.http.post(`${this.baseUrl}/register`, data);
  }

  loginUser(data: any): Observable<LoginSuccess> {
    return this.http.post<LoginSuccess>(`${this.baseUrl}/login`, data);
  }

  forgotPassword(data: any): Observable<any> {
    return this.http.post(`${this.baseUrl}/forgot-password`, data);
  }

  changePassword(data: any): Observable<any> {
    return this.http.post(`${this.baseUrl}/reset-password`, data);
  }

  // ---------------- LOGIN STATE METHODS ----------------

  setLogin(name:string,role:string,email:string) {
   
    localStorage.setItem("login", "yes");
    localStorage.setItem("name", name);
    localStorage.setItem("role", role);
    localStorage.setItem("email",email);
    console.log(role);
    this.loginStatus.next(true);

  }

  logout() {
    localStorage.removeItem("login");
    localStorage.removeItem("name");
    localStorage.removeItem("role");
    localStorage.removeItem("email");
    this.loginStatus.next(false);
  }

  isLoggedIn(): boolean {
    return localStorage.getItem("login") === "yes";
  }

  getUserName(): string {
    return localStorage.getItem("name") || "";
  }

  getUserEmail():string{
   return localStorage.getItem("email") || ""; 
  }

  getUserRole():string{
    return localStorage.getItem("role") || "ROLE_USER";
  }
}