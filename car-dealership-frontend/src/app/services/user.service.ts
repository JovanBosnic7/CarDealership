import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Client } from '../model/Client';
import { map } from 'rxjs/operators';

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
}
