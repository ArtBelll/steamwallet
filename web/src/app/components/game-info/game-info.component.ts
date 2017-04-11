import {Component} from '@angular/core';
import {GameInfoService} from "../../services/game-info.service";
import {Package} from "../../domain/game-info/package";
import {Dlc} from "../../domain/game-info/dlc";

@Component({
  selector: 'game-info',
  templateUrl: './game-info.component.html',
  styleUrls: ['./game-info.component.scss'],
})

export class GameInfoComponent {

  private packages: Package[] = [];

  private dlcs: Dlc[] = [];

  constructor(private gameInfoService:GameInfoService) {
  }

  getGameInfo(gameUrl:string) {
    this.packages = [];
    this.dlcs = [];

    this.gameInfoService.getGameInfo(gameUrl)
      .then(game => {

        game.packages.forEach(packageId => {
          this.gameInfoService.getPackageInfo(packageId)
            .then(packageInfo => {
              console.log(packageInfo);
              this.packages.push(packageInfo);
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
}
