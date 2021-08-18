import { Mark } from "./Mark";

export class Model {
    modelId: string;
    name: string;
    mark : Mark;
  
    constructor() {
      this.modelId = '';
      this.name = '';
      this.mark = new Mark();
    }
  }