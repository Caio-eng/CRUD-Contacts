import { PagePeople } from './peoples/pagePeople';
import { Injectable } from '@angular/core';
import { People } from './peoples/peoples-form/people';
import { HttpClient, HttpParams } from '@angular/common/http';
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

  update( people: People ) : Observable<any> {
    return this.http.put<People>(`${this.apiURL}/${people.id}`, people);
  }

  delete(people: People) : Observable<any> {
    return this.http.delete<any>(`${this.apiURL}/${people.id}`);
  }

  getPeople(): Observable<People[]> {
    return this.http.get<People[]>(this.apiURL);
  }

  getPeopleById(id: number): Observable<People> {
    return this.http.get<any>(`${this.apiURL}/${id}`);
  }

  list(page: number, size: number) : Observable<PagePeople>{
    const params = new HttpParams()
    .set('page', page)
    .set('size', size)
    return this.http.get<any>(`${this.apiURL}?${params.toString()}`)
  }
}
