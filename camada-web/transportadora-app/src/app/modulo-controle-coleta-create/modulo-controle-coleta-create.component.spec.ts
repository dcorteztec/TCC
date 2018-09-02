import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ModuloControleColetaCreateComponent } from './modulo-controle-coleta-create.component';

describe('ModuloControleColetaCreateComponent', () => {
  let component: ModuloControleColetaCreateComponent;
  let fixture: ComponentFixture<ModuloControleColetaCreateComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ModuloControleColetaCreateComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ModuloControleColetaCreateComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
