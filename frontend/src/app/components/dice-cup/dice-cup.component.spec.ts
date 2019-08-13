import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { DiceCupComponent } from './dice-cup.component';

describe('DiceCupComponent', () => {
  let component: DiceCupComponent;
  let fixture: ComponentFixture<DiceCupComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ DiceCupComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(DiceCupComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
