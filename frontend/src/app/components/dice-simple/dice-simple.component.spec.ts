import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { DiceSimpleComponent } from './dice-simple.component';

describe('DiceSimpleComponent', () => {
  let component: DiceSimpleComponent;
  let fixture: ComponentFixture<DiceSimpleComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ DiceSimpleComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(DiceSimpleComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
