import { Injectable } from '@angular/core';
import { Headers, Http } from "@angular/http";
import { AuthenticationService } from './authentication.service';
import { Observable } from 'rxjs/Observable';
import 'rxjs/add/operator/toPromise';
import { map } from 'rxjs/operators'; 
import 'rxjs/add/operator/catch';
import 'rxjs/add/observable/throw';
import 'rxjs/add/operator/map';

import { Solicitacao } from './Solicitacao'
import { TransportadoraParceira } from './TransportadoraParceira'

@Injectable({
  providedIn: 'root'
})
export class ModuloColetaServiceService {

  private listSolic = 'http://localhost:8080/modulo-coleta/listSolic';
  private saveUrl = 'http://localhost:8080/modulo-coleta/saveSolic';
  private updateUrl = 'http://localhost:8080/modulo-coleta/update';
  private deleteteUrl = 'http://localhost:8080/modulo-coleta/delete';
  private listTransp = 'http://localhost:8080/modulo-coleta/listTransportadora';

  private headers = new Headers({
    'Content-Type': 'application/json',
    'Authorization': 'Bearer ' + this.authenticationService.getToken()
    });

 constructor(
   private http: Http,
   private authenticationService: AuthenticationService) {
 }
 findAll(): Promise<Solicitacao[]> {
  return this.http
   .get(this.listSolic, {headers: this.headers})
   .toPromise()
   .then(response => response.json() as Solicitacao[])
   .catch(this.handleError);

}

findAllTransportadora(): Promise<TransportadoraParceira[]> {
  return this.http
   .get(this.listTransp, {headers: this.headers})
   .toPromise()
   .then(response => response.json() as TransportadoraParceira[])
   .catch(this.handleError);

}

private handleError(error: any): Promise<any> {
  console.error('An error occurred: ', error); // for demo only
  return Promise.reject(error.message || error);
}
 
getSolicitcao(id: number): Promise<Solicitacao> {
  return this.findAll()
    .then(solicitacoes => solicitacoes.find(solicitacao => solicitacao.numeroSolic === id))
}

create(solicitacao: Solicitacao): Promise<Solicitacao> {
  return this.http
    .post(this.saveUrl, JSON.stringify(solicitacao), {headers: this.headers})
    .toPromise()
    .then(res => res.json())
    .catch(this.handleError)
}

update(solicitacao: Solicitacao): Promise<Solicitacao> {
  const url = `${this.updateUrl}/${solicitacao.numeroSolic}`;
  return this.http
    .put(url, JSON.stringify(Solicitacao), {headers: this.headers})
    .toPromise()
    .then(() => Solicitacao)
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
 