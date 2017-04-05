import { Injectable } from '@angular/core';
import { Headers, Http}  from "@angular/http";

import 'rxjs/add/operator/map'
import 'rxjs/add/operator/toPromise';

import {Seller} from '../domain/seller';
import {SellerRequest} from '../domain/request/sellerRequest';

@Injectable()
export class AuthSellerService {

    private urlRegister = 'api/v1/auth/seller/register';
    private urlLogin = 'api/v1/auth/seller/login';
    private urlCurrentSeller = 'api/v1/seller/session';
    private urlLogout = 'api/v1/auth/seller/logout';

    private headers = new Headers({'Content-Type': 'application/json'});

    constructor(private http: Http) { }

    private handleError(error: any): Promise<any> {
        console.error('An error occurred', error);
        return Promise.reject(error.message || error);
    }

    register(sellerRequest: SellerRequest): Promise<Seller> {
        return this.http
            .post(this.urlRegister, JSON.stringify(sellerRequest), {headers: this.headers})
            .toPromise()
            .then(response => response.json() as Seller)
            .catch(this.handleError)
    }

    signIn(sellerRequest: SellerRequest): Promise<Seller> {
      return this.http
        .post(this.urlLogin, JSON.stringify(sellerRequest) ,{headers: this.headers})
        .toPromise()
        .then(response => response.json() as Seller)
        .catch(this.handleError)
    }

    logOut(): Promise<any> {
      return this.http
        .get(this.urlLogout, {headers: this.headers})
        .toPromise()
        .catch(this.handleError)
    }
}
