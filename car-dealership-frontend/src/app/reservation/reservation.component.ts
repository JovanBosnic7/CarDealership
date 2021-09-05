import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { AuthService } from '../services/auth.service';
import { UserService } from '../services/user.service';
import { VehicleService } from '../services/vehicle.service';
import { FormsModule } from '@angular/forms';
import { CreateReservation } from '../model/CreateReservation';
@Component({
  selector: 'app-reservation',
  templateUrl: './reservation.component.html',
  styleUrls: ['./reservation.component.css'],
})
export class ReservationComponent implements OnInit {
  public vehicleId: string = '';
  public vehicle: any;
  public reservationDate: string = '';
  public payment: string = '';
  public price: number = 0;
  public reservation: CreateReservation = new CreateReservation();
  constructor(
    public vehicleService: VehicleService,
    public authService: AuthService,
    public userService: UserService,
    public route: ActivatedRoute
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

  onClick = () => {
    this.reservation.clientId = this.authService.getId() as string;
    this.reservation.vehicleId = this.vehicleId;
    this.reservation.date = this.reservationDate;
    this.reservation.payment = this.payment;
    this.reservation.offeredPrice = this.price;
    this.userService.createReservation(this.reservation).subscribe((res) => {});
    alert('Rezervacija uspešno kerirana!');
    window.location.href = '/reservations';
  };
  onChange = (event: any) => {
    this.reservationDate = event.target.value;
  };
  setPayment = (event: any) => {
    this.payment = event.target.value;
  };

  onPriceChange = (event: any) => {
    this.price = event.target.value;
  };
}
