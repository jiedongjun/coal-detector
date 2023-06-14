import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import * as dayjs from 'dayjs';

import { isPresent } from 'app/core/util/operators';
import { ApplicationConfigService } from 'app/core/config/application-config.service';
import { createRequestOption } from 'app/core/request/request-util';
import { IExpend, getExpendIdentifier } from '../expend.model';

export type EntityResponseType = HttpResponse<IExpend>;
export type EntityArrayResponseType = HttpResponse<IExpend[]>;

@Injectable({ providedIn: 'root' })
export class ExpendService {
  public resourceUrl = this.applicationConfigService.getEndpointFor('api/expends');

  constructor(protected http: HttpClient, private applicationConfigService: ApplicationConfigService) {}

  create(expend: IExpend): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(expend);
    return this.http
      .post<IExpend>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(expend: IExpend): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(expend);
    return this.http
      .put<IExpend>(`${this.resourceUrl}/${getExpendIdentifier(expend) as number}`, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  partialUpdate(expend: IExpend): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(expend);
    return this.http
      .patch<IExpend>(`${this.resourceUrl}/${getExpendIdentifier(expend) as number}`, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<IExpend>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<IExpend[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  addExpendToCollectionIfMissing(expendCollection: IExpend[], ...expendsToCheck: (IExpend | null | undefined)[]): IExpend[] {
    const expends: IExpend[] = expendsToCheck.filter(isPresent);
    if (expends.length > 0) {
      const expendCollectionIdentifiers = expendCollection.map(expendItem => getExpendIdentifier(expendItem)!);
      const expendsToAdd = expends.filter(expendItem => {
        const expendIdentifier = getExpendIdentifier(expendItem);
        if (expendIdentifier == null || expendCollectionIdentifiers.includes(expendIdentifier)) {
          return false;
        }
        expendCollectionIdentifiers.push(expendIdentifier);
        return true;
      });
      return [...expendsToAdd, ...expendCollection];
    }
    return expendCollection;
  }

  protected convertDateFromClient(expend: IExpend): IExpend {
    return Object.assign({}, expend, {
      payTime: expend.payTime?.isValid() ? expend.payTime.toJSON() : undefined,
    });
  }

  protected convertDateFromServer(res: EntityResponseType): EntityResponseType {
    if (res.body) {
      res.body.payTime = res.body.payTime ? dayjs(res.body.payTime) : undefined;
    }
    return res;
  }

  protected convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
    if (res.body) {
      res.body.forEach((expend: IExpend) => {
        expend.payTime = expend.payTime ? dayjs(expend.payTime) : undefined;
      });
    }
    return res;
  }
}
