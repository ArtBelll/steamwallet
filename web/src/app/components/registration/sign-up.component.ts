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

  signUp():void {
    this.authSellerService.register(this.seller)
      .then(seller => console.log(seller));
  }
}
