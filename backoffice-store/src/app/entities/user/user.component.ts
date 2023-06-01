import { Component, OnInit } from '@angular/core';
import { User } from './model/user.model';
import { ActivatedRoute } from '@angular/router';
import { UserService } from './service/user.service';

@Component({
  selector: 'app-user',
  templateUrl: './user.component.html',
  styleUrls: ['./user.component.scss']
})
export class UserComponent implements OnInit{

  mode: "LOGIN" | "SING UP" = "LOGIN"
  userId?: number;
  user?: User;
  nick?: string;
  password?: string;

  login: boolean = false;

  nickFilter: string = "";

  constructor(private route: ActivatedRoute,
              private userService: UserService) { }


  ngOnInit(): void {
    
  }

  public getAllUsers(): void {
    /*this.userService.getAllUsers().subscribe({
      next: (data: any) => {
        console.log(data);
        this.user = new User(data.id,data.nick,data.name,data.surname,data.phoneNumber,data.email,data.password);
        this.userId = data.id;
        this.nick = data.nick;
        this.password = data.password;
        
      }
    })*/
  }

  public searchUser(nick: string, password: string) {

    this.userService.getUser(nick).subscribe({
      next: (data: any) => {
        
        let json = data[0];
        //console.log(json);
        this.user = data[0];
       // console.log(this.user);
        
        if (this.user!.nick == nick) {
          this.login = true;
          console.log("EXISTE EL USER");
        }
        else {
          console.log("NO EXISTE EL USER");
          console.log(data.nick);
        }

        if (this.user!.password == password) {
          console.log("CONTRASEÑA CORRECTA");
        } else {
          console.log("CONTRASEÑA INCORRECTA");
        }
      }
    });
        

  }

}
