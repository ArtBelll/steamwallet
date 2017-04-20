import {NgModule}             from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {SignUpComponent} from "./components/registration/sign-up.component";
import {SignInComponent} from "./components/login/sign-in.component";
import {NotAuthGuard} from "./services/not-auth-guard.service";
import {MainPageComponent} from "./components/main-page/main-page.component";
import {SellersListComponent} from "./components/sellers-list/sellers-list.component";
import {GameInfoComponent} from "./components/game-info/game-info.component";
import {PayPageComponent} from "./components/pay-page/pay-page.component";
import {SellerProfileComponent} from "./components/seller-profile/seller-profile.component";
import {BuyerProfileComponent} from "./components/buyer-profile/buyer-profile.component";
import {AuthGuard} from "./services/auth-guard.service";
import {SellerPurchasesComponent} from "./components/seller-purchases/seller-purchases.component";

const routes:Routes = [
  {path: '', redirectTo: 'home', pathMatch: 'full'},
  {path: 'home', component: MainPageComponent},
  {path: 'sign-up', component: SignUpComponent, canActivate: [NotAuthGuard]},
  {path: 'sign-in', component: SignInComponent, canActivate: [NotAuthGuard]},
  {path: 'sellers', component: SellersListComponent},
  {path: 'game-info', component: GameInfoComponent, canActivate: [AuthGuard]},
  {path: 'pay', component: PayPageComponent, canActivate: [AuthGuard]},
  {path: 'profile/seller', component: SellerProfileComponent, canActivate: [AuthGuard]},
  {path: 'profile/buyer', component: BuyerProfileComponent, canActivate: [AuthGuard]},
  {path: 'seller/purchases', component: SellerPurchasesComponent, canActivate: [AuthGuard]}
];
@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
