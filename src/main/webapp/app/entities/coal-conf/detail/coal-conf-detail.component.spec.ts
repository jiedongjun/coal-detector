import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { CoalConfDetailComponent } from './coal-conf-detail.component';

describe('Component Tests', () => {
  describe('CoalConf Management Detail Component', () => {
    let comp: CoalConfDetailComponent;
    let fixture: ComponentFixture<CoalConfDetailComponent>;

    beforeEach(() => {
      TestBed.configureTestingModule({
        declarations: [CoalConfDetailComponent],
        providers: [
          {
            provide: ActivatedRoute,
            useValue: { data: of({ coalConf: { id: 123 } }) },
          },
        ],
      })
        .overrideTemplate(CoalConfDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(CoalConfDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load coalConf on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.coalConf).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
