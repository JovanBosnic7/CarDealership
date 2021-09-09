import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { AuthService } from '../services/auth.service';
import { VehicleService } from '../services/vehicle.service';
import { UserService } from '../services/user.service';

@Component({
  selector: 'app-change-car',
  templateUrl: './change-car.component.html',
  styleUrls: ['./change-car.component.css'],
})
export class ChangeCarComponent implements OnInit {
  public vehicleId: string = '';
  public vehicle: any = '';
  constructor(
    public route: ActivatedRoute,
    public vehicleService: VehicleService,
    public authService: AuthService,
    public router: Router,
    public userService: UserService
  ) {}

  ngOnInit(): void {
    this.vehicleId = this.route.snapshot.params[`vehicleId`];
    this.initData();
  }

  initData = () => {
    this.vehicleService.getVehicleById(this.vehicleId).subscribe((res) => {
      this.vehicle = res as any;
    });
  };

  setCarBody = (event: any) => {
    this.vehicle.carBody = event.target.value;
  };
  setFuel = (event: any) => {
    this.vehicle.engine.engineSpecification.fuelType = event.target.value;
  };
  setEmissionClass = (event: any) => {
    this.vehicle.engine.engineSpecification.emissionClass = event.target.value;
  };
  setDrive = (event: any) => {
    this.vehicle.engine.engineSpecification.driveType = event.target.value;
  };
  setGearBox = (event: any) => {
    this.vehicle.gearBoxType = event.target.value;
  };
  setAirConditioning = (event: any) => {
    this.vehicle.airConditioning = event.target.value;
  };
  setColor = (event: any) => {
    this.vehicle.color = event.target.value;
  };
  setInteriorMaterial = (event: any) => {
    this.vehicle.interiorMaterial = event.target.value;
  };
}
