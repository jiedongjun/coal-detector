jest.mock('@angular/router');

import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { of, Subject } from 'rxjs';

import { ReportService } from '../service/report.service';
import { IReport, Report } from '../report.model';

import { ReportUpdateComponent } from './report-update.component';

describe('Component Tests', () => {
  describe('Report Management Update Component', () => {
    let comp: ReportUpdateComponent;
    let fixture: ComponentFixture<ReportUpdateComponent>;
    let activatedRoute: ActivatedRoute;
    let reportService: ReportService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
        declarations: [ReportUpdateComponent],
        providers: [FormBuilder, ActivatedRoute],
      })
        .overrideTemplate(ReportUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(ReportUpdateComponent);
      activatedRoute = TestBed.inject(ActivatedRoute);
      reportService = TestBed.inject(ReportService);

      comp = fixture.componentInstance;
    });

    describe('ngOnInit', () => {
      it('Should update editForm', () => {
        const report: IReport = { id: 456 };

        activatedRoute.data = of({ report });
        comp.ngOnInit();

        expect(comp.editForm.value).toEqual(expect.objectContaining(report));
      });
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', () => {
        // GIVEN
        const saveSubject = new Subject();
        const report = { id: 123 };
        spyOn(reportService, 'update').and.returnValue(saveSubject);
        spyOn(comp, 'previousState');
        activatedRoute.data = of({ report });
        comp.ngOnInit();

        // WHEN
        comp.save();
        expect(comp.isSaving).toEqual(true);
        saveSubject.next(new HttpResponse({ body: report }));
        saveSubject.complete();

        // THEN
        expect(comp.previousState).toHaveBeenCalled();
        expect(reportService.update).toHaveBeenCalledWith(report);
        expect(comp.isSaving).toEqual(false);
      });

      it('Should call create service on save for new entity', () => {
        // GIVEN
        const saveSubject = new Subject();
        const report = new Report();
        spyOn(reportService, 'create').and.returnValue(saveSubject);
        spyOn(comp, 'previousState');
        activatedRoute.data = of({ report });
        comp.ngOnInit();

        // WHEN
        comp.save();
        expect(comp.isSaving).toEqual(true);
        saveSubject.next(new HttpResponse({ body: report }));
        saveSubject.complete();

        // THEN
        expect(reportService.create).toHaveBeenCalledWith(report);
        expect(comp.isSaving).toEqual(false);
        expect(comp.previousState).toHaveBeenCalled();
      });

      it('Should set isSaving to false on error', () => {
        // GIVEN
        const saveSubject = new Subject();
        const report = { id: 123 };
        spyOn(reportService, 'update').and.returnValue(saveSubject);
        spyOn(comp, 'previousState');
        activatedRoute.data = of({ report });
        comp.ngOnInit();

        // WHEN
        comp.save();
        expect(comp.isSaving).toEqual(true);
        saveSubject.error('This is an error!');

        // THEN
        expect(reportService.update).toHaveBeenCalledWith(report);
        expect(comp.isSaving).toEqual(false);
        expect(comp.previousState).not.toHaveBeenCalled();
      });
    });
  });
});
