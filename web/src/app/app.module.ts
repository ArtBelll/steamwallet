import {NgModule}       from '@angular/core';
import {BrowserModule}  from '@angular/platform-browser';
import {FormsModule}    from '@angular/forms';
import {AppComponent}   from './components/main-component/app.component';
import {HttpModule}     from '@angular/http';
import {AppRoutingModule} from "./app-routing.module";
import {NavigationComponent} from "./components/navigation/nav.component";
import {AuthSellerService} from "./services/auth-seller-service";
import {SignUpComponent} from "./components/registration/sign-up.component";
import {SignInComponent} from "./components/login/sign-in.component";
import {NotAuthGuard} from "./services/not-auth-guard-service";
import {UserService} from "./services/user-service";
import {AuthBuyerService} from "./services/auth-buyer-service";
import {CustomObservable} from "./services/custom-observable-service";
import {MainPageComponent} from "./components/main-page/main-page.component";
import {SellersListComponent} from "./components/sellers-list/sellers-list.component";
import {SellersCardComponent} from "./components/seller-card/seller-card.component";
import {SellerService} from "./services/seller-service";

@NgModule({
  imports: [
    BrowserModule,
    FormsModule,
    HttpModule,
    AppRoutingModule
  ],
  declarations: [
    AppComponent,
    NavigationComponent,
    SignUpComponent,
    SignInComponent,
    MainPageComponent,
    SellersListComponent,
    SellersCardComponent
  ],
  providers: [
    AuthSellerService,
    AuthBuyerService,
    NotAuthGuard,
    UserService,
    CustomObservable,
    SellerService
  ],
  bootstrap: [AppComponent]
})
export class AppModule {
}
