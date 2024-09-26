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
  vacationTypes: { type: string; requiresReason: boolean }[] = [];

  constructor(private http: HttpClient) {
  }

  getVacationTypes() {
    this.http.get<{ type: string; requiresReason: boolean }[]>(this.url + '/vacation-types')
      .subscribe(types => {
        types.forEach(t => {
          t.type = t.type.replace('_', ' ');
        });
        this.vacationTypes = types;
      });
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







