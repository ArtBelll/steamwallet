import { Injectable } from '@angular/core';
import {CanActivate, Router} from '@angular/router';
import {UserService} from "../user.service";

@Injectable()
export class SellerGuard implements CanActivate {
  constructor(private userService: UserService, private router: Router) {}

  private redirectUrl = "/home";

  canActivate(): Promise<boolean> {
    return this.userService.getRoleUser().then(role => {
      if(role == 0) {
        return true;
      }
      else {
        this.router.navigate([this.redirectUrl]);
        return false;
      }
    }).catch(() => {
      this.router.navigate([this.redirectUrl]);
      return Promise.resolve(false);
    });
  }

}
