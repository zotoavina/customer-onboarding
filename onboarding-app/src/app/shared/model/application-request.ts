export class FirstFormRequest {
    purposeUuid!:string;
    companyName!:string;
    entityTypeUuid!:string;
    activityUuid!:string;
    licence:string="";
    countryName!:string;
    registrationNumber!:string;
    incorporationDate!:string;
}

export class SecondFormRequest {
    directorName!: string;
    directorPassportNumber!: string;
    nameOfApplicant!: string;
    emailForCom!: string
}

export class ThirdFormRequest {
    file!: string;
}