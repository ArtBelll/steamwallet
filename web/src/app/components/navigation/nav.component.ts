import {Component, OnInit} from '@angular/core';
import {AuthSellerService} from "../../services/auth-seller-service";
import {Router} from "@angular/router";
import {UserService} from "../../services/user-service";
import {CustomObservable} from "../../services/custom-observable-service";

@Component({
  selector: 'navigation',
  templateUrl: './nav.component.html',
  styleUrls: ['./nav.component.css'],
})

export class NavigationComponent implements OnInit {

  isLoggedIn:boolean;

  constructor(private authSellerService:AuthSellerService,
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
    this.authSellerService.logOut()
      .then(e => {
        this.isLoggedIn = false;
        this.route.navigate(['']);
      });
  }
}
