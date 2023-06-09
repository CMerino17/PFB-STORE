import { Component, OnInit } from '@angular/core';
import { User } from '../user/model/user.model';
import { UserService } from '../user/service/user.service';
import { OrderService } from './service/order.service';

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
  address?: string;

  constructor(private userService: UserService,
              private orderService: OrderService) { }


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
    this.orderService.insertOrderIntoUser(this.user!.id!,this.user!.items).subscribe({
      next: (data: any) => {
        console.log(data);
        this.ordered = true;
      }
    })
  }


}
