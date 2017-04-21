import {NgModule} from '@angular/core';
import {CommonModule} from "@angular/common";
import {BuyProcessComponent} from "./components/buy-process/buy-process.component";
import {GameInfoComponent} from "./components/game-info/game-info.component";
import {PayPageComponent} from "./components/pay-page/pay-page.component";
import {SellersCardComponent} from "./components/seller-card/seller-card.component";
import {SellersListComponent} from "./components/sellers-list/sellers-list.component";
import {BuyRoutingModule} from "./buy-routing.module";
import {BuyService} from "./services/buy.service";
import {BuyGuard} from "./services/buy-guard.service";
import {GameInfoService} from "./services/game-info.service";

@NgModule({
  imports: [
    BuyRoutingModule,
    CommonModule
  ],
  declarations: [
    BuyProcessComponent,
    GameInfoComponent,
    PayPageComponent,
    SellersCardComponent,
    SellersListComponent
  ],
  providers: [
    BuyService,
    BuyGuard,
    GameInfoService
  ],
})

export class BuyModule {
}
