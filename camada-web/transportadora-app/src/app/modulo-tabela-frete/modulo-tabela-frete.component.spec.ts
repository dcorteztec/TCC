import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ModuloTabelaFreteComponent } from './modulo-tabela-frete.component';

describe('ModuloTabelaFreteComponent', () => {
  let component: ModuloTabelaFreteComponent;
  let fixture: ComponentFixture<ModuloTabelaFreteComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ModuloTabelaFreteComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ModuloTabelaFreteComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
