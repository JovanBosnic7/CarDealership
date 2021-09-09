import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { ClientService } from '../client.service';
import { CarDealership, WorkTime } from '../model/CarDealership';

@Component({
  selector: 'app-change-car-dealership',
  templateUrl: './change-car-dealership.component.html',
  styleUrls: ['./change-car-dealership.component.css'],
})
export class ChangeCarDealershipComponent implements OnInit {
  public carDealership: CarDealership = new CarDealership();
  public name: string = '';
  public address: string = '';
  public email: string = '';
  public startTime: string = '';
  public endTime: string = '';
  public phoneNumber: string = '';
  constructor(public clientService: ClientService, public router: Router) {}

  ngOnInit(): void {
    this.initData();
  }

  initData = () => {
    this.clientService.getCarDealership().subscribe((res) => {
      this.carDealership = res as CarDealership;
    });
  };
  onChange = (event: any) => {
    this.name = event.target.value;
  };
  onChangeA = (event: any) => {
    this.address = event.target.value;
  };
  onChangeE = (event: any) => {
    this.email = event.target.value;
  };
  onChangeP = (event: any) => {
    this.phoneNumber = event.target.value;
  };
  onChangeT = (event: any) => {
    this.startTime = event.target.value;
  };
  onChangeEt = (event: any) => {
    this.endTime = event.target.value;
  };

  onClick = () => {
    this.carDealership.name =
      this.name === '' ? this.carDealership.name : this.name;
    this.carDealership.address =
      this.address === '' ? this.carDealership.address : this.address;
    this.carDealership.email =
      this.email === '' ? this.carDealership.email : this.email;
    this.carDealership.phoneNumber =
      this.phoneNumber === ''
        ? this.carDealership.phoneNumber
        : this.phoneNumber;

    let workTime = new WorkTime();
    workTime.startTime =
      this.startTime === ''
        ? this.carDealership.workTime.startTime
        : this.startTime;

    workTime.endTime =
      this.endTime === '' ? this.carDealership.workTime.endTime : this.endTime;
    this.carDealership.workTime = workTime;

    this.clientService
      .updateCarDealership(this.carDealership)
      .subscribe((res) => {});
    alert('Podaci uspešno izmenjeni!');
    this.router.navigate(['/cars']);
  };
}
