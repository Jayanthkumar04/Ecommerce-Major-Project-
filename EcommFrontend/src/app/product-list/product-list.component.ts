import { Component, OnInit } from '@angular/core';
import { Product } from '../common/product';
import { ProductService } from '../services/product.service';
import { CommonModule, CurrencyPipe } from '@angular/common';
import { ActivatedRoute, Router, RouterLink, RouterModule } from '@angular/router';
import { CategoryService } from '../services/category.service';
import { ProductCategory } from '../common/product-category';
import { CartItems } from '../common/cart-items';
import { CartService } from '../services/cart.service';

@Component({
  selector: 'app-product-list',
  standalone: true,
  imports: [CommonModule,RouterModule,RouterLink,CurrencyPipe,],
  templateUrl: './product-list.component.html',
  styleUrl: './product-list.component.css'
})
export class ProductListComponent implements OnInit{


  products:Product[]=[];

  categories:ProductCategory[]=[];
  currentCategoryId:number=1;
  constructor(private productService:ProductService,private route:ActivatedRoute,private router:Router,private categoryService:CategoryService,private cartService:CartService)
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

          this.categories=data;
    })

  }

  handleSearchProducts() {

  const keyword = this.route.snapshot.paramMap.get('keyword')!;

  this.productService.searchProducts(keyword).subscribe(data => {
    this.products = data;
  });
}

 addToCart(product:Product)
 {
  const cartItem = new CartItems(product.imageUrl,product.name,product.unitPrice,1);

  this.cartService.addToCart(cartItem);

  this.router.navigate(['cart-detail']);


 }



}
