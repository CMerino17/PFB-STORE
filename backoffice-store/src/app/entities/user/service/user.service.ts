import { HttpClient } from '@angular/common/http'
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { User } from '../model/user.model'
import { Item } from '../../item/model/item.model';

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

}
