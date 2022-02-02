import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { ProductService } from 'src/app/services/product.service';
import { AuthenticationService } from 'src/app/services/security/authentication-service.service';

@Component({
  selector: 'app-agent-products',
  templateUrl: './agent-products.component.html',
  styleUrls: ['./agent-products.component.css']
})
export class AgentProductsComponent implements OnInit {

  public agent_products = [];


  public chosenProduct;
  public updatedProduct;
  public productUpdateChosen = false;

  public createdProduct;
  public productCreationChosen = false;

  

  constructor(private productService: ProductService, private router: Router, private authService : AuthenticationService) {
    this.updatedProduct = {};
    this.createdProduct = {};
   }

  ngOnInit(): void {

    this.productService.getAgentProducts(this.authService.getCurrentUser().username).subscribe(success => {this.setAgentProducts(success)});
  }

  setAgentProducts(data){
    if (data.length !== 0){
      this.agent_products = data;
      console.log(this.agent_products)
    }
  }

  onDelete(productId){
    this.agent_products = this.agent_products.filter(ap => ap.id != productId);
    this.productService.deleteProduct(productId).subscribe();
  }

  onUpdate(product) {
    this.productCreationChosen = false;
    this.chosenProduct = product;
    this.productUpdateChosen = true;
  }

  cancel() {
    this.productUpdateChosen = false;
    this.productCreationChosen = false;
  }

  updateProduct() {
    this.updatedProduct.name = this.chosenProduct.name;
    this.updatedProduct.price = this.chosenProduct.price;
    this.updatedProduct.inStock = this.chosenProduct.inStock;
    this.updatedProduct.agentUsername = this.chosenProduct.agent.username;
    this.productService.updateProduct(this.chosenProduct.id, this.updatedProduct).subscribe();
    this.productUpdateChosen = false;
  }

  onCreate() {
    this.productUpdateChosen = false;
    this.productCreationChosen = true;
  }

  createProduct() {
    this.createdProduct.agentUsername = this.authService.getCurrentUser().username;
    this.productService.createProduct(this.createdProduct).subscribe();
    location.reload();
  }
}
