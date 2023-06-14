import { TestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import * as dayjs from 'dayjs';

import { DATE_TIME_FORMAT } from 'app/config/input.constants';
import { IExpend, Expend } from '../expend.model';

import { ExpendService } from './expend.service';

describe('Service Tests', () => {
  describe('Expend Service', () => {
    let service: ExpendService;
    let httpMock: HttpTestingController;
    let elemDefault: IExpend;
    let expectedResult: IExpend | IExpend[] | boolean | null;
    let currentDate: dayjs.Dayjs;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
      });
      expectedResult = null;
      service = TestBed.inject(ExpendService);
      httpMock = TestBed.inject(HttpTestingController);
      currentDate = dayjs();

      elemDefault = {
        id: 0,
        payTime: currentDate,
        amount: 0,
        direction: 'AAAAAAA',
        payWay: 'AAAAAAA',
        writer: 'AAAAAAA',
      };
    });

    describe('Service methods', () => {
      it('should find an element', () => {
        const returnedFromService = Object.assign(
          {
            payTime: currentDate.format(DATE_TIME_FORMAT),
          },
          elemDefault
        );

        service.find(123).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(elemDefault);
      });

      it('should create a Expend', () => {
        const returnedFromService = Object.assign(
          {
            id: 0,
            payTime: currentDate.format(DATE_TIME_FORMAT),
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            payTime: currentDate,
          },
          returnedFromService
        );

        service.create(new Expend()).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should update a Expend', () => {
        const returnedFromService = Object.assign(
          {
            id: 1,
            payTime: currentDate.format(DATE_TIME_FORMAT),
            amount: 1,
            direction: 'BBBBBB',
            payWay: 'BBBBBB',
            writer: 'BBBBBB',
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            payTime: currentDate,
          },
          returnedFromService
        );

        service.update(expected).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'PUT' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should partial update a Expend', () => {
        const patchObject = Object.assign(
          {
            payTime: currentDate.format(DATE_TIME_FORMAT),
            amount: 1,
            direction: 'BBBBBB',
            writer: 'BBBBBB',
          },
          new Expend()
        );

        const returnedFromService = Object.assign(patchObject, elemDefault);

        const expected = Object.assign(
          {
            payTime: currentDate,
          },
          returnedFromService
        );

        service.partialUpdate(patchObject).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'PATCH' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should return a list of Expend', () => {
        const returnedFromService = Object.assign(
          {
            id: 1,
            payTime: currentDate.format(DATE_TIME_FORMAT),
            amount: 1,
            direction: 'BBBBBB',
            payWay: 'BBBBBB',
            writer: 'BBBBBB',
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            payTime: currentDate,
          },
          returnedFromService
        );

        service.query().subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush([returnedFromService]);
        httpMock.verify();
        expect(expectedResult).toContainEqual(expected);
      });

      it('should delete a Expend', () => {
        service.delete(123).subscribe(resp => (expectedResult = resp.ok));

        const req = httpMock.expectOne({ method: 'DELETE' });
        req.flush({ status: 200 });
        expect(expectedResult);
      });

      describe('addExpendToCollectionIfMissing', () => {
        it('should add a Expend to an empty array', () => {
          const expend: IExpend = { id: 123 };
          expectedResult = service.addExpendToCollectionIfMissing([], expend);
          expect(expectedResult).toHaveLength(1);
          expect(expectedResult).toContain(expend);
        });

        it('should not add a Expend to an array that contains it', () => {
          const expend: IExpend = { id: 123 };
          const expendCollection: IExpend[] = [
            {
              ...expend,
            },
            { id: 456 },
          ];
          expectedResult = service.addExpendToCollectionIfMissing(expendCollection, expend);
          expect(expectedResult).toHaveLength(2);
        });

        it("should add a Expend to an array that doesn't contain it", () => {
          const expend: IExpend = { id: 123 };
          const expendCollection: IExpend[] = [{ id: 456 }];
          expectedResult = service.addExpendToCollectionIfMissing(expendCollection, expend);
          expect(expectedResult).toHaveLength(2);
          expect(expectedResult).toContain(expend);
        });

        it('should add only unique Expend to an array', () => {
          const expendArray: IExpend[] = [{ id: 123 }, { id: 456 }, { id: 35385 }];
          const expendCollection: IExpend[] = [{ id: 123 }];
          expectedResult = service.addExpendToCollectionIfMissing(expendCollection, ...expendArray);
          expect(expectedResult).toHaveLength(3);
        });

        it('should accept varargs', () => {
          const expend: IExpend = { id: 123 };
          const expend2: IExpend = { id: 456 };
          expectedResult = service.addExpendToCollectionIfMissing([], expend, expend2);
          expect(expectedResult).toHaveLength(2);
          expect(expectedResult).toContain(expend);
          expect(expectedResult).toContain(expend2);
        });

        it('should accept null and undefined values', () => {
          const expend: IExpend = { id: 123 };
          expectedResult = service.addExpendToCollectionIfMissing([], null, expend, undefined);
          expect(expectedResult).toHaveLength(1);
          expect(expectedResult).toContain(expend);
        });
      });
    });

    afterEach(() => {
      httpMock.verify();
    });
  });
});
