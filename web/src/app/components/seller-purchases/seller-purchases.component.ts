import {Component, OnInit} from '@angular/core';
import {SellerService} from "../../services/seller.service";
import {Order} from "../../domain/order";

@Component({
  selector: 'seller-purchases',
  templateUrl: 'seller-purchases.component.html',
  styleUrls: ['./seller-purchases.component.scss'],
})
export class SellerPurchasesComponent implements OnInit {

  private orders: Order[];

  constructor(private sellerService:SellerService) {
  }

  ngOnInit() {
    this.sellerService.getPurchases()
      .then(orders => this.orders = orders);
  }
}
