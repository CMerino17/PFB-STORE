import { Component, OnInit } from '@angular/core';
import { UserService } from '../user/service/user.service';
import { User } from '../user/model/user.model';
import { CartService } from './service/cart.service';

@Component({
  selector: 'app-cart',
  templateUrl: './cart.component.html',
  styleUrls: ['./cart.component.scss']
})
export class CartComponent implements OnInit{

  nick?: string;
  user?: User;
  totalPrice: number = 0;
  quantity: number = 1;
  quantities: { [key: number]: number } = {};
  defaultQuantities: { [key: number]: number } = {};

  constructor(private userService: UserService,
              private cartService: CartService) { }


  ngOnInit(): void {
    this.nick = localStorage.getItem('logged')!;
    this.getUser(this.nick);
  }

  public deleteFromCart(userId: number, itemId: number): void {
    this.deleteItemFromCart(userId, itemId);
  }
  

  public calculateTotalPrice() {
    if (this.user!.items) {
      this.totalPrice = 0; 
      for (const item of this.user!.items) {
        const quantity = this.quantities[item.id!] || 0;
        this.totalPrice += item.price * quantity;
      }
    }  
  }

  private getUser(nick: string): void {
    
    this.userService.getUser(nick).subscribe({
      next: (data: any) => {
        this.user = data[0];
        for (let item of this.user!.items) {
          this.defaultQuantities[item.id!] = 1;
          this.quantities[item.id!] = 1;
        }
        
        this.calculateTotalPrice();
      
      }
    })
  }

  private deleteItemFromCart(userId: number, itemId: number) {
    delete this.quantities[itemId];
    this.cartService.deleteItemFromCart(userId, itemId).subscribe({
      next: (data: any) => {
        this.getUser(this.nick!);
      },
    })    
  }
}
