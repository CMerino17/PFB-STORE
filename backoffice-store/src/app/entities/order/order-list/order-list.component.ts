import { Component, OnInit } from '@angular/core';
import { User } from '../../user/model/user.model';
import { UserService } from '../../user/service/user.service';
import { OrderService } from '../service/order.service';

@Component({
  selector: 'app-order-list',
  templateUrl: './order-list.component.html',
  styleUrls: ['./order-list.component.scss']
})
export class OrderListComponent implements OnInit {
  user?: User;
  userId?: number;
  nick?: string;
  orders?: any[];
  orderItems?: any[];

  constructor(private userService: UserService,
              private orderService: OrderService) {}

  ngOnInit(): void {
    this.nick = localStorage.getItem("logged")!;
    this.getUser(this.nick);
  }


  private getUser(nick: string): void {
    this.userService.getUser(nick).subscribe({
      next: (data: any) => {
        this.user = data[0];
        this.userId = data[0].id;
        this.getOrders(this.userId!);
      }
    })
  }
  private getOrders(userId: number): void {
    this.orderService.getOrdersFromUser(userId).subscribe({
      next: (data: any) => {
        this.orders = data;
        console.log(this.orders);
        for(let order of this.orders!){
          this.orderItems = order.items;

        }
      }
    })
  }


}
