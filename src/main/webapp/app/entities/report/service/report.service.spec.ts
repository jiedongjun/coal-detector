import { TestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';

import { IReport, Report } from '../report.model';

import { ReportService } from './report.service';

describe('Service Tests', () => {
  describe('Report Service', () => {
    let service: ReportService;
    let httpMock: HttpTestingController;
    let elemDefault: IReport;
    let expectedResult: IReport | IReport[] | boolean | null;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
      });
      expectedResult = null;
      service = TestBed.inject(ReportService);
      httpMock = TestBed.inject(HttpTestingController);

      elemDefault = {
        id: 0,
        coalConfId: 0,
        phone: 'AAAAAAA',
        coalType: 'AAAAAAA',
        checkDate: 'AAAAAAA',
        up_M4: 0,
        up_A4: 0,
        up_V4: 0,
        up_S: 0,
        up_C: 0,
        report1: 0,
        report2: 0,
        report3: 0,
        eggCone2: 0,
        up_Aar: 0,
        up_Ad: 0,
        up_Var: 0,
        up_Vd: 0,
        up_Vdaf: 0,
        up_H: 'AAAAAAA',
        slime: 'AAAAAAA',
        cleanCoal: 'AAAAAAA',
        ganGue: 'AAAAAAA',
        note: 'AAAAAAA',
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

      it('should create a Report', () => {
        const returnedFromService = Object.assign(
          {
            id: 0,
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);

        service.create(new Report()).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should update a Report', () => {
        const returnedFromService = Object.assign(
          {
            id: 1,
            coalConfId: 1,
            phone: 'BBBBBB',
            coalType: 'BBBBBB',
            checkDate: 'BBBBBB',
            up_M4: 1,
            up_A4: 1,
            up_V4: 1,
            up_S: 1,
            up_C: 1,
            report1: 1,
            report2: 1,
            report3: 1,
            eggCone2: 1,
            up_Aar: 1,
            up_Ad: 1,
            up_Var: 1,
            up_Vd: 1,
            up_Vdaf: 1,
            up_H: 'BBBBBB',
            slime: 'BBBBBB',
            cleanCoal: 'BBBBBB',
            ganGue: 'BBBBBB',
            note: 'BBBBBB',
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);

        service.update(expected).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'PUT' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should partial update a Report', () => {
        const patchObject = Object.assign(
          {
            coalConfId: 1,
            checkDate: 'BBBBBB',
            up_V4: 1,
            up_S: 1,
            up_C: 1,
            report1: 1,
            report2: 1,
            eggCone2: 1,
            up_Aar: 1,
            up_Ad: 1,
            up_Vd: 1,
            up_H: 'BBBBBB',
            slime: 'BBBBBB',
          },
          new Report()
        );

        const returnedFromService = Object.assign(patchObject, elemDefault);

        const expected = Object.assign({}, returnedFromService);

        service.partialUpdate(patchObject).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'PATCH' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should return a list of Report', () => {
        const returnedFromService = Object.assign(
          {
            id: 1,
            coalConfId: 1,
            phone: 'BBBBBB',
            coalType: 'BBBBBB',
            checkDate: 'BBBBBB',
            up_M4: 1,
            up_A4: 1,
            up_V4: 1,
            up_S: 1,
            up_C: 1,
            report1: 1,
            report2: 1,
            report3: 1,
            eggCone2: 1,
            up_Aar: 1,
            up_Ad: 1,
            up_Var: 1,
            up_Vd: 1,
            up_Vdaf: 1,
            up_H: 'BBBBBB',
            slime: 'BBBBBB',
            cleanCoal: 'BBBBBB',
            ganGue: 'BBBBBB',
            note: 'BBBBBB',
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

      it('should delete a Report', () => {
        service.delete(123).subscribe(resp => (expectedResult = resp.ok));

        const req = httpMock.expectOne({ method: 'DELETE' });
        req.flush({ status: 200 });
        expect(expectedResult);
      });

      describe('addReportToCollectionIfMissing', () => {
        it('should add a Report to an empty array', () => {
          const report: IReport = { id: 123 };
          expectedResult = service.addReportToCollectionIfMissing([], report);
          expect(expectedResult).toHaveLength(1);
          expect(expectedResult).toContain(report);
        });

        it('should not add a Report to an array that contains it', () => {
          const report: IReport = { id: 123 };
          const reportCollection: IReport[] = [
            {
              ...report,
            },
            { id: 456 },
          ];
          expectedResult = service.addReportToCollectionIfMissing(reportCollection, report);
          expect(expectedResult).toHaveLength(2);
        });

        it("should add a Report to an array that doesn't contain it", () => {
          const report: IReport = { id: 123 };
          const reportCollection: IReport[] = [{ id: 456 }];
          expectedResult = service.addReportToCollectionIfMissing(reportCollection, report);
          expect(expectedResult).toHaveLength(2);
          expect(expectedResult).toContain(report);
        });

        it('should add only unique Report to an array', () => {
          const reportArray: IReport[] = [{ id: 123 }, { id: 456 }, { id: 68510 }];
          const reportCollection: IReport[] = [{ id: 123 }];
          expectedResult = service.addReportToCollectionIfMissing(reportCollection, ...reportArray);
          expect(expectedResult).toHaveLength(3);
        });

        it('should accept varargs', () => {
          const report: IReport = { id: 123 };
          const report2: IReport = { id: 456 };
          expectedResult = service.addReportToCollectionIfMissing([], report, report2);
          expect(expectedResult).toHaveLength(2);
          expect(expectedResult).toContain(report);
          expect(expectedResult).toContain(report2);
        });

        it('should accept null and undefined values', () => {
          const report: IReport = { id: 123 };
          expectedResult = service.addReportToCollectionIfMissing([], null, report, undefined);
          expect(expectedResult).toHaveLength(1);
          expect(expectedResult).toContain(report);
        });
      });
    });

    afterEach(() => {
      httpMock.verify();
    });
  });
});
