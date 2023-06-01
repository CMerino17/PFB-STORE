import { HttpClient } from '@angular/common/http'
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs'
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

  public getAllUsers(): Observable<User[]>{
    let urlEndpoint: string = "http://localhost:8080/store/users";
    return this.http.get<User[]>(urlEndpoint);
  }
}
