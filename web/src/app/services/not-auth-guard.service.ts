import { Injectable }       from '@angular/core';
import {
  CanActivate, Router
}                           from '@angular/router';
import {UserService} from "./user.service";

@Injectable()
export class NotAuthGuard implements CanActivate {
  constructor(private userService: UserService, private router: Router) {}

  private redirectUrl = "/home";

  canActivate(): Promise<boolean> {
    return this.userService.checkSession().then(e => {
      if (e) {
        this.router.navigate([this.redirectUrl]);
        return false;
      }
    }).catch(() => {
      return Promise.resolve(true);
    });
  }

}
