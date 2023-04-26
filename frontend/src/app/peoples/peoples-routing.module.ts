import { PleopesFormComponent } from './peoples-form/peoples-form.component';
import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ListPeopleComponent } from './list-people/list-people.component';

const routes: Routes = [
  { path: 'peoples-form', component: PleopesFormComponent },
  { path: 'peoples-form/:id', component: PleopesFormComponent },
  { path: 'list-people', component: ListPeopleComponent }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class PeoplesRoutingModule { }
