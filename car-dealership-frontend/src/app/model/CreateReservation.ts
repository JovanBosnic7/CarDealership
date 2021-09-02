export class CreateReservation {
  vehicleId: string;
  clientId: string;
  payment: string;
  date: string;
  offeredPrice: number;
  constructor() {
    this.vehicleId = '';
    this.clientId = '';
    this.payment = '';
    this.date = '';
    this.offeredPrice = 0;
  }
}
