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

  private password = '';

  private validation = {
    'isLoginError': false,
    'isLoginValidError': false,
    'isEmailExistError': false,
    'isEmailValidError': false,
    'isPasswordError': false
  };

  constructor(private authService:AuthService,
              private location:Location,
              private loggedService:CustomObservable) {
  }

  user:UserRequest = new UserRequest();

  signUp():void {
    this.clearValid();
    if (this.password === this.user.password) {
      this.authService.register(this.user, this.role)
        .then(() => {
          this.loggedService.emitAuthChange(true);
          this.location.back();
        })
        .catch(index => {
          switch(index) {
            case 1: this.validation.isEmailValidError = true; break;
            case 2: this.validation.isEmailExistError = true; break;
            case 3: this.validation.isLoginError = true; break;
            case 4: this.validation.isLoginValidError = true; break;
          }
        });
    }
    else {
      this.validation.isPasswordError = true;
    }
  }

  changeRole():void {
    this.role = this.role == 0 ? 1 : 0;
  }

  clearValid() {
    for(var key in this.validation) {
      this.validation[key] = false;
    }
  }
}
