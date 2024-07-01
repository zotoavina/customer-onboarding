import { TestBed } from '@angular/core/testing';

import { DataReferenceServiceService } from './data-reference-service.service';

describe('DataReferenceServiceService', () => {
  let service: DataReferenceServiceService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(DataReferenceServiceService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
