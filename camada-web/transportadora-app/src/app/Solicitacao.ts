import { TransportadoraParceira } from './TransportadoraParceira'

export class Solicitacao {

    id:number;
    dataSolic: string;
    dataVolta: string;
    nomeSolic: string;
    emailSolic: string;
    endeSolic: string;
    veiculo: string;
    demandaTransferida: Boolean;
    cidade:string;
    uf:string;
    transportadoraParceira:TransportadoraParceira;
    idTransportadora:number;
    idEmpresa:number;

    //constructor(numeroSolic: number, dataSolic: Date, dataVolta: Date, nomeSolic: string,
     //   emailSolic:string,endeSolic:string,veiculo:string,demandaTransferida:boolean,
     // cidade:string,uf:string){
      //this.numeroSolic = numeroSolic;
      //this.dataSolic = dataSolic;
      //this.dataVolta = dataVolta;
      //this.nomeSolic = nomeSolic;
      //this.emailSolic = emailSolic;
      //this.endeSolic = endeSolic;
      //this.veiculo = veiculo;
      //this.demandaTransferida = demandaTransferida;
      //this.cidade = cidade;
      //this.uf = uf;
    //}
  
  }