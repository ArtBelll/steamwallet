import {Injectable} from '@angular/core';
import {Headers, Http}  from "@angular/http";

import 'rxjs/add/operator/map'
import 'rxjs/add/operator/toPromise';

import {Seller} from "../domain/seller";
import {RequestMapping} from "../request-mapping";
import {ErrorHandler} from "../utility/error-handler";
import {Order} from "../domain/order";

@Injectable()
export class SellerService {

  private headers = new Headers({'Content-Type': 'application/json'});

  constructor(private http: Http) { }

  getAllSellers(): Promise<Seller[]> {
    return this.http
      .get(RequestMapping.getAllSellers, {headers: this.headers})
      .toPromise()
      .then(response => response.json() as Seller[])
      .catch(ErrorHandler.hendleError)
  }

  getSellerById(id: number): Promise<Seller> {
    let url = RequestMapping.register.replace('{0}', id.toString());
    return this.http
      .get(url, {headers: this.headers})
      .toPromise()
      .then(response => response.json() as Seller)
      .catch(ErrorHandler.hendleError)
  }

  getPurchases(): Promise<Order[]> {
    return this.http
      .get(RequestMapping.getSellerPurchases, {headers: this.headers})
      .toPromise()
      .then(response => response.json() as Order[])
      .catch(ErrorHandler.hendleError)
  }

  updateAddInfoSeller(seller: Seller) {
    return this.http
      .post(RequestMapping.updateAddInfoSeller, JSON.stringify(seller), {headers: this.headers})
      .toPromise()
      .catch(ErrorHandler.hendleError);
  }
}
