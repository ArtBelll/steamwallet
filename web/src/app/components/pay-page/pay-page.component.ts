import {Component, OnInit} from '@angular/core';
import {Location}                 from '@angular/common';
import {Product} from "../../domain/game-info/product";
import {BuyService} from "../../services/buy.service";

@Component({
  selector: 'pay-page',
  templateUrl: './pay-page.component.html',
  styleUrls: ['./pay-page.component.scss'],
})

export class PayPageComponent implements OnInit{

  private product:Product;

  private seller:string;

  constructor(private buyService:BuyService,
              private location:Location) {
  }

  ngOnInit():void {
    if (this.buyService.currentBuy.product) {
      this.product = this.buyService.currentBuy.product;
      this.seller = this.buyService.currentBuy.seller.login;
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
