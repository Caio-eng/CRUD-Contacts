import { Observable } from 'rxjs';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment.prod';

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

  update( contact: Contact ) : Observable<any> {
    return this.http.put<Contact>(`${this.apiURL}/${contact.id}`, contact);
  }

  delete(contact: QuestContact) : Observable<any> {
    return this.http.delete<any>(`${this.apiURL}/${contact.id}`);
  }

  lookUp(name: string) : Observable<QuestContact[]> {

    const httpParams = new HttpParams()
      .set("name", name);

      const url = this.apiURL + "?" + httpParams.toString();
      return this.http.get<any>(url);
  }

  getContact(): Observable<Contact[]> {
    return this.http.get<Contact[]>(this.apiURL);
  }

  getContactById(id: number): Observable<Contact> {
    return this.http.get<any>(`${this.apiURL}/${id}`);
  }
}
