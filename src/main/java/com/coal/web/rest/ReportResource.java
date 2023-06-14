package com.coal.web.rest;

import com.coal.config.ApplicationProperties;
import com.coal.domain.Report;
import com.coal.repository.ReportRepository;
import com.coal.service.ReportService;
import com.coal.service.report.DocxReportService;
import com.coal.util.ObjectNodeUtil;
import com.coal.web.rest.errors.BadRequestAlertException;
import com.fasterxml.jackson.databind.node.ObjectNode;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.PaginationUtil;
import tech.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link com.coal.domain.Report}.
 */
@RestController
@RequestMapping("/api")
public class ReportResource {

    private final Logger log = LoggerFactory.getLogger(ReportResource.class);
    private static final String ENTITY_NAME = "report";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ReportService reportService;
    private final ReportRepository reportRepository;
    private final DocxReportService docxReportService;
    private final ApplicationProperties applicationProperties;

    public ReportResource(
        ReportService reportService,
        ReportRepository reportRepository,
        DocxReportService docxReportService,
        ApplicationProperties applicationProperties
    ) {
        this.reportService = reportService;
        this.reportRepository = reportRepository;
        this.docxReportService = docxReportService;
        this.applicationProperties = applicationProperties;
    }

    /**
     * {@code POST  /reports} : Create a new report.
     *
     * @param report the report to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new report, or with status {@code 400 (Bad Request)} if the report has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/reports")
    public ResponseEntity<Report> createReport(@RequestBody Report report) throws URISyntaxException {
        log.debug("REST request to save Report : {}", report);
        if (report.getId() != null) {
            throw new BadRequestAlertException("A new report cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Report result = reportService.save(report);
        return ResponseEntity
            .created(new URI("/api/reports/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    @GetMapping("/reports/save")
    public Report saveReport(@RequestParam String report) {
        log.debug("REST request to save Report : {}", report);
        Report newReport = ObjectNodeUtil.stringToObj(report, Report.class);
        newReport = reportRepository.save(newReport);
        return newReport;
    }

    /**
     * {@code PUT  /reports/:id} : Updates an existing report.
     *
     * @param id the id of the report to save.
     * @param report the report to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated report,
     * or with status {@code 400 (Bad Request)} if the report is not valid,
     * or with status {@code 500 (Internal Server Error)} if the report couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/reports/{id}")
    public ResponseEntity<Report> updateReport(@PathVariable(value = "id", required = false) final Long id, @RequestBody Report report)
        throws URISyntaxException {
        log.debug("REST request to update Report : {}, {}", id, report);
        if (report.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, report.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!reportRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Report result = reportService.save(report);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, report.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /reports/:id} : Partial updates given fields of an existing report, field will ignore if it is null
     *
     * @param id the id of the report to save.
     * @param report the report to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated report,
     * or with status {@code 400 (Bad Request)} if the report is not valid,
     * or with status {@code 404 (Not Found)} if the report is not found,
     * or with status {@code 500 (Internal Server Error)} if the report couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/reports/{id}", consumes = "application/merge-patch+json")
    public ResponseEntity<Report> partialUpdateReport(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody Report report
    ) throws URISyntaxException {
        log.debug("REST request to partial update Report partially : {}, {}", id, report);
        if (report.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, report.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!reportRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<Report> result = reportService.partialUpdate(report);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, report.getId().toString())
        );
    }

    /**
     * {@code GET  /reports} : get all the reports.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of reports in body.
     */
    @GetMapping("/reports")
    public ResponseEntity<List<Report>> getAllReports(Pageable pageable) {
        log.debug("REST request to get a page of Reports");
        Page<Report> page = reportService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /reports/:id} : get the "id" report.
     *
     * @param id the id of the report to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the report, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/reports/{id}")
    public ResponseEntity<Report> getReport(@PathVariable Long id) {
        log.debug("REST request to get Report : {}", id);
        Optional<Report> report = reportService.findOne(id);
        return ResponseUtil.wrapOrNotFound(report);
    }

    /**
     * {@code DELETE  /reports/:id} : delete the "id" report.
     *
     * @param id the id of the report to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/reports/{id}")
    public ResponseEntity<Void> deleteReport(@PathVariable Long id) {
        log.debug("REST request to delete Report : {}", id);
        reportService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }

    @GetMapping("/reports/coal/{coalConfId}")
    public Report getOne(@PathVariable Long coalConfId) {
        return reportService.findByCoalConfId(coalConfId);
    }

    @GetMapping("/reports/download/{reportId}")
    public void downloadReport(@PathVariable long reportId, HttpServletResponse response) {
        Report report = reportService.findOne(reportId).orElse(null);
        ObjectNode objectNode = ObjectNodeUtil.objectToJson(report, ObjectNode.class);
        sendFile(response, objectNode);
    }

    @GetMapping("/reports/export")
    public void exportReport(@RequestParam String param, HttpServletResponse response) {
        ObjectNode objectNode = ObjectNodeUtil.stringToJson(param);
        sendFile(response, objectNode);
    }

    private void sendFile(HttpServletResponse response, ObjectNode objectNode) {
        File templateFile = docxReportService.getTemplateFile("coal_report");
        try {
            byte[] bytes = docxReportService.fillWordTemplate(templateFile, objectNode);
            ServletOutputStream outputStream = response.getOutputStream();
            response.setContentType("application/octet-stream");
            response.setHeader("Content-Disposition", "attachment; filename=file.docx");

            outputStream.write(bytes);
            outputStream.flush();
            outputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
