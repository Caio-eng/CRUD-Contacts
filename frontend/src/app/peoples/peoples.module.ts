import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

import { PeoplesRoutingModule } from './peoples-routing.module';
import { PleopesFormComponent } from './peoples-form/peoples-form.component';
import { ListPeopleComponent } from './list-people/list-people.component';


@NgModule({
  declarations: [
    PleopesFormComponent,
    ListPeopleComponent
  ],
  imports: [
    CommonModule,
    PeoplesRoutingModule,
    FormsModule
  ], exports: [
    PleopesFormComponent,
    ListPeopleComponent
  ]
})
export class PeoplesModule { }
