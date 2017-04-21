import {Injectable} from '@angular/core';
import {Headers, Http} from "@angular/http";

import 'rxjs/add/operator/map'
import 'rxjs/add/operator/toPromise';
import {RequestMapping} from "../request-mapping";
import {PurchaseRequest} from "../domain/request/purchase-request";
import {ErrorHandler} from "../utility/error-handler";

@Injectable()
export class PurchaseService {

  private headers = new Headers({'Content-Type': 'application/json'});

  constructor(private http: Http) { }

  createPurchase(purchase:PurchaseRequest): Promise<PurchaseRequest> {
    return this.http.post(RequestMapping.createPurchase, JSON.stringify(purchase), {headers: this.headers})
      .toPromise()
      .then(response => response.json() as PurchaseRequest)
      .catch(ErrorHandler.hendleError);
  }
}
