export class CreateVehicle {
  mark: string;
  vehicleId: string;
  model: string;
  carBody: string;
  year: string;
  fuel: string;
  landMark: string;
  cubicCapacity: string;
  power: string;
  mileage: string;
  emissionClass: string;
  drive: string;
  gearBox: string;
  numberOfDoors: string;
  numberOfSeats: string;
  airConditioning: string;
  color: string;
  interiorMaterial: string;
  price: string;
  accessories: string[];
  engineNumber: string;
  chassisNumber: string;
  images: string[];
  existingImages: any[];

  constructor() {
    this.mark = '';
    this.vehicleId = '';
    this.model = '';
    this.carBody = '';
    this.year = '';
    this.fuel = '';
    this.landMark = '';
    this.cubicCapacity = '';
    this.power = '';
    this.mileage = '';
    this.emissionClass = '';
    this.drive = '';
    this.gearBox = '';
    this.numberOfDoors = '';
    this.numberOfSeats = '';
    this.airConditioning = '';
    this.color = '';
    this.interiorMaterial = '';
    this.price = '';
    this.accessories = [];
    this.engineNumber = '';
    this.chassisNumber = '';
    this.images = [];
    this.existingImages = [];
  }
}
