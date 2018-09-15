import { Injectable } from '@angular/core';
import { Headers, Http } from "@angular/http";
import { AuthenticationService } from './authentication.service';
import 'rxjs/add/operator/toPromise';
import 'rxjs/add/operator/catch';
import 'rxjs/add/observable/throw';
import 'rxjs/add/operator/map';

import { Frete } from './Frete';
import { SimulcaoFreteDTO } from './SimulcaoFreteDTO';

@Injectable({
  providedIn: 'root'
})
export class ModuloFreteServiceService {

  constructor(
    private http: Http,
    private authenticationService: AuthenticationService) {
  }

  private headers = new Headers({ 
    'Content-Type': 'application/json',
    'Authorization': 'Bearer ' + this.authenticationService.getToken()
  });

  private listFrete = 'http://localhost:8080/modulo-frete/listFrete';
  private saveUrl = 'http://localhost:8080/modulo-frete/saveFrete';
  private updateUrl = 'http://localhost:8080/modulo-frete/update';
  private deleteteUrl = 'http://localhost:8080/modulo-frete/delete';
  private urlFrete = 'http://localhost:8080/modulo-frete/frete';
  private urlUltimoKinical = 'http://localhost:8080/modulo-frete/ultimoKinical';
  private urlSimulacao = 'http://localhost:8080/modulo-frete/simulacaoFrete'

  findAll(): Promise<Frete[]> {
    return this.http
     .get(this.listFrete, {headers: this.headers})
     .toPromise()
     .then(response => response.json() as Frete[])
     .catch(this.handleError);
  
  }

  getFrete(id: number): Promise<Frete> {
    return this.findAll()
      .then(fretes => fretes.find(frete => frete.id === id))
  }

  private handleError(error: any): Promise<any> {
    console.error('An error occurred: ', error); 
    return Promise.reject(error.message || error);
  }

  findByIdFrete(id: number): Promise<Frete> {
    const url = `${this.urlFrete}/${id}`;
    console.log(`frete.findByIdFrete - get ${id}`);
    return this.http
     .get(url, {headers: this.headers})
     .toPromise()
     .then(response => response.json() as Frete)
     .catch(this.handleError);
  }

  findUltimoKmInicial(): Promise<number> {
    console.log(`frete.findUltimoKmInicial - get`);
    return this.http
     .get(this.urlUltimoKinical, {headers: this.headers})
     .toPromise()
     .then(response => response.json() as number)
     .catch(this.handleError);
  }

  create(frete: Frete): Promise<Frete> {
    return this.http
      .post(this.saveUrl, JSON.stringify(frete), {headers: this.headers})
      .toPromise()
      .then(res => res.json())
      .catch(this.handleError)
  }

  simular(simulacaoFrete: SimulcaoFreteDTO): Promise<SimulcaoFreteDTO> {
    return this.http
      .post(this.urlSimulacao, JSON.stringify(simulacaoFrete), {headers: this.headers})
      .toPromise()
      .then(res => res.json())
      .catch(this.handleError)
  }

  update(frete: Frete): Promise<Frete> {
    const url = `${this.updateUrl}/${frete.id}`;
    return this.http
      .put(url, JSON.stringify(Frete), {headers: this.headers})
      .toPromise()
      .then(() => Frete)
      .catch(this.handleError);
  }

  delete(id: number): Promise<void> {
    console.log(`solicitacao.service - deleting ${id}`);
    const url = `${this.deleteteUrl}/${id}`;
    return this.http
      .delete(url, {headers: this.headers})
      .toPromise()
      .then(() => null)
      .catch(this.handleError);
  }
}
