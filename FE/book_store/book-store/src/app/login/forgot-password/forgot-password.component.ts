import {Component, OnDestroy, OnInit} from '@angular/core';
import {ShareDataService} from "../../service/share-data.service";

@Component({
  selector: 'app-forgot-password',
  templateUrl: './forgot-password.component.html',
  styleUrls: ['./forgot-password.component.css']
})
export class ForgotPasswordComponent implements OnInit, OnDestroy {

  constructor(private shareDataService:ShareDataService) {
    shareDataService.changeLoginModuleStatus(true);
  }

  ngOnInit(): void {
  }

  ngOnDestroy(): void {
    this.shareDataService.changeLoginModuleStatus(false);
  }
}
