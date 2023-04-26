import { PeoplesService } from './../../peoples.service';
import { People } from './people';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-pleopes-form',
  templateUrl: './peoples-form.component.html',
  styleUrls: ['./peoples-form.component.css']
})
export class PleopesFormComponent implements OnInit {

  people: People;

  constructor(private service: PeoplesService) {
    this.people = new People();
   }

  ngOnInit(): void {
  }

  onSubmit() {
    this.service
    .insert(this.people)
    .subscribe( response => {
      console.log(response);
    })
  }

}
