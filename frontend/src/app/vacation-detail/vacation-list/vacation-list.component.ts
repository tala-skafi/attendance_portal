import { Component } from '@angular/core';
import {VacationDetailService} from "../../shared/vacation-detail.service";

@Component({
  selector: 'app-vacation-list',
  templateUrl: './vacation-list.component.html',
  styleUrl: './vacation-list.component.css'
})
export class VacationListComponent {
  constructor(public service: VacationDetailService) {

  }

  onDelete(id: number) {
    if (confirm("Are you sure you want to delete this vacation?")) {
      this.service.deleteVacationDetail(id);
    }
  }



}
