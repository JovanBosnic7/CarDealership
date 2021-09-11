import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Client } from '../model/Client';
import { map } from 'rxjs/operators';
import { TestDrive } from '../model/TestDrive';
import { CreateReservation } from '../model/CreateReservation';
import { CreateFeedback } from '../model/CreateFeedback';
import { Promotion } from '../model/Promotion';

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

  createPromotion = (promotion: Promotion) => {
    return this.http
      .post('http://localhost:8080/api/clients/createPromotion', promotion)
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
          for (let i = 0; i < responseData.length; i++) {
            responseData[i].date = responseData[i].date.slice(0, 10);
          }
          return responseData;
        })
      );
  };

  getTestDrives = () => {
    return this.http
      .get('http://localhost:8080/api/clients/getAllTestDrives')
      .pipe(
        map((responseData: any) => {
          for (let i = 0; i < responseData.length; i++) {
            responseData[i].date = responseData[i].date.slice(0, 10);
          }
          return responseData;
        })
      );
  };

  getReservations = (clientId: string) => {
    return this.http
      .get('http://localhost:8080/api/clients/getReservations/' + clientId)
      .pipe(
        map((responseData: any) => {
          for (let i = 0; i < responseData.length; i++) {
            responseData[i].date = responseData[i].date.slice(0, 10);
          }
          return responseData;
        })
      );
  };

  getAllReservations = () => {
    return this.http
      .get('http://localhost:8080/api/clients/getAllReservations')
      .pipe(
        map((responseData: any) => {
          for (let i = 0; i < responseData.length; i++) {
            responseData[i].date = responseData[i].date.slice(0, 10);
          }
          return responseData;
        })
      );
  };

  getPrices = () => {
    return this.http.get('http://localhost:8080/api/clients/getAllPrices').pipe(
      map((responseData: any) => {
        for (let i = 0; i < responseData.length; i++) {
          responseData[i].dateOfSetting = new Date(
            responseData[i].dateOfSetting
          ).toLocaleDateString();
        }
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

  createReservation = (dto: CreateReservation) => {
    return this.http
      .post('http://localhost:8080/api/clients/createReservation', dto)
      .pipe(
        map((responseData: any) => {
          return responseData;
        })
      );
  };

  createFeedback = (dto: CreateFeedback) => {
    return this.http
      .post('http://localhost:8080/api/clients/createFeedback', dto)
      .pipe(
        map((responseData: any) => {
          return responseData;
        })
      );
  };
  cancelTestDrive = (id: string) => {
    return this.http
      .post('http://localhost:8080/api/clients/cancelTestDrive', id)
      .pipe(
        map((responseData: any) => {
          return responseData;
        })
      );
  };

  closeTestDrive = (id: string) => {
    return this.http
      .post('http://localhost:8080/api/clients/closeTestDrive', id)
      .pipe(
        map((responseData: any) => {
          return responseData;
        })
      );
  };

  cancelReservation = (id: string) => {
    return this.http
      .post('http://localhost:8080/api/clients/cancelReservation', id)
      .pipe(
        map((responseData: any) => {
          return responseData;
        })
      );
  };

  closeReservation = (id: string) => {
    return this.http
      .post('http://localhost:8080/api/clients/closeReservation', id)
      .pipe(
        map((responseData: any) => {
          return responseData;
        })
      );
  };

  acceptOffer = (id: string) => {
    return this.http
      .post('http://localhost:8080/api/clients/acceptOffer', id)
      .pipe(
        map((responseData: any) => {
          return responseData;
        })
      );
  };

  declineOffer = (id: string) => {
    return this.http
      .post('http://localhost:8080/api/clients/declineOffer', id)
      .pipe(
        map((responseData: any) => {
          return responseData;
        })
      );
  };
}
