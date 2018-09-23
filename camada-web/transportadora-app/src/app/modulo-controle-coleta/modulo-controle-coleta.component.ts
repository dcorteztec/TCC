import { Component, OnInit } from '@angular/core';
import { Router, NavigationExtras } from '@angular/router';
import { Solicitacao } from '../Solicitacao'
import { ModuloColetaServiceService } from '../modulo-coleta-service.service'
import {AuthenticationService} from 'src/app/authentication.service'
import swal from 'sweetalert2';
 
@Component({
  selector: 'app-modulo-controle-coleta',
  templateUrl: './modulo-controle-coleta.component.html',
  styleUrls: ['./modulo-controle-coleta.component.css'],
  providers: [ModuloColetaServiceService]
})
export class ModuloControleColetaComponent implements OnInit {

  solicitacoes: Solicitacao[];
  solicitacao: Solicitacao; 
  public searchString: string; 
  constructor(private router: Router, private moduloColetaService: ModuloColetaServiceService,
    private authenticationService: AuthenticationService) { }

  ngOnInit() {
    this.findAll();
  }

  logout() {
    this.authenticationService.logout();
    this.router.navigate(['/login']);
  } 

  findAll(): void {
    this.moduloColetaService.findAll()
    .then(
      solicitacoes => this.solicitacoes = solicitacoes,
      error => {
      this.router.navigate(['login']);
      console.error('An error occurred in ModuloControleColetaComponent component, navigating to login: ', error);
      }
    )
}

redirectNovaSolicitacaoPage() {
  this.router.navigate(['/modulo-coleta-create']);
}

editSolicitacaoPage(solicitacao: Solicitacao) {
  if (solicitacao) {
    this.solicitacao = solicitacao;
    let navigationExtras: NavigationExtras = {
      queryParams: this.solicitacoes,
    };
    this.router.navigate(['/modulo-coleta-edit', solicitacao.id]);
  }
}

onSelect(solicitacao: Solicitacao): void {
  this.solicitacao = solicitacao;
}

deleteSolicitacao(solicitacao: Solicitacao): void {
    this.moduloColetaService
    .delete(solicitacao.id)
    .then(() => {
        this.solicitacoes = this.solicitacoes.filter(h => h !== solicitacao);
        if(this.solicitacao === solicitacao) {
          this.solicitacao = null;
      } 
    });
    swal('Solicitação','Deletada com sucesso!!','success' ); 
}
}
