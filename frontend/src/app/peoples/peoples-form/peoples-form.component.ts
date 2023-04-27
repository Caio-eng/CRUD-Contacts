import { PeoplesService } from './../../peoples.service';
import { People } from './people';
import { Component, OnInit } from '@angular/core';
import { Observable } from 'rxjs';
import { Router, ActivatedRoute, Params } from '@angular/router';

@Component({
  selector: 'app-pleopes-form',
  templateUrl: './peoples-form.component.html',
  styleUrls: ['./peoples-form.component.css']
})
export class PleopesFormComponent implements OnInit {

  people: People;
  success: boolean = false;
  errors: String[];
  id: number;

  constructor(
    private service: PeoplesService,
    private router: Router,
    private activatedRoute: ActivatedRoute,
    ) {
    this.people = new People();
    let params : Observable<Params> = this.activatedRoute.params;
    params.subscribe( urlParams => {
      this.id = urlParams['id'];
      if (this.id) {
        this.service
          .getPeopleById(this.id)
          .subscribe(
            response => this.people = response,
            errorResponse => this.people = new People()
          )
      }
    })

    if (params) {

    }
   }

  ngOnInit(): void {
  }

  onSubmit() {
    if(this.id) {
      this.service
        .update(this.people)
        .subscribe(response => {
          this.success = true;
          this.errors = [];
          this.router.navigate(['/list-people']);
        }, errorResponse => {
          this.errors = ['Erro ao atualizar a Pessoa.']
        })
    } else {
      this.service
      .insert(this.people)
      .subscribe( response => {
        this.success = true;
        this.errors = [];
        this.people = response;
        this.router.navigate(['/list-people']);
      }, errorResponse => {
        this.success = false;
        this.errors = errorResponse.error.errors;
      })
    }
  }

  backList() {
    this.router.navigate(['/list-people'])
  }

}
