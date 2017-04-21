import {Component, OnInit} from '@angular/core';
import {Location} from '@angular/common';
import {Router} from "@angular/router";
import {Game} from "../../domain/game-info/game";
import {Product} from "../../domain/game-info/product";
import {BuyService} from "../../services/buy.service";
import {GameInfoService} from "../../services/game-info.service";

@Component({
  selector: 'game-info',
  templateUrl: './game-info.component.html',
  styleUrls: ['./game-info.component.scss'],
})

export class GameInfoComponent implements OnInit{

  private game: Game;

  private packages:Product[] = [];

  private dlcs:Product[] = [];

  constructor(private gameInfoService:GameInfoService,
              private buyService:BuyService,
              private router: Router,
              private location:Location) {
  }

  ngOnInit():void {
    if (this.buyService.currentBuy.gameUrl) {
      this.getGameInfo(this.buyService.currentBuy.gameUrl);
    }
  }

  getGameInfo(gameUrl:string) {
    this.buyService.currentBuy.gameUrl = gameUrl;

    this.game = undefined;
    this.packages = [];
    this.dlcs = [];

    this.gameInfoService.getGameInfo(gameUrl)
      .then(game => {
        this.game = game;

        game.packages.forEach(packageId => {
          this.gameInfoService.getPackageInfo(packageId)
            .then(packageInfo => {
              if (!packageInfo.image) {
                packageInfo.image = game.image;
              }
              this.packages.push(packageInfo)
            });
        });

        if (game.dlc) {
          game.dlc.forEach(dlcId => {
            this.gameInfoService.getDlcInfo(dlcId)
              .then(dlc => this.dlcs.push(dlc));
          });
        }
      });
  }

  toPayPage(selectProduct:Product) {
    this.buyService.currentBuy.product = selectProduct;
    this.router.navigate(['buy/pay']);
  }

  goBack() {
    this.location.back();
  }
}
