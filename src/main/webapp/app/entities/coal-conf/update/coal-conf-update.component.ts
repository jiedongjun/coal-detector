import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { finalize } from 'rxjs/operators';

import { ICoalConf, CoalConf } from '../coal-conf.model';
import { CoalConfService } from '../service/coal-conf.service';

@Component({
  selector: 'jhi-coal-conf-update',
  templateUrl: './coal-conf-update.component.html',
})
export class CoalConfUpdateComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    id: [],
    project: [],
    phone: [],
    send: [],
    soft2: [],
    soft3: [],
    payStatus: [],
    note1: [],
    note2: [],
    coalType: [],
    up_M: [],
    up_M1: [],
    up_M2: [],
    up_M3: [],
    up_M4: [],
    up_A: [],
    up_A1: [],
    up_A2: [],
    up_A3: [],
    up_A4: [],
    up_V: [],
    up_V1: [],
    up_V2: [],
    up_V3: [],
    up_V4: [],
    low_m: [],
    low_m1: [],
    low_m2: [],
    low_m3: [],
    low_m4: [],
    eggCone1: [],
    eggCone2: [],
    eggCone3: [],
    price1: [],
    price2: [],
  });

  constructor(protected coalConfService: CoalConfService, protected activatedRoute: ActivatedRoute, protected fb: FormBuilder) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ coalConf }) => {
      this.updateForm(coalConf);
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const coalConf = this.createFromForm();
    if (coalConf.id !== undefined) {
      this.subscribeToSaveResponse(this.coalConfService.update(coalConf));
    } else {
      this.subscribeToSaveResponse(this.coalConfService.create(coalConf));
    }
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<ICoalConf>>): void {
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

  protected updateForm(coalConf: ICoalConf): void {
    this.editForm.patchValue({
      id: coalConf.id,
      project: coalConf.project,
      phone: coalConf.phone,
      send: coalConf.send,
      soft2: coalConf.soft2,
      soft3: coalConf.soft3,
      payStatus: coalConf.payStatus,
      note1: coalConf.note1,
      note2: coalConf.note2,
      coalType: coalConf.coalType,
      up_M: coalConf.up_M,
      up_M1: coalConf.up_M1,
      up_M2: coalConf.up_M2,
      up_M3: coalConf.up_M3,
      up_M4: coalConf.up_M4,
      up_A: coalConf.up_A,
      up_A1: coalConf.up_A1,
      up_A2: coalConf.up_A2,
      up_A3: coalConf.up_A3,
      up_A4: coalConf.up_A4,
      up_V: coalConf.up_V,
      up_V1: coalConf.up_V1,
      up_V2: coalConf.up_V2,
      up_V3: coalConf.up_V3,
      up_V4: coalConf.up_V4,
      low_m: coalConf.low_m,
      low_m1: coalConf.low_m1,
      low_m2: coalConf.low_m2,
      low_m3: coalConf.low_m3,
      low_m4: coalConf.low_m4,
      eggCone1: coalConf.eggCone1,
      eggCone2: coalConf.eggCone2,
      eggCone3: coalConf.eggCone3,
      price1: coalConf.price1,
      price2: coalConf.price2,
    });
  }

  protected createFromForm(): ICoalConf {
    return {
      ...new CoalConf(),
      id: this.editForm.get(['id'])!.value,
      project: this.editForm.get(['project'])!.value,
      phone: this.editForm.get(['phone'])!.value,
      send: this.editForm.get(['send'])!.value,
      soft2: this.editForm.get(['soft2'])!.value,
      soft3: this.editForm.get(['soft3'])!.value,
      payStatus: this.editForm.get(['payStatus'])!.value,
      note1: this.editForm.get(['note1'])!.value,
      note2: this.editForm.get(['note2'])!.value,
      coalType: this.editForm.get(['coalType'])!.value,
      up_M: this.editForm.get(['up_M'])!.value,
      up_M1: this.editForm.get(['up_M1'])!.value,
      up_M2: this.editForm.get(['up_M2'])!.value,
      up_M3: this.editForm.get(['up_M3'])!.value,
      up_M4: this.editForm.get(['up_M4'])!.value,
      up_A: this.editForm.get(['up_A'])!.value,
      up_A1: this.editForm.get(['up_A1'])!.value,
      up_A2: this.editForm.get(['up_A2'])!.value,
      up_A3: this.editForm.get(['up_A3'])!.value,
      up_A4: this.editForm.get(['up_A4'])!.value,
      up_V: this.editForm.get(['up_V'])!.value,
      up_V1: this.editForm.get(['up_V1'])!.value,
      up_V2: this.editForm.get(['up_V2'])!.value,
      up_V3: this.editForm.get(['up_V3'])!.value,
      up_V4: this.editForm.get(['up_V4'])!.value,
      low_m: this.editForm.get(['low_m'])!.value,
      low_m1: this.editForm.get(['low_m1'])!.value,
      low_m2: this.editForm.get(['low_m2'])!.value,
      low_m3: this.editForm.get(['low_m3'])!.value,
      low_m4: this.editForm.get(['low_m4'])!.value,
      eggCone1: this.editForm.get(['eggCone1'])!.value,
      eggCone2: this.editForm.get(['eggCone2'])!.value,
      eggCone3: this.editForm.get(['eggCone3'])!.value,
      price1: this.editForm.get(['price1'])!.value,
      price2: this.editForm.get(['price2'])!.value,
    };
  }
}
