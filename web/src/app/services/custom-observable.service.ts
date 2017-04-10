import {Injectable} from '@angular/core';

import 'rxjs/add/operator/map'
import 'rxjs/add/operator/toPromise';

import {Subject} from "rxjs/Rx";

@Injectable()
export class CustomObservable {

  private emitChangeSource = new Subject<any>();

  changeEmitted = this.emitChangeSource.asObservable();

  emitChange(change: any) {
    this.emitChangeSource.next(change);
  }

}
