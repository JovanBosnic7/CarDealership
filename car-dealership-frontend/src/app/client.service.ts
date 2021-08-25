import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { map } from 'rxjs/operators';
import { CarDealership } from './model/CarDealership';

@Injectable({
  providedIn: 'root',
})
export class ClientService {
  constructor(private http: HttpClient) {}

  getCarDealership = () => {
    return this.http
      .get('http://localhost:8080/api/clients/getCarDealership')
      .pipe(
        map((responseData: any) => {
          return responseData;
        })
      );
  };

  updateCarDealership = (carDealership: CarDealership) => {
    return this.http
      .post(
        'http://localhost:8080/api/clients/updateCarDealership',
        carDealership
      )
      .pipe(
        map((responseData: any) => {
          return responseData;
        })
      );
  };
}
