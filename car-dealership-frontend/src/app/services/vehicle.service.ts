import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { map } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class VehicleService {

  constructor(private http: HttpClient) { }

  getAllMarks = () => {
    return this.http
      .get('http://localhost:8080/api/vehicles/getMarks')
      .pipe(
        map((responseData: any) => {
          return responseData;
        })
      );
  };
  getAllModelsByMark = (markId: string) => {
    return this.http
      .get('http://localhost:8080/api/vehicles/getModels/' + markId )
      .pipe(
        map((responseData: any) => {
          return responseData;
        })
      );
  };
}
