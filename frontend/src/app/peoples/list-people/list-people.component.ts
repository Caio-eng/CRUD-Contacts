import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { PeoplesService } from 'src/app/peoples.service';

import { People } from '../peoples-form/people';

@Component({
  selector: 'app-list-people',
  templateUrl: './list-people.component.html',
  styleUrls: ['./list-people.component.css']
})
export class ListPeopleComponent implements OnInit {

  peoples: People[] = [];

  constructor(
    private service: PeoplesService,
    private router: Router
  ) {
    this.service.getPeople()
    .subscribe( response => this.peoples = response )
  }

  ngOnInit(): void {
  }

  newPeople() {
    this.router.navigate(['/peoples-form']);
  }


}
