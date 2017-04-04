import {Component, OnInit} from '@angular/core';
import {AuthSellerService} from "../../services/auth-seller-service";
import {Router} from "@angular/router";

@Component({
  selector: 'navigation',
  templateUrl: './nav.component.html',
  styleUrls: ['./nav.component.css'],
})

export class NavigationComponent implements OnInit {
  userIsAuth = true;

  constructor(
    private authSellerService:AuthSellerService
  ) {}

  ngOnInit(): void {
    this.authSellerService.checkLogin()
      .then(e => this.userIsAuth = true)
      .catch(e => this.userIsAuth = false)
  }

  logOut():void {
    this.authSellerService.logOut()
      .then(e => window.location.reload());
  }
}
