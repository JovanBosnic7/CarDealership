import { Component, OnInit } from '@angular/core';
import { ClientService } from '../client.service';
import { AuthService } from '../services/auth.service';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css'],
})
export class HeaderComponent implements OnInit {
  public carDealership: any;

  constructor(
    public authService: AuthService,
    public clientService: ClientService
  ) {}

  ngOnInit(): void {
    this.initData();
  }

  onLogout = () => {
    this.authService.doLogout();
  };
  initData = () => {
    this.clientService.getCarDealership().subscribe((res) => {
      this.carDealership = res as any;
    });
  };
}
