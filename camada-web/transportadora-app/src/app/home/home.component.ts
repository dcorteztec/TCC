import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import {AuthenticationService} from 'src/app/authentication.service'

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  public usuario: String; 

  constructor(private authenticationService: AuthenticationService,private router: Router) { }

  ngOnInit() {
    this.usuario =  this.authenticationService.getLogin();
    console.log(this.authenticationService.getLogin())
  }

  logout() {
    this.authenticationService.logout(); 
    this.router.navigate(['/login']);
  }
  login() {
    this.router.navigate(['/login']);
  }  
}
