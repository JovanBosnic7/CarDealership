import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AccountInfoModel } from '../model/AccountInfo';
import { AuthService } from '../services/auth.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css'],
})
export class LoginComponent implements OnInit {
  public user: AccountInfoModel = new AccountInfoModel();
  constructor(private authService: AuthService, private router: Router) {}

  ngOnInit(): void {}

  onLogin = () => {
    this.authService.signIn(this.user);
  };
}
