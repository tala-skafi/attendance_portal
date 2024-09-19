import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {environment} from "../../environments/environment";
import {VacationDetail} from "./vacation-detail.model";

@Injectable({
  providedIn: 'root'
})
export class VacationDetailService {

  url: string = environment.apiUrl + '/vacations';
  list: VacationDetail[] = []
  formData: VacationDetail = new VacationDetail();
  vacationTypes: string[] = ["Annual Vacation", "Death Vacation", "Sick Vacation", "Unpaid Vacation"];

  // Variable to hold the selected option
  constructor(private http: HttpClient) {
    this.formData.vacationType = this.vacationTypes[0]; // Default to "Annual Vacation"
  }

  refreshList() {
    this.http.get(this.url).subscribe({
      next: results => {
        this.list = results as VacationDetail[]
      },
      error: error => {
        console.log(error)
      }
    })
  }

  postVacationDetails() {
    this.http.post(this.url, this.formData).subscribe({
      next: results => {
        this.list = results as VacationDetail[]
        this.resetForm()
      },
      error: error => {
        console.log(error)
      }
    })
  }

  resetForm() {
    this.formData = new VacationDetail();
  }
  deleteVacationDetail(id: number) {
    return this.http.delete(this.url + '/' + id).subscribe({
      next: () => {
        this.refreshList(); // Refresh the list after deletion
      },
      error: error => {
        console.log('Error deleting vacation:', error);
      }
    });
  }

}







