import { Injectable } from '@angular/core';
import { HttpServiceService } from './http-service.service';
import { Observable, map } from 'rxjs';
import { Country } from '../shared/model/country';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class DataReferenceServiceService {

  host: string = environment.dataRefHost;

  constructor(private http: HttpServiceService) { }

  getAllCountries(): Observable<any> {
    return this.http.get<any>(this.host,'countries');
  }

  getCountryByName(name: string){
    return this.http.get<any>(this.host,'countries');
  }

  getApplyingPurpose(){
    return this.http.get<any>(this.host,'applying-purposes');
  }

  getActivities(){
    return this.http.get<any>(this.host,'activities');
  }

  getEntityTypes(){
    return this.http.get(this.host,'entity-types');
  }



}
