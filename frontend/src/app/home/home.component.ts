import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Client } from '../models/client';
import { RestapiService } from '../restapi.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  clients: Client[];
  searchText;

  constructor(private service: RestapiService,
              private router: Router) { }

  ngOnInit(): void {
    this.getClients();
  }

  getClients() {
    this.service.getClients().subscribe(data => {
      this.clients = data;
    })
  }

  newClient() {
    this.router.navigate(["new-client"]);
  }

  alterar(client: Client) {
    this.router.navigate([`alter-client/${client.id}`]);
  }

  remover(id) {
    this.service.deleteById(id).subscribe(data => {
      this.getClients();
    });
  }

  show() {
    console.log('opa');
  }

}
