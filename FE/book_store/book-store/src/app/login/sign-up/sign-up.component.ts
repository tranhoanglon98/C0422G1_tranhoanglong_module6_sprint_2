import {Component, OnDestroy, OnInit} from '@angular/core';
import {ShareDataService} from "../../service/share-data.service";

@Component({
  selector: 'app-sign-up',
  templateUrl: './sign-up.component.html',
  styleUrls: ['./sign-up.component.css']
})
export class SignUpComponent implements OnInit,OnDestroy{

  constructor(private shareDataService:ShareDataService) {
    shareDataService.changeLoginModuleStatus(true);
  }

  ngOnInit(): void {
  }

  ngOnDestroy(): void {
    this.shareDataService.changeLoginModuleStatus(false);
  }

}
