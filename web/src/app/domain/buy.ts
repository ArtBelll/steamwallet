import {Seller} from "./seller";
import {Product} from "../modules/buy/domain/game-info/product";

export class Buy {
  seller:Seller;
  gameUrl:string;
  product:Product;
}
