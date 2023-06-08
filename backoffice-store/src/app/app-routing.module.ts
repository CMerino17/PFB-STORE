import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomeComponent } from './home/home.component'
import { CategoryListComponent } from './entities/category/category-list/category-list.component'
import { ItemListComponent } from './entities/item/item-list/item-list.component'
import { ItemFormComponent } from './entities/item/item-form/item-form.component'
import { ItemReactiveFormComponent } from './entities/item/item-reactive-form/item-reactive-form.component'
import { UserComponent } from './entities/user/user.component';
import { LoginComponent } from './entities/user/login/login.component';
import { RegisterComponent } from './entities/user/register/register.component';
import { CardItemComponent } from './entities/item/card-item/card-item.component';
import { CartComponent } from './entities/cart/cart.component';
import { OrderComponent } from './entities/order/order.component';


const routes: Routes = [
  { path: '', component: HomeComponent, pathMatch: 'full' },
  { path: 'categories', component: CategoryListComponent },
  { path: 'items', component: ItemListComponent },
  { path: 'categories/:categoryId/items', component: ItemListComponent },
  { path: 'items/:itemId', component: ItemFormComponent },
  { path: 'items/reactive/:itemId', component: ItemReactiveFormComponent },
  { path: 'user', component: UserComponent },
  { path: 'login', component: LoginComponent },
  { path: 'register', component: RegisterComponent },
  { path: 'cardItem/:itemId', component: CardItemComponent },
  { path: 'cart', component: CartComponent },
  { path: 'order', component: OrderComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
