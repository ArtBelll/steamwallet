import {Component} from '@angular/core';
import {Location}                 from '@angular/common';
import {Product} from "../../domain/game-info/product";
import {BuyService} from "../../services/buy.service";

@Component({
  selector: 'pay-page',
  templateUrl: './pay-page.component.html',
  styleUrls: ['./pay-page.component.scss'],
})

export class PayPageComponent {

  private product:Product;

  constructor(private buyService:BuyService,
              private location:Location) {
    if (buyService.currentBuy.product) {
      this.product = buyService.currentBuy.product;
    }
    //TODO: Handle error
    else {
      this.product = new Product();
    }
  }

  goBack() {
    this.location.back();
  }

}
