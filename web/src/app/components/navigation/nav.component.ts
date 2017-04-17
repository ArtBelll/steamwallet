import {Component, OnInit} from '@angular/core';
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
  isSeller:boolean;

  constructor(private authService:AuthService,
              private userService:UserService,
              private loggedService:CustomObservable,
              private router:Router) {
    this.loggedService.changeAuthEmitted.subscribe(() => this.ngOnInit());
  }

  ngOnInit():void {
    this.userService.getRoleUser()
      .then(role => {
        this.isLoggedIn = true;
        this.isSeller = role == 0 ? true : false;
      })
      .catch(e => this.isLoggedIn = false)
  }

  gotoProfile() {
    this.userService.getRoleUser()
      .then(role => {
        let nameRole = '';
        if (role == 0) nameRole = "seller";
        else if (role == 1) nameRole = "buyer";
        this.router.navigate(['/profile/' + nameRole])
      });
  }

  logOut():void {
    this.authService.logOut()
      .then(() => {
        this.isLoggedIn = false;
      });
  }
}
