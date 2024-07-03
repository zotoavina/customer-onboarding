import { Component } from '@angular/core';
import {ManagerServiceService} from "../../../services/manager-service.service";

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})
export class NavbarComponent {

  constructor(private managerSev: ManagerServiceService) {
  }


  logout(){
    this.managerSev.logout();
  }
}
