import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Item } from '../../item/model/item.model';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class OrderService {

  constructor(private http: HttpClient) { }

  public insertOrderIntoUser(userId: number, items: Item[]): Observable<any> {
    let urlEndpoint: string = "http://localhost:8080/store/users/"+userId+"/orders";
    let body = {items};
    return this.http.post<any>(urlEndpoint, body);
  }

  public getOrdersFromUser(userId: number): Observable<any[]> {
    let urlEndpoint: string = "http://localhost:8080/store/users/"+userId+"/orders";
    return this.http.get<any[]>(urlEndpoint);
  }
}
