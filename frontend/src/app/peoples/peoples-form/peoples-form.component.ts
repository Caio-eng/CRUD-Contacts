import { PeoplesService } from './../../peoples.service';
import { People } from './people';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-pleopes-form',
  templateUrl: './peoples-form.component.html',
  styleUrls: ['./peoples-form.component.css']
})
export class PleopesFormComponent implements OnInit {

  people: People;
  success: boolean = false;
  errors: String[];

  constructor(
    private service: PeoplesService,
    private router: Router
    ) {
    this.people = new People();
   }

  ngOnInit(): void {
  }

  onSubmit() {
    this.service
    .insert(this.people)
    .subscribe( response => {
      this.success = true;
      this.errors = [];
      this.people = response;
    }, errorResponse => {
      this.success = false;
      this.errors = errorResponse.error.errors;
    })
  }

  backList() {
    this.router.navigate(['/list-people'])
  }

}
