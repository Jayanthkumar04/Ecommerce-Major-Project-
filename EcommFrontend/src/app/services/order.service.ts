import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { OrderRequest } from '../common/order-request';
import { OrderResponse } from '../common/order-response';
import { Observable } from 'rxjs';
import { FilterRequest } from '../common/filter-request';
import { FilterResponse } from '../common/filter-response';

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


  getUserOrders(request:FilterRequest)
  {
    return this.httpClient.post<FilterResponse[]>(`http://localhost:8083/users/filter`,request);
  }

  filterOrders(request:FilterRequest)
  {
    return this.httpClient.post<FilterResponse[]>(`http://localhost:8083/users/filter`,request);
  }

}
