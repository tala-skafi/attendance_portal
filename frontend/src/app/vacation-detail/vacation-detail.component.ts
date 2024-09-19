import { Component } from '@angular/core';
import {VacationDetailService} from "../shared/vacation-detail.service";

@Component({
  selector: 'app-vacation-detail',
  templateUrl: './vacation-detail.component.html',
  styleUrls: ['./vacation-detail.component.css']  // Corrected this line
})
export class VacationDetailComponent {
  constructor(public service: VacationDetailService) {
    this.service.refreshList()
  }

}

