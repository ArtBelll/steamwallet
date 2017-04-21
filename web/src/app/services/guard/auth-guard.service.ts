import {Injectable} from '@angular/core';
import {CanActivate, Router} from '@angular/router';
import {UserService} from "../user.service";

@Injectable()
export class AuthGuard implements CanActivate {
  constructor(private userService: UserService, private router: Router) {}

  private redirectUrl = "/home";

  canActivate(): Promise<boolean> {
    return this.userService.getCurrentUser().then(e => {
      if (e) {
        return true;
      }
    }).catch(() => {
      this.router.navigate([this.redirectUrl]);
      return Promise.resolve(false);
    });
  }

}
