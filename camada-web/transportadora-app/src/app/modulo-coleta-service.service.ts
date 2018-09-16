import { Injectable } from '@angular/core';
import { Headers, Http } from "@angular/http";
import { AuthenticationService } from './authentication.service';;
import 'rxjs/add/operator/toPromise';
import 'rxjs/add/operator/catch';
import 'rxjs/add/observable/throw';
import 'rxjs/add/operator/map';

import { Solicitacao } from './Solicitacao'
import { TransportadoraParceira } from './TransportadoraParceira'
import { EmpresaParceira } from './EmpresaParceira'


@Injectable({
  providedIn: 'root'
})
export class ModuloColetaServiceService {

  constructor(
    private http: Http,
    private authenticationService: AuthenticationService) {
  }

  private headers = new Headers({
    'Content-Type': 'application/json',
    'Authorization': 'Bearer ' + this.authenticationService.getToken()
  });

  private listSolic = 'http://localhost:8080/modulo-coleta/listSolic';
  private saveUrl = 'http://localhost:8080/modulo-coleta/saveSolic';
  private updateUrl = 'http://localhost:8080/modulo-coleta/update';
  private deleteteUrl = 'http://localhost:8080/modulo-coleta/delete';
  private listTransp = 'http://localhost:8080/modulo-coleta/listTransportadora';
  private listEmpresas = 'http://localhost:8080/modulo-coleta/listEmpresaParceira';
  private getSolicitacao = 'http://localhost:8080/modulo-coleta/solicitacao';
  

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

findAllEmpresaParceira(): Promise<EmpresaParceira[]> {
  return this.http
   .get(this.listEmpresas, {headers: this.headers})
   .toPromise()
   .then(response => response.json() as EmpresaParceira[])
   .catch(this.handleError);

}

private handleError(error: any): Promise<any> {
  console.error('An error occurred: ', error); 
  return Promise.reject(error.message || error);
}
 
getSolicitcao(id: number): Promise<Solicitacao> {
  return this.findAll()
    .then(solicitacoes => solicitacoes.find(solicitacao => solicitacao.id === id))
}

findByIdSolicitacao(id: number): Promise<Solicitacao> {
  const url = `${this.getSolicitacao}/${id}`;
  console.log(`solicitacao.findByIdSolicitacao - get ${id}`);
  return this.http
   .get(url, {headers: this.headers})
   .toPromise()
   .then(response => response.json() as Solicitacao)
   .catch(this.handleError);
}

create(solicitacao: Solicitacao): Promise<Solicitacao> {
  return this.http
    .post(this.saveUrl, JSON.stringify(solicitacao), {headers: this.headers})
    .toPromise()
    .then(res => res.json())
    .catch(this.handleError)
}

update(solicitacao: Solicitacao): Promise<Solicitacao> {
  const url = `${this.updateUrl}/${solicitacao.id}`;
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
 