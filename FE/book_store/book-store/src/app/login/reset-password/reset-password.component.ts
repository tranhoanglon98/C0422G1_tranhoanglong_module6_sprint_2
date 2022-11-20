import {Component, OnDestroy, OnInit} from '@angular/core';
import {ShareDataService} from "../../service/share-data.service";

@Component({
  selector: 'app-reset-password',
  templateUrl: './reset-password.component.html',
  styleUrls: ['./reset-password.component.css']
})
export class ResetPasswordComponent implements OnInit, OnDestroy {

  constructor(private shareDataService:ShareDataService) {
    shareDataService.changeLoginModuleStatus(true);
  }

  ngOnInit(): void {
  }

  ngOnDestroy(): void {
    this.shareDataService.changeLoginModuleStatus(false);
  }
}
