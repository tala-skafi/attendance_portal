import { Component } from '@angular/core';
import { VacationDetailService } from "../../shared/vacation-detail.service";
import { NgForm } from "@angular/forms";

@Component({
  selector: 'app-vacation-form',
  templateUrl: './vacation-form.component.html',
  styleUrls: ['./vacation-form.component.css']
})
export class VacationFormComponent {
  dateError: boolean = false;
  submitted: boolean = false; // Track submission state

  constructor(public service: VacationDetailService) {}

  validateDates() {
    const { vacationDateFrom, vacationDateTo } = this.service.formData;
    this.dateError = vacationDateFrom && vacationDateTo && new Date(vacationDateFrom) > new Date(vacationDateTo);
  }

  isOnlyWhitespace(value: string): boolean {
    return !value || !value.trim().length;
  }

  onSubmit(form: NgForm) {
    this.submitted = true; // Track that the form has been submitted

    // Prevent submission if there are validation errors
    if (this.dateError || form.invalid) {
      return;
    }

    this.service.postVacationDetails();
    form.resetForm();
    this.submitted = false; // Reset submission state if needed
  }
}
