import { ContactService } from './../../contact.service';
import { Contact } from './../contact';
import { Component, OnInit } from '@angular/core';
import { People } from 'src/app/peoples/peoples-form/people';
import { PeoplesService } from 'src/app/peoples.service';
import { Observable } from 'rxjs';
import { ActivatedRoute, Params, Router } from '@angular/router';

@Component({
  selector: 'app-contact-form',
  templateUrl: './contact-form.component.html',
  styleUrls: ['./contact-form.component.css'],
})
export class ContactFormComponent implements OnInit {
  peoples: People[] = [];
  contact: Contact;
  success: boolean = false;
  errors: String[];
  id: number;

  constructor(
    private peoplesService: PeoplesService,
    private service: ContactService,
    private activatedRoute: ActivatedRoute,
    private router: Router
  ) {
    this.contact = new Contact();
    let params: Observable<Params> = this.activatedRoute.params;
    params.subscribe((urlParams) => {
      this.id = urlParams['id'];
      if (this.id) {
        this.service.getContactById(this.id).subscribe(
          (response) => (this.contact = response),
          (errorResponse) => (this.contact = new Contact())
        );
      }
    });

    if (params) {
    }
  }

  ngOnInit(): void {
    this.peoplesService
      .getPeople()
      .subscribe((response) => (this.peoples = response));
  }

  onSubmit() {
    if (this.id) {
      this.service.update(this.contact).subscribe(
        (response) => {
          this.success = true;
          this.errors = [];
          this.router.navigate(['/contact-list']);
        },
        (errorResponse) => {
          this.errors = ['Erro ao atualizar o Contato.'];
        }
      );
    } else {
      this.service.insert(this.contact).subscribe(
        (response) => {
          this.success = true;
          this.errors = [];
          this.contact = new Contact();
          this.router.navigate(['/contact-list']);
        },
        (errorResponse) => {
          this.success = false;
          this.errors = errorResponse.error.errors;
        }
      );
    }
  }
}
