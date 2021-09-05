import { Component, OnInit } from '@angular/core';
import { AuthService } from '../services/auth.service';
import { UserService } from '../services/user.service';

@Component({
  selector: 'app-reservations',
  templateUrl: './reservations.component.html',
  styleUrls: ['./reservations.component.css'],
})
export class ReservationsComponent implements OnInit {
  public reservations: any[] = [];
  constructor(
    public userService: UserService,
    public authService: AuthService
  ) {}

  ngOnInit(): void {
    this.initData();
  }

  initData = () => {
    if (this.authService.getRole() === 'client') {
      this.userService
        .getReservations(this.authService.getId() as string)
        .subscribe((res) => {
          this.reservations = res as any[];
        });
    } else {
      this.userService.getAllReservations().subscribe((res) => {
        this.reservations = res as any[];
      });
    }
  };

  onCancel = (id: string) => {
    this.userService.cancelReservation(id).subscribe((res) => {});
    alert('Rezervacija uspešno otkazana!');
    window.location.href = '/reservations';
  };
  onClose = (id: string) => {
    this.userService.closeReservation(id).subscribe((res) => {});
    alert('Rezervacija uspešno zatvorena!');
    window.location.href = '/reservations';
  };
  onAccept = (id: string) => {
    this.userService.acceptOffer(id).subscribe((res) => {});
    alert('Ponuda prihvaćena!');
    window.location.href = '/reservations';
  };
  onDecline = (id: string) => {
    this.userService.declineOffer(id).subscribe((res) => {});
    alert('Ponuda odbijena!');
    window.location.href = '/reservations';
  };
}
