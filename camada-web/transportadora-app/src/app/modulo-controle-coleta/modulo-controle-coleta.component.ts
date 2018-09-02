import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

import { Solicitacao } from '../Solicitacao'
import { ModuloColetaServiceService } from '../modulo-coleta-service.service'

@Component({
  selector: 'app-modulo-controle-coleta',
  templateUrl: './modulo-controle-coleta.component.html',
  styleUrls: ['./modulo-controle-coleta.component.css'],
  providers: [ModuloColetaServiceService]
})
export class ModuloControleColetaComponent implements OnInit {

  solicitacoes: Solicitacao[];
  solicitacao: Solicitacao;
  
  constructor(private router: Router, private moduloColetaService: ModuloColetaServiceService) { }

  ngOnInit() {
    this.findAll();
  }

  findAll(): void {
    this.moduloColetaService.findAll()
    .then(
      solicitacoes => this.solicitacoes = solicitacoes,
      error => {
      this.router.navigate(['login']);
      console.error('An error occurred in heroes component, navigating to login: ', error);
      }
    )
}

redirectNovaSolicitacaoPage() {
  this.router.navigate(['/modulo-coleta-create']);
}

editSolicitacaoPage(solicitacao: Solicitacao) {
  if (solicitacao) {
    this.solicitacao = solicitacao;
    this.router.navigate(['/modulo-coleta-edit', solicitacao.numeroSolic]);
  }
}

onSelect(solicitacao: Solicitacao): void {
  this.solicitacao = solicitacao;
}

deleteSolicitacao(solicitacao: Solicitacao): void {
    this.moduloColetaService
    .delete(solicitacao.numeroSolic)
    .then(() => {
        this.solicitacoes = this.solicitacoes.filter(h => h !== solicitacao);
        if(this.solicitacao === solicitacao) {
          this.solicitacao = null;
      }
    });
}
}
