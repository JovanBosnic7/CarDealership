import { Component, OnInit } from '@angular/core';
import { ClientService } from '../client.service';

@Component({
  selector: 'app-home-page',
  templateUrl: './home-page.component.html',
  styleUrls: ['./home-page.component.css'],
})
export class HomePageComponent implements OnInit {
  public carDealership: any;
  constructor(public clientService: ClientService) {}

  ngOnInit(): void {
    this.initData();
  }

  initData = () => {
    this.clientService.getCarDealership().subscribe((res) => {
      this.carDealership = res as any;
    });
  };
}
