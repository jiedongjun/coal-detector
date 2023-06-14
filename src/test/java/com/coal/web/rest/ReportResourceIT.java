package com.coal.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.coal.IntegrationTest;
import com.coal.domain.Report;
import com.coal.repository.ReportRepository;
import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;
import javax.persistence.EntityManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

/**
 * Integration tests for the {@link ReportResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class ReportResourceIT {

    private static final Long DEFAULT_COAL_CONF_ID = 1L;
    private static final Long UPDATED_COAL_CONF_ID = 2L;

    private static final String DEFAULT_PHONE = "AAAAAAAAAA";
    private static final String UPDATED_PHONE = "BBBBBBBBBB";

    private static final String DEFAULT_COAL_TYPE = "AAAAAAAAAA";
    private static final String UPDATED_COAL_TYPE = "BBBBBBBBBB";

    private static final String DEFAULT_CHECK_DATE = "AAAAAAAAAA";
    private static final String UPDATED_CHECK_DATE = "BBBBBBBBBB";

    private static final Float DEFAULT_UP_M_4 = 1F;
    private static final Float UPDATED_UP_M_4 = 2F;

    private static final Float DEFAULT_UP_A_4 = 1F;
    private static final Float UPDATED_UP_A_4 = 2F;

    private static final Float DEFAULT_UP_V_4 = 1F;
    private static final Float UPDATED_UP_V_4 = 2F;

    private static final Float DEFAULT_UP_S = 1F;
    private static final Float UPDATED_UP_S = 2F;

    private static final Float DEFAULT_UP_C = 1F;
    private static final Float UPDATED_UP_C = 2F;

    private static final Float DEFAULT_REPORT_1 = 1F;
    private static final Float UPDATED_REPORT_1 = 2F;

    private static final Float DEFAULT_REPORT_2 = 1F;
    private static final Float UPDATED_REPORT_2 = 2F;

    private static final Float DEFAULT_REPORT_3 = 1F;
    private static final Float UPDATED_REPORT_3 = 2F;

    private static final Float DEFAULT_EGG_CONE_2 = 1F;
    private static final Float UPDATED_EGG_CONE_2 = 2F;

    private static final Float DEFAULT_UP_AAR = 1F;
    private static final Float UPDATED_UP_AAR = 2F;

    private static final Float DEFAULT_UP_AD = 1F;
    private static final Float UPDATED_UP_AD = 2F;

    private static final Float DEFAULT_UP_VAR = 1F;
    private static final Float UPDATED_UP_VAR = 2F;

    private static final Float DEFAULT_UP_VD = 1F;
    private static final Float UPDATED_UP_VD = 2F;

    private static final Float DEFAULT_UP_VDAF = 1F;
    private static final Float UPDATED_UP_VDAF = 2F;

    private static final String DEFAULT_UP_H = "AAAAAAAAAA";
    private static final String UPDATED_UP_H = "BBBBBBBBBB";

    private static final String DEFAULT_SLIME = "AAAAAAAAAA";
    private static final String UPDATED_SLIME = "BBBBBBBBBB";

    private static final String DEFAULT_CLEAN_COAL = "AAAAAAAAAA";
    private static final String UPDATED_CLEAN_COAL = "BBBBBBBBBB";

    private static final String DEFAULT_GAN_GUE = "AAAAAAAAAA";
    private static final String UPDATED_GAN_GUE = "BBBBBBBBBB";

    private static final String DEFAULT_NOTE = "AAAAAAAAAA";
    private static final String UPDATED_NOTE = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/reports";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private ReportRepository reportRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restReportMockMvc;

    private Report report;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Report createEntity(EntityManager em) {
        Report report = new Report()
            .coalConfId(DEFAULT_COAL_CONF_ID)
            .phone(DEFAULT_PHONE)
            .coalType(DEFAULT_COAL_TYPE)
            .checkDate(DEFAULT_CHECK_DATE)
            .up_M4(DEFAULT_UP_M_4)
            .up_A4(DEFAULT_UP_A_4)
            .up_V4(DEFAULT_UP_V_4)
            .up_S(DEFAULT_UP_S)
            .up_C(DEFAULT_UP_C)
            .report1(DEFAULT_REPORT_1)
            .report2(DEFAULT_REPORT_2)
            .report3(DEFAULT_REPORT_3)
            .eggCone2(DEFAULT_EGG_CONE_2)
            .up_Aar(DEFAULT_UP_AAR)
            .up_Ad(DEFAULT_UP_AD)
            .up_Var(DEFAULT_UP_VAR)
            .up_Vd(DEFAULT_UP_VD)
            .up_Vdaf(DEFAULT_UP_VDAF)
            .up_H(DEFAULT_UP_H)
            .slime(DEFAULT_SLIME)
            .cleanCoal(DEFAULT_CLEAN_COAL)
            .ganGue(DEFAULT_GAN_GUE)
            .note(DEFAULT_NOTE);
        return report;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Report createUpdatedEntity(EntityManager em) {
        Report report = new Report()
            .coalConfId(UPDATED_COAL_CONF_ID)
            .phone(UPDATED_PHONE)
            .coalType(UPDATED_COAL_TYPE)
            .checkDate(UPDATED_CHECK_DATE)
            .up_M4(UPDATED_UP_M_4)
            .up_A4(UPDATED_UP_A_4)
            .up_V4(UPDATED_UP_V_4)
            .up_S(UPDATED_UP_S)
            .up_C(UPDATED_UP_C)
            .report1(UPDATED_REPORT_1)
            .report2(UPDATED_REPORT_2)
            .report3(UPDATED_REPORT_3)
            .eggCone2(UPDATED_EGG_CONE_2)
            .up_Aar(UPDATED_UP_AAR)
            .up_Ad(UPDATED_UP_AD)
            .up_Var(UPDATED_UP_VAR)
            .up_Vd(UPDATED_UP_VD)
            .up_Vdaf(UPDATED_UP_VDAF)
            .up_H(UPDATED_UP_H)
            .slime(UPDATED_SLIME)
            .cleanCoal(UPDATED_CLEAN_COAL)
            .ganGue(UPDATED_GAN_GUE)
            .note(UPDATED_NOTE);
        return report;
    }

    @BeforeEach
    public void initTest() {
        report = createEntity(em);
    }

    @Test
    @Transactional
    void createReport() throws Exception {
        int databaseSizeBeforeCreate = reportRepository.findAll().size();
        // Create the Report
        restReportMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(report)))
            .andExpect(status().isCreated());

        // Validate the Report in the database
        List<Report> reportList = reportRepository.findAll();
        assertThat(reportList).hasSize(databaseSizeBeforeCreate + 1);
        Report testReport = reportList.get(reportList.size() - 1);
        assertThat(testReport.getCoalConfId()).isEqualTo(DEFAULT_COAL_CONF_ID);
        assertThat(testReport.getPhone()).isEqualTo(DEFAULT_PHONE);
        assertThat(testReport.getCoalType()).isEqualTo(DEFAULT_COAL_TYPE);
        assertThat(testReport.getCheckDate()).isEqualTo(DEFAULT_CHECK_DATE);
        assertThat(testReport.getUp_M4()).isEqualTo(DEFAULT_UP_M_4);
        assertThat(testReport.getUp_A4()).isEqualTo(DEFAULT_UP_A_4);
        assertThat(testReport.getUp_V4()).isEqualTo(DEFAULT_UP_V_4);
        assertThat(testReport.getUp_S()).isEqualTo(DEFAULT_UP_S);
        assertThat(testReport.getUp_C()).isEqualTo(DEFAULT_UP_C);
        assertThat(testReport.getReport1()).isEqualTo(DEFAULT_REPORT_1);
        assertThat(testReport.getReport2()).isEqualTo(DEFAULT_REPORT_2);
        assertThat(testReport.getReport3()).isEqualTo(DEFAULT_REPORT_3);
        assertThat(testReport.getEggCone2()).isEqualTo(DEFAULT_EGG_CONE_2);
        assertThat(testReport.getUp_Aar()).isEqualTo(DEFAULT_UP_AAR);
        assertThat(testReport.getUp_Ad()).isEqualTo(DEFAULT_UP_AD);
        assertThat(testReport.getUp_Var()).isEqualTo(DEFAULT_UP_VAR);
        assertThat(testReport.getUp_Vd()).isEqualTo(DEFAULT_UP_VD);
        assertThat(testReport.getUp_Vdaf()).isEqualTo(DEFAULT_UP_VDAF);
        assertThat(testReport.getUp_H()).isEqualTo(DEFAULT_UP_H);
        assertThat(testReport.getSlime()).isEqualTo(DEFAULT_SLIME);
        assertThat(testReport.getCleanCoal()).isEqualTo(DEFAULT_CLEAN_COAL);
        assertThat(testReport.getGanGue()).isEqualTo(DEFAULT_GAN_GUE);
        assertThat(testReport.getNote()).isEqualTo(DEFAULT_NOTE);
    }

    @Test
    @Transactional
    void createReportWithExistingId() throws Exception {
        // Create the Report with an existing ID
        report.setId(1L);

        int databaseSizeBeforeCreate = reportRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restReportMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(report)))
            .andExpect(status().isBadRequest());

        // Validate the Report in the database
        List<Report> reportList = reportRepository.findAll();
        assertThat(reportList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllReports() throws Exception {
        // Initialize the database
        reportRepository.saveAndFlush(report);

        // Get all the reportList
        restReportMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(report.getId().intValue())))
            .andExpect(jsonPath("$.[*].coalConfId").value(hasItem(DEFAULT_COAL_CONF_ID.intValue())))
            .andExpect(jsonPath("$.[*].phone").value(hasItem(DEFAULT_PHONE)))
            .andExpect(jsonPath("$.[*].coalType").value(hasItem(DEFAULT_COAL_TYPE)))
            .andExpect(jsonPath("$.[*].checkDate").value(hasItem(DEFAULT_CHECK_DATE)))
            .andExpect(jsonPath("$.[*].up_M4").value(hasItem(DEFAULT_UP_M_4.doubleValue())))
            .andExpect(jsonPath("$.[*].up_A4").value(hasItem(DEFAULT_UP_A_4.doubleValue())))
            .andExpect(jsonPath("$.[*].up_V4").value(hasItem(DEFAULT_UP_V_4.doubleValue())))
            .andExpect(jsonPath("$.[*].up_S").value(hasItem(DEFAULT_UP_S.doubleValue())))
            .andExpect(jsonPath("$.[*].up_C").value(hasItem(DEFAULT_UP_C.doubleValue())))
            .andExpect(jsonPath("$.[*].report1").value(hasItem(DEFAULT_REPORT_1.doubleValue())))
            .andExpect(jsonPath("$.[*].report2").value(hasItem(DEFAULT_REPORT_2.doubleValue())))
            .andExpect(jsonPath("$.[*].report3").value(hasItem(DEFAULT_REPORT_3.doubleValue())))
            .andExpect(jsonPath("$.[*].eggCone2").value(hasItem(DEFAULT_EGG_CONE_2.doubleValue())))
            .andExpect(jsonPath("$.[*].up_Aar").value(hasItem(DEFAULT_UP_AAR.doubleValue())))
            .andExpect(jsonPath("$.[*].up_Ad").value(hasItem(DEFAULT_UP_AD.doubleValue())))
            .andExpect(jsonPath("$.[*].up_Var").value(hasItem(DEFAULT_UP_VAR.doubleValue())))
            .andExpect(jsonPath("$.[*].up_Vd").value(hasItem(DEFAULT_UP_VD.doubleValue())))
            .andExpect(jsonPath("$.[*].up_Vdaf").value(hasItem(DEFAULT_UP_VDAF.doubleValue())))
            .andExpect(jsonPath("$.[*].up_H").value(hasItem(DEFAULT_UP_H)))
            .andExpect(jsonPath("$.[*].slime").value(hasItem(DEFAULT_SLIME)))
            .andExpect(jsonPath("$.[*].cleanCoal").value(hasItem(DEFAULT_CLEAN_COAL)))
            .andExpect(jsonPath("$.[*].ganGue").value(hasItem(DEFAULT_GAN_GUE)))
            .andExpect(jsonPath("$.[*].note").value(hasItem(DEFAULT_NOTE)));
    }

    @Test
    @Transactional
    void getReport() throws Exception {
        // Initialize the database
        reportRepository.saveAndFlush(report);

        // Get the report
        restReportMockMvc
            .perform(get(ENTITY_API_URL_ID, report.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(report.getId().intValue()))
            .andExpect(jsonPath("$.coalConfId").value(DEFAULT_COAL_CONF_ID.intValue()))
            .andExpect(jsonPath("$.phone").value(DEFAULT_PHONE))
            .andExpect(jsonPath("$.coalType").value(DEFAULT_COAL_TYPE))
            .andExpect(jsonPath("$.checkDate").value(DEFAULT_CHECK_DATE))
            .andExpect(jsonPath("$.up_M4").value(DEFAULT_UP_M_4.doubleValue()))
            .andExpect(jsonPath("$.up_A4").value(DEFAULT_UP_A_4.doubleValue()))
            .andExpect(jsonPath("$.up_V4").value(DEFAULT_UP_V_4.doubleValue()))
            .andExpect(jsonPath("$.up_S").value(DEFAULT_UP_S.doubleValue()))
            .andExpect(jsonPath("$.up_C").value(DEFAULT_UP_C.doubleValue()))
            .andExpect(jsonPath("$.report1").value(DEFAULT_REPORT_1.doubleValue()))
            .andExpect(jsonPath("$.report2").value(DEFAULT_REPORT_2.doubleValue()))
            .andExpect(jsonPath("$.report3").value(DEFAULT_REPORT_3.doubleValue()))
            .andExpect(jsonPath("$.eggCone2").value(DEFAULT_EGG_CONE_2.doubleValue()))
            .andExpect(jsonPath("$.up_Aar").value(DEFAULT_UP_AAR.doubleValue()))
            .andExpect(jsonPath("$.up_Ad").value(DEFAULT_UP_AD.doubleValue()))
            .andExpect(jsonPath("$.up_Var").value(DEFAULT_UP_VAR.doubleValue()))
            .andExpect(jsonPath("$.up_Vd").value(DEFAULT_UP_VD.doubleValue()))
            .andExpect(jsonPath("$.up_Vdaf").value(DEFAULT_UP_VDAF.doubleValue()))
            .andExpect(jsonPath("$.up_H").value(DEFAULT_UP_H))
            .andExpect(jsonPath("$.slime").value(DEFAULT_SLIME))
            .andExpect(jsonPath("$.cleanCoal").value(DEFAULT_CLEAN_COAL))
            .andExpect(jsonPath("$.ganGue").value(DEFAULT_GAN_GUE))
            .andExpect(jsonPath("$.note").value(DEFAULT_NOTE));
    }

    @Test
    @Transactional
    void getNonExistingReport() throws Exception {
        // Get the report
        restReportMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putNewReport() throws Exception {
        // Initialize the database
        reportRepository.saveAndFlush(report);

        int databaseSizeBeforeUpdate = reportRepository.findAll().size();

        // Update the report
        Report updatedReport = reportRepository.findById(report.getId()).get();
        // Disconnect from session so that the updates on updatedReport are not directly saved in db
        em.detach(updatedReport);
        updatedReport
            .coalConfId(UPDATED_COAL_CONF_ID)
            .phone(UPDATED_PHONE)
            .coalType(UPDATED_COAL_TYPE)
            .checkDate(UPDATED_CHECK_DATE)
            .up_M4(UPDATED_UP_M_4)
            .up_A4(UPDATED_UP_A_4)
            .up_V4(UPDATED_UP_V_4)
            .up_S(UPDATED_UP_S)
            .up_C(UPDATED_UP_C)
            .report1(UPDATED_REPORT_1)
            .report2(UPDATED_REPORT_2)
            .report3(UPDATED_REPORT_3)
            .eggCone2(UPDATED_EGG_CONE_2)
            .up_Aar(UPDATED_UP_AAR)
            .up_Ad(UPDATED_UP_AD)
            .up_Var(UPDATED_UP_VAR)
            .up_Vd(UPDATED_UP_VD)
            .up_Vdaf(UPDATED_UP_VDAF)
            .up_H(UPDATED_UP_H)
            .slime(UPDATED_SLIME)
            .cleanCoal(UPDATED_CLEAN_COAL)
            .ganGue(UPDATED_GAN_GUE)
            .note(UPDATED_NOTE);

        restReportMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedReport.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(updatedReport))
            )
            .andExpect(status().isOk());

        // Validate the Report in the database
        List<Report> reportList = reportRepository.findAll();
        assertThat(reportList).hasSize(databaseSizeBeforeUpdate);
        Report testReport = reportList.get(reportList.size() - 1);
        assertThat(testReport.getCoalConfId()).isEqualTo(UPDATED_COAL_CONF_ID);
        assertThat(testReport.getPhone()).isEqualTo(UPDATED_PHONE);
        assertThat(testReport.getCoalType()).isEqualTo(UPDATED_COAL_TYPE);
        assertThat(testReport.getCheckDate()).isEqualTo(UPDATED_CHECK_DATE);
        assertThat(testReport.getUp_M4()).isEqualTo(UPDATED_UP_M_4);
        assertThat(testReport.getUp_A4()).isEqualTo(UPDATED_UP_A_4);
        assertThat(testReport.getUp_V4()).isEqualTo(UPDATED_UP_V_4);
        assertThat(testReport.getUp_S()).isEqualTo(UPDATED_UP_S);
        assertThat(testReport.getUp_C()).isEqualTo(UPDATED_UP_C);
        assertThat(testReport.getReport1()).isEqualTo(UPDATED_REPORT_1);
        assertThat(testReport.getReport2()).isEqualTo(UPDATED_REPORT_2);
        assertThat(testReport.getReport3()).isEqualTo(UPDATED_REPORT_3);
        assertThat(testReport.getEggCone2()).isEqualTo(UPDATED_EGG_CONE_2);
        assertThat(testReport.getUp_Aar()).isEqualTo(UPDATED_UP_AAR);
        assertThat(testReport.getUp_Ad()).isEqualTo(UPDATED_UP_AD);
        assertThat(testReport.getUp_Var()).isEqualTo(UPDATED_UP_VAR);
        assertThat(testReport.getUp_Vd()).isEqualTo(UPDATED_UP_VD);
        assertThat(testReport.getUp_Vdaf()).isEqualTo(UPDATED_UP_VDAF);
        assertThat(testReport.getUp_H()).isEqualTo(UPDATED_UP_H);
        assertThat(testReport.getSlime()).isEqualTo(UPDATED_SLIME);
        assertThat(testReport.getCleanCoal()).isEqualTo(UPDATED_CLEAN_COAL);
        assertThat(testReport.getGanGue()).isEqualTo(UPDATED_GAN_GUE);
        assertThat(testReport.getNote()).isEqualTo(UPDATED_NOTE);
    }

    @Test
    @Transactional
    void putNonExistingReport() throws Exception {
        int databaseSizeBeforeUpdate = reportRepository.findAll().size();
        report.setId(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restReportMockMvc
            .perform(
                put(ENTITY_API_URL_ID, report.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(report))
            )
            .andExpect(status().isBadRequest());

        // Validate the Report in the database
        List<Report> reportList = reportRepository.findAll();
        assertThat(reportList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchReport() throws Exception {
        int databaseSizeBeforeUpdate = reportRepository.findAll().size();
        report.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restReportMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(report))
            )
            .andExpect(status().isBadRequest());

        // Validate the Report in the database
        List<Report> reportList = reportRepository.findAll();
        assertThat(reportList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamReport() throws Exception {
        int databaseSizeBeforeUpdate = reportRepository.findAll().size();
        report.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restReportMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(report)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the Report in the database
        List<Report> reportList = reportRepository.findAll();
        assertThat(reportList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateReportWithPatch() throws Exception {
        // Initialize the database
        reportRepository.saveAndFlush(report);

        int databaseSizeBeforeUpdate = reportRepository.findAll().size();

        // Update the report using partial update
        Report partialUpdatedReport = new Report();
        partialUpdatedReport.setId(report.getId());

        partialUpdatedReport
            .coalConfId(UPDATED_COAL_CONF_ID)
            .checkDate(UPDATED_CHECK_DATE)
            .up_V4(UPDATED_UP_V_4)
            .up_S(UPDATED_UP_S)
            .up_C(UPDATED_UP_C)
            .report1(UPDATED_REPORT_1)
            .report2(UPDATED_REPORT_2)
            .eggCone2(UPDATED_EGG_CONE_2)
            .up_Aar(UPDATED_UP_AAR)
            .up_Ad(UPDATED_UP_AD)
            .up_Vd(UPDATED_UP_VD)
            .up_H(UPDATED_UP_H)
            .slime(UPDATED_SLIME);

        restReportMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedReport.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedReport))
            )
            .andExpect(status().isOk());

        // Validate the Report in the database
        List<Report> reportList = reportRepository.findAll();
        assertThat(reportList).hasSize(databaseSizeBeforeUpdate);
        Report testReport = reportList.get(reportList.size() - 1);
        assertThat(testReport.getCoalConfId()).isEqualTo(UPDATED_COAL_CONF_ID);
        assertThat(testReport.getPhone()).isEqualTo(DEFAULT_PHONE);
        assertThat(testReport.getCoalType()).isEqualTo(DEFAULT_COAL_TYPE);
        assertThat(testReport.getCheckDate()).isEqualTo(UPDATED_CHECK_DATE);
        assertThat(testReport.getUp_M4()).isEqualTo(DEFAULT_UP_M_4);
        assertThat(testReport.getUp_A4()).isEqualTo(DEFAULT_UP_A_4);
        assertThat(testReport.getUp_V4()).isEqualTo(UPDATED_UP_V_4);
        assertThat(testReport.getUp_S()).isEqualTo(UPDATED_UP_S);
        assertThat(testReport.getUp_C()).isEqualTo(UPDATED_UP_C);
        assertThat(testReport.getReport1()).isEqualTo(UPDATED_REPORT_1);
        assertThat(testReport.getReport2()).isEqualTo(UPDATED_REPORT_2);
        assertThat(testReport.getReport3()).isEqualTo(DEFAULT_REPORT_3);
        assertThat(testReport.getEggCone2()).isEqualTo(UPDATED_EGG_CONE_2);
        assertThat(testReport.getUp_Aar()).isEqualTo(UPDATED_UP_AAR);
        assertThat(testReport.getUp_Ad()).isEqualTo(UPDATED_UP_AD);
        assertThat(testReport.getUp_Var()).isEqualTo(DEFAULT_UP_VAR);
        assertThat(testReport.getUp_Vd()).isEqualTo(UPDATED_UP_VD);
        assertThat(testReport.getUp_Vdaf()).isEqualTo(DEFAULT_UP_VDAF);
        assertThat(testReport.getUp_H()).isEqualTo(UPDATED_UP_H);
        assertThat(testReport.getSlime()).isEqualTo(UPDATED_SLIME);
        assertThat(testReport.getCleanCoal()).isEqualTo(DEFAULT_CLEAN_COAL);
        assertThat(testReport.getGanGue()).isEqualTo(DEFAULT_GAN_GUE);
        assertThat(testReport.getNote()).isEqualTo(DEFAULT_NOTE);
    }

    @Test
    @Transactional
    void fullUpdateReportWithPatch() throws Exception {
        // Initialize the database
        reportRepository.saveAndFlush(report);

        int databaseSizeBeforeUpdate = reportRepository.findAll().size();

        // Update the report using partial update
        Report partialUpdatedReport = new Report();
        partialUpdatedReport.setId(report.getId());

        partialUpdatedReport
            .coalConfId(UPDATED_COAL_CONF_ID)
            .phone(UPDATED_PHONE)
            .coalType(UPDATED_COAL_TYPE)
            .checkDate(UPDATED_CHECK_DATE)
            .up_M4(UPDATED_UP_M_4)
            .up_A4(UPDATED_UP_A_4)
            .up_V4(UPDATED_UP_V_4)
            .up_S(UPDATED_UP_S)
            .up_C(UPDATED_UP_C)
            .report1(UPDATED_REPORT_1)
            .report2(UPDATED_REPORT_2)
            .report3(UPDATED_REPORT_3)
            .eggCone2(UPDATED_EGG_CONE_2)
            .up_Aar(UPDATED_UP_AAR)
            .up_Ad(UPDATED_UP_AD)
            .up_Var(UPDATED_UP_VAR)
            .up_Vd(UPDATED_UP_VD)
            .up_Vdaf(UPDATED_UP_VDAF)
            .up_H(UPDATED_UP_H)
            .slime(UPDATED_SLIME)
            .cleanCoal(UPDATED_CLEAN_COAL)
            .ganGue(UPDATED_GAN_GUE)
            .note(UPDATED_NOTE);

        restReportMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedReport.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedReport))
            )
            .andExpect(status().isOk());

        // Validate the Report in the database
        List<Report> reportList = reportRepository.findAll();
        assertThat(reportList).hasSize(databaseSizeBeforeUpdate);
        Report testReport = reportList.get(reportList.size() - 1);
        assertThat(testReport.getCoalConfId()).isEqualTo(UPDATED_COAL_CONF_ID);
        assertThat(testReport.getPhone()).isEqualTo(UPDATED_PHONE);
        assertThat(testReport.getCoalType()).isEqualTo(UPDATED_COAL_TYPE);
        assertThat(testReport.getCheckDate()).isEqualTo(UPDATED_CHECK_DATE);
        assertThat(testReport.getUp_M4()).isEqualTo(UPDATED_UP_M_4);
        assertThat(testReport.getUp_A4()).isEqualTo(UPDATED_UP_A_4);
        assertThat(testReport.getUp_V4()).isEqualTo(UPDATED_UP_V_4);
        assertThat(testReport.getUp_S()).isEqualTo(UPDATED_UP_S);
        assertThat(testReport.getUp_C()).isEqualTo(UPDATED_UP_C);
        assertThat(testReport.getReport1()).isEqualTo(UPDATED_REPORT_1);
        assertThat(testReport.getReport2()).isEqualTo(UPDATED_REPORT_2);
        assertThat(testReport.getReport3()).isEqualTo(UPDATED_REPORT_3);
        assertThat(testReport.getEggCone2()).isEqualTo(UPDATED_EGG_CONE_2);
        assertThat(testReport.getUp_Aar()).isEqualTo(UPDATED_UP_AAR);
        assertThat(testReport.getUp_Ad()).isEqualTo(UPDATED_UP_AD);
        assertThat(testReport.getUp_Var()).isEqualTo(UPDATED_UP_VAR);
        assertThat(testReport.getUp_Vd()).isEqualTo(UPDATED_UP_VD);
        assertThat(testReport.getUp_Vdaf()).isEqualTo(UPDATED_UP_VDAF);
        assertThat(testReport.getUp_H()).isEqualTo(UPDATED_UP_H);
        assertThat(testReport.getSlime()).isEqualTo(UPDATED_SLIME);
        assertThat(testReport.getCleanCoal()).isEqualTo(UPDATED_CLEAN_COAL);
        assertThat(testReport.getGanGue()).isEqualTo(UPDATED_GAN_GUE);
        assertThat(testReport.getNote()).isEqualTo(UPDATED_NOTE);
    }

    @Test
    @Transactional
    void patchNonExistingReport() throws Exception {
        int databaseSizeBeforeUpdate = reportRepository.findAll().size();
        report.setId(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restReportMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, report.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(report))
            )
            .andExpect(status().isBadRequest());

        // Validate the Report in the database
        List<Report> reportList = reportRepository.findAll();
        assertThat(reportList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchReport() throws Exception {
        int databaseSizeBeforeUpdate = reportRepository.findAll().size();
        report.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restReportMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(report))
            )
            .andExpect(status().isBadRequest());

        // Validate the Report in the database
        List<Report> reportList = reportRepository.findAll();
        assertThat(reportList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamReport() throws Exception {
        int databaseSizeBeforeUpdate = reportRepository.findAll().size();
        report.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restReportMockMvc
            .perform(patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(TestUtil.convertObjectToJsonBytes(report)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the Report in the database
        List<Report> reportList = reportRepository.findAll();
        assertThat(reportList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteReport() throws Exception {
        // Initialize the database
        reportRepository.saveAndFlush(report);

        int databaseSizeBeforeDelete = reportRepository.findAll().size();

        // Delete the report
        restReportMockMvc
            .perform(delete(ENTITY_API_URL_ID, report.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Report> reportList = reportRepository.findAll();
        assertThat(reportList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
