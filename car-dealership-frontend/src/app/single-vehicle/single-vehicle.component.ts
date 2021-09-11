import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { CreateAction } from '../model/CreateAction';
import { CreateFeedback } from '../model/CreateFeedback';
import { TestDrive } from '../model/TestDrive';
import { AuthService } from '../services/auth.service';
import { UserService } from '../services/user.service';
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
  public isTestDriveClicked: boolean = false;
  public testDriveDate: string = '';
  public createFeedback: CreateFeedback = new CreateFeedback();
  public description: string = '';
  public feedbacks : any[] = [];
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
    this.vehicleService.getVehicleFeedbacks(this.vehicleId).subscribe((feeds)=>{
      this.feedbacks = feeds as any;
    } )
  };

  onSellVehicleClick = () => {
    this.vehicleService.sellVehicle(this.vehicleId).subscribe((res) => {});
    alert('Vozilo uspešno uklonjeno iz ponude!');
    this.router.navigate(['/cars']);
  };
  onActionClick = () => {
    this.isActionClicked = true;
  };
  onTestDriveClick = () => {
    this.isTestDriveClicked = true;
  };
  scheduleTestDrive = () => {
    let testDrive: TestDrive = new TestDrive();
    testDrive.clientId = this.authService.getId() as string;
    testDrive.vehicleId = this.vehicleId;
    testDrive.dateOfTestDrive = this.testDriveDate;
    this.userService.createTestDrive(testDrive).subscribe((res) => {});
    alert('Test vožnja uspešno zakazana!');
    this.router.navigate(['/testDrives']);
  };
  onChange = (event: any) => {
    this.actionPrecent = event.target.value;
  };
  onChangeVehicle = () => {
    this.router.navigate(['/changeCar/' + this.vehicleId]);
  };
  onAddFeedback = () => {
    this.createFeedback.description = this.description;
    this.createFeedback.clientId = this.authService.getId() as string;
    this.createFeedback.vehicleId = this.vehicleId;
    this.userService.createFeedback(this.createFeedback).subscribe((res) => {});
    alert('Utisak uspešno ostavljen!');
    this.router.navigate(['/cars']);
  };
  onReservationClick = () => {
    this.router.navigate(['/reservation/' + this.vehicleId]);
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
