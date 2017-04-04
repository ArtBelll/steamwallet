import {NgModule}             from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {SignUpComponent} from "./components/registration/sign-up.component";
import {SignInComponent} from "./components/login/sign-in.component";

const routes:Routes = [
    {path: '', redirectTo: '/', pathMatch: 'full'},
    {path: 'sign-up', component: SignUpComponent},
    {path: 'sign-in', component: SignInComponent}
];
@NgModule({
    imports: [RouterModule.forRoot(routes)],
    exports: [RouterModule]
})
export class AppRoutingModule {
}
