import {Component} from '@angular/core';
import {SellerRequest} from "../../domain/request/sellerRequest";

import {AuthSellerService} from "../../services/auth-seller-service"

@Component({
  selector: 'sing-in',
  templateUrl: './sign-in.component.html',
  styleUrls: ['./sign-in.component.css'],
})

export class SignInComponent {

  constructor(private authSellerService:AuthSellerService) {
  }

  seller: SellerRequest = new SellerRequest();

  signIn():void {
    this.authSellerService.signIn(this.seller)
      .then(seller => console.log(seller));
  }
}
