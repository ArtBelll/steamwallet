import { Injectable } from '@angular/core';
import { Headers, Http}  from "@angular/http";

import 'rxjs/add/operator/map'
import 'rxjs/add/operator/toPromise';

@Injectable()
export class UserService {

  private urlCheckSession = 'api/v1/user/session';

  private headers = new Headers({'Content-Type': 'application/json'});

  constructor(private http: Http) { }

  checkSession() {
    return this.http
      .get(this.urlCheckSession, {headers: this.headers})
      .map(response => response.ok)
      .toPromise()
  }
}
