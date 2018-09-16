import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { DatePipe } from '@angular/common';

import { Solicitacao } from '../Solicitacao'
import { TransportadoraParceira } from '../TransportadoraParceira'
import { ModuloColetaServiceService } from '../modulo-coleta-service.service'
import { ModuloControleColetaComponent } from '../modulo-controle-coleta/modulo-controle-coleta.component'
import { AuthenticationService } from 'src/app/authentication.service'
import swal from 'sweetalert2';
import { EmpresaParceira } from '../EmpresaParceira';

@Component({
  selector: 'app-modulo-controle-coleta-create',
  templateUrl: './modulo-controle-coleta-create.component.html',
  styleUrls: ['./modulo-controle-coleta-create.component.css'],
  providers: [ModuloColetaServiceService]
})
export class ModuloControleColetaCreateComponent implements OnInit {
  
  transportadoras : TransportadoraParceira[];
  empresasParceiras:EmpresaParceira[];
  solicitacao: Solicitacao = new Solicitacao();
  solicitacoes :Solicitacao[];
  controleColeta: ModuloControleColetaComponent;
  submitted = false;
  
  constructor(private router: Router, private rota: ActivatedRoute, 
    private moduloColetaService: ModuloColetaServiceService,private authenticationService: AuthenticationService) { }

  ngOnInit() {
    this.findAllTransportadora();
    this.findAllEmpresasParceias();
    this.rota.params.subscribe((parametro) => {
    if(parametro.id)  
    this.moduloColetaService.findByIdSolicitacao(parametro.id).then(res => 
      {this.solicitacao = res
       this.solicitacao.dataSolic = new DatePipe('en-US').transform(res.dataSolic, 'yyyy-MM-dd') 
       this.solicitacao.dataVolta = new DatePipe('en-US').transform(res.dataVolta, 'yyyy-MM-dd')}); 
    });
  }

  logout() {
    this.authenticationService.logout();
    this.router.navigate(['/login']);
  } 

  findAllTransportadora(): void {
    this.moduloColetaService.findAllTransportadora()
    .then(
      transportadoras => this.transportadoras = transportadoras,
      error => {
      this.router.navigate(['login']);
      swal('Solicitação','An error occurred in heroes component, navigating to login:'  ,'error' );
      console.error('An error occurred in heroes component, navigating to login: ', error);
      }
    )
}

findAllEmpresasParceias(): void {
  this.moduloColetaService.findAllEmpresaParceira()
  .then(
    empresasParceiras => this.empresasParceiras = empresasParceiras,
    error => {
    this.router.navigate(['login']);
    swal('Solicitação','An error occurred in heroes component, navigating to login:'  ,'error' );
    console.error('An error occurred in heroes component, navigating to login: ', error);
    }
  )
}
  save(solicitacao: Solicitacao): void {
    this.moduloColetaService.create(solicitacao)
    swal('Solicitação','Editada com sucesso!!'  ,'success' );
    //this.router.navigate(['modulo-coleta']);
  }

  onSubmit() { 
    this.submitted = true;
    this.save(this.solicitacao);
  }
}
