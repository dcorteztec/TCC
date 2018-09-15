import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { HomeComponent}         from './home/home.component';
import { LoginComponent}        from './login/login.component';
import { ModuloControleColetaComponent} from './modulo-controle-coleta/modulo-controle-coleta.component'
import { ModuloTabelaFreteComponent} from './modulo-tabela-frete/modulo-tabela-frete.component'
import { ModuloControleColetaCreateComponent } from './modulo-controle-coleta-create/modulo-controle-coleta-create.component'
import { CanActivateAuthGuard } from './CanActivateAuthGuard'
import { ModuloControleFreteSimulacaoComponent } from './modulo-controle-frete-simulacao/modulo-controle-frete-simulacao.component'
import { ModuloControleTabelaCreateComponent } from './modulo-controle-tabela-create/modulo-controle-tabela-create.component';

const routes: Routes = [
  { path: '', redirectTo: '/home', pathMatch: 'full' },
  { path: 'home',       component: HomeComponent },
  { path: 'login',      component: LoginComponent},
  { path: 'modulo-coleta',      component: ModuloControleColetaComponent, canActivate: [CanActivateAuthGuard]},
  { path: 'modulo-coleta-create',      component: ModuloControleColetaCreateComponent, canActivate: [CanActivateAuthGuard]},
  { path: 'modulo-coleta-edit/:id',      component: ModuloControleColetaCreateComponent, canActivate: [CanActivateAuthGuard]},
  { path: 'modulo-frete',       component: ModuloTabelaFreteComponent, canActivate: [CanActivateAuthGuard]},
  { path: 'modulo-frete-create',      component: ModuloControleTabelaCreateComponent, canActivate: [CanActivateAuthGuard]},
  { path: 'modulo-frete-edit/:id',      component: ModuloControleTabelaCreateComponent, canActivate: [CanActivateAuthGuard]},
  { path: 'modulo-controle-frete-simulacao',      component: ModuloControleFreteSimulacaoComponent, canActivate: [CanActivateAuthGuard]}


];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
