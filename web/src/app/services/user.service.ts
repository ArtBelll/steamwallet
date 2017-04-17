import { Injectable } from '@angular/core';
import { Headers, Http}  from "@angular/http";

import 'rxjs/add/operator/map'
import 'rxjs/add/operator/toPromise';
import {RequestMapping} from "../request-mapping";
import {User} from "../domain/core/user";

@Injectable()
export class UserService {

  private headers = new Headers({'Content-Type': 'application/json'});

  constructor(private http: Http) { }

  getCurrentUser(): Promise<User> {
    return this.http
      .get(RequestMapping.getCurrentUser, {headers: this.headers})
      .toPromise()
      .then(response => response.json());
  }

  getRoleUser(): Promise<number> {
    return this.http
      .get(RequestMapping.getCurrentRole)
      .toPromise()
      .then(response => response.json()["role"]);
  }
}
