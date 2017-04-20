import {Component, OnInit, Input} from '@angular/core';
import {Order} from "../../domain/order";
import {StatusService} from "../../services/status.service";

@Component({
  selector: 'purchase',
  templateUrl: 'purchase.component.html',
  styleUrls: ['./purchase.component.scss'],
})
export class PurchaseComponent {

  @Input() order: Order;

  constructor(private statusService:StatusService) {
  }

  getStatus():string {
    return this.statusService.getStatus(this.order.status);
  }
}
