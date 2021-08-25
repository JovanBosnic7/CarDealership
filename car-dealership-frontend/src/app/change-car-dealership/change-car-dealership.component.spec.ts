import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ChangeCarDealershipComponent } from './change-car-dealership.component';

describe('ChangeCarDealershipComponent', () => {
  let component: ChangeCarDealershipComponent;
  let fixture: ComponentFixture<ChangeCarDealershipComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ChangeCarDealershipComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ChangeCarDealershipComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
