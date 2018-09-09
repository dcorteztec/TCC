import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ModuloControleTabelaCreateComponent } from './modulo-controle-tabela-create.component';

describe('ModuloControleTabelaCreateComponent', () => {
  let component: ModuloControleTabelaCreateComponent;
  let fixture: ComponentFixture<ModuloControleTabelaCreateComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ModuloControleTabelaCreateComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ModuloControleTabelaCreateComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
