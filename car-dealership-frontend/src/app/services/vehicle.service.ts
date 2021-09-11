import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { map } from 'rxjs/operators';
import { CreateAction } from '../model/CreateAction';
import { CreateVehicle } from '../model/CreateVehicle';

@Injectable({
  providedIn: 'root',
})
export class VehicleService {
  constructor(private http: HttpClient) {}

  getAllVehicles = () => {
    return this.http.get('http://localhost:8080/api/vehicles/getAll').pipe(
      map((responseData: any) => {
        for (let res of responseData) {
          for (let img of res.images) {
            img.picByte = 'data:' + img.type + ';base64,' + img.picByte;
          }
        }
        return responseData;
      })
    );
  };

  getVehicleById = (vehicleId: string) => {
    return this.http
      .get('http://localhost:8080/api/vehicles/getById/' + vehicleId)
      .pipe(
        map((responseData: any) => {
          for (let img of responseData.images) {
            img.picByte = 'data:' + img.type + ';base64,' + img.picByte;
          }

          return responseData;
        })
      );
  };

  getVehicleFeedbacks = (vehicleId: string) => {
    return this.http
      .get('http://localhost:8080/api/clients/getFeedbacks/' + vehicleId)
      .pipe(
        map((responseData: any) => {
          for (let i = 0; i < responseData.length; i++) {
            responseData[i].dateOfPosting = new Date(
              responseData[i].dateOfPosting
            ).toLocaleDateString();
          }
          return responseData;
        })
      );
  };

  getAllMarks = () => {
    return this.http.get('http://localhost:8080/api/vehicles/getMarks').pipe(
      map((responseData: any) => {
        return responseData;
      })
    );
  };
  getAllModelsByMark = (markId: string) => {
    return this.http
      .get('http://localhost:8080/api/vehicles/getModels/' + markId)
      .pipe(
        map((responseData: any) => {
          return responseData;
        })
      );
  };
  createVehicle = (createVehicle: CreateVehicle) => {
    return this.http
      .post('http://localhost:8080/api/vehicles/create', createVehicle)
      .pipe(
        map((responseData: any) => {
          return responseData;
        })
      );
  };

  updateVehicle = (createVehicle: CreateVehicle) => {
    return this.http
      .post('http://localhost:8080/api/vehicles/update', createVehicle)
      .pipe(
        map((responseData: any) => {
          return responseData;
        })
      );
  };
  uploadImages = (images: FormData) => {
    return this.http
      .post('http://localhost:8080/api/vehicles/uploadImages', images)
      .pipe(
        map((item) => {
          return item;
        })
      );
  };
  sellVehicle = (vehicleId: string) => {
    return this.http
      .post('http://localhost:8080/api/vehicles/sellVehicle', vehicleId)
      .pipe(
        map((responseData: any) => {
          return responseData;
        })
      );
  };
  createAction = (createAction: CreateAction) => {
    return this.http
      .post('http://localhost:8080/api/vehicles/createAction', createAction)
      .pipe(
        map((responseData: any) => {
          return responseData;
        })
      );
  };
}
