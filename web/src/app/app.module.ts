import {NgModule}       from '@angular/core';
import {BrowserModule}  from '@angular/platform-browser';
import {FormsModule}    from '@angular/forms';
import {AppComponent}   from './components/main-component/app.component';
import {HttpModule}     from '@angular/http';
import {AppRoutingModule} from "./app-routing.module";
import {NavigationComponent} from "./components/navigation/nav.component";
import {AuthSellerService} from "./services/auth-seller-service";
import {SignUpComponent} from "./components/registration/sign-up.component";

@NgModule({
    imports: [
        BrowserModule,
        FormsModule,
        HttpModule,
        AppRoutingModule
    ],
    declarations: [
        AppComponent,
        NavigationComponent,
        SignUpComponent
    ],
    providers: [AuthSellerService],
    bootstrap: [AppComponent]
})
export class AppModule {
}
