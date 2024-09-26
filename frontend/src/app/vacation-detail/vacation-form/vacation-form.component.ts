import {Component, OnInit} from '@angular/core';
import {VacationDetailService} from "../../shared/vacation-detail.service";
import {NgForm} from "@angular/forms";

@Component({
  selector: 'app-vacation-form',
  templateUrl: './vacation-form.component.html',
  styleUrls: ['./vacation-form.component.css']
})
export class VacationFormComponent implements OnInit {
  dateError: boolean = false;
  submitted: boolean = false;
  requiresReason: boolean | undefined = false;
  selectedVacationType: any;

  constructor(public service: VacationDetailService) {
  }

  ngOnInit() {
    this.service.getVacationTypes();
  }

  onVacationTypeChange() {
    this.service.formData.vacationType = this.selectedVacationType?.type; // Assign string value
    this.requiresReason = this.selectedVacationType?.requiresReason; // Update requiresReason
  }


  validateDates() {
    const vacationDateFrom = this.service.formData.vacationDateFrom;
    const vacationDateTo = this.service.formData.vacationDateTo;
    this.dateError = !!(vacationDateFrom && vacationDateTo && new Date(vacationDateFrom) > new Date(vacationDateTo));
  }

  isOnlyWhitespace(value: string): boolean {
    return !value || !value.trim().length;
  }

  onSubmit(form: NgForm) {
    this.submitted = true;

    // Prevent submission if there are validation errors
    if (this.dateError || form.invalid) {
      return;
    }

    this.service.postVacationDetails();
    form.resetForm();
    this.submitted = false;
  }
}
