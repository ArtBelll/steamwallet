import {NgModule, OnInit}             from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {SignUpComponent} from "./components/registration/sign-up.component";
import {SignInComponent} from "./components/login/sign-in.component";
import {NotAuthGuard} from "./services/not-auth-guard-service";

const routes:Routes = [
    {path: '', redirectTo: '/', pathMatch: 'full'},
    {path: 'sign-up', component: SignUpComponent, canActivate: [NotAuthGuard]},
    {path: 'sign-in', component: SignInComponent, canActivate: [NotAuthGuard]}
];
@NgModule({
    imports: [RouterModule.forRoot(routes)],
    exports: [RouterModule]
})
export class AppRoutingModule { }
