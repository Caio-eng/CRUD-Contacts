import { ContactService } from './../../contact.service';
import { Contact } from './../contact';
import { Component, OnInit } from '@angular/core';
import { People } from 'src/app/peoples/peoples-form/people';
import { PeoplesService } from 'src/app/peoples.service';

@Component({
  selector: 'app-contact-form',
  templateUrl: './contact-form.component.html',
  styleUrls: ['./contact-form.component.css']
})
export class ContactFormComponent implements OnInit {

  peoples: People[] = [];
  contact: Contact;
  success: boolean = false;
  errors: String[];

  constructor(
    private peoplesService: PeoplesService,
    private service: ContactService
  ) {
    this.contact = new Contact();
  }

  ngOnInit(): void {
    this.peoplesService
    .getPeople()
    .subscribe( response => this.peoples = response )
  }

  onSubmit() {
    this.service
        .insert(this.contact)
        .subscribe( response => {
          this.success = true;
          this.errors = [];
          this.contact = new Contact();
         } , errorResponse => {
           this.success = false;
           this.errors = errorResponse.error.errors;
         }

         );
  }

}
