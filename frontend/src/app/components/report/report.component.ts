import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { OrderService } from 'src/app/services/order.service';
import { AuthenticationService } from 'src/app/services/security/authentication-service.service';

@Component({
  selector: 'app-report',
  templateUrl: './report.component.html',
  styleUrls: ['./report.component.css']
})
export class ReportComponent implements OnInit {


  public mostSoldProduct;
  public mostProfitableProduct;

  constructor(private authService: AuthenticationService,
    private router: Router, private orderService : OrderService) { }

  ngOnInit(): void {

    this.orderService.getMostSoldProduct(this.authService.getCurrentUser().username).subscribe(success => {this.mostSoldProduct = success});
    console.log(this.mostSoldProduct);
    this.orderService.getMostProfitProduct(this.authService.getCurrentUser().username).subscribe(success => {this.mostProfitableProduct = success});
  }

}
