import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
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
}
