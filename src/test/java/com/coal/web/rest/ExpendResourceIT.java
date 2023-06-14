package com.coal.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.coal.IntegrationTest;
import com.coal.domain.Expend;
import com.coal.repository.ExpendRepository;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
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
 * Integration tests for the {@link ExpendResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class ExpendResourceIT {

    private static final Instant DEFAULT_PAY_TIME = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_PAY_TIME = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Float DEFAULT_AMOUNT = 1F;
    private static final Float UPDATED_AMOUNT = 2F;

    private static final String DEFAULT_DIRECTION = "AAAAAAAAAA";
    private static final String UPDATED_DIRECTION = "BBBBBBBBBB";

    private static final String DEFAULT_PAY_WAY = "AAAAAAAAAA";
    private static final String UPDATED_PAY_WAY = "BBBBBBBBBB";

    private static final String DEFAULT_WRITER = "AAAAAAAAAA";
    private static final String UPDATED_WRITER = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/expends";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private ExpendRepository expendRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restExpendMockMvc;

    private Expend expend;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Expend createEntity(EntityManager em) {
        Expend expend = new Expend()
            .payTime(DEFAULT_PAY_TIME)
            .amount(DEFAULT_AMOUNT)
            .direction(DEFAULT_DIRECTION)
            .payWay(DEFAULT_PAY_WAY)
            .writer(DEFAULT_WRITER);
        return expend;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Expend createUpdatedEntity(EntityManager em) {
        Expend expend = new Expend()
            .payTime(UPDATED_PAY_TIME)
            .amount(UPDATED_AMOUNT)
            .direction(UPDATED_DIRECTION)
            .payWay(UPDATED_PAY_WAY)
            .writer(UPDATED_WRITER);
        return expend;
    }

    @BeforeEach
    public void initTest() {
        expend = createEntity(em);
    }

    @Test
    @Transactional
    void createExpend() throws Exception {
        int databaseSizeBeforeCreate = expendRepository.findAll().size();
        // Create the Expend
        restExpendMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(expend)))
            .andExpect(status().isCreated());

        // Validate the Expend in the database
        List<Expend> expendList = expendRepository.findAll();
        assertThat(expendList).hasSize(databaseSizeBeforeCreate + 1);
        Expend testExpend = expendList.get(expendList.size() - 1);
        assertThat(testExpend.getPayTime()).isEqualTo(DEFAULT_PAY_TIME);
        assertThat(testExpend.getAmount()).isEqualTo(DEFAULT_AMOUNT);
        assertThat(testExpend.getDirection()).isEqualTo(DEFAULT_DIRECTION);
        assertThat(testExpend.getPayWay()).isEqualTo(DEFAULT_PAY_WAY);
        assertThat(testExpend.getWriter()).isEqualTo(DEFAULT_WRITER);
    }

    @Test
    @Transactional
    void createExpendWithExistingId() throws Exception {
        // Create the Expend with an existing ID
        expend.setId(1L);

        int databaseSizeBeforeCreate = expendRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restExpendMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(expend)))
            .andExpect(status().isBadRequest());

        // Validate the Expend in the database
        List<Expend> expendList = expendRepository.findAll();
        assertThat(expendList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllExpends() throws Exception {
        // Initialize the database
        expendRepository.saveAndFlush(expend);

        // Get all the expendList
        restExpendMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(expend.getId().intValue())))
            .andExpect(jsonPath("$.[*].payTime").value(hasItem(DEFAULT_PAY_TIME.toString())))
            .andExpect(jsonPath("$.[*].amount").value(hasItem(DEFAULT_AMOUNT.doubleValue())))
            .andExpect(jsonPath("$.[*].direction").value(hasItem(DEFAULT_DIRECTION)))
            .andExpect(jsonPath("$.[*].payWay").value(hasItem(DEFAULT_PAY_WAY)))
            .andExpect(jsonPath("$.[*].writer").value(hasItem(DEFAULT_WRITER)));
    }

    @Test
    @Transactional
    void getExpend() throws Exception {
        // Initialize the database
        expendRepository.saveAndFlush(expend);

        // Get the expend
        restExpendMockMvc
            .perform(get(ENTITY_API_URL_ID, expend.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(expend.getId().intValue()))
            .andExpect(jsonPath("$.payTime").value(DEFAULT_PAY_TIME.toString()))
            .andExpect(jsonPath("$.amount").value(DEFAULT_AMOUNT.doubleValue()))
            .andExpect(jsonPath("$.direction").value(DEFAULT_DIRECTION))
            .andExpect(jsonPath("$.payWay").value(DEFAULT_PAY_WAY))
            .andExpect(jsonPath("$.writer").value(DEFAULT_WRITER));
    }

    @Test
    @Transactional
    void getNonExistingExpend() throws Exception {
        // Get the expend
        restExpendMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putNewExpend() throws Exception {
        // Initialize the database
        expendRepository.saveAndFlush(expend);

        int databaseSizeBeforeUpdate = expendRepository.findAll().size();

        // Update the expend
        Expend updatedExpend = expendRepository.findById(expend.getId()).get();
        // Disconnect from session so that the updates on updatedExpend are not directly saved in db
        em.detach(updatedExpend);
        updatedExpend
            .payTime(UPDATED_PAY_TIME)
            .amount(UPDATED_AMOUNT)
            .direction(UPDATED_DIRECTION)
            .payWay(UPDATED_PAY_WAY)
            .writer(UPDATED_WRITER);

        restExpendMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedExpend.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(updatedExpend))
            )
            .andExpect(status().isOk());

        // Validate the Expend in the database
        List<Expend> expendList = expendRepository.findAll();
        assertThat(expendList).hasSize(databaseSizeBeforeUpdate);
        Expend testExpend = expendList.get(expendList.size() - 1);
        assertThat(testExpend.getPayTime()).isEqualTo(UPDATED_PAY_TIME);
        assertThat(testExpend.getAmount()).isEqualTo(UPDATED_AMOUNT);
        assertThat(testExpend.getDirection()).isEqualTo(UPDATED_DIRECTION);
        assertThat(testExpend.getPayWay()).isEqualTo(UPDATED_PAY_WAY);
        assertThat(testExpend.getWriter()).isEqualTo(UPDATED_WRITER);
    }

    @Test
    @Transactional
    void putNonExistingExpend() throws Exception {
        int databaseSizeBeforeUpdate = expendRepository.findAll().size();
        expend.setId(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restExpendMockMvc
            .perform(
                put(ENTITY_API_URL_ID, expend.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(expend))
            )
            .andExpect(status().isBadRequest());

        // Validate the Expend in the database
        List<Expend> expendList = expendRepository.findAll();
        assertThat(expendList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchExpend() throws Exception {
        int databaseSizeBeforeUpdate = expendRepository.findAll().size();
        expend.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restExpendMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(expend))
            )
            .andExpect(status().isBadRequest());

        // Validate the Expend in the database
        List<Expend> expendList = expendRepository.findAll();
        assertThat(expendList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamExpend() throws Exception {
        int databaseSizeBeforeUpdate = expendRepository.findAll().size();
        expend.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restExpendMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(expend)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the Expend in the database
        List<Expend> expendList = expendRepository.findAll();
        assertThat(expendList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateExpendWithPatch() throws Exception {
        // Initialize the database
        expendRepository.saveAndFlush(expend);

        int databaseSizeBeforeUpdate = expendRepository.findAll().size();

        // Update the expend using partial update
        Expend partialUpdatedExpend = new Expend();
        partialUpdatedExpend.setId(expend.getId());

        partialUpdatedExpend.payTime(UPDATED_PAY_TIME).amount(UPDATED_AMOUNT).direction(UPDATED_DIRECTION).writer(UPDATED_WRITER);

        restExpendMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedExpend.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedExpend))
            )
            .andExpect(status().isOk());

        // Validate the Expend in the database
        List<Expend> expendList = expendRepository.findAll();
        assertThat(expendList).hasSize(databaseSizeBeforeUpdate);
        Expend testExpend = expendList.get(expendList.size() - 1);
        assertThat(testExpend.getPayTime()).isEqualTo(UPDATED_PAY_TIME);
        assertThat(testExpend.getAmount()).isEqualTo(UPDATED_AMOUNT);
        assertThat(testExpend.getDirection()).isEqualTo(UPDATED_DIRECTION);
        assertThat(testExpend.getPayWay()).isEqualTo(DEFAULT_PAY_WAY);
        assertThat(testExpend.getWriter()).isEqualTo(UPDATED_WRITER);
    }

    @Test
    @Transactional
    void fullUpdateExpendWithPatch() throws Exception {
        // Initialize the database
        expendRepository.saveAndFlush(expend);

        int databaseSizeBeforeUpdate = expendRepository.findAll().size();

        // Update the expend using partial update
        Expend partialUpdatedExpend = new Expend();
        partialUpdatedExpend.setId(expend.getId());

        partialUpdatedExpend
            .payTime(UPDATED_PAY_TIME)
            .amount(UPDATED_AMOUNT)
            .direction(UPDATED_DIRECTION)
            .payWay(UPDATED_PAY_WAY)
            .writer(UPDATED_WRITER);

        restExpendMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedExpend.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedExpend))
            )
            .andExpect(status().isOk());

        // Validate the Expend in the database
        List<Expend> expendList = expendRepository.findAll();
        assertThat(expendList).hasSize(databaseSizeBeforeUpdate);
        Expend testExpend = expendList.get(expendList.size() - 1);
        assertThat(testExpend.getPayTime()).isEqualTo(UPDATED_PAY_TIME);
        assertThat(testExpend.getAmount()).isEqualTo(UPDATED_AMOUNT);
        assertThat(testExpend.getDirection()).isEqualTo(UPDATED_DIRECTION);
        assertThat(testExpend.getPayWay()).isEqualTo(UPDATED_PAY_WAY);
        assertThat(testExpend.getWriter()).isEqualTo(UPDATED_WRITER);
    }

    @Test
    @Transactional
    void patchNonExistingExpend() throws Exception {
        int databaseSizeBeforeUpdate = expendRepository.findAll().size();
        expend.setId(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restExpendMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, expend.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(expend))
            )
            .andExpect(status().isBadRequest());

        // Validate the Expend in the database
        List<Expend> expendList = expendRepository.findAll();
        assertThat(expendList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchExpend() throws Exception {
        int databaseSizeBeforeUpdate = expendRepository.findAll().size();
        expend.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restExpendMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(expend))
            )
            .andExpect(status().isBadRequest());

        // Validate the Expend in the database
        List<Expend> expendList = expendRepository.findAll();
        assertThat(expendList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamExpend() throws Exception {
        int databaseSizeBeforeUpdate = expendRepository.findAll().size();
        expend.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restExpendMockMvc
            .perform(patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(TestUtil.convertObjectToJsonBytes(expend)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the Expend in the database
        List<Expend> expendList = expendRepository.findAll();
        assertThat(expendList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteExpend() throws Exception {
        // Initialize the database
        expendRepository.saveAndFlush(expend);

        int databaseSizeBeforeDelete = expendRepository.findAll().size();

        // Delete the expend
        restExpendMockMvc
            .perform(delete(ENTITY_API_URL_ID, expend.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Expend> expendList = expendRepository.findAll();
        assertThat(expendList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
