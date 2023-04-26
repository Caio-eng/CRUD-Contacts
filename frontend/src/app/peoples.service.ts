import { Injectable } from '@angular/core';
import { People } from './peoples/peoples-form/people';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class PeoplesService {

  apiURL: string = environment.apiURLBase + '/people';

  constructor(private http: HttpClient) { }

  insert(people: People) : Observable<People> {
    console.log(people);
    return this.http.post<People>(`${this.apiURL}`, people);
  }

  getPeople(): Observable<People[]> {
    return this.http.get<People[]>(this.apiURL);
  }
}
