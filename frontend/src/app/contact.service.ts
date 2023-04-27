import { Observable } from 'rxjs';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';

import { Contact } from './contact/contact';

@Injectable({
  providedIn: 'root'
})
export class ContactService {

  apiURL: string = environment.apiURLBase + '/contact';

  constructor(private http: HttpClient) { }

  insert(contact: Contact) : Observable<Contact> {
    return this.http.post<Contact>(this.apiURL, contact);
  }
}
