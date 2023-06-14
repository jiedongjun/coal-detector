import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { finalize } from 'rxjs/operators';

import { IReport, Report } from '../report.model';
import { ReportService } from '../service/report.service';

@Component({
  selector: 'jhi-report-update',
  templateUrl: './report-update.component.html',
})
export class ReportUpdateComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    id: [],
    coalConfId: [],
    phone: [],
    coalType: [],
    checkDate: [],
    up_M4: [],
    up_A4: [],
    up_V4: [],
    up_S: [],
    up_C: [],
    report1: [],
    report2: [],
    report3: [],
    eggCone2: [],
    up_Aar: [],
    up_Ad: [],
    up_Var: [],
    up_Vd: [],
    up_Vdaf: [],
    up_H: [],
    slime: [],
    cleanCoal: [],
    ganGue: [],
    note: [],
  });

  constructor(protected reportService: ReportService, protected activatedRoute: ActivatedRoute, protected fb: FormBuilder) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ report }) => {
      this.updateForm(report);
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const report = this.createFromForm();
    if (report.id !== undefined) {
      this.subscribeToSaveResponse(this.reportService.update(report));
    } else {
      this.subscribeToSaveResponse(this.reportService.create(report));
    }
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IReport>>): void {
    result.pipe(finalize(() => this.onSaveFinalize())).subscribe(
      () => this.onSaveSuccess(),
      () => this.onSaveError()
    );
  }

  protected onSaveSuccess(): void {
    this.previousState();
  }

  protected onSaveError(): void {
    // Api for inheritance.
  }

  protected onSaveFinalize(): void {
    this.isSaving = false;
  }

  protected updateForm(report: IReport): void {
    this.editForm.patchValue({
      id: report.id,
      coalConfId: report.coalConfId,
      phone: report.phone,
      coalType: report.coalType,
      checkDate: report.checkDate,
      up_M4: report.up_M4,
      up_A4: report.up_A4,
      up_V4: report.up_V4,
      up_S: report.up_S,
      up_C: report.up_C,
      report1: report.report1,
      report2: report.report2,
      report3: report.report3,
      eggCone2: report.eggCone2,
      up_Aar: report.up_Aar,
      up_Ad: report.up_Ad,
      up_Var: report.up_Var,
      up_Vd: report.up_Vd,
      up_Vdaf: report.up_Vdaf,
      up_H: report.up_H,
      slime: report.slime,
      cleanCoal: report.cleanCoal,
      ganGue: report.ganGue,
      note: report.note,
    });
  }

  protected createFromForm(): IReport {
    return {
      ...new Report(),
      id: this.editForm.get(['id'])!.value,
      coalConfId: this.editForm.get(['coalConfId'])!.value,
      phone: this.editForm.get(['phone'])!.value,
      coalType: this.editForm.get(['coalType'])!.value,
      checkDate: this.editForm.get(['checkDate'])!.value,
      up_M4: this.editForm.get(['up_M4'])!.value,
      up_A4: this.editForm.get(['up_A4'])!.value,
      up_V4: this.editForm.get(['up_V4'])!.value,
      up_S: this.editForm.get(['up_S'])!.value,
      up_C: this.editForm.get(['up_C'])!.value,
      report1: this.editForm.get(['report1'])!.value,
      report2: this.editForm.get(['report2'])!.value,
      report3: this.editForm.get(['report3'])!.value,
      eggCone2: this.editForm.get(['eggCone2'])!.value,
      up_Aar: this.editForm.get(['up_Aar'])!.value,
      up_Ad: this.editForm.get(['up_Ad'])!.value,
      up_Var: this.editForm.get(['up_Var'])!.value,
      up_Vd: this.editForm.get(['up_Vd'])!.value,
      up_Vdaf: this.editForm.get(['up_Vdaf'])!.value,
      up_H: this.editForm.get(['up_H'])!.value,
      slime: this.editForm.get(['slime'])!.value,
      cleanCoal: this.editForm.get(['cleanCoal'])!.value,
      ganGue: this.editForm.get(['ganGue'])!.value,
      note: this.editForm.get(['note'])!.value,
    };
  }
}
