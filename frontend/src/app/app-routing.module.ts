import { NgModule } from '@angular/core';
import { Routes, RouterModule, ActivatedRouteSnapshot, RouterStateSnapshot } from '@angular/router';
import { HomeComponent } from './home/home.component';
import { LoginComponent } from './login/login.component';
import { NewClientComponent } from './new-client/new-client.component';

const routes: Routes = [
  { path: "", redirectTo: "login", pathMatch: "full" },
  { path: "login", component: LoginComponent },
  { path: "home", component: HomeComponent },
  { path: "new-client", component: NewClientComponent },
  { path: "alter-client/:id", component: NewClientComponent },
  { path: 'source',
  component: HomeComponent,
  resolve: {
      url: 'externalUrlRedirectResolver'
  },
  data: {
      externalUrl: 'https://github.com/tvsjazz/cadastroDeClientes'
  } }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
  providers: [
    {
      provide: 'externalUrlRedirectResolver',
            useValue: (route: ActivatedRouteSnapshot, state: RouterStateSnapshot) =>
            {
                window.location.href = (route.data as any).externalUrl;
            }
    }
  ]
})
export class AppRoutingModule { }
