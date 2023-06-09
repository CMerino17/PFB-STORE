import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class CartService {

  constructor(private http: HttpClient) { }

  public deleteItemFromCart(userId: number, itemId: number): Observable<any> {
    let urlEndpoint: string = "http://localhost:8080/store/users/"+userId+"/items/"+itemId;
    return this.http.delete<any>(urlEndpoint);
  }
}
