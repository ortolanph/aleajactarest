import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { DiceNotationComponent } from './dice-notation.component';

describe('DiceNotationComponent', () => {
  let component: DiceNotationComponent;
  let fixture: ComponentFixture<DiceNotationComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ DiceNotationComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(DiceNotationComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
