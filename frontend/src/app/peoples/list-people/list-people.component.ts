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
  selectedPeople: People;
  messageSuccess: string;
  messageError: string;

  constructor(
    private service: PeoplesService,
    private router: Router
  ) {
    this.service.getPeople()
    .subscribe( response => this.peoples = response )
  }

  ngOnInit(): void {
    this.service.getPeople()
    .subscribe( response => this.peoples = response );
  }

  newPeople() {
    this.router.navigate(['/peoples-form']);
  }

  preparedDelete(people: People) {
    this.selectedPeople = people;
  }

  deletePeople() {
    this.service
    .delete(this.selectedPeople)
    .subscribe( resonse => {
                  this.messageSuccess = 'Pessoa deletada com sucesso!'
                  this.ngOnInit();
                },
                erro => this.messageError = 'Ocorreu um erro ao deletar a Pessoa'
    );
  }

}
