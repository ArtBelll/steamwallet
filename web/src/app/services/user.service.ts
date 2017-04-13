import { Injectable } from '@angular/core';
import { Headers, Http}  from "@angular/http";

import 'rxjs/add/operator/map'
import 'rxjs/add/operator/toPromise';
import {RequestMapping} from "../request-mapping";

@Injectable()
export class UserService {

  private headers = new Headers({'Content-Type': 'application/json'});

  constructor(private http: Http) { }

  checkSession() {
    return this.http
      .get(RequestMapping.checkSession, {headers: this.headers})
      .map(response => response.ok)
      .toPromise()
  }
}
