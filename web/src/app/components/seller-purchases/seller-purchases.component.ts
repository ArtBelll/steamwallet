import {Component, OnInit} from '@angular/core';
import {SellerService} from "../../services/seller.service";
import {Order} from "../../domain/order";
import {StatusService} from "../../services/status.service";

@Component({
  selector: 'seller-purchases',
  templateUrl: 'seller-purchases.component.html',
  styleUrls: ['./seller-purchases.component.scss'],
})
export class SellerPurchasesComponent implements OnInit {

  private orders: Order[];

  constructor(private sellerService:SellerService,
              private statusService:StatusService) {
  }

  ngOnInit() {
    this.sellerService.getPurchases()
      .then(orders => this.orders = orders);
  }

  getStatus(id:number):string {
    return this.statusService.getStatus(id);
  }
}
