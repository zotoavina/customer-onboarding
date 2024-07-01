import { Injectable } from '@angular/core';
import { HttpServiceService } from './http-service.service';
import { Observable, map } from 'rxjs';
import { Country } from '../shared/model/country';

@Injectable({
  providedIn: 'root'
})
export class DataReferenceServiceService {

  constructor(private http: HttpServiceService) { }

  getAllCountries(): Observable<any> {
    return this.http.get<any>('countries');
  }

  getCountryByName(name: string){
    return this.http.get<any>('countries');
  }

  getApplyingPurpose(){
    return this.http.get<any>('applying-purposes');
  }

  getActivities(){
    return this.http.get<any>('activities');
  }

  getEntityTypes(){
    return this.http.get('entity-types');
  }

}
