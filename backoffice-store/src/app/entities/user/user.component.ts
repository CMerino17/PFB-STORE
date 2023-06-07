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

  user?: User;
  nick?: string;
  password?: string;
  isFavourite: boolean = false;

  login: boolean = false;

  nickFilter: string = "";

  constructor(private route: ActivatedRoute,
              private userService: UserService) { }


  ngOnInit(): void {
    this.nick = localStorage.getItem('logged')!;
    this.getUser(this.nick);
    
  }

  private getUser(nick: string): void {
    this.userService.getUser(nick).subscribe({
      next: (data: any) => {
        this.user = data[0];
      }
    })
  }

  public delete(itemId: number, userId: number): void {
    this.deleteFavourite(itemId, userId);
  }

  private deleteFavourite(itemId: number, userId: number): void {
    this.userService.deleteFavourite(userId,itemId).subscribe({
      next: (data: any) => {
        this.isFavourite = false;
        this.getUser(this.nick!);
      }
    })
  }

  private isFavouriteItem(itemId: number, userNick: string): void {
    this.userService.getUser(userNick).subscribe({
      next: (data: any) => {
        for(let fav of data[0].favourites){
          if (fav.id == itemId) {
            this.isFavourite = true;
            console.log("marcado como fav" + this.isFavourite);
            break;
          }
        }
        
      }
    })
  }
  


}
