import { Component, OnInit } from '@angular/core';
import { Router, RouterLink, RouterModule } from '@angular/router';
import { CartService } from '../../services/cart.service';
import { CurrencyPipe } from '@angular/common';

@Component({
  selector: 'app-header',
  standalone: true,
  imports: [CurrencyPipe, RouterLink,RouterModule],
  templateUrl: './header.component.html',
  styleUrl: './header.component.css'
})
export class HeaderComponent implements OnInit{


  totalQuantity:number=0
  totalPrice:number=0

  constructor(private router:Router,private cartService:CartService)
  {

  }

  ngOnInit(): void {

    this.cartService.totalQuantity.subscribe(data=>{
      this.totalQuantity=data;
    })

    this.cartService.totalPrice.subscribe(data=>{
      this.totalPrice=data;
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
