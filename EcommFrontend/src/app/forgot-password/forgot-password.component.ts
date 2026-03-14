import { Component } from '@angular/core';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { Router, RouterLink } from '@angular/router';
import { AuthService } from '../services/auth.service';
import { ToastrService } from 'ngx-toastr';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-forgot-password',
  standalone: true,
  imports: [RouterLink,CommonModule,ReactiveFormsModule],
  templateUrl: './forgot-password.component.html',
  styleUrl: './forgot-password.component.css'
})
export class ForgotPasswordComponent {

  forgotPasswordForm!:FormGroup;
  isFilled=false;

  constructor(private http:AuthService,private router:Router,private toaster:ToastrService,private fb:FormBuilder)
  {

    this.forgotPasswordForm = this.fb.group({
      email:["",[Validators.required,Validators.required]]
    })
  }


  forgotPassword()
  {
    if(this.forgotPasswordForm.invalid) {
      this.toaster.warning("please fill the details");
      return;
    }

    this.isFilled = true;
    this.http.forgotPassword(this.forgotPasswordForm.value).subscribe({

      
      next:(data)=>{
        this.toaster.success("Password Reset url link is been sent to mail","please reset it");
        this.forgotPasswordForm.reset();
        this.router.navigate(["/login"]);
      },
      error:(error)=>{
        this.toaster.error("Wrong email id");
        this.router.navigate(["/login"]);
      }
    })
  }

}
