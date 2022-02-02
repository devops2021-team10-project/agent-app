import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AgentProductsComponent } from './agent-products.component';

describe('AgentProductsComponent', () => {
  let component: AgentProductsComponent;
  let fixture: ComponentFixture<AgentProductsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AgentProductsComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(AgentProductsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
