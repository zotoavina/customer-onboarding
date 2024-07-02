import { TestBed } from '@angular/core/testing';

import { ApplicationSelectionService } from './application-selection.service';

describe('ApplicationSelectionService', () => {
  let service: ApplicationSelectionService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(ApplicationSelectionService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
