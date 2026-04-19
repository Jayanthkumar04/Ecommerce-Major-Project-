import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { OrderRequest } from '../common/order-request';
import { OrderResponse } from '../common/order-response';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class OrderService {
apiUrl:string="http://localhost:8081/api/orders"
  constructor(private httpClient:HttpClient) { }


  createOrder(order: OrderRequest): Observable<OrderResponse> {
    console.log("request sent successfull");
    return this.httpClient.post<OrderResponse>(
      `${this.apiUrl}/checkout`,
      order
    );
  }

  verifyPayment(data: any): Observable<OrderResponse> {
  return this.httpClient.post<OrderResponse>(
    `${this.apiUrl}/verify-payment`,
    data
  );
}

  retryPayment(orderId:number){
    return this.httpClient.get<OrderResponse>(`${this.apiUrl}/retry/${orderId}`)
  }


}
