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

  constructor(private authService:AuthService,
              private userService:UserService,
              private loggedService:CustomObservable,
              private router:Router) {
    this.loggedService.changeEmitted.subscribe(bool => this.isLoggedIn = bool);
  }

  ngOnInit():void {
    this.userService.getCurrentUser()
      .then(e => this.isLoggedIn = true)
      .catch(e => this.isLoggedIn = false)
  }

  gotoProfile() {
    this.userService.getRoleUser()
      .then(role => {
        console.log(role);
        this.router.navigate(['/profile/' + role])
      });
  }

  logOut():void {
    this.authService.logOut()
      .then(() => {
        this.isLoggedIn = false;
      });
  }
}
