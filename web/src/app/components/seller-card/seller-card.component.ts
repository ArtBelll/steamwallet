import {Component, OnInit, Input} from '@angular/core';
import {Seller} from "../../domain/seller";

@Component({
  selector: 'seller-card',
  templateUrl: './seller-card.component.html',
  styleUrls: ['./seller-card.component.scss'],
})

export class SellersCardComponent {

  @Input('seller') seller:Seller;
}
