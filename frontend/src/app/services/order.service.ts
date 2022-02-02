import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class OrderService {

  private readonly basePath = 'http://localhost:8081/purchase'

  constructor(private http: HttpClient) {
    this.http = http;
  }

  newOrder(newOrder) {
    var headers: HttpHeaders = new HttpHeaders({ 'Content-Type': 'application/json' });

    return this.http.post(this.basePath + '/new', JSON.stringify(newOrder),
    {headers, responseType : 'text' as 'json'});
  }

  getMostSoldProduct(agentUsername : String){
    return this.http.get(this.basePath + '/' + agentUsername + '/most_sold', {responseType: 'text'});
  }

  getMostProfitProduct(agentUsername : String){
    return this.http.get(this.basePath + '/' + agentUsername + '/most_profit', {responseType: 'text'});
  }
}
