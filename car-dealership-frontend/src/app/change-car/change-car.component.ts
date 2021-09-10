import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { AuthService } from '../services/auth.service';
import { VehicleService } from '../services/vehicle.service';
import { UserService } from '../services/user.service';
import { CreateVehicle } from '../model/CreateVehicle';

@Component({
  selector: 'app-change-car',
  templateUrl: './change-car.component.html',
  styleUrls: ['./change-car.component.css'],
})
export class ChangeCarComponent implements OnInit {
  public vehicleId: string = '';
  public vehicle: any = '';
  public airbag: boolean = false;
  public codedKey: boolean = false;
  public abs: boolean = false;
  public esp: boolean = false;
  public alarm: boolean = false;
  public metalic: boolean = false;
  public tempomat: boolean = false;
  public navigation: boolean = false;
  public alloyWheels: boolean = false;
  public servo: boolean = false;
  public board: boolean = false;
  public electricWindows: boolean = false;
  public roofWindow: boolean = false;
  public camera: boolean = false;
  public parkingSensors: boolean = false;
  public leatherSteeringVheel: boolean = false;
  public startStop: boolean = false;
  public reserveWheel: boolean = false;
  public usb: boolean = false;
  public addVehicle: CreateVehicle = new CreateVehicle();
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
      this.airbag = this.vehicle.accessories.some(
        (ac: any) => ac.name === 'Airbag'
      );

      this.codedKey = this.vehicle.accessories.some(
        (ac: any) => ac.name === 'Kodiran ključ'
      );
      this.abs = this.vehicle.accessories.some((ac: any) => ac.name === 'ABS');
      this.esp = this.vehicle.accessories.some((ac: any) => ac.name === 'ESP');
      this.alarm = this.vehicle.accessories.some(
        (ac: any) => ac.name === 'Alarm'
      );
      this.metalic = this.vehicle.accessories.some(
        (ac: any) => ac.name === 'Metalik'
      );
      this.tempomat = this.vehicle.accessories.some(
        (ac: any) => ac.name === 'Tempomat'
      );
      this.navigation = this.vehicle.accessories.some(
        (ac: any) => ac.name === 'Navigacija'
      );
      this.alloyWheels = this.vehicle.accessories.some(
        (ac: any) => ac.name === 'Alu felne'
      );
      this.servo = this.vehicle.accessories.some(
        (ac: any) => ac.name === 'Servo volan'
      );
      this.board = this.vehicle.accessories.some(
        (ac: any) => ac.name === 'Putni računar'
      );
      this.electricWindows = this.vehicle.accessories.some(
        (ac: any) => ac.name === 'Električni podizači'
      );
      this.roofWindow = this.vehicle.accessories.some(
        (ac: any) => ac.name === 'Šiber'
      );
      this.camera = this.vehicle.accessories.some(
        (ac: any) => ac.name === 'Kamera'
      );
      this.leatherSteeringVheel = this.vehicle.accessories.some(
        (ac: any) => ac.name === 'Kožni volan'
      );
      this.startStop = this.vehicle.accessories.some(
        (ac: any) => ac.name === 'Start-stop'
      );
      this.reserveWheel = this.vehicle.accessories.some(
        (ac: any) => ac.name === 'Rezervni točak'
      );
      this.usb = this.vehicle.accessories.some((ac: any) => ac.name === 'USB');
      this.parkingSensors = this.vehicle.accessories.some(
        (ac: any) => ac.name === 'Parking senzori'
      );
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
  createListOfAccessories = () => {
    this.airbag && this.addVehicle.accessories.push('Airbag');
    this.codedKey && this.addVehicle.accessories.push('Kodiran ključ');
    this.abs && this.addVehicle.accessories.push('ABS');
    this.esp && this.addVehicle.accessories.push('ESP');
    this.alarm && this.addVehicle.accessories.push('Alarm');
    this.metalic && this.addVehicle.accessories.push('Metalik');
    this.tempomat && this.addVehicle.accessories.push('Tempomat');
    this.navigation && this.addVehicle.accessories.push('Navigacija');
    this.alloyWheels && this.addVehicle.accessories.push('Alu felne');
    this.servo && this.addVehicle.accessories.push('Servo volan');
    this.board && this.addVehicle.accessories.push('Putni računar');
    this.electricWindows &&
      this.addVehicle.accessories.push('Električni podizači');
    this.roofWindow && this.addVehicle.accessories.push('Šiber');
    this.camera && this.addVehicle.accessories.push('Kamera');
    this.leatherSteeringVheel &&
      this.addVehicle.accessories.push('Kožni volan');
    this.startStop && this.addVehicle.accessories.push('Start-stop');
    this.reserveWheel && this.addVehicle.accessories.push('Rezervni točak');
    this.usb && this.addVehicle.accessories.push('USB');
    this.parkingSensors && this.addVehicle.accessories.push('Parking senzori');
  };
  buildAddVehicle = () => {
    this.addVehicle.mark = this.vehicle.model.mark.name;
    this.addVehicle.vehicleId = this.vehicleId;
    this.addVehicle.year = this.vehicle.productionYear;
    this.addVehicle.model = this.vehicle.model.name;
    this.addVehicle.carBody = this.vehicle.carBody;
    this.addVehicle.fuel = this.vehicle.engine.engineSpecification.fuelType;
    this.addVehicle.emissionClass =
      this.vehicle.engine.engineSpecification.emissionClass;
    this.addVehicle.landMark =
      this.vehicle.engine.engineSpecification.engineModel;
    this.addVehicle.cubicCapacity =
      this.vehicle.engine.engineSpecification.engineDisplacment;
    this.addVehicle.power = this.vehicle.engine.engineSpecification.enginePower;
    this.addVehicle.mileage = this.vehicle.mileage;
    this.addVehicle.drive = this.vehicle.engine.engineSpecification.driveType;
    this.addVehicle.gearBox = this.vehicle.gearBoxType;
    this.addVehicle.numberOfDoors = this.vehicle.numberOfDoors;
    this.addVehicle.numberOfSeats = this.vehicle.numberOfSeats;
    this.addVehicle.airConditioning = this.vehicle.airConditioning;
    this.addVehicle.color = this.vehicle.carBodyColor;
    this.addVehicle.interiorMaterial = this.vehicle.interiorMaterial;
    this.addVehicle.price = this.vehicle.price;
    this.createListOfAccessories();
    this.addVehicle.engineNumber = this.vehicle.engine.engineNumber;
    this.addVehicle.chassisNumber = this.vehicle.chassisNumber;
    this.vehicle.images.map((image: any) => this.addVehicle.images.push(image.name));
  };
  onClick = () => {
    this.buildAddVehicle();
    console.log(this.addVehicle);
    this.vehicleService.updateVehicle(this.addVehicle).subscribe((res) => {});
    alert('Vozilo uspešno izmenjeno!');
    this.router.navigate(['/cars']);
  };
}
