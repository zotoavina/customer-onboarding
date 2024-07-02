import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { map } from 'rxjs';
import { ManagerServiceService } from 'src/app/services/manager-service.service';
import { Login } from 'src/app/shared/model/login';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {
  loginForm!: FormGroup;
  userLogin: Login = new Login("processor@gmail.com", "password");
  message: string | undefined;

  constructor(
    private formBuilder: FormBuilder, 
    private router: Router,
    private managerSrv: ManagerServiceService
  ) {
    this.loginForm = this.formBuilder.group({
      email: [this.userLogin.email, [Validators.required, Validators.email]],
      password: [this.userLogin.password, Validators.required],
    });
  }

  login(){
    if(this.loginForm.valid){
      this.userLogin = this.loginForm.value;
      this.managerSrv.login(this.userLogin).subscribe(
        (res) => {
          console.log(res);
          if(res.code === 200){
             console.log("connecté 200");
          }
          if(res.code === 400){
            this.message = res.message;
          }
        },
        (err) =>{
          this.message = err.error.message;
          console.log(this.message);
        });
    }
  }

}
