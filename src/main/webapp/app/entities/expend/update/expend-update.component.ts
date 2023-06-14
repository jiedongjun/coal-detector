import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { finalize } from 'rxjs/operators';

import * as dayjs from 'dayjs';
import { DATE_TIME_FORMAT } from 'app/config/input.constants';

import { IExpend, Expend } from '../expend.model';
import { ExpendService } from '../service/expend.service';

@Component({
  selector: 'jhi-expend-update',
  templateUrl: './expend-update.component.html',
})
export class ExpendUpdateComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    id: [],
    payTime: [],
    amount: [],
    direction: [],
    payWay: [],
    writer: [],
  });

  constructor(protected expendService: ExpendService, protected activatedRoute: ActivatedRoute, protected fb: FormBuilder) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ expend }) => {
      if (expend.id === undefined) {
        const today = dayjs().startOf('day');
        expend.payTime = today;
      }

      this.updateForm(expend);
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const expend = this.createFromForm();
    if (expend.id !== undefined) {
      this.subscribeToSaveResponse(this.expendService.update(expend));
    } else {
      this.subscribeToSaveResponse(this.expendService.create(expend));
    }
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IExpend>>): void {
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

  protected updateForm(expend: IExpend): void {
    this.editForm.patchValue({
      id: expend.id,
      payTime: expend.payTime ? expend.payTime.format(DATE_TIME_FORMAT) : null,
      amount: expend.amount,
      direction: expend.direction,
      payWay: expend.payWay,
      writer: expend.writer,
    });
  }

  protected createFromForm(): IExpend {
    return {
      ...new Expend(),
      id: this.editForm.get(['id'])!.value,
      payTime: this.editForm.get(['payTime'])!.value ? dayjs(this.editForm.get(['payTime'])!.value, DATE_TIME_FORMAT) : undefined,
      amount: this.editForm.get(['amount'])!.value,
      direction: this.editForm.get(['direction'])!.value,
      payWay: this.editForm.get(['payWay'])!.value,
      writer: this.editForm.get(['writer'])!.value,
    };
  }
}
