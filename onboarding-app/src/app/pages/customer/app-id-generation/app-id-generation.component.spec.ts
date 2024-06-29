import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AppIdGenerationComponent } from './app-id-generation.component';

describe('AppIdGenerationComponent', () => {
  let component: AppIdGenerationComponent;
  let fixture: ComponentFixture<AppIdGenerationComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [AppIdGenerationComponent]
    });
    fixture = TestBed.createComponent(AppIdGenerationComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
