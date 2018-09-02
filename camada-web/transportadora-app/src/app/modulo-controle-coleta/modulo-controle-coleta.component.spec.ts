import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ModuloControleColetaComponent } from './modulo-controle-coleta.component';

describe('ModuloControleColetaComponent', () => {
  let component: ModuloControleColetaComponent;
  let fixture: ComponentFixture<ModuloControleColetaComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ModuloControleColetaComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ModuloControleColetaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
