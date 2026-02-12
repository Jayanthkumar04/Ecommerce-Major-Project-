import { Component, OnInit } from '@angular/core';
import { Product } from '../common/product';
import { ProductService } from '../services/product.service';
import { CommonModule } from '@angular/common';
import { ActivatedRoute, Router, RouterModule } from '@angular/router';
import { CategoryService } from '../services/category.service';
import { ProductCategory } from '../common/product-category';

@Component({
  selector: 'app-product-list',
  standalone: true,
  imports: [CommonModule,RouterModule],
  templateUrl: './product-list.component.html',
  styleUrl: './product-list.component.css'
})
export class ProductListComponent implements OnInit{


  products:Product[]=[];

  categories:ProductCategory[]=[];
  currentCategoryId:number=1;
  constructor(private productService:ProductService,private route:ActivatedRoute,private router:Router,private categoryService:CategoryService)
  {

  }

  ngOnInit(): void {

       this.route.paramMap.subscribe(() => {
      if (this.route.snapshot.paramMap.has('keyword')) {
      this.handleSearchProducts();
    } else {
      this.listProducts();
    }
    })

   this.listCategories();

  }

  listProducts()
  {

    const hasCategoryId:boolean =  this.route.snapshot.paramMap.has('id');

    if(hasCategoryId)
    {
        this.currentCategoryId=+this.route.snapshot.paramMap.get('id')!;
    }
    else
    {
      this.currentCategoryId=1;
    }

    this.productService.getProductsByCategoryId(this.currentCategoryId).subscribe(data=>{
      this.products=data;
    })
  }


  listCategories()
  {

    this.categoryService.getAllCategories().subscribe(data=>{

         
         console.log("i am cmng",data);
          this.categories=data;
    })

  }

  handleSearchProducts() {

  const keyword = this.route.snapshot.paramMap.get('keyword')!;

  this.productService.searchProducts(keyword).subscribe(data => {
    this.products = data;
  });
}

}
