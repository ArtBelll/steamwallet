import {Injectable} from '@angular/core';
import {Headers, Http}  from "@angular/http";

import 'rxjs/add/operator/map'
import 'rxjs/add/operator/toPromise';

import {UserRequest} from '../domain/request/userRequest';
import {Buyer} from "../domain/buyer";
import {IUserAuth} from "./user-auth";

@Injectable()
export class AuthBuyerService implements IUserAuth<Buyer> {

  private urlRegister = 'api/v1/auth/buyer/register';
  private urlLogin = 'api/v1/auth/buyer/login';
  private urlLogout = 'api/v1/auth/buyer/logout';

  private headers = new Headers({'Content-Type': 'application/json'});

  constructor(private http:Http) {
  }

  private handleError(error:any):Promise<any> {
    console.error('An error occurred', error);
    return Promise.reject(error.message || error);
  }

  register(userRequest:UserRequest):Promise<Buyer> {
    return this.http
      .post(this.urlRegister, JSON.stringify(userRequest), {headers: this.headers})
      .toPromise()
      .then(response => response.json() as Buyer)
      .catch(this.handleError)
  }

  signIn(userRequest:UserRequest):Promise<Buyer> {
    return this.http
      .post(this.urlLogin, JSON.stringify(userRequest), {headers: this.headers})
      .toPromise()
      .then(response => response.json() as Buyer)
      .catch(this.handleError)
  }

  logOut():Promise<any> {
    return this.http
      .get(this.urlLogout, {headers: this.headers})
      .toPromise()
      .catch(this.handleError)
  }
}
