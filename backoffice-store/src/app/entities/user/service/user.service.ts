import { HttpClient } from '@angular/common/http'
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { User } from '../model/user.model'

@Injectable({
  providedIn: 'root'
})
export class UserService {

  constructor(private http: HttpClient) { }

  public getUser(partialName?: string): Observable<User>{
    let urlEndpoint: string = "http://localhost:8080/store/users";
    if (partialName) {
      urlEndpoint = urlEndpoint + "?partialName=" + partialName;
    }
    return this.http.get<User>(urlEndpoint);
  }

  public loginUser(nick: string, password: string): Observable<boolean>{
    let urlEndpoint: string = "http://localhost:8080/store/login";
    let body = {"nick":nick, "password":password}
    return this.http.post<boolean>(urlEndpoint,body);
  }

  public insert(user: User): Observable<User> {
    let urlEndpoint: string = "http://localhost:8080/store/users";
    return this.http.post<User>(urlEndpoint, user);
  }

  public deleteFavourite(userId: number, itemId: number): Observable<any> {
    let urlEndpoint: string = "http://localhost:8080/store/users/"+userId+"/favourites/"+itemId;
    return this.http.delete<any>(urlEndpoint);
  }

  public insertFavourite(userId: number, itemId: number): Observable<User> {
    let urlEndpoint: string = "http://localhost:8080/store/users/"+userId+"/favourites";
    let body = {"id":itemId}
    return this.http.post<User>(urlEndpoint, body);
  }

  public inserItemIntoCart(userId: number, itemId: number): Observable<any> {
    let urlEndpoint: string = "http://localhost:8080/store/users/"+userId+"/items";
    let body = {"id":itemId}
    return this.http.post<any>(urlEndpoint, body);
  }

  public deleteItemFromCart(userId: number, itemId: number): Observable<any> {
    let urlEndpoint: string = "http://localhost:8080/store/users/"+userId+"/items/"+itemId;
    return this.http.delete<any>(urlEndpoint);
  }

}
