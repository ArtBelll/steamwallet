import {Component, OnInit} from '@angular/core';
import {Seller} from "../../domain/seller";
import {SellerService} from "../../services/seller-service";

@Component({
  selector: 'sellers-list',
  templateUrl: './sellers-list.component.html',
  styleUrls: ['./sellers-list.component.css'],
})

export class SellersListComponent implements OnInit{

  private sellers:Seller[];

  constructor(private sellerService:SellerService) {}

  ngOnInit():void {
    this.sellerService.getAllSellers()
      .then(sellers => this.sellers = sellers);
  }

}
