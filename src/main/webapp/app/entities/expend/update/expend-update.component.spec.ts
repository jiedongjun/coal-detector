jest.mock('@angular/router');

import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { of, Subject } from 'rxjs';

import { ExpendService } from '../service/expend.service';
import { IExpend, Expend } from '../expend.model';

import { ExpendUpdateComponent } from './expend-update.component';

describe('Component Tests', () => {
  describe('Expend Management Update Component', () => {
    let comp: ExpendUpdateComponent;
    let fixture: ComponentFixture<ExpendUpdateComponent>;
    let activatedRoute: ActivatedRoute;
    let expendService: ExpendService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
        declarations: [ExpendUpdateComponent],
        providers: [FormBuilder, ActivatedRoute],
      })
        .overrideTemplate(ExpendUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(ExpendUpdateComponent);
      activatedRoute = TestBed.inject(ActivatedRoute);
      expendService = TestBed.inject(ExpendService);

      comp = fixture.componentInstance;
    });

    describe('ngOnInit', () => {
      it('Should update editForm', () => {
        const expend: IExpend = { id: 456 };

        activatedRoute.data = of({ expend });
        comp.ngOnInit();

        expect(comp.editForm.value).toEqual(expect.objectContaining(expend));
      });
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', () => {
        // GIVEN
        const saveSubject = new Subject();
        const expend = { id: 123 };
        spyOn(expendService, 'update').and.returnValue(saveSubject);
        spyOn(comp, 'previousState');
        activatedRoute.data = of({ expend });
        comp.ngOnInit();

        // WHEN
        comp.save();
        expect(comp.isSaving).toEqual(true);
        saveSubject.next(new HttpResponse({ body: expend }));
        saveSubject.complete();

        // THEN
        expect(comp.previousState).toHaveBeenCalled();
        expect(expendService.update).toHaveBeenCalledWith(expend);
        expect(comp.isSaving).toEqual(false);
      });

      it('Should call create service on save for new entity', () => {
        // GIVEN
        const saveSubject = new Subject();
        const expend = new Expend();
        spyOn(expendService, 'create').and.returnValue(saveSubject);
        spyOn(comp, 'previousState');
        activatedRoute.data = of({ expend });
        comp.ngOnInit();

        // WHEN
        comp.save();
        expect(comp.isSaving).toEqual(true);
        saveSubject.next(new HttpResponse({ body: expend }));
        saveSubject.complete();

        // THEN
        expect(expendService.create).toHaveBeenCalledWith(expend);
        expect(comp.isSaving).toEqual(false);
        expect(comp.previousState).toHaveBeenCalled();
      });

      it('Should set isSaving to false on error', () => {
        // GIVEN
        const saveSubject = new Subject();
        const expend = { id: 123 };
        spyOn(expendService, 'update').and.returnValue(saveSubject);
        spyOn(comp, 'previousState');
        activatedRoute.data = of({ expend });
        comp.ngOnInit();

        // WHEN
        comp.save();
        expect(comp.isSaving).toEqual(true);
        saveSubject.error('This is an error!');

        // THEN
        expect(expendService.update).toHaveBeenCalledWith(expend);
        expect(comp.isSaving).toEqual(false);
        expect(comp.previousState).not.toHaveBeenCalled();
      });
    });
  });
});
