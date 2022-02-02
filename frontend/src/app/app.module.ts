import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { FormsModule, ReactiveFormsModule } from '@angular/forms'

import {RouterModule, Routes} from '@angular/router';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HttpClientModule } from '@angular/common/http';

import { AuthGuardGuard }  from './services/security/auth-guard.guard';
import { JwtUtilsService } from './services/security/jwt-utils.service';
import { AuthenticationService } from './services/security/authentication-service.service';
import { HTTP_INTERCEPTORS } from '@angular/common/http';
import { TokenInterceptorService } from './services/security/token-interceptor.service';
import { LoginGuardGuard } from './services/security/login-guard.guard';

import { UserService } from './services/user.service';
import { LoginComponent } from './components/login/login.component';
import { RegisterComponent } from './components/register/register.component';
import { MainComponent } from './components/main/main.component';
import { AgentProductsComponent } from './components/agent-products/agent-products.component';
import { ProductService } from './services/product.service';
import { OrderService } from './services/order.service';
import { ReportComponent } from './components/report/report.component';

const appRoutes: Routes = [
  { path: 'login',
    component: LoginComponent,
    canActivate : [LoginGuardGuard]
  },
  {
    path: 'register',
    component: RegisterComponent
  },
  {
    path: 'main',
    component: MainComponent
  },
  {
    path: 'agent_products',
    component: AgentProductsComponent
  },
  {
    path: 'report',
    component: ReportComponent
  }
]

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    RegisterComponent,
    MainComponent,
    AgentProductsComponent,
    ReportComponent
  ],
  imports: [
    BrowserModule,
    RouterModule.forRoot(
        appRoutes,
        { enableTracing: true}),
    FormsModule,
    HttpClientModule,
    ReactiveFormsModule,
    AppRoutingModule,
  ],
  providers: [
    AuthGuardGuard,
    JwtUtilsService,
    {
      provide: HTTP_INTERCEPTORS,
      useClass: TokenInterceptorService,
      multi: true
    },
    AuthenticationService,
    UserService,
    ProductService,
    OrderService
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
