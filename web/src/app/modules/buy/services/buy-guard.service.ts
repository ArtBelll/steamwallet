import {Injectable} from '@angular/core';
import {CanActivate, Router} from '@angular/router';
import {BuyService} from "./buy.service";

@Injectable()
export class BuyGuard implements CanActivate {
  constructor(private buyService:BuyService, private router:Router) {
  }

  private redirectUrl = "/sellers";

  canActivate():boolean {
    if (this.buyService.checkCurrentBuy()) {
      return true;
    }
    else {
      this.router.navigate([this.redirectUrl]);
      return false;
    }
  }

}
