import {Component} from '@angular/core';
import {VacationDetailService} from "../../shared/vacation-detail.service";
import {NgForm} from "@angular/forms";

@Component({
  selector: 'app-vacation-form',
  templateUrl: './vacation-form.component.html',
  styleUrls: ['./vacation-form.component.css']  // Corrected this line
})
export class VacationFormComponent {
  dateError: boolean = false;

  constructor(public service: VacationDetailService) {}

  validateDates() {
    const { vacationDateFrom, vacationDateTo } = this.service.formData;
    this.dateError = vacationDateFrom && vacationDateTo && new Date(vacationDateFrom) > new Date(vacationDateTo);
  }
  isOnlyWhitespace(value: string): boolean {
    return !value.trim().length;
  }

  onSubmit(form: NgForm) {
    if (this.dateError) {
      return; // Prevent form submission if date validation fails
    }
    this.service.postVacationDetails();
    form.resetForm();
    this.service.resetForm();
  }
}
