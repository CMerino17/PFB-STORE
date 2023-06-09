import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HomeComponent } from './home/home.component';
import { NavbarComponent } from './layouts/navbar/navbar.component';
import { FooterComponent } from './layouts/footer/footer.component';
import { ItemListComponent } from './entities/item/item-list/item-list.component';
import { ItemFormComponent } from './entities/item/item-form/item-form.component';
import { CategoryListComponent } from './entities/category/category-list/category-list.component';
import { CategoryFormComponent } from './entities/category/category-form/category-form.component';
import { HttpRequestIntercept } from './config/interceptors/http-request-interceptor.interceptor';
import { AutoCompleteModule } from 'primeng/autocomplete';
import { ItemReactiveFormComponent } from './entities/item/item-reactive-form/item-reactive-form.component';
import { UserComponent } from './entities/user/user.component';
import { LoginComponent } from './entities/user/login/login.component';
import { RegisterComponent } from './entities/user/register/register.component';
import { CardItemComponent } from './entities/item/card-item/card-item.component';
import { CartComponent } from './entities/cart/cart.component';
import { OrderComponent } from './entities/order/order.component';
import { OrderListComponent } from './entities/order/order-list/order-list.component';

@NgModule({
  declarations: [
    AppComponent,
    HomeComponent,
    NavbarComponent,
    FooterComponent,
    ItemListComponent,
    ItemFormComponent,
    CategoryListComponent,
    CategoryFormComponent,
    ItemReactiveFormComponent,
    UserComponent,
    LoginComponent,
    RegisterComponent,
    CardItemComponent,
    CartComponent,
    OrderComponent,
    OrderListComponent
  ],
  imports: [
    BrowserModule,
    BrowserAnimationsModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule,
    ReactiveFormsModule,
    AutoCompleteModule
  ],
  providers: [
    {
      provide: HTTP_INTERCEPTORS,
      useClass: HttpRequestIntercept,
      multi: true
    }
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
