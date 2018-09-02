import { TestBed, inject } from '@angular/core/testing';

import { ModuloColetaServiceService } from './modulo-coleta-service.service';

describe('ModuloColetaServiceService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [ModuloColetaServiceService]
    });
  });

  it('should be created', inject([ModuloColetaServiceService], (service: ModuloColetaServiceService) => {
    expect(service).toBeTruthy();
  }));
});
