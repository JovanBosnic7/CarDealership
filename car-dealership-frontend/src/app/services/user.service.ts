import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Client } from '../model/Client';
import { map } from 'rxjs/operators';
import { TestDrive } from '../model/TestDrive';

@Injectable({
  providedIn: 'root',
})
export class UserService {
  constructor(private http: HttpClient) {}

  registerUser = (client: Client) => {
    return this.http
      .post('http://localhost:8080/api/clients/create', client)
      .pipe(
        map((responseData: any) => {
          return responseData;
        })
      );
  };
  getAllTestDrives = (clientId: string) => {
    return this.http
      .get('http://localhost:8080/api/clients/getAllTestDrives/' + clientId)
      .pipe(
        map((responseData: any) => {
          return responseData;
        })
      );
  };
  createTestDrive = (dto: TestDrive) => {
    return this.http
      .post('http://localhost:8080/api/clients/createTestDrive', dto)
      .pipe(
        map((responseData: any) => {
          return responseData;
        })
      );
  };
}
