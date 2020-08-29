import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { CarEntitiesComponent } from './car-entities.component';

describe('CarEntitiesComponent', () => {
  let component: CarEntitiesComponent;
  let fixture: ComponentFixture<CarEntitiesComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ CarEntitiesComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(CarEntitiesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
