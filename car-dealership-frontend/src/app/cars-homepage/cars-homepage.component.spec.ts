import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CarsHomepageComponent } from './cars-homepage.component';

describe('CarsHomepageComponent', () => {
  let component: CarsHomepageComponent;
  let fixture: ComponentFixture<CarsHomepageComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CarsHomepageComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(CarsHomepageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
