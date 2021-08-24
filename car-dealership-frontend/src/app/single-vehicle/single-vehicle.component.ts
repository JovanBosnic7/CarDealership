import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { CreateAction } from '../model/CreateAction';
import { AuthService } from '../services/auth.service';
import { VehicleService } from '../services/vehicle.service';

@Component({
  selector: 'app-single-vehicle',
  templateUrl: './single-vehicle.component.html',
  styleUrls: ['./single-vehicle.component.css'],
})
export class SingleVehicleComponent implements OnInit {
  public vehicleId: string = '';
  public vehicle: any;
  public actionPrecent: string = '';
  public isActionClicked: boolean = false;
  constructor(
    public route: ActivatedRoute,
    public vehicleService: VehicleService,
    public authService: AuthService,
    public router: Router
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

  onSellVehicleClick = () => {
    this.vehicleService.sellVehicle(this.vehicleId).subscribe((res) => {});
    alert('Vozilo uspešno uklonjeno iz ponude!');
    this.router.navigate(['/cars']);
  };
  onActionClick = () => {
    this.isActionClicked = true;
  };
  onChange = (event: any) => {
    this.actionPrecent = event.target.value;
  };
  createAction = () => {
    let createAction = new CreateAction();
    createAction.vehicleId = this.vehicleId;
    createAction.actionPrecentage = this.actionPrecent;
    this.vehicleService.createAction(createAction).subscribe((res) => {});
    alert('Uspešno ste kreirali akciju');
    this.router.navigate(['/cars']);
  };
}
