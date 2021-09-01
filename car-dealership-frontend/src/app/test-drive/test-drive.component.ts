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
    if (this.authService.getRole() === 'client') {
      this.userService
        .getAllTestDrives(this.authService.getId() as string)
        .subscribe((res) => {
          this.testDrives = res as any[];
        });
    } else {
      this.userService.getTestDrives().subscribe((res) => {
        this.testDrives = res as any[];
      });
    }
  };
  onCancel = (id: string) => {
    this.userService.cancelTestDrive(id).subscribe((res) => {});
    alert('Test vožnja uspešno otkazana!');
    window.location.href = '/testDrives';
  };
  onClose = (id: string) => {
    this.userService.closeTestDrive(id).subscribe((res) => {});
    alert('Test vožnja uspešno zatvorena!');
    window.location.href = '/testDrives';
  };
}
