import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { isPresent } from 'app/core/util/operators';
import { ApplicationConfigService } from 'app/core/config/application-config.service';
import { createRequestOption } from 'app/core/request/request-util';
import { ICoalConf, getCoalConfIdentifier } from '../coal-conf.model';

export type EntityResponseType = HttpResponse<ICoalConf>;
export type EntityArrayResponseType = HttpResponse<ICoalConf[]>;

@Injectable({ providedIn: 'root' })
export class CoalConfService {
  public resourceUrl = this.applicationConfigService.getEndpointFor('api/coal-confs');

  constructor(protected http: HttpClient, private applicationConfigService: ApplicationConfigService) {}

  create(coalConf: ICoalConf): Observable<EntityResponseType> {
    return this.http.post<ICoalConf>(this.resourceUrl, coalConf, { observe: 'response' });
  }

  update(coalConf: ICoalConf): Observable<EntityResponseType> {
    return this.http.put<ICoalConf>(`${this.resourceUrl}/${getCoalConfIdentifier(coalConf) as number}`, coalConf, { observe: 'response' });
  }

  partialUpdate(coalConf: ICoalConf): Observable<EntityResponseType> {
    return this.http.patch<ICoalConf>(`${this.resourceUrl}/${getCoalConfIdentifier(coalConf) as number}`, coalConf, {
      observe: 'response',
    });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<ICoalConf>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<ICoalConf[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  addCoalConfToCollectionIfMissing(coalConfCollection: ICoalConf[], ...coalConfsToCheck: (ICoalConf | null | undefined)[]): ICoalConf[] {
    const coalConfs: ICoalConf[] = coalConfsToCheck.filter(isPresent);
    if (coalConfs.length > 0) {
      const coalConfCollectionIdentifiers = coalConfCollection.map(coalConfItem => getCoalConfIdentifier(coalConfItem)!);
      const coalConfsToAdd = coalConfs.filter(coalConfItem => {
        const coalConfIdentifier = getCoalConfIdentifier(coalConfItem);
        if (coalConfIdentifier == null || coalConfCollectionIdentifiers.includes(coalConfIdentifier)) {
          return false;
        }
        coalConfCollectionIdentifiers.push(coalConfIdentifier);
        return true;
      });
      return [...coalConfsToAdd, ...coalConfCollection];
    }
    return coalConfCollection;
  }
}
