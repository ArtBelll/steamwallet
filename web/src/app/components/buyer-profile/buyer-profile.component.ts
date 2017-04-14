import {Component, OnInit} from '@angular/core';
import {UserService} from "../../services/user.service";
import {Buyer} from "../../domain/buyer";
import {BuyerService} from "../../services/buyer.service";

@Component({
  selector: 'buyer-profile',
  templateUrl: './buyer-profile.component.html',
  styleUrls: ['./buyer-profile.component.scss'],
})

export class BuyerProfileComponent implements OnInit {

  private buyer:Buyer;

  constructor(private buyerService:BuyerService,
              private userService:UserService) {
  }

  ngOnInit():void {
    this.userService.getCurrentUser()
      .then(buyer => {
        console.log(buyer);
        this.buyer = buyer;
      });
  }

}
