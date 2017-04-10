import {Component, OnInit} from '@angular/core';
import {UserRequest} from "../../domain/request/userRequest";
import {Location}                 from '@angular/common';
import {AuthSellerService} from "../../services/auth-seller.service"
import {AuthBuyerService} from "../../services/auth-buyer.service";
import {Buyer} from "../../domain/buyer";
import {Seller} from "../../domain/seller";
import {IUserAuth} from "../../services/user-auth";
import {Params, ActivatedRoute} from "@angular/router";
import {CustomObservable} from "../../services/custom-observable.service";

@Component({
  selector: 'sing-in',
  templateUrl: './sign-in.component.html',
  styleUrls: ['./sign-in.component.css'],
})

export class SignInComponent implements OnInit{

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

  signIn():void {
    this.authService.signIn(this.user)
      .then(user => {
        this.loggedService.emitChange(true);
        console.log(user);
        this.location.back();
      });
  }
}
