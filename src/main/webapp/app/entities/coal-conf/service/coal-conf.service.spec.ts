import { TestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';

import { ICoalConf, CoalConf } from '../coal-conf.model';

import { CoalConfService } from './coal-conf.service';

describe('Service Tests', () => {
  describe('CoalConf Service', () => {
    let service: CoalConfService;
    let httpMock: HttpTestingController;
    let elemDefault: ICoalConf;
    let expectedResult: ICoalConf | ICoalConf[] | boolean | null;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
      });
      expectedResult = null;
      service = TestBed.inject(CoalConfService);
      httpMock = TestBed.inject(HttpTestingController);

      elemDefault = {
        id: 0,
        project: 'AAAAAAA',
        phone: 'AAAAAAA',
        send: 0,
        soft2: 'AAAAAAA',
        soft3: 'AAAAAAA',
        payStatus: 0,
        note1: 'AAAAAAA',
        note2: 'AAAAAAA',
        coalType: 'AAAAAAA',
        up_M: 0,
        up_M1: 0,
        up_M2: 0,
        up_M3: 0,
        up_M4: 0,
        up_A: 0,
        up_A1: 0,
        up_A2: 0,
        up_A3: 0,
        up_A4: 0,
        up_V: 0,
        up_V1: 0,
        up_V2: 0,
        up_V3: 0,
        up_V4: 0,
        low_m: 0,
        low_m1: 0,
        low_m2: 0,
        low_m3: 0,
        low_m4: 0,
        eggCone1: 'AAAAAAA',
        eggCone2: 'AAAAAAA',
        eggCone3: 'AAAAAAA',
        price1: 0,
        price2: 0,
      };
    });

    describe('Service methods', () => {
      it('should find an element', () => {
        const returnedFromService = Object.assign({}, elemDefault);

        service.find(123).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(elemDefault);
      });

      it('should create a CoalConf', () => {
        const returnedFromService = Object.assign(
          {
            id: 0,
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);

        service.create(new CoalConf()).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should update a CoalConf', () => {
        const returnedFromService = Object.assign(
          {
            id: 1,
            project: 'BBBBBB',
            phone: 'BBBBBB',
            send: 1,
            soft2: 'BBBBBB',
            soft3: 'BBBBBB',
            payStatus: 1,
            note1: 'BBBBBB',
            note2: 'BBBBBB',
            coalType: 'BBBBBB',
            up_M: 1,
            up_M1: 1,
            up_M2: 1,
            up_M3: 1,
            up_M4: 1,
            up_A: 1,
            up_A1: 1,
            up_A2: 1,
            up_A3: 1,
            up_A4: 1,
            up_V: 1,
            up_V1: 1,
            up_V2: 1,
            up_V3: 1,
            up_V4: 1,
            low_m: 1,
            low_m1: 1,
            low_m2: 1,
            low_m3: 1,
            low_m4: 1,
            eggCone1: 'BBBBBB',
            eggCone2: 'BBBBBB',
            eggCone3: 'BBBBBB',
            price1: 1,
            price2: 1,
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);

        service.update(expected).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'PUT' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should partial update a CoalConf', () => {
        const patchObject = Object.assign(
          {
            send: 1,
            soft2: 'BBBBBB',
            payStatus: 1,
            note1: 'BBBBBB',
            up_M: 1,
            up_M2: 1,
            up_M3: 1,
            up_A1: 1,
            up_V: 1,
            up_V2: 1,
            up_V3: 1,
            low_m: 1,
            low_m1: 1,
            low_m2: 1,
            eggCone1: 'BBBBBB',
            eggCone2: 'BBBBBB',
          },
          new CoalConf()
        );

        const returnedFromService = Object.assign(patchObject, elemDefault);

        const expected = Object.assign({}, returnedFromService);

        service.partialUpdate(patchObject).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'PATCH' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should return a list of CoalConf', () => {
        const returnedFromService = Object.assign(
          {
            id: 1,
            project: 'BBBBBB',
            phone: 'BBBBBB',
            send: 1,
            soft2: 'BBBBBB',
            soft3: 'BBBBBB',
            payStatus: 1,
            note1: 'BBBBBB',
            note2: 'BBBBBB',
            coalType: 'BBBBBB',
            up_M: 1,
            up_M1: 1,
            up_M2: 1,
            up_M3: 1,
            up_M4: 1,
            up_A: 1,
            up_A1: 1,
            up_A2: 1,
            up_A3: 1,
            up_A4: 1,
            up_V: 1,
            up_V1: 1,
            up_V2: 1,
            up_V3: 1,
            up_V4: 1,
            low_m: 1,
            low_m1: 1,
            low_m2: 1,
            low_m3: 1,
            low_m4: 1,
            eggCone1: 'BBBBBB',
            eggCone2: 'BBBBBB',
            eggCone3: 'BBBBBB',
            price1: 1,
            price2: 1,
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);

        service.query().subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush([returnedFromService]);
        httpMock.verify();
        expect(expectedResult).toContainEqual(expected);
      });

      it('should delete a CoalConf', () => {
        service.delete(123).subscribe(resp => (expectedResult = resp.ok));

        const req = httpMock.expectOne({ method: 'DELETE' });
        req.flush({ status: 200 });
        expect(expectedResult);
      });

      describe('addCoalConfToCollectionIfMissing', () => {
        it('should add a CoalConf to an empty array', () => {
          const coalConf: ICoalConf = { id: 123 };
          expectedResult = service.addCoalConfToCollectionIfMissing([], coalConf);
          expect(expectedResult).toHaveLength(1);
          expect(expectedResult).toContain(coalConf);
        });

        it('should not add a CoalConf to an array that contains it', () => {
          const coalConf: ICoalConf = { id: 123 };
          const coalConfCollection: ICoalConf[] = [
            {
              ...coalConf,
            },
            { id: 456 },
          ];
          expectedResult = service.addCoalConfToCollectionIfMissing(coalConfCollection, coalConf);
          expect(expectedResult).toHaveLength(2);
        });

        it("should add a CoalConf to an array that doesn't contain it", () => {
          const coalConf: ICoalConf = { id: 123 };
          const coalConfCollection: ICoalConf[] = [{ id: 456 }];
          expectedResult = service.addCoalConfToCollectionIfMissing(coalConfCollection, coalConf);
          expect(expectedResult).toHaveLength(2);
          expect(expectedResult).toContain(coalConf);
        });

        it('should add only unique CoalConf to an array', () => {
          const coalConfArray: ICoalConf[] = [{ id: 123 }, { id: 456 }, { id: 1643 }];
          const coalConfCollection: ICoalConf[] = [{ id: 123 }];
          expectedResult = service.addCoalConfToCollectionIfMissing(coalConfCollection, ...coalConfArray);
          expect(expectedResult).toHaveLength(3);
        });

        it('should accept varargs', () => {
          const coalConf: ICoalConf = { id: 123 };
          const coalConf2: ICoalConf = { id: 456 };
          expectedResult = service.addCoalConfToCollectionIfMissing([], coalConf, coalConf2);
          expect(expectedResult).toHaveLength(2);
          expect(expectedResult).toContain(coalConf);
          expect(expectedResult).toContain(coalConf2);
        });

        it('should accept null and undefined values', () => {
          const coalConf: ICoalConf = { id: 123 };
          expectedResult = service.addCoalConfToCollectionIfMissing([], null, coalConf, undefined);
          expect(expectedResult).toHaveLength(1);
          expect(expectedResult).toContain(coalConf);
        });
      });
    });

    afterEach(() => {
      httpMock.verify();
    });
  });
});
