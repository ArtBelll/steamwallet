import {Injectable} from '@angular/core';

import 'rxjs/add/operator/map'
import 'rxjs/add/operator/toPromise';

import {Subject} from "rxjs/Rx";

@Injectable()
export class CustomObservable {

  private emitChangeAuth = new Subject<any>();

  changeAuthEmitted = this.emitChangeAuth.asObservable();

  emitAuthChange(change: any) {
    this.emitChangeAuth.next(change);
  }

}
