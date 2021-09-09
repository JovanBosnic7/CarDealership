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
  public price: string = '';
  public isSearchClicked: boolean = false;
  constructor(public vehicleService: VehicleService) {}

  ngOnInit(): void {
    this.initData();
  }

  initData = () => {
    this.vehicleService.getAllMarks().subscribe((res) => {
      this.marks = res as Mark[];
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
    this.price = event.target.value;
  };
  setSelectedModel = (event: any) => {
    this.model = event.target.value;
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
    if (this.price !== '') {
      this.vehicles = this.vehicles.filter(
        (vehicle) => vehicle.price <= this.price
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
}
