import { Component, OnInit } from '@angular/core';
import {AuthenticationService} from 'src/app/authentication.service'
import { Router, ActivatedRoute, NavigationExtras } from '@angular/router';

import { ModuloFreteServiceService } from '../modulo-frete-service.service'

import { Frete } from '../Frete'

@Component({
  selector: 'app-modulo-tabela-frete',
  templateUrl: './modulo-tabela-frete.component.html',
  styleUrls: ['./modulo-tabela-frete.component.css'],
  providers: [ModuloFreteServiceService]
})
export class ModuloTabelaFreteComponent implements OnInit {
  
  fretes: Frete[];
  frete: Frete 
  public searchString: string; 
  constructor(private router: Router,private authenticationService: AuthenticationService,
  private moduloFreteServiceService:ModuloFreteServiceService) { }

  ngOnInit() {
    this.findAll()
  }

  findAll(): void {
    this.moduloFreteServiceService.findAll()
    .then(
      fretes => this.fretes = fretes,
      error => {
      this.router.navigate(['login']);
      console.error('An error occurred in ModuloTabelaFreteComponent component, navigating to login: ', error);
      }
    )
  }
  logout() {
    this.authenticationService.logout();
    this.router.navigate(['/login']);
  }

  redirectNovoFretePage() {
    this.router.navigate(['/modulo-frete-create']);
  } 
  
  editFretePage(frete: Frete) {
    if (frete) {
      this.frete = frete;
      this.router.navigate(['/modulo-frete-edit', frete.id]);
    }
  }
  
  onSelect(frete: Frete): void {
    this.frete = frete;
  }
  
  deleteFrete(frete: Frete): void {
      this.moduloFreteServiceService
      .delete(frete.id)
      .then(() => {
          this.fretes = this.fretes.filter(h => h !== frete);
          if(this.frete === frete) {
            this.frete = null;
        }
      });
  }

}
