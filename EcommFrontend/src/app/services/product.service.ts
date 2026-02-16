import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Product } from '../common/product';
import { map, Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ProductService {

  private apiUrl ="http://localhost:8080/api/products"
  constructor(private httpClient:HttpClient) { 


  }


  getProducts():Observable<Product[]>{
    
    return this.httpClient.get<GetResponse>(this.apiUrl)
                          .pipe(map(response=>response._embedded.products));
  }

  getProductsByCategoryId(categoryId:number):Observable<Product[]>{
    
      console.log(categoryId)
      const searchUrl = `${this.apiUrl}/search/findByCategoryId?id=${categoryId}`
    return this.httpClient.get<GetResponse>(searchUrl)
                          .pipe(map(response=>response._embedded.products));

  }

  searchProducts(keyword: string): Observable<Product[]> {

  const searchUrl =
    `${this.apiUrl}/search/findByNameContaining?name=${keyword}`;

  return this.httpClient.get<GetResponse>(searchUrl)
    .pipe(map(response => response._embedded.products));
}

getProductById(id: number): Observable<Product> {

  const productUrl = `${this.apiUrl}/${id}`;

  return this.httpClient.get<Product>(productUrl);

}


}

interface GetResponse{

  _embedded:{
    products:Product[];
  }
}
