import {Component} from '@angular/core';
import {SellerRequest} from "../../domain/request/sellerRequest";

import {AuthSellerService} from "../../services/auth-seller-service"

@Component({
  selector: 'sing-up',
  templateUrl: './sign-up.component.html',
  styleUrls: ['./sign-up.component.css'],
})

export class SignUpComponent {

  constructor(private authSellerService:AuthSellerService) {
  }

  seller:SellerRequest = new SellerRequest();

  singUp():void {
    this.authSellerService.register(this.seller)
      .then(seller => console.log(seller));
  }

  getCurrentSeller():void {
    this.authSellerService.getCurrentSeller()
      .then(seller => console.log(seller));
  }

  logOut():void {
    this.authSellerService.logOut()
      .then();
  }
}
