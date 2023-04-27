import { RouterModule } from '@angular/router';
import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

import { ContactRoutingModule } from './contact-routing.module';
import { ContactFormComponent } from './contact-form/contact-form.component';
import { ContactListComponent } from './contact-list/contact-list.component';


@NgModule({
  declarations: [
    ContactFormComponent,
    ContactListComponent
  ],
  imports: [
    CommonModule,
    ContactRoutingModule,
    FormsModule,
    RouterModule
  ], exports: [
    ContactFormComponent,
    ContactListComponent
  ]
})
export class ContactModule { }
