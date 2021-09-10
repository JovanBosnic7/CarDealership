import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { CreateVehicle } from '../model/CreateVehicle';
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
  public addVehicle: CreateVehicle = new CreateVehicle();
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
  public files: File[] = [];
  public formData: FormData = new FormData();
  public isPhotosUploaded: boolean = false;
  constructor(public vehicleSerivce: VehicleService, public router: Router) {}

  ngOnInit(): void {
    this.initData();
  }
  initData = () => {
    this.vehicleSerivce.getAllMarks().subscribe((res) => {
      this.marks = res as Mark[];
    });
  };

  onChange(event: any): void {
    for (var i = 0; i < event.target.files.length; i++) {
      this.files.push(event.target.files[i]);
    }
  }
  setModel = (event: any) => {
    let markId = event.target.value;
    this.addVehicle.mark = markId;
    this.vehicleSerivce.getAllModelsByMark(markId).subscribe((res) => {
      this.models = res as Model[];
    });
  };
  setSelectedModel = (event: any) => {
    this.addVehicle.model = event.target.value;
  };
  setCarBody = (event: any) => {
    this.addVehicle.carBody = event.target.value;
  };
  setFuel = (event: any) => {
    this.addVehicle.fuel = event.target.value;
  };
  setEmissionClass = (event: any) => {
    this.addVehicle.emissionClass = event.target.value;
  };
  setDrive = (event: any) => {
    this.addVehicle.drive = event.target.value;
  };
  setGearBox = (event: any) => {
    this.addVehicle.gearBox = event.target.value;
  };
  setAirConditioning = (event: any) => {
    this.addVehicle.airConditioning = event.target.value;
  };
  setColor = (event: any) => {
    this.addVehicle.color = event.target.value;
  };
  setInteriorMaterial = (event: any) => {
    this.addVehicle.interiorMaterial = event.target.value;
  };

  onClick = () => {
    for (let i = 0; i < this.files.length; i++) {
      this.formData.append('images', this.files[i], this.files[i].name);
      this.addVehicle.images.push(this.files[i].name);
    }
    this.vehicleSerivce.uploadImages(this.formData).subscribe((res) => {});
    this.isPhotosUploaded = true;
  };
  onAddVehicle = () => {
    if (!this.isPhotosUploaded) {
      alert('Prvo kliknite Dodajte fotografije pa zatim Dodajte vozilo');
      return;
    }
    this.createListOfAccessories();
    this.vehicleSerivce.createVehicle(this.addVehicle).subscribe((response) => {
      alert('Uspešno ste dodali vozilo!');
      this.router.navigate(['']);
    });
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
    this.parkingSensors && this.addVehicle.accessories.push("Parking senzori");
  };
}
