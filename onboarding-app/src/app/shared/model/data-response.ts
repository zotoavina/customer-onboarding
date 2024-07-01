export class DataResponse<T>{
    code!: number | string;
    message!: string;
    responseDatetime!: string;
    data!: T[];
  
    constructor() {  }
  }
  