import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { LoginService } from './service/login.service';
import { UserService } from '../user/service/user.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit{

  mode: "LOGIN" | "SING UP" = "LOGIN"
  userId?: number;
  nick?: string;
  password?: string;

  login: boolean = false;

  nickFilter: string = "";

  constructor(private route: ActivatedRoute,
              private loginService: LoginService) { }


  ngOnInit(): void {
    
  }

  public loginUser(nick: string, password: string) {

    this.loginService.loginUser(nick, password).subscribe({
      next: (data: any) => {
        let response = data;
        if (response) {
          
          localStorage.setItem('logged',nick);
          console.log("LOGIN SUCCESS")
        } else {
          localStorage.removeItem('logged');
          console.log("LOGIN FAIL")
        }
      }
    });
        

  }

  public isLogged(): boolean {

    return this.login;
  }
}
