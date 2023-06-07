import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router'
import { ItemService } from '../service/item.service'
import { Item } from '../model/item.model'
import { UserService } from '../../user/service/user.service';
import { User } from '../../user/model/user.model';

@Component({
  selector: 'app-item-list',
  templateUrl: './item-list.component.html',
  styleUrls: ['./item-list.component.scss']
})
export class ItemListComponent implements OnInit {
  categoryId?: number;
  title: string = "";
  items: Item[] = [];

  page: number = 0;
  size: number = 25;
  sort: string = "name,asc";

  first: boolean = false;
  last: boolean = false;
  totalPages: number = 0;
  totalElements: number = 0;

  nameFilter?: string;
  priceFilter?: string;

  itemIdToDelete?: number;
  userId?: number;
  userIsLogged: boolean = false;

  constructor(private route: ActivatedRoute,
              private itemService: ItemService,
              private userService: UserService) {}

  ngOnInit(): void {
      if (this.route.snapshot.paramMap.get("categoryId")){
        this.categoryId = +this.route.snapshot.paramMap.get("categoryId")!;
        this.title = "Artículos de la categoría " + this.categoryId;
        
      } else {
        this.title = "Lista de artículos";
        
      }
      this.getAllItems();
      this.isLogged();
  }

  private isLogged() {
    if (localStorage.getItem("logged")) {
      this.userIsLogged = true;
      this.getUserByNick();
      return true;
    }
    return false;
  }

  private getUserByNick() {
    this.userService.getUser(localStorage.getItem("logged")!).subscribe({
      next: (data: any) => {
        this.userId = data[0].id;
      }
    })
  }

  public addItemToCart(userId:number, itemId:number){
   

    this.addItemToUserCart(userId, itemId);
  }
  private addItemToUserCart(userId: number, itemId: number) {
    this.userService.inserItemIntoCart(userId, itemId).subscribe({
      next: (data: any) => {
        
      }
    })
  }

  public nextPage(): void {
    this.page = this.page+1;
    this.getAllItems();
  }

  public previousPage(): void {
    this.page = this.page-1;
    this.getAllItems();
  }

  public searchByFilters(): void {
    this.getAllItems();
  }

  public prepareItemToDelete(itemId: number): void {
    this.itemIdToDelete = itemId;
  }

  public deleteItem(): void {
    if (this.itemIdToDelete) {
      this.itemService.deleteItem(this.itemIdToDelete).subscribe({
        next: (data) => {
          this.getAllItems();
        },
        error: (err) => {this.handleError(err)}
      })
    }
  }

  //No funcionan los filtros, lo devuelve todo
  public buildFilters(): string|undefined{
    const filters: string[] = [];

    if (this.categoryId) {
      filters.push("category.id:EQUAL:" + this.categoryId);
    }

    if (this.nameFilter) {
      filters.push("name:MATCH:" + this.nameFilter);
    }

    if (this.priceFilter) {
      filters.push("price:LESS_THAN_EQUAL:" + this.priceFilter)
    }

    if (filters.length > 0) {
      let globalFilters: string = "";
      for (let filter of filters){
        globalFilters = globalFilters + filter + ",";
      }
      globalFilters = globalFilters.substring(0,globalFilters.length-1);
      return globalFilters;
    } else {
      return undefined;
    }
  }

  private getAllItems(): void {

    const filters: string | undefined = this.buildFilters();

    this.itemService.getAllItems(this.page, this.size, this.sort, filters).subscribe({
      next: (data: any) => {
        this.items = data.content;
        this.first = data.first;
        this.last = data.last;
        this.totalPages = data.totalPages;
        this.totalElements = data.totalElements;
      },
      error: (err) => {this.handleError(err);}
    })
  }

  private handleError(error: any){
    // lo que queramos
  }

}
