import { Component, OnInit } from '@angular/core';
import { stringify } from 'querystring';
import { ContactService } from 'src/app/contact.service';
import { QuestContact } from './questContact';

@Component({
  selector: 'app-contact-list',
  templateUrl: './contact-list.component.html',
  styleUrls: ['./contact-list.component.css']
})
export class ContactListComponent implements OnInit {

  name: string;
  list: QuestContact[];
  message: string;

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

}
