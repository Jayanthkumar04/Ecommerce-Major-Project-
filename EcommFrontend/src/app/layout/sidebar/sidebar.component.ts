import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { ProductCategory } from '../../common/product-category';
import { RouterLink, RouterLinkActive } from '@angular/router';
import { CategoryService } from '../../services/category.service';

@Component({
  selector: 'app-sidebar',
  standalone: true,
  imports: [CommonModule,RouterLink,RouterLinkActive],
  templateUrl: './sidebar.component.html',
  styleUrl: './sidebar.component.css'
})
export class SidebarComponent {

  categories:ProductCategory[]=[{id:1,categoryName:"mobiles"}];


  constructor(private categoryService:CategoryService)
  {

  }

  ngOnInit(): void {
    
    this.listCategories();
   }
   
  listCategories()
  {

    this.categoryService.getAllCategories().subscribe(data=>{

          this.categories=data;
    }
  )

  }
}
