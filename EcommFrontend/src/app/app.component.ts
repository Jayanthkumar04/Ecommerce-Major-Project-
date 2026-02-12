import { Component, OnInit } from '@angular/core';
import { RouterOutlet, RouterLink, RouterLinkActive, RouterModule, Router } from '@angular/router';
import { ProductListComponent } from './product-list/product-list.component';
import { ProductCategory } from './common/product-category';
import { CategoryService } from './services/category.service';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [RouterLink, RouterLinkActive, RouterModule, CommonModule],
  templateUrl: './app.component.html',
  styleUrl: './app.component.css'
})
export class AppComponent implements OnInit{
  title = 'EcommFrontend';


   categories:ProductCategory[]=[];
   

   constructor(private categoryService:CategoryService,private router:Router)
   {

   }
   ngOnInit(): void {
    
    this.listCategories();
   }


   listCategories()
  {

    this.categoryService.getAllCategories().subscribe(data=>{

          this.categories=data;
    })


   }

   doSearch(keyword:string)
   {
console.log("Searching for:", keyword);

 if(keyword.trim() !== ''){
  this.router.navigate([`search`,keyword])
 }else {
    this.router.navigate(['category', 1]);
  }

   }


}