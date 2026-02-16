import { Routes } from '@angular/router';
import { ProductListComponent } from './product-list/product-list.component';
import { ProductDetailsComponent } from './product-details/product-details.component';
import { CartDetailsComponent } from './cart-details/cart-details.component';
import { CheckoutComponent } from './checkout/checkout.component';

export const routes: Routes = [
  {path:'search/:keyword',component:ProductListComponent},
  {path:'product/:id',component:ProductDetailsComponent},
  {path:'category/:id',component:ProductListComponent},
  {path:'category',component:ProductListComponent},
  {path:'product',component:ProductListComponent},
  {path:'checkout',component:CheckoutComponent},
  {path:'cart-detail',component:CartDetailsComponent},
  {path:'',component:ProductListComponent,pathMatch:'full'},
  {path:'**',component:ProductListComponent,pathMatch:'full'},
  
  
];
