import { Injectable } from '@angular/core';
import { Headers, Http}  from "@angular/http";

import 'rxjs/add/operator/map'
import 'rxjs/add/operator/toPromise';
import {Seller} from "../domain/seller";

@Injectable()
export class SellerService {

  private urlAllSellers = 'api/v1/seller/all';
  private urlSellerById = 'api/v1/seller';

  private headers = new Headers({'Content-Type': 'application/json'});

  constructor(private http: Http) { }

  private handleError(error:any):Promise<any> {
    console.error('An error occurred', error);
    return Promise.reject(error.message || error);
  }

  getAllSellers() {
    return this.http
      .get(this.urlAllSellers, {headers: this.headers})
      .toPromise()
      .then(response => response.json() as Seller[])
      .catch(this.handleError)
  }

  getSellerById(id: number) {
    const url = `${this.urlSellerById}/${id}`;
    return this.http
      .get(url, {headers: this.headers})
      .toPromise()
      .then(response => response.json() as Seller)
      .catch(this.handleError)
  }
}
