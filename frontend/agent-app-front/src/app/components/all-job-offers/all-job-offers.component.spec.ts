import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AllJobOffersComponent } from './all-job-offers.component';

describe('AllJobOffersComponent', () => {
  let component: AllJobOffersComponent;
  let fixture: ComponentFixture<AllJobOffersComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AllJobOffersComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(AllJobOffersComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
