import { Component, OnInit } from '@angular/core';
import { Router, RouterLink, RouterModule } from '@angular/router';
import { CartService } from '../../services/cart.service';
import { CommonModule, CurrencyPipe } from '@angular/common';
import { AuthService } from '../../services/auth.service';
import { ToastrService } from 'ngx-toastr';

@Component({
  selector: 'app-header',
  standalone: true,
  imports: [CurrencyPipe, RouterLink, RouterModule, CommonModule],
  templateUrl: './header.component.html',
  styleUrl: './header.component.css'
})
export class HeaderComponent implements OnInit {

  name: string = "";
  isLogin: boolean = false;
  role:string="";


  totalQuantity: number = 0;
  totalPrice: number = 0;

  constructor(
    private router: Router,
    private cartService: CartService,
    private authService: AuthService,
    private toaster:ToastrService
  ) {}

  ngOnInit(): void {

    // Cart data
    this.cartService.totalQuantity.subscribe(data => {
      this.totalQuantity = data;
    });

    this.cartService.totalPrice.subscribe(data => {

      this.totalPrice = data;

    });

    // Listen for login changes
    this.authService.loginStatus$.subscribe(status => {
      this.isLogin = status;
      this.name = this.authService.getUserName();
      this.role=this.authService.getUserRole();
    });

    // Initial load
    this.isLogin = this.authService.isLoggedIn();
    this.name = this.authService.getUserName();
    this.role = this.authService.getUserRole();
   }

  doSearch(keyword: string) {
    if (keyword.trim() !== '') {
      this.router.navigate(['search', keyword]);
    } else {
      this.router.navigate(['category', 1]);
    }
  }

  logout() {
    this.authService.logout();
    this.toaster.success("User logged out successfully")
    this.router.navigate(['/login']);
  }
}