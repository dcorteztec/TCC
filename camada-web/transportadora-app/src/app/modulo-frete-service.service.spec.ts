import { TestBed, inject } from '@angular/core/testing';

import { ModuloFreteServiceService } from './modulo-frete-service.service';

describe('ModuloFreteServiceService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [ModuloFreteServiceService]
    });
  });

  it('should be created', inject([ModuloFreteServiceService], (service: ModuloFreteServiceService) => {
    expect(service).toBeTruthy();
  }));
});
