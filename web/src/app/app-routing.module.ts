import {NgModule}             from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {SignUpComponent} from "./components/registration/sign-up.component";
import {SignInComponent} from "./components/login/sign-in.component";
import {NotAuthGuard} from "./services/not-auth-guard.service";
import {MainPageComponent} from "./components/main-page/main-page.component";
import {SellerProfileComponent} from "./components/seller-profile/seller-profile.component";
import {BuyerProfileComponent} from "./components/buyer-profile/buyer-profile.component";
import {AuthGuard} from "./services/auth-guard.service";
import {SellerPurchasesComponent} from "./components/seller-purchases/seller-purchases.component";
import {BuyerGuard} from "./services/buyer-guard.service";
import {SellerGuard} from "./services/seller-guard.service";

const routes:Routes = [
  {
    path: '',
    redirectTo: 'home',
    pathMatch: 'full'
  },
  {
    path: 'home',
    component: MainPageComponent
  },
  {
    path: 'sign-up',
    component: SignUpComponent,
    canActivate: [NotAuthGuard]
  },
  {
    path: 'sign-in',
    component: SignInComponent,
    canActivate: [NotAuthGuard]
  },
  {
    path: 'profile/seller',
    component: SellerProfileComponent,
    canActivate: [AuthGuard]
  },
  {
    path: 'profile/buyer',
    component: BuyerProfileComponent,
    canActivate: [AuthGuard]
  },
  {
    path: 'seller/purchases',
    component: SellerPurchasesComponent,
    canActivate: [SellerGuard]
  },
  {
    path: 'buy',
    loadChildren: 'app/modules/buy/buy.module#BuyModule',
    canActivate: [BuyerGuard]
  }
];
@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
