import {NgModule}             from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {SignUpComponent} from "./components/registration/sign-up.component";
import {SignInComponent} from "./components/login/sign-in.component";
import {NotAuthGuard} from "./services/not-auth-guard.service";
import {MainPageComponent} from "./components/main-page/main-page.component";
import {SellersListComponent} from "./components/sellers-list/sellers-list.component";
import {GameInfoComponent} from "./components/game-info/game-info.component";
import {PayPageComponent} from "./components/pay-page/pay-page.component";
import {BuyGuard} from "./services/buy-guard.service";

const routes:Routes = [
  {path: '', redirectTo: 'home', pathMatch: 'full'},
  {path: 'home', component: MainPageComponent},
  {path: 'sign-up/:user', component: SignUpComponent, canActivate: [NotAuthGuard]},
  {path: 'sign-in/:user', component: SignInComponent, canActivate: [NotAuthGuard]},
  {path: 'sellers', component: SellersListComponent},
  {path: 'game-info', component: GameInfoComponent},
  {path: 'pay', component: PayPageComponent, canActivate: [BuyGuard]}
];
@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
