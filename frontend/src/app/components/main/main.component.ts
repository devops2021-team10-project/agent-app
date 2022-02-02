import { Component, OnInit, Output, EventEmitter } from '@angular/core';
import { UserService } from 'src/app/services/user.service';
import { AuthenticationService } from 'src/app/services/security/authentication-service.service';
import { Router } from '@angular/router';
import { ProductService } from 'src/app/services/product.service';
import { OrderService } from 'src/app/services/order.service';

@Component({
  selector: 'app-main',
  templateUrl: './main.component.html',
  styleUrls: ['./main.component.css']
})
export class MainComponent implements OnInit {

  public user;
  public isBuyer;

  public allProducts = [];
  public cart = [];
  public totalSum = 0;

  public buying = false;

  public order;
  public buyerInfo;

  constructor(private productService: ProductService, private authService: AuthenticationService,
    private router: Router, private orderService : OrderService) {
      this.order = {};
      this.buyerInfo = {};
    }

  ngOnInit(): void {
    this.user = this.authService.getCurrentUser();
    this.isBuyer = this.user == null? true : false;

    this.productService.getAllProducts().subscribe(success => {this.setAllProducts(success)});
  }

  setAllProducts(data){
    if (data.length !== 0){
      this.allProducts = data;
    }
  }

  addToCart(product) {
    if (product.inStock > 0 ) {
      this.cart.push(product);
      this.totalSum += product.price;
      product.inStock--;
    }
  }

  removeFromCart(product) {
    product.inStock++;
    const index = this.cart.indexOf(product, 0);
      if (index > -1) {
        this.cart.splice(index, 1);
      }
    this.totalSum -= product.price;
    this.buying = false;
  }

  dropCart() {
    this.cart.forEach(pr => {
      this.removeFromCart(pr);
    });
  }

  checkoutCart() {
    this.buying = true;
  }

  cancel() {
    this.buying = false;
  }

  buy() {
    var boughtProducts = this.cart.map(function(p) {return p.name;});
    var buyerInfo = "First Name: " + this.buyerInfo.firstName + "\n"
                    + "Last Name: " + this.buyerInfo.lastName + "\n"
                    + "Email: " + this.buyerInfo.email + "\n"
                    + "Address " + this.buyerInfo.address;
    
    this.order.boughtProducts = boughtProducts;
    this.order.buyerInfo = buyerInfo;
    this.orderService.newOrder(this.order).subscribe();
    location.reload();
  }

}
