import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { ProductCategory } from '../common/product-category';
import { map, Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class CategoryService {


  apiUrl:string="http://localhost:8080/api/product-category";

  constructor(private httpClient:HttpClient) { }


  getAllCategories():Observable<ProductCategory[]>
  {

return this.httpClient.get<getResponse>(this.apiUrl).pipe(map(response=>response._embedded["product-category"]));
       
  }

}

interface getResponse{

  _embedded:{
    "product-category":ProductCategory[];
  }
}
