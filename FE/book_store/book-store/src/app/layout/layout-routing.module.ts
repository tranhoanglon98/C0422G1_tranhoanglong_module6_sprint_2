import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {HomeComponent} from "./home/home.component";


const routes: Routes = [
  {path: "", pathMatch: "full",redirectTo: "home"},
  {path: 'home/search/:searchValue', component: HomeComponent},
  {path: 'home/category/:category', component: HomeComponent},
  {path: 'home', component: HomeComponent}
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class LayoutRoutingModule { }
