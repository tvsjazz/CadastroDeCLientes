import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { Client } from '../models/client';
import { RestapiService } from '../restapi.service';

@Component({
  selector: 'app-new-client',
  templateUrl: './new-client.component.html',
  styleUrls: ['./new-client.component.css']
})
export class NewClientComponent implements OnInit {

  clientForm: FormGroup;
  id;
  cliente: Client;
  mensagem: any;

  constructor(private service: RestapiService,
              private formBuilder: FormBuilder,
              private router: Router,
              private route: ActivatedRoute) { }

  ngOnInit(): void {
    this.buildForm();
    this.hasClient();

  }

  hasClient() {
    this.id = this.route.snapshot.paramMap.get('id');
    if (this.id) {
      this.service.getClientById(this.id).subscribe(data => {
        this.cliente = data;
        this.populateForm();
      })
    } else {
      return;
    }
  }

  buildForm() {
    this.clientForm = this.formBuilder.group({
      nome: ['', [Validators.required]],
      sexo: [''],
      email: ['', Validators.email],
      dataNascimento: ['', [Validators.required]],
      naturalidade: [''],
      nacionalidade: [''],
      cpf: ['', [Validators.required]],
      dataCadastro: [null]
    });
  }

  saveClient() {
    this.service.newClient(this.clientForm.value).subscribe(data => {
      console.log(data);
      this.router.navigate(['home']);
    },
    error => {
      alert(error.error.errors);
    })
  }

  alterClient() {
    const client: Client = this.clientForm.value;
    const id = this.route.snapshot.paramMap.get('id');
    client.id = id;
    this.service.updateById(client).subscribe(data => {
      console.log(data);
      this.router.navigate(['home']);
    });
  }

  populateForm() {
    this.clientForm.setValue({
      nome: this.cliente.nome,
      sexo: this.cliente.sexo,
      email: this.cliente.email,
      dataNascimento: this.cliente.dataNascimento,
      naturalidade: this.cliente.naturalidade,
      nacionalidade: this.cliente.nacionalidade,
      cpf: this.cliente.cpf,
      dataCadastro: this.cliente.dataCadastro
    })
  }

}
