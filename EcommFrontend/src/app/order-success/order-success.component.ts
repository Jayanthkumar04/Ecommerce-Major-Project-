import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, RouterLink } from "@angular/router";

@Component({
  selector: 'app-order-success',
  standalone: true,
  imports: [RouterLink],
  templateUrl: './order-success.component.html',
  styleUrl: './order-success.component.css'
})
export class OrderSuccessComponent implements OnInit{

  orderTrackingId:string="";

  constructor(private route:ActivatedRoute){

  }

  ngOnInit(): void {
      this.orderTrackingId = this.route.snapshot.paramMap.get('id')!;
  }
}
