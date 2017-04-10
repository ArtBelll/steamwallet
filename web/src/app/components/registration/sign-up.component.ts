import {Component, OnInit} from '@angular/core';
import {UserRequest} from "../../domain/request/userRequest";
import {Location}                 from '@angular/common';
import {ActivatedRoute, Params}   from '@angular/router';
import {AuthSellerService} from "../../services/auth-seller.service"
import {AuthBuyerService} from "../../services/auth-buyer.service";
import {IUserAuth} from "../../services/user-auth";
import {Seller} from "../../domain/seller";
import {Buyer} from "../../domain/buyer";
import {CustomObservable} from "../../services/custom-observable.service";

@Component({
  selector: 'sing-up',
  templateUrl: './sign-up.component.html',
  styleUrls: ['./sign-up.component.css'],
})

export class SignUpComponent implements OnInit {

  private authService:IUserAuth<Seller | Buyer>;

  private userRole = "";

  constructor(private authSellerService:AuthSellerService,
              private authBuyerService:AuthBuyerService,
              private route:ActivatedRoute,
              private location:Location,
              private loggedService:CustomObservable) {
  }

  ngOnInit():void {
    this.route.params
      .map((params:Params) => params['user'])
      .subscribe(user => {
        if (user === "seller") {
          this.userRole = 'продавцом';
          this.authService = this.authSellerService;
        }
        else if (user === "buyer") {
          this.userRole = 'покупателем';
          this.authService = this.authBuyerService;
        }
        else this.location.back();
      });
  }

  user:UserRequest = new UserRequest();

  signUp():void {
    this.authService.register(this.user)
      .then(user => {
        console.log(user);
        this.loggedService.emitChange(true);
        this.location.back();
      });
  }
}
