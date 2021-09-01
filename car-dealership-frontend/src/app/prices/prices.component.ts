import { Component, OnInit } from '@angular/core';
import { AuthService } from '../services/auth.service';
import { UserService } from '../services/user.service';

@Component({
  selector: 'app-prices',
  templateUrl: './prices.component.html',
  styleUrls: ['./prices.component.css'],
})
export class PricesComponent implements OnInit {
  public prices: any[] = [];
  constructor(
    public userService: UserService,
    public authService: AuthService
  ) {}

  ngOnInit(): void {
    this.initData();
  }

  initData = () => {
    this.userService.getPrices().subscribe((res) => {
      this.prices = res as any[];
      console.log(this.prices);
    });
  };
}
