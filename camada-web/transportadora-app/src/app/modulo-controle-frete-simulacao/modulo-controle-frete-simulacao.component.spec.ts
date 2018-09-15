import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ModuloControleFreteSimulacaoComponent } from './modulo-controle-frete-simulacao.component';

describe('ModuloControleFreteSimulacaoComponent', () => {
  let component: ModuloControleFreteSimulacaoComponent;
  let fixture: ComponentFixture<ModuloControleFreteSimulacaoComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ModuloControleFreteSimulacaoComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ModuloControleFreteSimulacaoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
