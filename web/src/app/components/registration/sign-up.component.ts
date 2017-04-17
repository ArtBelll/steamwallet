import {Component} from '@angular/core';
import {UserRequest} from "../../domain/request/user-request";
import {Location}                 from '@angular/common';
import {CustomObservable} from "../../services/custom-observable.service";
import {AuthService} from "../../services/auth.service";

@Component({
  selector: 'sing-up',
  templateUrl: './sign-up.component.html',
  styleUrls: ['./sign-up.component.scss'],
})

export class SignUpComponent {

  private role = 1;

  constructor(private authService:AuthService,
              private location:Location,
              private loggedService:CustomObservable) {
  }

  user:UserRequest = new UserRequest();

  signUp():void {
    this.authService.register(this.user, this.role)
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
