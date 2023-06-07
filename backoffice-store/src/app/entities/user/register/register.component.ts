import { Component, OnInit } from '@angular/core';
import { UserService } from '../service/user.service';
import { User } from '../model/user.model';
import { Router } from '@angular/router';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.scss']
})
export class RegisterComponent implements OnInit{
  user?: User;
  passwordRepeat: string = "";

  constructor(private userService: UserService, private route: Router){  }


  ngOnInit(): void {
    this.initializeUser();
  }

  initializeUser() {
    this.user = new User(undefined, "", "", "", 0, "", "",[],[]);
  }

  public saveUser(): void {
    if (this.samePassword(this.user!.password, this.passwordRepeat)) {
      this.insertUser();
      this.route.navigate(['/']);
    } else {
      console.log("No se ha insertado el usuario");
    }
  }

  public samePassword(password: string, passwordRepeat: string): boolean {
    if (password == passwordRepeat) {
      return true;
    }
    return false;
  }

  private insertUser(): void {
      this.userService.insert(this.user!).subscribe({
        next: (userInserted) => {
          console.log("insertado correctamente");
          console.log(userInserted);
        },
        error: (err) => {this.handleError(err);}
      })
    
  
  }

  handleError(err: any) {
    throw new Error('Method not implemented.');
  }
  

}
