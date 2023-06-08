import { Component, OnInit } from '@angular/core';
import { User } from '../user/model/user.model';
import { UserService } from '../user/service/user.service';
import { Item } from '../item/model/item.model';
import { ActivatedRoute, Params } from '@angular/router';

@Component({
  selector: 'app-order',
  templateUrl: './order.component.html',
  styleUrls: ['./order.component.scss']
})
export class OrderComponent implements OnInit{
  nick?: string;
  user?: User;
  itemsId?: number[]
  ordered: boolean = false;

  constructor(private userService: UserService,
              private route: ActivatedRoute) { }


  ngOnInit(): void {
    this.nick = localStorage.getItem("logged")!;
    this.getUser(this.nick);
  }

  private getUser(nick: string) {
    this.userService.getUser(nick).subscribe({
      next: (data: any) => {
        this.user = data[0];
        console.log(this.user);
      }
    })
    
  }

  public createOrder(): void {
    this.createOrderIntoUser();
  }

  private createOrderIntoUser(){
    this.userService.insertOrderIntoUser(this.user!.id!,this.user!.items).subscribe({
      next: (data: any) => {
        console.log(data);
        this.ordered = true;
      }
    })
  }


}
