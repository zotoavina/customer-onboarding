import { ComponentFixture, TestBed } from '@angular/core/testing';

import { OnboardingFormComponent } from './onboarding-form.component';

describe('OnboardingFormComponent', () => {
  let component: OnboardingFormComponent;
  let fixture: ComponentFixture<OnboardingFormComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [OnboardingFormComponent]
    });
    fixture = TestBed.createComponent(OnboardingFormComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
