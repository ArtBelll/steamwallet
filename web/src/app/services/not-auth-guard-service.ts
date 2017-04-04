import { Injectable }       from '@angular/core';
import {
  CanActivate, Router
}                           from '@angular/router';
import { AuthSellerService }      from './auth-seller-service';

@Injectable()
export class NotAuthGuard implements CanActivate {
  constructor(private authSellerService: AuthSellerService, private router: Router) {}

  private redirectUrl = "/";

  canActivate(): Promise<boolean> {
    return this.authSellerService.checkLogin().then(e => {
      if (e) {
        this.router.navigate([this.redirectUrl]);
        return false;
      }
    }).catch(() => {
      return Promise.resolve(true);
    });
  }

}
