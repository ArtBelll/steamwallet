import {Component, Input} from '@angular/core';
import {Router} from "@angular/router";
import {Seller} from "../../../../domain/seller";
import {BuyService} from "../../services/buy.service";

@Component({
  selector: 'seller-card',
  templateUrl: './seller-card.component.html',
  styleUrls: ['./seller-card.component.scss'],
})

export class SellersCardComponent {

  @Input('seller') seller:Seller;

  constructor(private buyService:BuyService,
              private router:Router) {
  }

  selectSeller(seller:Seller) {
    this.buyService.currentBuy.seller = seller;
    this.router.navigate(['buy/game-info']);
  }
}
