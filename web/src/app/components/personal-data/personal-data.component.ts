import {Component, Input} from '@angular/core';


@Component({
  selector: 'personal-data',
  templateUrl: './personal-data.component.html',
  styleUrls: ['./personal-data.component.scss'],
})

export class PersonalDataComponent {

  @Input('user') user = {};

}
