import { Component, OnInit } from '@angular/core';
import { Mark } from '../model/Mark';
import { Model } from '../model/Model';
import { VehicleService } from '../services/vehicle.service';

@Component({
  selector: 'app-cars-homepage',
  templateUrl: './cars-homepage.component.html',
  styleUrls: ['./cars-homepage.component.css'],
})
export class CarsHomepageComponent implements OnInit {
  public vehicles: any[] = [];
  public marks: Mark[] = [];
  public models: Model[] = [];
  public mark: string = '';
  public model: string = '';
  public maxPrice: string = '';
  public minPrice: string = '';
  public carBody: string = '';
  public fuel: string = '';
  public gearBox: string = '';
  public isSearchClicked: boolean = false;
  public promotions: any[] = [];
  constructor(public vehicleService: VehicleService) {}

  ngOnInit(): void {
    this.initData();
  }

  initData = () => {
    this.vehicleService.getAllMarks().subscribe((res) => {
      this.marks = res as Mark[];
    });
    this.vehicleService.getAllPromotions().subscribe((res) => {
      this.promotions = res as any[];
    });
    this.vehicleService.getAllVehicles().subscribe((response: any[]) => {
      this.vehicles = response as any[];
      this.vehicles = this.vehicles.filter(
        (vehicle) => vehicle.vehicle_status === 'Available'
      );
    });
  };
  setModel = (event: any) => {
    let markId = event.target.value;
    this.mark = markId;
    this.vehicleService.getAllModelsByMark(markId).subscribe((res) => {
      this.models = res as Model[];
    });
  };
  onPriceChange = (event: any) => {
    this.maxPrice = event.target.value;
  };
  onMinPriceChange = (event: any) => {
    this.minPrice = event.target.value;
  };
  setSelectedModel = (event: any) => {
    this.model = event.target.value;
  };
  setCarBody = (event: any) => {
    this.carBody = event.target.value;
  };
  setFuel = (event: any) => {
    this.fuel = event.target.value;
  };
  setGearBox = (event: any) => {
    this.gearBox = event.target.value;
  };
  onSearchClick = () => {
    this.isSearchClicked = true;
    if (this.mark !== '') {
      if (this.model !== '') {
        this.vehicles = this.vehicles.filter(
          (vehicle) =>
            vehicle.model.mark.markId === this.mark &&
            vehicle.model.modelId === this.model
        );
      }
      this.vehicles = this.vehicles.filter(
        (vehicle) => vehicle.model.mark.markId === this.mark
      );
    }
    if (this.maxPrice !== '') {
      this.vehicles = this.vehicles.filter(
        (vehicle) => vehicle.price <= this.maxPrice
      );
    }
    if (this.minPrice !== '') {
      this.vehicles = this.vehicles.filter(
        (vehicle) => vehicle.price >= this.minPrice
      );
    }
    if (this.carBody !== '') {
      this.vehicles = this.vehicles.filter(
        (vehicle) => vehicle.carBody === this.carBody
      );
    }
    if (this.fuel !== '') {
      this.vehicles = this.vehicles.filter(
        (vehicle) => vehicle.engine.engineSpecification.fuelType === this.fuel
      );
    }
    if (this.gearBox !== '') {
      this.vehicles = this.vehicles.filter(
        (vehicle) => vehicle.gearBoxType === this.gearBox
      );
    }
  };
  onResetFilters = () => {
    window.location.href = 'cars';
  };
  onSortClick = () => {
    this.vehicles = this.vehicles.sort(
      (vehicle1, vehicle2) => vehicle1.price - vehicle2.price
    );
  };
  onSortClick2 = () => {
    this.vehicles = this.vehicles.sort(
      (vehicle1, vehicle2) => vehicle2.price - vehicle1.price
    );
  };
}
