import {Component} from '@angular/core';
import {UserRequest} from "../../domain/request/user-request";
import {Location}                 from '@angular/common';
import {CustomObservable} from "../../services/custom-observable.service";
import {AuthService} from "../../services/auth.service";

@Component({
  selector: 'sing-in',
  templateUrl: './sign-in.component.html',
  styleUrls: ['./sign-in.component.scss'],
})

export class SignInComponent {

  private role = 1;

  constructor(private authService:AuthService,
              private location:Location,
              private loggedService:CustomObservable) {
  }

  user:UserRequest = new UserRequest();

  signIn():void {
    this.authService.signIn(this.user, this.role)
      .then(user => {
        console.log(user);
        this.loggedService.emitAuthChange(true);
        this.location.back();
      });
  }

  changeRole():void {
    this.role = this.role == 0 ? 1 : 0;
  }
}
