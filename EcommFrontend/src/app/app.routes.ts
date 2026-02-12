import { Routes } from '@angular/router';
import { ProductListComponent } from './product-list/product-list.component';

export const routes: Routes = [
  {path:'category/:id',component:ProductListComponent},
  {path:'category',component:ProductListComponent},
  {path:'product',component:ProductListComponent},
  {path:'search/:keyword',component:ProductListComponent},
  {path:'',component:ProductListComponent,pathMatch:'full'},
  {path:'**',component:ProductListComponent,pathMatch:'full'},
  
  
];
