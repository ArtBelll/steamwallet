import {Injectable} from '@angular/core';
import {Headers, Http}  from "@angular/http";

import 'rxjs/add/operator/map'
import 'rxjs/add/operator/toPromise';
import {Game} from "../domain/game-info/game";
import {Package} from "../domain/game-info/package";
import {Dlc} from "../domain/game-info/dlc";

@Injectable()
export class GameInfoService {

  private urlGameInfo = 'api/appdetails?appids=';
  private urlPackageInfo = 'api/packagedetails/?packageids=';
  private languageParam = '&l=russian';

  private headers = new Headers({'Content-Type': 'application/json'});

  constructor(private http:Http) {

  }

  getGameInfo(id:number):Promise<Game> {
    var url = `${this.urlGameInfo}${id}${this.languageParam}`;
    return this.http
      .get(url, {headers: this.headers})
      .toPromise()
      .then(response => {
        var json = response.json()[`${id}`]["data"];
        var game = new Game();
        game.name = json["name"];
        game.packages = json["packages"];
        game.dlc = json["dlc"];
        return game;
      });
  }

  getPackageInfo(id:number):Promise<Package> {
    var url = `${this.urlPackageInfo}${id}${this.languageParam}`;
    return this.http
      .get(url, {headers: this.headers})
      .toPromise()
      .then(response => {
        var json = response.json()[`${id}`]["data"];
        var packageInfo = new Package();
        packageInfo.name = json["name"];
        packageInfo.price = json["price"]["final"];
        return packageInfo;
      })
  }

  getDlcInfo(id:number):Promise<Dlc> {
    var url = `${this.urlGameInfo}${id}${this.languageParam}`;
    return this.http
      .get(url, {headers: this.headers})
      .toPromise()
      .then(response => {
        var json = response.json()[`${id}`]["data"];
        var dlc = new Dlc();
        dlc.name = json["name"];
        dlc.price = json["price_overview"]["final"];
        return dlc;
      })
  }
}
