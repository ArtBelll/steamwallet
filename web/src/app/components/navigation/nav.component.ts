import {Component, OnInit} from '@angular/core';
import {AuthSellerService} from "../../services/auth-seller.service";
import {Router} from "@angular/router";
import {UserService} from "../../services/user.service";
import {CustomObservable} from "../../services/custom-observable.service";
import {AuthService} from "../../services/auth.service";

@Component({
  selector: 'navigation',
  templateUrl: './nav.component.html',
  styleUrls: ['./nav.component.css'],
})

export class NavigationComponent implements OnInit {

  isLoggedIn:boolean;

  constructor(private authService:AuthService,
              private userService:UserService,
              private loggedService:CustomObservable,
              private route:Router) {
    this.loggedService.changeEmitted.subscribe(bool => this.isLoggedIn = bool);
  }

  ngOnInit():void {
    this.userService.checkSession()
      .then(e => this.isLoggedIn = true)
      .catch(e => this.isLoggedIn = false)
  }

  logOut():void {
    this.authService.logOut()
      .then(() => {
        this.isLoggedIn = false;
      });
  }
}
