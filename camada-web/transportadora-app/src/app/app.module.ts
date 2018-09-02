import { BrowserModule } from '@angular/platform-browser';
import { NgModule, NO_ERRORS_SCHEMA } from '@angular/core';
import { MDBBootstrapModule } from 'angular-bootstrap-md';

import { HttpModule } from '@angular/http';
import { FormsModule } from '@angular/forms';

import { AppRoutingModule } from './app-routing.module';

import { AppComponent } from './app.component';
import { HomeComponent } from './home/home.component';
import { LoginComponent } from './login/login.component';

import {AuthenticationService} from './authentication.service';
import { CanActivateAuthGuard } from './CanActivateAuthGuard';
import { ModuloControleColetaComponent } from './modulo-controle-coleta/modulo-controle-coleta.component';
import { ModuloTabelaFreteComponent } from './modulo-tabela-frete/modulo-tabela-frete.component';
import { ModuloControleColetaCreateComponent } from './modulo-controle-coleta-create/modulo-controle-coleta-create.component';

@NgModule({
  declarations: [
    AppComponent,
    HomeComponent,
    LoginComponent,
    ModuloControleColetaComponent,
    ModuloTabelaFreteComponent,
    ModuloControleColetaCreateComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpModule,
    FormsModule,
    MDBBootstrapModule.forRoot()
  ],
  schemas: [NO_ERRORS_SCHEMA],
  providers: [AuthenticationService,CanActivateAuthGuard],
  bootstrap: [AppComponent]
})
export class AppModule { }
