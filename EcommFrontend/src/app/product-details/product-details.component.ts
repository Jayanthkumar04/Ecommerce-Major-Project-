import { Component, OnInit } from '@angular/core';
import { Product } from '../common/product';
import { ProductService } from '../services/product.service';
import { ActivatedRoute, RouterLink } from '@angular/router';
import { CurrencyPipe } from '@angular/common';

@Component({
  selector: 'app-product-details',
  standalone: true,
  imports: [RouterLink,CurrencyPipe],
  templateUrl: './product-details.component.html',
  styleUrl: './product-details.component.css'
})
export class ProductDetailsComponent implements OnInit{


   selectedProduct !:Product;
   

   constructor(private productService:ProductService,private router:ActivatedRoute){

   }

   id:number=1;
   ngOnInit(): void {

         this.router.paramMap.subscribe(()=>{
         
         this.id=+this.router.snapshot.paramMap.get('id')!;

         this.getProductById(this.id);

         })
   }


  getProductById(id:number)
  {
    console.log(id,"getproductsbyId");
    this.productService.getProductById(id).subscribe(data=>{
      this.selectedProduct=data;
    })
  }
}
