import { Component, OnInit } from '@angular/core';
import { Mark } from '../model/Mark';
import { Model } from '../model/Model';
import { VehicleService } from '../services/vehicle.service';

@Component({
  selector: 'app-add-vehicle',
  templateUrl: './add-vehicle.component.html',
  styleUrls: ['./add-vehicle.component.css'],
})
export class AddVehicleComponent implements OnInit {
  public marks: Mark[] = [];
  public models: Model[] = [];
  public mark: string = '';
  public model: string= '';
  public carBody: string = '';
  public year: string ='';
  public fuel: string = '';
  public landMark: string ='';
  public cubicCapacity: string = '';
  public power : string = '';
  public mileage: string = '';
  public emissionClass: string = '';
  public drive: string = '';
  public gearBox: string = '';
  public numberOfDoors: string = '';
  public numberOfSeats: string = '';
  public airConditioning: string = '';
  public color: string = '';
  public interiorMaterial: string = '';
  public price: string = '';
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
  
  constructor(public vehicleSerivce: VehicleService) {}

  ngOnInit(): void {
    this.initData();
  }
  initData = () => {
    this.vehicleSerivce.getAllMarks().subscribe((res) => {
      this.marks = res as Mark[];
    });
  };

  setModel = (event: any) => {
    let markId = event.target.value;
    this.mark = markId;
    this.vehicleSerivce.getAllModelsByMark(markId).subscribe((res) => {
      this.models = res as Model[];
    });
  }
  setSelectedModel = (event:any) => {
    this.model = event.target.value;
  }
  setCarBody = (event:any) => {
    this.carBody = event.target.value;
  }
  setFuel = (event:any) => {
    this.fuel = event.target.value;
  }
  setEmissionClass = (event:any) => {
    this.emissionClass = event.target.value;
  }
  setDrive = (event:any) => {
    this.drive = event.target.value;
  }
  setGearBox = (event:any) => {
    this.gearBox = event.target.value;
  }
  setAirConditioning = (event:any) => {
    this.airConditioning = event.target.value;
  }
  setColor = (event:any) => {
    this.color = event.target.value;
  }
  setInteriorMaterial = (event:any) => {
    this.interiorMaterial = event.target.value;
  }

}
