import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class LoginService {

  constructor(private http: HttpClient) { }

  public loginUser(nick: string, password: string): Observable<boolean>{
    let urlEndpoint: string = "http://localhost:8080/store/login";
    let body = {"nick":nick, "password":password}
    return this.http.post<boolean>(urlEndpoint,body);
  }
}
