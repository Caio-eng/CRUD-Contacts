import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

import { PeoplesRoutingModule } from './peoples-routing.module';
import { PleopesFormComponent } from './peoples-form/peoples-form.component';


@NgModule({
  declarations: [
    PleopesFormComponent
  ],
  imports: [
    CommonModule,
    PeoplesRoutingModule,
    FormsModule
  ], exports: [
    PleopesFormComponent
  ]
})
export class PeoplesModule { }
