import {NgModule}             from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {SignUpComponent} from "./components/registration/sign-up.component";

const routes:Routes = [
    {path: '', redirectTo: '/', pathMatch: 'full'},
    {path: 'sign-up', component: SignUpComponent}
];
@NgModule({
    imports: [RouterModule.forRoot(routes)],
    exports: [RouterModule]
})
export class AppRoutingModule {
}
