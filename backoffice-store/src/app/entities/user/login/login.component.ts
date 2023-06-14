import { Component, OnInit } from '@angular/core';
import { Router} from '@angular/router';
import { Location } from '@angular/common';
import { UserService } from '../service/user.service';

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
  isLogged: boolean = true;

  constructor(private userService: UserService,
              private location: Location,
              private route: Router) { }


  ngOnInit(): void {
    
  }

  public getLoginUser(nick: string, password: string): void {
    this.loginUser(nick, password);
  }

  private loginUser(nick: string, password: string) {

    this.userService.loginUser(nick, password).subscribe({
      next: (data: any) => {
        let response = data;
        if (response) {
          localStorage.setItem('logged',nick);
          console.log("LOGIN SUCCESS");
          this.isLogged = true;
          this.goBack();
        } else {
          localStorage.removeItem('logged');
          console.log("LOGIN FAIL")
          this.isLogged = false;
        }
      }
    });
        

  }

  goBack(): void {
    this.location.back();
  }

  navigateToRegister(): void {
    this.route.navigate(['register']);
  }

}
