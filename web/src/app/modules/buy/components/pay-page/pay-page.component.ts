import {Component, OnInit} from '@angular/core';
import {Location}                 from '@angular/common';
import {Router} from "@angular/router";
import {PurchaseService} from "../../../../services/purchase.service";
import {BuyService} from "../../services/buy.service";
import {PurchaseRequest} from "../../../../domain/request/purchase-request";
import {Product} from "../../../../domain/game-info/product";
import {Seller} from "../../../../domain/seller";

@Component({
  selector: 'pay-page',
  templateUrl: './pay-page.component.html',
  styleUrls: ['./pay-page.component.scss'],
})

export class PayPageComponent implements OnInit{

  private product:Product;

  private seller:Seller;

  constructor(private buyService:BuyService,
              private location:Location,
              private purchaseService:PurchaseService,
              private router:Router) {
  }

  ngOnInit():void {
    if (this.buyService.currentBuy.product) {
      this.product = this.buyService.currentBuy.product;
      this.seller = this.buyService.currentBuy.seller;
    }
    //TODO: Handle error
    else {
      this.product = new Product();
    }
  }

  pay() {
    let purchaseRequest = new PurchaseRequest(this.seller.id, this.product.price,
      this.buyService.currentBuy.gameUrl, this.product.name);

    this.purchaseService.createPurchase(purchaseRequest)
      .then(response => this.router.navigate(['/home']));
  }

  goBack() {
    this.location.back();
  }

}
