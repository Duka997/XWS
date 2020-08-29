import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { CommentAndGradesComponent } from './comment-and-grades.component';

describe('CommentAndGradesComponent', () => {
  let component: CommentAndGradesComponent;
  let fixture: ComponentFixture<CommentAndGradesComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ CommentAndGradesComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(CommentAndGradesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
