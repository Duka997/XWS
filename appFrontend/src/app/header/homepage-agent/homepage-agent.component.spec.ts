import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { HomepageAgentComponent } from './homepage-agent.component';

describe('HomepageAgentComponent', () => {
  let component: HomepageAgentComponent;
  let fixture: ComponentFixture<HomepageAgentComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ HomepageAgentComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(HomepageAgentComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
