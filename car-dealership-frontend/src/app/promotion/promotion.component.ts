import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Promotion } from '../model/Promotion';
import { UserService } from '../services/user.service';
import { VehicleService } from '../services/vehicle.service';

@Component({
  selector: 'app-promotion',
  templateUrl: './promotion.component.html',
  styleUrls: ['./promotion.component.css'],
})
export class PromotionComponent implements OnInit {
  public name: string = '';
  public startDate: string = '';
  public endDate: string = '';
  public description: string = '';
  public promotion: Promotion = new Promotion();
  public promotions: any[] = [];
  constructor(
    public userService: UserService,
    public router: Router,
    public vehicleService: VehicleService
  ) {}

  ngOnInit(): void {
    this.initData();
  }
  initData = () => {
    this.vehicleService.getAllPromotions().subscribe((res) => {
      this.promotions = res as any[];
    });
  };
  onChange = (event: any) => {
    this.name = event.target.value;
  };
  onChangeA = (event: any) => {
    this.startDate = event.target.value;
  };
  onChangeE = (event: any) => {
    this.endDate = event.target.value;
  };
  onChangeP = (event: any) => {
    this.description = event.target.value;
  };
  onClick = () => {
    this.promotion.name = this.name;
    this.promotion.startDate = this.startDate;
    this.promotion.endDate = this.endDate;
    this.promotion.description = this.description;
    this.userService.createPromotion(this.promotion).subscribe((res) => {});
    alert('Promocija uspešno kreirana!');
    this.router.navigate(['/cars']);
  };
  onDelete = (promotionId: string) => {
    this.vehicleService.deletePromotion(promotionId).subscribe(res => {});
    alert("Promocija uspešno obrisana!");
    this.router.navigate(['/cars']);
  };
}
