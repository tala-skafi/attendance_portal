import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';

import { VacationDetailComponent } from './vacation-detail/vacation-detail.component';
import { VacationFormComponent } from './vacation-detail/vacation-form/vacation-form.component';
import { VacationListComponent } from './vacation-detail/vacation-list/vacation-list.component';

import {FormsModule} from "@angular/forms";
import {HttpClientModule} from "@angular/common/http";

@NgModule({
  declarations: [
    AppComponent,
    VacationDetailComponent,
    VacationFormComponent,
    VacationListComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
