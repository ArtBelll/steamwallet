export class PurchaseRequest {
  sellerId:number;
  sum:number;
  url:string;
  game:string;

  constructor(sellerId:number, sum:number, url:string, game:string) {
    this.sellerId = sellerId;
    this.sum = sum;
    this.url = url;
    this.game = game;
  }
}
