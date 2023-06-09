import { Component, OnInit } from '@angular/core';
import { Item } from '../model/item.model';
import { ActivatedRoute } from '@angular/router';
import { ItemService } from '../service/item.service';
import { UserService } from '../../user/service/user.service';
import { User } from '../../user/model/user.model';

@Component({
  selector: 'app-card-item',
  templateUrl: './card-item.component.html',
  styleUrls: ['./card-item.component.scss']
})
export class CardItemComponent implements OnInit {
  itemId?: number;
  item?: Item;
  userNick?: string;
  isFavourite: boolean = false;
  user?: User;
  isLogged: boolean = false;

  constructor(private route: ActivatedRoute,
    private itemService: ItemService,
    private userService: UserService) { }


  ngOnInit(): void {
    this.itemId = +this.route.snapshot.paramMap.get("itemId")!;
    this.userNick = localStorage.getItem("logged")!;
    if(localStorage.getItem("logged")){
      this.isLogged = true;
    }
    this.getItem(this.itemId!);
    this.isFavouriteItem(this.itemId,this.userNick);
  }

  private getItem(itemId: number): void {
    this.itemService.getItemById(itemId).subscribe({
      next: (data) => {
        this.item = data;
      }
    })
  }

  public addFavourite(itemId: number, userId: number):void {
    this.addFavouriteItem(itemId, userId);
  }

  public deleteFavourite(itemId: number, userId: number): void {
    this.deleteFavouriteItem(itemId,userId);
  }

  private addFavouriteItem(itemId: number, userId: number): void {
    this.itemService.insertFavourite(userId,itemId).subscribe({
      next: (data: any) => {
        this.isFavourite = true;
        
      }
    })
  }
  
  private isFavouriteItem(itemId: number, userNick: string): void {
    this.userService.getUser(userNick).subscribe({
      next: (data: any) => {
        this.user = data[0];
        for(let fav of data[0].favourites){
          if (fav.id == itemId) {
            this.isFavourite = true;
            break;
          }
        }
        
      }
    })
  }

  private deleteFavouriteItem(itemId: number, userId: number): void {
    this.itemService.deleteFavourite(userId,itemId).subscribe({
      next: (data: any) => {
        this.isFavourite = false;
      }
    })
  }

  public addItemToCart(userId: number, itemId: number){
    this.addItemToUserCart(userId, itemId);
  }

  private addItemToUserCart(userId: number, itemId: number) {
    this.itemService.inserItemIntoCart(userId, itemId).subscribe({
      next: (data: any) => {
        
      }
    })
  }
}
