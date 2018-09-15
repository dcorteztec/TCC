import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';

import { AuthenticationService } from 'src/app/authentication.service'
import { ModuloFreteServiceService } from 'src/app/modulo-frete-service.service'
import swal from 'sweetalert2';
import { Frete } from '../Frete'

@Component({
  selector: 'app-modulo-controle-tabela-create',
  templateUrl: './modulo-controle-tabela-create.component.html',
  styleUrls: ['./modulo-controle-tabela-create.component.css'],
  providers: [ModuloFreteServiceService]
})
export class ModuloControleTabelaCreateComponent implements OnInit {

  submitted = false;
  frete: Frete = new Frete();

  constructor(private router: Router, private rota: ActivatedRoute, 
    private moduloFreteServiceService:ModuloFreteServiceService,
    private authenticationService: AuthenticationService) { }

  ngOnInit() {
    this.rota.params.subscribe((parametro) => {
      if(parametro.id){  
      this.moduloFreteServiceService.findByIdFrete(parametro.id).then(res => 
        {this.frete = res});
      }else{
        this.moduloFreteServiceService.findUltimoKmInicial().then(res=>{
          this.frete.kmInicial = res+1;
        })
      }  
      });
  }

  save(frete: Frete): void {
    this.moduloFreteServiceService.create(frete)
    swal('Valor de Frete','Cadastrada ou Editada com sucesso!!'  ,'success' );
    //this.router.navigate(['modulo-frete']);
  }

  onSubmit() {
    this.submitted = true;
    this.save(this.frete);
  }

  logout() {
    this.authenticationService.logout();
    this.router.navigate(['/login']);
  }
   
}
