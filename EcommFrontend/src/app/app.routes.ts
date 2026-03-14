import { Routes } from '@angular/router';
import { ProductListComponent } from './product-list/product-list.component';
import { ProductDetailsComponent } from './product-details/product-details.component';
import { CartDetailsComponent } from './cart-details/cart-details.component';
import { CheckoutComponent } from './checkout/checkout.component';
import { OrderSuccessComponent } from './order-success/order-success.component';
import { LoginComponent } from './login/login.component';
import { RegisterComponent } from './register/register.component';
import { ChangePasswordComponent } from './change-password/change-password.component';
import { ForgotPasswordComponent } from './forgot-password/forgot-password.component';
import { OrderDashboardComponent } from './order-dashboard/order-dashboard.component';

export const routes: Routes = [
  {path:'search/:keyword',component:ProductListComponent},
  {path:'product/:id',component:ProductDetailsComponent},
  {path:'category/:id',component:ProductListComponent},
  {path:'category',component:ProductListComponent},
  {path:'product',component:ProductListComponent},
  {path:'checkout',component:CheckoutComponent},
  {path:'cart-detail',component:CartDetailsComponent},
  {path:'order-success/:id',component:OrderSuccessComponent},
  {path:'login',component:LoginComponent},
  {path:'register',component:RegisterComponent},
  {path:'change-password',component:ChangePasswordComponent},
  {path:'change-password/:email',component:ChangePasswordComponent},
  {path:'forgot-password',component:ForgotPasswordComponent},
  {path:'orders-dashboard',component:OrderDashboardComponent},
  {path:'',component:ProductListComponent,pathMatch:'full'},
  {path:'**',component:ProductListComponent,pathMatch:'full'},

  
  
];
