import { Observable } from 'rxjs';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';

import { Contact } from './contact/contact';
import { QuestContact } from './contact/contact-list/questContact';

@Injectable({
  providedIn: 'root'
})
export class ContactService {

  apiURL: string = environment.apiURLBase + '/contact';

  constructor(private http: HttpClient) { }

  insert(contact: Contact) : Observable<Contact> {
    return this.http.post<Contact>(this.apiURL, contact);
  }

  lookUp(name: string) : Observable<QuestContact[]> {

    const httpParams = new HttpParams()
      .set("name", name);

      const url = this.apiURL + "?" + httpParams.toString();
      return this.http.get<any>(url);
  }
}
