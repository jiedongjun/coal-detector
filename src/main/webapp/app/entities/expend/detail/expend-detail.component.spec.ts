import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { ExpendDetailComponent } from './expend-detail.component';

describe('Component Tests', () => {
  describe('Expend Management Detail Component', () => {
    let comp: ExpendDetailComponent;
    let fixture: ComponentFixture<ExpendDetailComponent>;

    beforeEach(() => {
      TestBed.configureTestingModule({
        declarations: [ExpendDetailComponent],
        providers: [
          {
            provide: ActivatedRoute,
            useValue: { data: of({ expend: { id: 123 } }) },
          },
        ],
      })
        .overrideTemplate(ExpendDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(ExpendDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load expend on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.expend).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
