import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { RouterModule, Routes } from '@angular/router';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HeaderComponent } from './header/header.component';
import { HomePageComponent } from './home-page/home-page.component';
import { CarsHomepageComponent } from './cars-homepage/cars-homepage.component';
import { LoginComponent } from './login/login.component';
import { FormsModule } from '@angular/forms';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { TokenInterceptor } from './interceptor/TokenInterceptor';
import { RegistrationComponent } from './registration/registration.component';
import { AddVehicleComponent } from './add-vehicle/add-vehicle.component';
import { IvyCarouselModule } from 'angular-responsive-carousel';
import { SingleVehicleComponent } from './single-vehicle/single-vehicle.component';
import { ChangeCarDealershipComponent } from './change-car-dealership/change-car-dealership.component';
import { TestDriveComponent } from './test-drive/test-drive.component';
import { TestDrive } from './model/TestDrive';
import { PricesComponent } from './prices/prices.component';
import { ReservationComponent } from './reservation/reservation.component';
import { ReservationsComponent } from './reservations/reservations.component';

const appRoutes: Routes = [
  { path: '', component: HomePageComponent },
  { path: 'cars', component: CarsHomepageComponent },
  { path: 'login', component: LoginComponent },
  { path: 'register', component: RegistrationComponent },
  { path: 'addVehicle', component: AddVehicleComponent },
  { path: 'single-vehicle/:vehicleId', component: SingleVehicleComponent },
  { path: 'changeCarDealership', component: ChangeCarDealershipComponent },
  { path: 'prices', component: PricesComponent },
  { path: 'testDrives', component: TestDriveComponent },
  { path: 'reservations', component: ReservationsComponent },
  { path: 'reservation/:vehicleId', component: ReservationComponent },
  { path: '**', redirectTo: '/404' },
];

@NgModule({
  declarations: [
    AppComponent,
    HeaderComponent,
    HomePageComponent,
    CarsHomepageComponent,
    LoginComponent,
    RegistrationComponent,
    AddVehicleComponent,
    SingleVehicleComponent,
    ChangeCarDealershipComponent,
    TestDriveComponent,
    PricesComponent,
    ReservationComponent,
    ReservationsComponent,
  ],
  imports: [
    BrowserModule,
    FormsModule,
    RouterModule.forRoot(appRoutes),
    AppRoutingModule,
    HttpClientModule,
    NgbModule,
    IvyCarouselModule,
  ],
  exports: [RouterModule],
  providers: [
    {
      provide: HTTP_INTERCEPTORS,
      useClass: TokenInterceptor,
      multi: true,
    },
  ],
  bootstrap: [AppComponent],
})
export class AppModule {}
