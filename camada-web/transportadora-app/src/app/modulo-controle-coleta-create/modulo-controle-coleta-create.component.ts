import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

import { Solicitacao } from '../Solicitacao'
import { ModuloColetaServiceService } from '../modulo-coleta-service.service'

@Component({
  selector: 'app-modulo-controle-coleta-create',
  templateUrl: './modulo-controle-coleta-create.component.html',
  styleUrls: ['./modulo-controle-coleta-create.component.css'],
  providers: [ModuloColetaServiceService]
})
export class ModuloControleColetaCreateComponent implements OnInit {

  solicitacao: Solicitacao = new Solicitacao(); 
  submitted = false;

  constructor(private router: Router, private moduloColetaService: ModuloColetaServiceService) { }

  ngOnInit() {
  }

  save(solicitacao: Solicitacao): void {
    if(!solicitacao) { return; }
    this.moduloColetaService.create(solicitacao)
  }

  onSubmit() {
    this.submitted = true;
    this.save(this.solicitacao);
  }
}
