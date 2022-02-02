import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class ProductService {

  private readonly basePath = 'http://localhost:8081/product'

  constructor(private http: HttpClient) {
    this.http = http;
  }

  getAllProducts(){
    return this.http.get(this.basePath + '/get_all', {responseType: 'json'});
  }

  getAgentProducts(agentUsername : String){
    return this.http.get(this.basePath + '/' + agentUsername, {responseType: 'json'});
  }

  deleteProduct(productId) {
    var headers: HttpHeaders = new HttpHeaders({ 'Content-Type': 'application/json' });

    return this.http.post(this.basePath + '/' + productId + '/delete',
    {headers, responseType : 'text' as 'json'});
  }

  updateProduct(productId, updatedProduct) {
    var headers: HttpHeaders = new HttpHeaders({ 'Content-Type': 'application/json' });

    return this.http.post(this.basePath + '/' + productId + '/update', JSON.stringify(updatedProduct),
    {headers, responseType : 'text' as 'json'});
  }

  createProduct(newProduct) {
    var headers: HttpHeaders = new HttpHeaders({ 'Content-Type': 'application/json' });

    return this.http.post(this.basePath + '/create', JSON.stringify(newProduct),
    {headers, responseType : 'text' as 'json'});
  }
}
