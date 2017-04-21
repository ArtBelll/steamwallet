import {NgModule} from '@angular/core';
import {Routes, RouterModule} from "@angular/router";
import {BuyProcessComponent} from "./components/buy-process/buy-process.component";
import {SellersListComponent} from "./components/sellers-list/sellers-list.component";
import {GameInfoComponent} from "./components/game-info/game-info.component";
import {PayPageComponent} from "./components/pay-page/pay-page.component";

const buyRoutes:Routes = [
  {
    path: '',
    component: BuyProcessComponent,
    children: [
      {path: '', redirectTo: 'sellers', pathMatch: 'full'},
      {path: 'sellers', component: SellersListComponent},
      {path: 'game-info', component: GameInfoComponent},
      {path: 'pay', component: PayPageComponent}
    ]
  }
];

@NgModule({
  imports: [
    RouterModule.forChild(buyRoutes)
  ],
  exports: [
    RouterModule
  ]
})
export class BuyRoutingModule {
}
