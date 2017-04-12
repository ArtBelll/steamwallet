import {Injectable} from '@angular/core';

import 'rxjs/add/operator/map'
import 'rxjs/add/operator/toPromise';
import {Buy} from "../domain/buy";

@Injectable()
export class BuyService {

  public currentBuy = new Buy();

  checkCurrentBuy():boolean {
    return this.currentBuy.gameUrl && this.currentBuy.seller && this.currentBuy.product != undefined;
  }
}
