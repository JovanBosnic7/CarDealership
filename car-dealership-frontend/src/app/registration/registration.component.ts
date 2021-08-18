import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Client } from '../model/Client';
import { UserService } from '../services/user.service';

@Component({
  selector: 'app-registration',
  templateUrl: './registration.component.html',
  styleUrls: ['./registration.component.css'],
})
export class RegistrationComponent implements OnInit {
  public user: Client = new Client();
  public repeatPassword: string = '';
  public nameError: string = '';
  public lastNameError: string = '';
  public passwordError: string = '';
  public repeatPasswordError: string = '';
  public usernameError: string = '';
  constructor(private userService: UserService, private router:Router) {}

  ngOnInit(): void {}

  register = () => {
    if (this.validateInputs()) {
      this.userService.registerUser(this.user).subscribe((response) => {
        alert('Uspešno ste se registrovali!');
        this.resetValues();
        this.router.navigate(['/login']);
      });
    }
  };

  validateInputs = () => {
    let ret = true;
    if (this.user.name.length < 3 || this.user.name.length > 10) {
      this.nameError = 'Morate uneti ime duže od 3 karaktera!';
      ret = false;
    } else {
      this.nameError = '';
    }
    if (this.user.lastName.length < 3 || this.user.lastName.length > 10) {
      this.lastNameError = 'Morate uneti prezime duže od 3 karaktera!';
      ret = false;
    } else {
      this.lastNameError = '';
    }
    if (this.user.username.length < 3 || this.user.username.length > 10) {
      this.usernameError = 'Morate uneti validno korisničko ime!';
      ret = false;
    } else {
      this.usernameError = '';
    }
    if (this.user.password.length < 3 || this.user.password.length > 10) {
      this.passwordError = 'Morate uneti lozinku dužu od 3 karaktera!';
      ret = false;
    } else {
      this.passwordError = '';
    }
    if (this.repeatPassword !== this.user.password) {
      this.repeatPasswordError = 'Lozinke se ne poklapaju!';
      ret = false;
    } else {
      this.repeatPasswordError = '';
    }
    return ret;
  };

  resetValues = () => {
    this.user.name = '';
    this.user.lastName = '';
    this.user.password = '';
    this.user.username = '';
    this.nameError = '';
    this.lastNameError = '';
    this.usernameError = '';
    this.passwordError = '';
    this.repeatPasswordError = '';
  };
}
