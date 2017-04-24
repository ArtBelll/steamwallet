import {Injectable} from '@angular/core';
import {Headers, Http}  from "@angular/http";

import 'rxjs/add/operator/map'
import 'rxjs/add/operator/toPromise';
import {Game} from "../domain/game-info/game";
import {Product} from "../domain/game-info/product";
import {ErrorHandler} from "../../../utility/error-handler";

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
        game.image = json["header_image"];
        return game;
      })
      .catch(ErrorHandler.handleError);
  }

  getPackageInfo(id:number):Promise<Product> {
    var url = `${this.urlPackageInfo}${id}${this.languageParam}`;
    return this.http
      .get(url, {headers: this.headers})
      .toPromise()
      .then(response => {
        var json = response.json()[`${id}`]["data"];
        var packageInfo = new Product();
        packageInfo.name = json["name"];
        packageInfo.price = json["price"]["final"] / 100;
        if(json["header_image"]) packageInfo.image = json["header_image"];
        return packageInfo;
      })
      .catch(ErrorHandler.handleError);
  }

  getDlcInfo(id:number):Promise<Product> {
    var url = `${this.urlGameInfo}${id}${this.languageParam}`;
    return this.http
      .get(url, {headers: this.headers})
      .toPromise()
      .then(response => {
        var json = response.json()[`${id}`]["data"];
        var dlc = new Product();
        dlc.name = json["name"];
        dlc.price = json["price_overview"]["final"] / 100;
        dlc.image = json["header_image"];
        return dlc;
      })
      .catch(ErrorHandler.handleError);
  }
}
