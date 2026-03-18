import { Component } from '@angular/core';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { Router, RouterLink } from '@angular/router';
import { AuthService } from '../services/auth.service';
import { ToastrService } from 'ngx-toastr';
import { CommonModule } from '@angular/common';
import { LoginSuccess } from '../common/login-success';

@Component({
  selector: 'app-login',
  standalone: true,
  imports: [RouterLink, ReactiveFormsModule, CommonModule],
  templateUrl: './login.component.html',
  styleUrl: './login.component.css'
})
export class LoginComponent {

loginForm!:FormGroup;

isFilled=false;

user!:LoginSuccess;

constructor(private http:AuthService,private toaster:ToastrService,private fb:FormBuilder,private router:Router){

  this.loginForm = this.fb.group({
  
    email:["",[Validators.required,Validators.email]],
    password:["",[Validators.required]]
  })
}

loginUser()
{
  console.log(this.loginForm.value);
  this.isFilled=true;

  if(this.loginForm.invalid) {
    this.toaster.error("please provide valid details");
    return;
  }

  this.http.loginUser(this.loginForm.value).subscribe({
    next:(data)=>{

      
      if(data.firstLogin)
      {
        this.toaster.error("please reset password before login");
        return;
      }

          this.user = data;
          this.toaster.success("login is sucessfull");
          this.http.setLogin(data.name,data.role);
          this.loginForm.reset();
          this.router.navigate(["/"]);
    },
    error:(error)=>{
      this.toaster.error("Something went wrong","please check credentials");
    }
  })
  
}


}
