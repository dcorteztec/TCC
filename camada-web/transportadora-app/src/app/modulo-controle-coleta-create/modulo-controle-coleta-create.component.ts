import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

import { Solicitacao } from '../Solicitacao'
import { TransportadoraParceira } from '../TransportadoraParceira'
import { ModuloColetaServiceService } from '../modulo-coleta-service.service'
import { ModuloControleColetaComponent } from '../modulo-controle-coleta/modulo-controle-coleta.component'

@Component({
  selector: 'app-modulo-controle-coleta-create',
  templateUrl: './modulo-controle-coleta-create.component.html',
  styleUrls: ['./modulo-controle-coleta-create.component.css'],
  providers: [ModuloColetaServiceService]
})
export class ModuloControleColetaCreateComponent implements OnInit {
  
  transportadoras : TransportadoraParceira[];
  solicitacao: Solicitacao = new Solicitacao();
  controleColeta: ModuloControleColetaComponent;
  submitted = false;

  constructor(private router: Router, private moduloColetaService: ModuloColetaServiceService) { }

  ngOnInit() {
    this.findAllTransportadora();
  }

  findAllTransportadora(): void {
    this.moduloColetaService.findAllTransportadora()
    .then(
      transportadoras => this.transportadoras = transportadoras,
      error => {
      this.router.navigate(['login']);
      console.error('An error occurred in heroes component, navigating to login: ', error);
      }
    )
}

  save(solicitacao: Solicitacao): void {
    if(!solicitacao) { return; }
    this.moduloColetaService.create(solicitacao)
    .then(solic => {
      this.controleColeta.solicitacoes.push(solic);
    });
   
    this.router.navigate(['modulo-coleta']);
  }

  onSubmit() {
    this.submitted = true;
    this.save(this.solicitacao);
  }
}
