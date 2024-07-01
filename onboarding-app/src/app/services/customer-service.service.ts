import { Injectable } from '@angular/core';
import { HttpServiceService } from './http-service.service';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class CustomerServiceService {

 
  host: string = environment.submissionHost;

  constructor(private http: HttpServiceService) { }

  postCustomerForm(formData: FormData){
    return this.http.post(this.host, 'submissions', formData);
  }
}
