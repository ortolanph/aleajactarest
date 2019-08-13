import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { DiceCustomComponent } from './dice-custom.component';

describe('DiceCustomComponent', () => {
  let component: DiceCustomComponent;
  let fixture: ComponentFixture<DiceCustomComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ DiceCustomComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(DiceCustomComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
