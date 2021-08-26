import { Component, OnInit } from '@angular/core';
import { AuthService } from '../services/auth.service';
import { UserService } from '../services/user.service';

@Component({
  selector: 'app-test-drive',
  templateUrl: './test-drive.component.html',
  styleUrls: ['./test-drive.component.css'],
})
export class TestDriveComponent implements OnInit {
  public testDrives: any[] = [];
  constructor(
    public userService: UserService,
    public authService: AuthService
  ) {}

  ngOnInit(): void {
    this.initData();
  }

  initData = () => {
    this.userService
      .getAllTestDrives(this.authService.getId() as string)
      .subscribe((res) => {
        this.testDrives = res as any[];
      });
  };
}
