import { Contact } from './../contact';

import { Component, OnInit } from '@angular/core';
import { ContactService } from 'src/app/contact.service';
import { QuestContact } from './questContact';

@Component({
  selector: 'app-contact-list',
  templateUrl: './contact-list.component.html',
  styleUrls: ['./contact-list.component.css']
})
export class ContactListComponent implements OnInit {

  selectedContact: QuestContact;
  name: string;
  list: QuestContact[];
  message: string;
  messageError: string;

  constructor(
    private service: ContactService
  ) {   }

  ngOnInit(): void {

  }

  consult() {
    this.service.lookUp(this.name)
       .subscribe(response => {
        this.list = response;
        if(this.list.length <= 0) {
          this.message = "Nenhum Registro encontrado."
        } else {
          this.message = '';
        }
       });
  }

  preparedDelete(contact: QuestContact) {
    this.selectedContact = contact;
  }

  deleteContact() {
    this.service
    .delete(this.selectedContact)
    .subscribe( resonse => {
                  this.message = 'Pessoa deletada com sucesso!'
                  this.ngOnInit();
                },
                erro => this.messageError = 'Ocorreu um erro ao deletar a Pessoa'
    );
  }

}
