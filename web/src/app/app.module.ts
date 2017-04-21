import {NgModule}       from '@angular/core';
import {BrowserModule}  from '@angular/platform-browser';
import {FormsModule}    from '@angular/forms';
import {AppComponent}   from './components/main-component/app.component';
import {HttpModule}     from '@angular/http';
import {AppRoutingModule} from "./app-routing.module";
import {NavigationComponent} from "./components/navigation/nav.component";
import {SignUpComponent} from "./components/registration/sign-up.component";
import {SignInComponent} from "./components/login/sign-in.component";
import {NotAuthGuard} from "./services/not-auth-guard.service";
import {UserService} from "./services/user.service";
import {CustomObservable} from "./services/custom-observable.service";
import {MainPageComponent} from "./components/main-page/main-page.component";
import {SellerService} from "./services/seller.service";
import {BuyService} from "./modules/buy/services/buy.service";
import {AuthService} from "./services/auth.service";
import {SellerProfileComponent} from "./components/seller-profile/seller-profile.component";
import {BuyerProfileComponent} from "./components/buyer-profile/buyer-profile.component";
import {BuyerService} from "./services/buyer.service";
import {PersonalDataComponent} from "./components/personal-data/personal-data.component";
import {AuthGuard} from "./services/auth-guard.service";
import {PurchaseService} from "./services/purchase.service";
import {SellerPurchasesComponent} from "./components/seller-purchases/seller-purchases.component";
import {StatusService} from "./services/status.service";
import {PurchaseComponent} from "./components/purchase/purchase.component";
import {SellerGuard} from "./services/seller-guard.service";
import {BuyModule} from "./modules/buy/buy.module";
import {BuyerGuard} from "./services/buyer-guard.service";

@NgModule({
  imports: [
    BrowserModule,
    FormsModule,
    HttpModule,
    AppRoutingModule,
    BuyModule
  ],
  declarations: [
    AppComponent,
    NavigationComponent,
    SignUpComponent,
    SignInComponent,
    MainPageComponent,
    SellerProfileComponent,
    BuyerProfileComponent,
    PersonalDataComponent,
    SellerPurchasesComponent,
    PurchaseComponent,
  ],
  providers: [
    NotAuthGuard,
    UserService,
    CustomObservable,
    SellerService,
    BuyerService,
    BuyService,
    AuthService,
    AuthGuard,
    PurchaseService,
    StatusService,
    SellerGuard,
    BuyerGuard
  ],
  bootstrap: [AppComponent]
})
export class AppModule {
}
