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

  getPeople(): People {
    let people: People = new People();
    people.name = "Fuluno";
    people.cpf = "65485269874";
    people.dateBirth = "20/04/2020"
    return people;
  }
}
