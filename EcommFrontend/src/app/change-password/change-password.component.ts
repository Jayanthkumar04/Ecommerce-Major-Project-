import { Component } from '@angular/core';
import { ActivatedRoute, Router, RouterLink } from '@angular/router';
import { AuthService } from '../services/auth.service';
import { ToastrService } from 'ngx-toastr';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { ChangePassword } from '../common/change-password';

@Component({
  selector: 'app-change-password',
  standalone: true,
  imports: [RouterLink,CommonModule,ReactiveFormsModule],
  templateUrl: './change-password.component.html',
  styleUrl: './change-password.component.css'
})
export class ChangePasswordComponent {

  changePasswordForm!:FormGroup;

  isFilled=false;


  constructor(private http:AuthService,private router:Router,private toaster:ToastrService,private activatedRoute:ActivatedRoute,private fb:FormBuilder)
  {
         this.changePasswordForm = this.fb.group({
          email:["",[Validators.required,Validators.email]],
          oldPassword:["",[Validators.required]],
          newPassword:["",[Validators.required]],
          confirmPassword:["",[Validators.required]]
         })
  }

  changePassword()
  {
    console.log(this.changePasswordForm.value);
    if(this.changePasswordForm.invalid)
    {
      this.toaster.warning("please enter valid details")
    }

    const formValue = this.changePasswordForm.value;

    const request={
      email:formValue.email,
      oldPassword:formValue.oldPassword,
      newPassword:formValue.newPassword
    };

    this.http.changePassword(request).subscribe({
      next:(data)=>{
        console.log(data);
        this.toaster.success("password has been changed successfully","Login now")
        this.changePasswordForm.reset();
        this.router.navigate(["/login"]);
      },
      error:(error)=>{
        this.toaster.error("Wrong credentials");
      }
    })
  }


}
