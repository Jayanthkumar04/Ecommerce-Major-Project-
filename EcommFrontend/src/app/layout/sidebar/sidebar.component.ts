import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { ProductCategory } from '../../common/product-category';
import { RouterLink, RouterLinkActive } from '@angular/router';
import { CategoryService } from '../../services/category.service';
import { AuthService } from '../../services/auth.service';

@Component({
  selector: 'app-sidebar',
  standalone: true,
  imports: [CommonModule,RouterLink,RouterLinkActive],
  templateUrl: './sidebar.component.html',
  styleUrl: './sidebar.component.css'
})
export class SidebarComponent {

  role:string="ROLE_USER";

  isLogin:boolean=false;

  
  categories:ProductCategory[]=[{id:1,categoryName:"mobiles"}];


  constructor(private categoryService:CategoryService,private authService:AuthService)
  {

  }

  ngOnInit(): void {
    this.listCategories();
    
    this.role = this.authService.getUserRole();

    this.authService.loginStatus$.subscribe(status=>{
      this.isLogin = status;
      this.role=this.authService.getUserRole();

    });

    this.isLogin = this.authService.isLoggedIn();


   }
   
  listCategories()
  {

    this.categoryService.getAllCategories().subscribe(data=>{

          this.categories=data;
    }
  )

  }
}
