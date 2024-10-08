import {RouterModule, Routes} from "@angular/router";
import {NgModule} from "@angular/core";
import {LoginComponent} from "./login/login.component";
import {RegisterComponent} from "./register/register.component";

const routes : Routes = [
  { title: 'Login', path: 'login', component : LoginComponent },
  { title: 'Register', path: 'register', component : RegisterComponent },
  { path: '', redirectTo: 'login', pathMatch: 'full' }
]

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})

export class AuthRoutingModule { }
