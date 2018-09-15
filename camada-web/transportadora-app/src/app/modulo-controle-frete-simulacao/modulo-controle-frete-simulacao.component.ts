import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import {AuthenticationService} from 'src/app/authentication.service'
import { SimulcaoFreteDTO } from '../SimulcaoFreteDTO'
import {ModuloFreteServiceService } from 'src/app/modulo-frete-service.service'
import swal from 'sweetalert2';

@Component({
  selector: 'app-modulo-controle-frete-simulacao',
  templateUrl: './modulo-controle-frete-simulacao.component.html',
  styleUrls: ['./modulo-controle-frete-simulacao.component.css'],
  providers: [ModuloFreteServiceService]
})
export class ModuloControleFreteSimulacaoComponent implements OnInit {

  submitted = false;
  simulcaoFrete: SimulcaoFreteDTO = new SimulcaoFreteDTO();
  simulacaoFreteRes:SimulcaoFreteDTO

  constructor(private router: Router, private rota: ActivatedRoute, 
    private moduloFreteServiceService:ModuloFreteServiceService,
    private authenticationService: AuthenticationService) { }

  ngOnInit() {
  }

  logout() {
    this.authenticationService.logout();
    this.router.navigate(['/login']);
  } 

  simular(simulcaoFrete: SimulcaoFreteDTO): void {
    this.moduloFreteServiceService.simular(simulcaoFrete).then(
      simulacao =>{
        this.simulacaoFreteRes = simulacao
        var valor:string;
        swal('Simulação de Frete','Valor da Simulação foi de R$'+ this.simulacaoFreteRes.valorFrete.toString()+',00'  ,'success' );
      } 
    )
  }

  onSubmit() { 
    this.submitted = true;
    this.simular(this.simulcaoFrete);
  }
}
