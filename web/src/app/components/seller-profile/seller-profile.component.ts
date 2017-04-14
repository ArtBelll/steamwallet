import {Component, OnInit} from '@angular/core';
import {SellerService} from "../../services/seller.service";
import {Seller} from "../../domain/seller";
import {UserService} from "../../services/user.service";

@Component({
  selector: 'seller-profile',
  templateUrl: './seller-profile.component.html',
  styleUrls: ['./seller-profile.component.scss'],
})

export class SellerProfileComponent implements OnInit {

  private seller:Seller;

  constructor(private sellerService:SellerService,
              private userService:UserService) {
  }

  ngOnInit():void {
    this.userService.getCurrentUser()
      .then(seller => {
        console.log(seller);
        this.seller = seller;
      });
  }

  save() {
    this.sellerService.updateSeller(this.seller);
  }

}
