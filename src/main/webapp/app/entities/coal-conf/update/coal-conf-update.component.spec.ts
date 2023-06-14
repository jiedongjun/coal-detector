jest.mock('@angular/router');

import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { of, Subject } from 'rxjs';

import { CoalConfService } from '../service/coal-conf.service';
import { ICoalConf, CoalConf } from '../coal-conf.model';

import { CoalConfUpdateComponent } from './coal-conf-update.component';

describe('Component Tests', () => {
  describe('CoalConf Management Update Component', () => {
    let comp: CoalConfUpdateComponent;
    let fixture: ComponentFixture<CoalConfUpdateComponent>;
    let activatedRoute: ActivatedRoute;
    let coalConfService: CoalConfService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
        declarations: [CoalConfUpdateComponent],
        providers: [FormBuilder, ActivatedRoute],
      })
        .overrideTemplate(CoalConfUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(CoalConfUpdateComponent);
      activatedRoute = TestBed.inject(ActivatedRoute);
      coalConfService = TestBed.inject(CoalConfService);

      comp = fixture.componentInstance;
    });

    describe('ngOnInit', () => {
      it('Should update editForm', () => {
        const coalConf: ICoalConf = { id: 456 };

        activatedRoute.data = of({ coalConf });
        comp.ngOnInit();

        expect(comp.editForm.value).toEqual(expect.objectContaining(coalConf));
      });
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', () => {
        // GIVEN
        const saveSubject = new Subject();
        const coalConf = { id: 123 };
        spyOn(coalConfService, 'update').and.returnValue(saveSubject);
        spyOn(comp, 'previousState');
        activatedRoute.data = of({ coalConf });
        comp.ngOnInit();

        // WHEN
        comp.save();
        expect(comp.isSaving).toEqual(true);
        saveSubject.next(new HttpResponse({ body: coalConf }));
        saveSubject.complete();

        // THEN
        expect(comp.previousState).toHaveBeenCalled();
        expect(coalConfService.update).toHaveBeenCalledWith(coalConf);
        expect(comp.isSaving).toEqual(false);
      });

      it('Should call create service on save for new entity', () => {
        // GIVEN
        const saveSubject = new Subject();
        const coalConf = new CoalConf();
        spyOn(coalConfService, 'create').and.returnValue(saveSubject);
        spyOn(comp, 'previousState');
        activatedRoute.data = of({ coalConf });
        comp.ngOnInit();

        // WHEN
        comp.save();
        expect(comp.isSaving).toEqual(true);
        saveSubject.next(new HttpResponse({ body: coalConf }));
        saveSubject.complete();

        // THEN
        expect(coalConfService.create).toHaveBeenCalledWith(coalConf);
        expect(comp.isSaving).toEqual(false);
        expect(comp.previousState).toHaveBeenCalled();
      });

      it('Should set isSaving to false on error', () => {
        // GIVEN
        const saveSubject = new Subject();
        const coalConf = { id: 123 };
        spyOn(coalConfService, 'update').and.returnValue(saveSubject);
        spyOn(comp, 'previousState');
        activatedRoute.data = of({ coalConf });
        comp.ngOnInit();

        // WHEN
        comp.save();
        expect(comp.isSaving).toEqual(true);
        saveSubject.error('This is an error!');

        // THEN
        expect(coalConfService.update).toHaveBeenCalledWith(coalConf);
        expect(comp.isSaving).toEqual(false);
        expect(comp.previousState).not.toHaveBeenCalled();
      });
    });
  });
});
