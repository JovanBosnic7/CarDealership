export class CarDealership {
  carDealershipId: string;
  name: string;
  address: string;
  email: string;
  phoneNumber: string;
  workTime: WorkTime;
  constructor() {
    this.carDealershipId = '';
    this.name = '';
    this.address = '';
    this.email = '';
    this.phoneNumber = '';
    this.workTime = new WorkTime();
  }
}

export class WorkTime {
  day: string;
  startTime: string;
  endTime: string;
  constructor() {
    this.day = '';
    this.startTime = '';
    this.endTime = '';
  }
}
