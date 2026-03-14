import { Component } from '@angular/core';
import { Router, RouterLink } from '@angular/router';
import { AuthService } from '../services/auth.service';
import { FormBuilder, FormGroup, FormsModule, ReactiveFormsModule, Validators } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { ToastrService } from 'ngx-toastr';

@Component({
  selector: 'app-register',
  standalone: true,
  imports: [RouterLink, ReactiveFormsModule, FormsModule,CommonModule],
  templateUrl: './register.component.html',
  styleUrl: './register.component.css'
})
export class RegisterComponent {

  registerForm!:FormGroup;

  isSubmitted=false;

  constructor(private http:AuthService,private fb:FormBuilder,private toastr:ToastrService,private router:Router){

    this.registerForm = this.fb.group({
      name:['',[Validators.required,Validators.minLength(5)]],
      email:['',[Validators.required,Validators.email]]
    });
  }

  register()
  {
    this.isSubmitted=true;

    console.log(this.registerForm.value);
    if(this.registerForm.invalid){
      console.log("please fill valid details")
      return;
    }
    this.http.registerUser(this.registerForm.value).subscribe({
      next:(data)=>{
        this.toastr.success("Registration is successfull please reset password")
        this.registerForm.reset();
        this.router.navigate(['/change-password'])
      },
      error:(error)=>{
        this.toastr.error(
        "Something went wrong",
        "Registration Failed"
      );
        console.log(error);
      }
    });
  }
}
