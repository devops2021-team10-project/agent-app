import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AuthenticationService } from './services/security/authentication-service.service';
import { UserService } from './services/user.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit{
  title = 'frontend';

  constructor(private userService: UserService,
    private authService: AuthenticationService, public router: Router){


  }
  ngOnInit() {}

  loggedIn(): boolean {
    return this.authService.isLoggedIn();
  }

  login(): void {
    this.router.navigate(['/login']);
  }

  logout() {
    this.authService.logout();
    this.router.navigate(['/login']);
  }

  isAgent(){
    const roles = this.authService.getRoles();
    if(roles.includes('AGENT_ROLE')){
      return true;
    }else{
      return false;
    }
  }

  showAllAgentProducts(){
    this.router.navigate(['/agent_products']);
  }

  showReport(){
    this.router.navigate(['/report']);
  }
}
