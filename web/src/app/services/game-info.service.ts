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

  private parserUrlToId(url:string) {
    var findSubString = "app/";
    var startIndex = url.search(findSubString) + findSubString.length;
    url = url.substr(startIndex);
    var finishIndex = url.search('/');
    return url.substr(0, finishIndex);
  }

  getGameInfo(gameUrl:string):Promise<Game> {
    var id = this.parserUrlToId(gameUrl);
    console.log(id);
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
        packageInfo.price = json["price"]["final"] / 100;
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
        dlc.price = json["price_overview"]["final"] / 100;
        return dlc;
      })
  }
}
