import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Client } from './models/client';

@Injectable({
  providedIn: 'root'
})
export class RestapiService {

  username = "admin";
  password = "secret";

  httpOptions = {
    headers: new HttpHeaders({
      'Content-Type': 'application/json',
      Authorization: 'Basic ' + btoa(this.username + ":" + this.password)
    })
  };

  constructor(private http: HttpClient) { }

  public login(username: string, password: string) {
    const headers = new HttpHeaders({ Authorization: 'Basic ' + btoa(username + ":" + password) });
    return this.http.get("http://localhost:8080/api/v1/client", { headers, responseType: 'text' as 'json' });
  }

  public getClients() {
    const username = "admin";
    const password = "secret";
    const headers = new HttpHeaders({ Authorization: 'Basic ' + btoa(username + ":" + password) });
    return this.http.get<Client[]>("http://localhost:8080/api/v1/client", { headers });
  }

  public newClient(client: Client) {
    const username = "admin";
    const password = "secret";
    const headers = new HttpHeaders({ Authorization: 'Basic ' + btoa(username + ":" + password) });
    return this.http.post<any>("http://localhost:8080/api/v1/client", client, this.httpOptions);
  }

  public getClientById(clientID) {
    return this.http.get<Client>(`http://localhost:8080/api/v1/client/${clientID}`, this.httpOptions)
  }

  public updateById(client: Client) {
    const clientID = client.id;
    console.log(client);
    return this.http.put(`http://localhost:8080/api/v1/client/${clientID}`, client, this.httpOptions);
  }

  public deleteById(clientID) {
    return this.http.delete(`http://localhost:8080/api/v1/client/${clientID}`, this.httpOptions);
  }
}
