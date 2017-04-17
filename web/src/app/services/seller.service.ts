import { Injectable } from '@angular/core';
import { Headers, Http}  from "@angular/http";

import 'rxjs/add/operator/map'
import 'rxjs/add/operator/toPromise';
import {Seller} from "../domain/seller";
import {RequestMapping} from "../request-mapping";
import {ErrorHandler} from "./utility/error-handler";

@Injectable()
export class SellerService {

  private urlAllSellers = 'api/v1/seller/all';
  private urlSellerById = 'api/v1/seller';

  private headers = new Headers({'Content-Type': 'application/json'});

  constructor(private http: Http) { }

  getAllSellers(): Promise<Seller[]> {
    return this.http
      .get(this.urlAllSellers, {headers: this.headers})
      .toPromise()
      .then(response => response.json() as Seller[])
      .catch(ErrorHandler.hendleError)
  }

  getSellerById(id: number): Promise<Seller> {
    const url = `${this.urlSellerById}/${id}`;
    return this.http
      .get(url, {headers: this.headers})
      .toPromise()
      .then(response => response.json() as Seller)
      .catch(ErrorHandler.hendleError)
  }

  updateSeller(seller: Seller) {
    return this.http
      .post(RequestMapping.updateAddInfoSeller, JSON.stringify(seller), {headers: this.headers})
      .toPromise()
      .catch(ErrorHandler.hendleError);
  }
}
