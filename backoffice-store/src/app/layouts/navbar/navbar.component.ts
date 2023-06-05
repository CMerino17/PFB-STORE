import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.scss']
})
export class NavbarComponent implements OnInit{




  ngOnInit(): void {
    
  }

  isLogged(): boolean {
    if (localStorage.getItem('logged')) {
      return true;
    }
    return false;
  }

  getNick(): string | null{
    return localStorage.getItem("logged");
  }

  logout(): void {
    localStorage.removeItem('logged');

  }
}
