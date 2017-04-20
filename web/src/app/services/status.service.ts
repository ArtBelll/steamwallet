import {Injectable} from '@angular/core';

@Injectable()
export class StatusService {

  private statuses = {
    1: "Оплачено",
    2: "Товар отправлен",
    3: "Успех",
    4: "Ошибка",
  };

  constructor() {
  }

  getStatus(id:number):string {
    return this.statuses[id];
  }
}
