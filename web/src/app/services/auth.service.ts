import {Injectable} from '@angular/core';
import {Headers, Http} from "@angular/http";

import 'rxjs/add/operator/toPromise';

import {Seller} from '../domain/seller';
import {UserRequest} from '../domain/request/user-request';
import {RequestMapping} from "../request-mapping";
import {User} from "../domain/core/user";
import {ErrorHandler} from "../utility/error-handler";

@Injectable()
export class AuthService {

  private headers = new Headers({'Content-Type': 'application/json'});

  constructor(private http:Http) {
  }

  register(userRequest:UserRequest, role:number):Promise<Seller> {
    let url = RequestMapping.register.replace('{0}', role.toString());
    return this.http
      .post(url, JSON.stringify(userRequest), {headers: this.headers})
      .toPromise()
      .then(response => response.json() as User)
      .catch(ErrorHandler.hendleError)
  }

  signIn(userRequest:UserRequest, role:number):Promise<Seller> {
    let url = RequestMapping.login.replace('{0}', role.toString());
    return this.http
      .post(url, JSON.stringify(userRequest), {headers: this.headers})
      .toPromise()
      .then(response => response.json() as User)
      .catch(ErrorHandler.hendleError)
  }

  logOut():Promise<any> {
    return this.http
      .get(RequestMapping.logout, {headers: this.headers})
      .toPromise()
      .catch(ErrorHandler.hendleError)
  }
}
