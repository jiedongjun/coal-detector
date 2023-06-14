package com.coal.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.coal.IntegrationTest;
import com.coal.domain.CoalConf;
import com.coal.repository.CoalConfRepository;
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
 * Integration tests for the {@link CoalConfResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class CoalConfResourceIT {

    private static final String DEFAULT_PROJECT = "AAAAAAAAAA";
    private static final String UPDATED_PROJECT = "BBBBBBBBBB";

    private static final String DEFAULT_PHONE = "AAAAAAAAAA";
    private static final String UPDATED_PHONE = "BBBBBBBBBB";

    private static final Integer DEFAULT_SEND = 1;
    private static final Integer UPDATED_SEND = 2;

    private static final String DEFAULT_SOFT_2 = "AAAAAAAAAA";
    private static final String UPDATED_SOFT_2 = "BBBBBBBBBB";

    private static final String DEFAULT_SOFT_3 = "AAAAAAAAAA";
    private static final String UPDATED_SOFT_3 = "BBBBBBBBBB";

    private static final Integer DEFAULT_PAY_STATUS = 1;
    private static final Integer UPDATED_PAY_STATUS = 2;

    private static final String DEFAULT_NOTE_1 = "AAAAAAAAAA";
    private static final String UPDATED_NOTE_1 = "BBBBBBBBBB";

    private static final String DEFAULT_NOTE_2 = "AAAAAAAAAA";
    private static final String UPDATED_NOTE_2 = "BBBBBBBBBB";

    private static final String DEFAULT_COAL_TYPE = "AAAAAAAAAA";
    private static final String UPDATED_COAL_TYPE = "BBBBBBBBBB";

    private static final Float DEFAULT_UP_M = 1F;
    private static final Float UPDATED_UP_M = 2F;

    private static final Float DEFAULT_UP_M_1 = 1F;
    private static final Float UPDATED_UP_M_1 = 2F;

    private static final Float DEFAULT_UP_M_2 = 1F;
    private static final Float UPDATED_UP_M_2 = 2F;

    private static final Float DEFAULT_UP_M_3 = 1F;
    private static final Float UPDATED_UP_M_3 = 2F;

    private static final Float DEFAULT_UP_M_4 = 1F;
    private static final Float UPDATED_UP_M_4 = 2F;

    private static final Float DEFAULT_UP_A = 1F;
    private static final Float UPDATED_UP_A = 2F;

    private static final Float DEFAULT_UP_A_1 = 1F;
    private static final Float UPDATED_UP_A_1 = 2F;

    private static final Float DEFAULT_UP_A_2 = 1F;
    private static final Float UPDATED_UP_A_2 = 2F;

    private static final Float DEFAULT_UP_A_3 = 1F;
    private static final Float UPDATED_UP_A_3 = 2F;

    private static final Float DEFAULT_UP_A_4 = 1F;
    private static final Float UPDATED_UP_A_4 = 2F;

    private static final Float DEFAULT_UP_V = 1F;
    private static final Float UPDATED_UP_V = 2F;

    private static final Float DEFAULT_UP_V_1 = 1F;
    private static final Float UPDATED_UP_V_1 = 2F;

    private static final Float DEFAULT_UP_V_2 = 1F;
    private static final Float UPDATED_UP_V_2 = 2F;

    private static final Float DEFAULT_UP_V_3 = 1F;
    private static final Float UPDATED_UP_V_3 = 2F;

    private static final Float DEFAULT_UP_V_4 = 1F;
    private static final Float UPDATED_UP_V_4 = 2F;

    private static final Float DEFAULT_LOW_M = 1F;
    private static final Float UPDATED_LOW_M = 2F;

    private static final Float DEFAULT_LOW_M_1 = 1F;
    private static final Float UPDATED_LOW_M_1 = 2F;

    private static final Float DEFAULT_LOW_M_2 = 1F;
    private static final Float UPDATED_LOW_M_2 = 2F;

    private static final Float DEFAULT_LOW_M_3 = 1F;
    private static final Float UPDATED_LOW_M_3 = 2F;

    private static final Float DEFAULT_LOW_M_4 = 1F;
    private static final Float UPDATED_LOW_M_4 = 2F;

    private static final String DEFAULT_EGG_CONE_1 = "AAAAAAAAAA";
    private static final String UPDATED_EGG_CONE_1 = "BBBBBBBBBB";

    private static final String DEFAULT_EGG_CONE_2 = "AAAAAAAAAA";
    private static final String UPDATED_EGG_CONE_2 = "BBBBBBBBBB";

    private static final String DEFAULT_EGG_CONE_3 = "AAAAAAAAAA";
    private static final String UPDATED_EGG_CONE_3 = "BBBBBBBBBB";

    private static final Float DEFAULT_PRICE_1 = 1F;
    private static final Float UPDATED_PRICE_1 = 2F;

    private static final Float DEFAULT_PRICE_2 = 1F;
    private static final Float UPDATED_PRICE_2 = 2F;

    private static final String ENTITY_API_URL = "/api/coal-confs";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private CoalConfRepository coalConfRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restCoalConfMockMvc;

    private CoalConf coalConf;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static CoalConf createEntity(EntityManager em) {
        CoalConf coalConf = new CoalConf()
            .project(DEFAULT_PROJECT)
            .phone(DEFAULT_PHONE)
            .note1(DEFAULT_NOTE_1)
            .note2(DEFAULT_NOTE_2)
            .coalType(DEFAULT_COAL_TYPE)
            .up_M(DEFAULT_UP_M)
            .up_M1(DEFAULT_UP_M_1)
            .up_M2(DEFAULT_UP_M_2)
            .up_M3(DEFAULT_UP_M_3)
            .up_M4(DEFAULT_UP_M_4)
            .up_A(DEFAULT_UP_A)
            .up_A1(DEFAULT_UP_A_1)
            .up_A2(DEFAULT_UP_A_2)
            .up_A3(DEFAULT_UP_A_3)
            .up_A4(DEFAULT_UP_A_4)
            .up_V(DEFAULT_UP_V)
            .up_V1(DEFAULT_UP_V_1)
            .up_V2(DEFAULT_UP_V_2)
            .up_V3(DEFAULT_UP_V_3)
            .up_V4(DEFAULT_UP_V_4)
            .low_m(DEFAULT_LOW_M)
            .low_m1(DEFAULT_LOW_M_1)
            .low_m2(DEFAULT_LOW_M_2)
            .low_m3(DEFAULT_LOW_M_3)
            .low_m4(DEFAULT_LOW_M_4)
            .price1(DEFAULT_PRICE_1)
            .price2(DEFAULT_PRICE_2);
        return coalConf;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static CoalConf createUpdatedEntity(EntityManager em) {
        CoalConf coalConf = new CoalConf()
            .project(UPDATED_PROJECT)
            .phone(UPDATED_PHONE)
            .note1(UPDATED_NOTE_1)
            .note2(UPDATED_NOTE_2)
            .coalType(UPDATED_COAL_TYPE)
            .up_M(UPDATED_UP_M)
            .up_M1(UPDATED_UP_M_1)
            .up_M2(UPDATED_UP_M_2)
            .up_M3(UPDATED_UP_M_3)
            .up_M4(UPDATED_UP_M_4)
            .up_A(UPDATED_UP_A)
            .up_A1(UPDATED_UP_A_1)
            .up_A2(UPDATED_UP_A_2)
            .up_A3(UPDATED_UP_A_3)
            .up_A4(UPDATED_UP_A_4)
            .up_V(UPDATED_UP_V)
            .up_V1(UPDATED_UP_V_1)
            .up_V2(UPDATED_UP_V_2)
            .up_V3(UPDATED_UP_V_3)
            .up_V4(UPDATED_UP_V_4)
            .low_m(UPDATED_LOW_M)
            .low_m1(UPDATED_LOW_M_1)
            .low_m2(UPDATED_LOW_M_2)
            .low_m3(UPDATED_LOW_M_3)
            .low_m4(UPDATED_LOW_M_4)
            .price1(UPDATED_PRICE_1)
            .price2(UPDATED_PRICE_2);
        return coalConf;
    }

    @BeforeEach
    public void initTest() {
        coalConf = createEntity(em);
    }

    @Test
    @Transactional
    void createCoalConf() throws Exception {
        int databaseSizeBeforeCreate = coalConfRepository.findAll().size();
        // Create the CoalConf
        restCoalConfMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(coalConf)))
            .andExpect(status().isCreated());

        // Validate the CoalConf in the database
        List<CoalConf> coalConfList = coalConfRepository.findAll();
        assertThat(coalConfList).hasSize(databaseSizeBeforeCreate + 1);
        CoalConf testCoalConf = coalConfList.get(coalConfList.size() - 1);
        assertThat(testCoalConf.getProject()).isEqualTo(DEFAULT_PROJECT);
        assertThat(testCoalConf.getPhone()).isEqualTo(DEFAULT_PHONE);
        assertThat(testCoalConf.getSend()).isEqualTo(DEFAULT_SEND);
        assertThat(testCoalConf.getSoft2()).isEqualTo(DEFAULT_SOFT_2);
        assertThat(testCoalConf.getSoft3()).isEqualTo(DEFAULT_SOFT_3);
        assertThat(testCoalConf.getPayStatus()).isEqualTo(DEFAULT_PAY_STATUS);
        assertThat(testCoalConf.getNote1()).isEqualTo(DEFAULT_NOTE_1);
        assertThat(testCoalConf.getNote2()).isEqualTo(DEFAULT_NOTE_2);
        assertThat(testCoalConf.getCoalType()).isEqualTo(DEFAULT_COAL_TYPE);
        assertThat(testCoalConf.getUp_M()).isEqualTo(DEFAULT_UP_M);
        assertThat(testCoalConf.getUp_M1()).isEqualTo(DEFAULT_UP_M_1);
        assertThat(testCoalConf.getUp_M2()).isEqualTo(DEFAULT_UP_M_2);
        assertThat(testCoalConf.getUp_M3()).isEqualTo(DEFAULT_UP_M_3);
        assertThat(testCoalConf.getUp_M4()).isEqualTo(DEFAULT_UP_M_4);
        assertThat(testCoalConf.getUp_A()).isEqualTo(DEFAULT_UP_A);
        assertThat(testCoalConf.getUp_A1()).isEqualTo(DEFAULT_UP_A_1);
        assertThat(testCoalConf.getUp_A2()).isEqualTo(DEFAULT_UP_A_2);
        assertThat(testCoalConf.getUp_A3()).isEqualTo(DEFAULT_UP_A_3);
        assertThat(testCoalConf.getUp_A4()).isEqualTo(DEFAULT_UP_A_4);
        assertThat(testCoalConf.getUp_V()).isEqualTo(DEFAULT_UP_V);
        assertThat(testCoalConf.getUp_V1()).isEqualTo(DEFAULT_UP_V_1);
        assertThat(testCoalConf.getUp_V2()).isEqualTo(DEFAULT_UP_V_2);
        assertThat(testCoalConf.getUp_V3()).isEqualTo(DEFAULT_UP_V_3);
        assertThat(testCoalConf.getUp_V4()).isEqualTo(DEFAULT_UP_V_4);
        assertThat(testCoalConf.getLow_m()).isEqualTo(DEFAULT_LOW_M);
        assertThat(testCoalConf.getLow_m1()).isEqualTo(DEFAULT_LOW_M_1);
        assertThat(testCoalConf.getLow_m2()).isEqualTo(DEFAULT_LOW_M_2);
        assertThat(testCoalConf.getLow_m3()).isEqualTo(DEFAULT_LOW_M_3);
        assertThat(testCoalConf.getLow_m4()).isEqualTo(DEFAULT_LOW_M_4);
        assertThat(testCoalConf.getEggCone1()).isEqualTo(DEFAULT_EGG_CONE_1);
        assertThat(testCoalConf.getEggCone2()).isEqualTo(DEFAULT_EGG_CONE_2);
        assertThat(testCoalConf.getEggCone3()).isEqualTo(DEFAULT_EGG_CONE_3);
        assertThat(testCoalConf.getPrice1()).isEqualTo(DEFAULT_PRICE_1);
        assertThat(testCoalConf.getPrice2()).isEqualTo(DEFAULT_PRICE_2);
    }

    @Test
    @Transactional
    void createCoalConfWithExistingId() throws Exception {
        // Create the CoalConf with an existing ID
        coalConf.setId(1L);

        int databaseSizeBeforeCreate = coalConfRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restCoalConfMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(coalConf)))
            .andExpect(status().isBadRequest());

        // Validate the CoalConf in the database
        List<CoalConf> coalConfList = coalConfRepository.findAll();
        assertThat(coalConfList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllCoalConfs() throws Exception {
        // Initialize the database
        coalConfRepository.saveAndFlush(coalConf);

        // Get all the coalConfList
        restCoalConfMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(coalConf.getId().intValue())))
            .andExpect(jsonPath("$.[*].project").value(hasItem(DEFAULT_PROJECT)))
            .andExpect(jsonPath("$.[*].phone").value(hasItem(DEFAULT_PHONE)))
            .andExpect(jsonPath("$.[*].send").value(hasItem(DEFAULT_SEND)))
            .andExpect(jsonPath("$.[*].soft2").value(hasItem(DEFAULT_SOFT_2)))
            .andExpect(jsonPath("$.[*].soft3").value(hasItem(DEFAULT_SOFT_3)))
            .andExpect(jsonPath("$.[*].payStatus").value(hasItem(DEFAULT_PAY_STATUS)))
            .andExpect(jsonPath("$.[*].note1").value(hasItem(DEFAULT_NOTE_1)))
            .andExpect(jsonPath("$.[*].note2").value(hasItem(DEFAULT_NOTE_2)))
            .andExpect(jsonPath("$.[*].coalType").value(hasItem(DEFAULT_COAL_TYPE)))
            .andExpect(jsonPath("$.[*].up_M").value(hasItem(DEFAULT_UP_M.doubleValue())))
            .andExpect(jsonPath("$.[*].up_M1").value(hasItem(DEFAULT_UP_M_1.doubleValue())))
            .andExpect(jsonPath("$.[*].up_M2").value(hasItem(DEFAULT_UP_M_2.doubleValue())))
            .andExpect(jsonPath("$.[*].up_M3").value(hasItem(DEFAULT_UP_M_3.doubleValue())))
            .andExpect(jsonPath("$.[*].up_M4").value(hasItem(DEFAULT_UP_M_4.doubleValue())))
            .andExpect(jsonPath("$.[*].up_A").value(hasItem(DEFAULT_UP_A.doubleValue())))
            .andExpect(jsonPath("$.[*].up_A1").value(hasItem(DEFAULT_UP_A_1.doubleValue())))
            .andExpect(jsonPath("$.[*].up_A2").value(hasItem(DEFAULT_UP_A_2.doubleValue())))
            .andExpect(jsonPath("$.[*].up_A3").value(hasItem(DEFAULT_UP_A_3.doubleValue())))
            .andExpect(jsonPath("$.[*].up_A4").value(hasItem(DEFAULT_UP_A_4.doubleValue())))
            .andExpect(jsonPath("$.[*].up_V").value(hasItem(DEFAULT_UP_V.doubleValue())))
            .andExpect(jsonPath("$.[*].up_V1").value(hasItem(DEFAULT_UP_V_1.doubleValue())))
            .andExpect(jsonPath("$.[*].up_V2").value(hasItem(DEFAULT_UP_V_2.doubleValue())))
            .andExpect(jsonPath("$.[*].up_V3").value(hasItem(DEFAULT_UP_V_3.doubleValue())))
            .andExpect(jsonPath("$.[*].up_V4").value(hasItem(DEFAULT_UP_V_4.doubleValue())))
            .andExpect(jsonPath("$.[*].low_m").value(hasItem(DEFAULT_LOW_M.doubleValue())))
            .andExpect(jsonPath("$.[*].low_m1").value(hasItem(DEFAULT_LOW_M_1.doubleValue())))
            .andExpect(jsonPath("$.[*].low_m2").value(hasItem(DEFAULT_LOW_M_2.doubleValue())))
            .andExpect(jsonPath("$.[*].low_m3").value(hasItem(DEFAULT_LOW_M_3.doubleValue())))
            .andExpect(jsonPath("$.[*].low_m4").value(hasItem(DEFAULT_LOW_M_4.doubleValue())))
            .andExpect(jsonPath("$.[*].eggCone1").value(hasItem(DEFAULT_EGG_CONE_1)))
            .andExpect(jsonPath("$.[*].eggCone2").value(hasItem(DEFAULT_EGG_CONE_2)))
            .andExpect(jsonPath("$.[*].eggCone3").value(hasItem(DEFAULT_EGG_CONE_3)))
            .andExpect(jsonPath("$.[*].price1").value(hasItem(DEFAULT_PRICE_1.doubleValue())))
            .andExpect(jsonPath("$.[*].price2").value(hasItem(DEFAULT_PRICE_2.doubleValue())));
    }

    @Test
    @Transactional
    void getCoalConf() throws Exception {
        // Initialize the database
        coalConfRepository.saveAndFlush(coalConf);

        // Get the coalConf
        restCoalConfMockMvc
            .perform(get(ENTITY_API_URL_ID, coalConf.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(coalConf.getId().intValue()))
            .andExpect(jsonPath("$.project").value(DEFAULT_PROJECT))
            .andExpect(jsonPath("$.phone").value(DEFAULT_PHONE))
            .andExpect(jsonPath("$.send").value(DEFAULT_SEND))
            .andExpect(jsonPath("$.soft2").value(DEFAULT_SOFT_2))
            .andExpect(jsonPath("$.soft3").value(DEFAULT_SOFT_3))
            .andExpect(jsonPath("$.payStatus").value(DEFAULT_PAY_STATUS))
            .andExpect(jsonPath("$.note1").value(DEFAULT_NOTE_1))
            .andExpect(jsonPath("$.note2").value(DEFAULT_NOTE_2))
            .andExpect(jsonPath("$.coalType").value(DEFAULT_COAL_TYPE))
            .andExpect(jsonPath("$.up_M").value(DEFAULT_UP_M.doubleValue()))
            .andExpect(jsonPath("$.up_M1").value(DEFAULT_UP_M_1.doubleValue()))
            .andExpect(jsonPath("$.up_M2").value(DEFAULT_UP_M_2.doubleValue()))
            .andExpect(jsonPath("$.up_M3").value(DEFAULT_UP_M_3.doubleValue()))
            .andExpect(jsonPath("$.up_M4").value(DEFAULT_UP_M_4.doubleValue()))
            .andExpect(jsonPath("$.up_A").value(DEFAULT_UP_A.doubleValue()))
            .andExpect(jsonPath("$.up_A1").value(DEFAULT_UP_A_1.doubleValue()))
            .andExpect(jsonPath("$.up_A2").value(DEFAULT_UP_A_2.doubleValue()))
            .andExpect(jsonPath("$.up_A3").value(DEFAULT_UP_A_3.doubleValue()))
            .andExpect(jsonPath("$.up_A4").value(DEFAULT_UP_A_4.doubleValue()))
            .andExpect(jsonPath("$.up_V").value(DEFAULT_UP_V.doubleValue()))
            .andExpect(jsonPath("$.up_V1").value(DEFAULT_UP_V_1.doubleValue()))
            .andExpect(jsonPath("$.up_V2").value(DEFAULT_UP_V_2.doubleValue()))
            .andExpect(jsonPath("$.up_V3").value(DEFAULT_UP_V_3.doubleValue()))
            .andExpect(jsonPath("$.up_V4").value(DEFAULT_UP_V_4.doubleValue()))
            .andExpect(jsonPath("$.low_m").value(DEFAULT_LOW_M.doubleValue()))
            .andExpect(jsonPath("$.low_m1").value(DEFAULT_LOW_M_1.doubleValue()))
            .andExpect(jsonPath("$.low_m2").value(DEFAULT_LOW_M_2.doubleValue()))
            .andExpect(jsonPath("$.low_m3").value(DEFAULT_LOW_M_3.doubleValue()))
            .andExpect(jsonPath("$.low_m4").value(DEFAULT_LOW_M_4.doubleValue()))
            .andExpect(jsonPath("$.eggCone1").value(DEFAULT_EGG_CONE_1))
            .andExpect(jsonPath("$.eggCone2").value(DEFAULT_EGG_CONE_2))
            .andExpect(jsonPath("$.eggCone3").value(DEFAULT_EGG_CONE_3))
            .andExpect(jsonPath("$.price1").value(DEFAULT_PRICE_1.doubleValue()))
            .andExpect(jsonPath("$.price2").value(DEFAULT_PRICE_2.doubleValue()));
    }

    @Test
    @Transactional
    void getNonExistingCoalConf() throws Exception {
        // Get the coalConf
        restCoalConfMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putNewCoalConf() throws Exception {
        // Initialize the database
        coalConfRepository.saveAndFlush(coalConf);

        int databaseSizeBeforeUpdate = coalConfRepository.findAll().size();

        // Update the coalConf
        CoalConf updatedCoalConf = coalConfRepository.findById(coalConf.getId()).get();
        // Disconnect from session so that the updates on updatedCoalConf are not directly saved in db
        em.detach(updatedCoalConf);
        updatedCoalConf
            .project(UPDATED_PROJECT)
            .phone(UPDATED_PHONE)
            .note1(UPDATED_NOTE_1)
            .note2(UPDATED_NOTE_2)
            .coalType(UPDATED_COAL_TYPE)
            .up_M(UPDATED_UP_M)
            .up_M1(UPDATED_UP_M_1)
            .up_M2(UPDATED_UP_M_2)
            .up_M3(UPDATED_UP_M_3)
            .up_M4(UPDATED_UP_M_4)
            .up_A(UPDATED_UP_A)
            .up_A1(UPDATED_UP_A_1)
            .up_A2(UPDATED_UP_A_2)
            .up_A3(UPDATED_UP_A_3)
            .up_A4(UPDATED_UP_A_4)
            .up_V(UPDATED_UP_V)
            .up_V1(UPDATED_UP_V_1)
            .up_V2(UPDATED_UP_V_2)
            .up_V3(UPDATED_UP_V_3)
            .up_V4(UPDATED_UP_V_4)
            .low_m(UPDATED_LOW_M)
            .low_m1(UPDATED_LOW_M_1)
            .low_m2(UPDATED_LOW_M_2)
            .low_m3(UPDATED_LOW_M_3)
            .low_m4(UPDATED_LOW_M_4)
            .price1(UPDATED_PRICE_1)
            .price2(UPDATED_PRICE_2);

        restCoalConfMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedCoalConf.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(updatedCoalConf))
            )
            .andExpect(status().isOk());

        // Validate the CoalConf in the database
        List<CoalConf> coalConfList = coalConfRepository.findAll();
        assertThat(coalConfList).hasSize(databaseSizeBeforeUpdate);
        CoalConf testCoalConf = coalConfList.get(coalConfList.size() - 1);
        assertThat(testCoalConf.getProject()).isEqualTo(UPDATED_PROJECT);
        assertThat(testCoalConf.getPhone()).isEqualTo(UPDATED_PHONE);
        assertThat(testCoalConf.getSend()).isEqualTo(UPDATED_SEND);
        assertThat(testCoalConf.getSoft2()).isEqualTo(UPDATED_SOFT_2);
        assertThat(testCoalConf.getSoft3()).isEqualTo(UPDATED_SOFT_3);
        assertThat(testCoalConf.getPayStatus()).isEqualTo(UPDATED_PAY_STATUS);
        assertThat(testCoalConf.getNote1()).isEqualTo(UPDATED_NOTE_1);
        assertThat(testCoalConf.getNote2()).isEqualTo(UPDATED_NOTE_2);
        assertThat(testCoalConf.getCoalType()).isEqualTo(UPDATED_COAL_TYPE);
        assertThat(testCoalConf.getUp_M()).isEqualTo(UPDATED_UP_M);
        assertThat(testCoalConf.getUp_M1()).isEqualTo(UPDATED_UP_M_1);
        assertThat(testCoalConf.getUp_M2()).isEqualTo(UPDATED_UP_M_2);
        assertThat(testCoalConf.getUp_M3()).isEqualTo(UPDATED_UP_M_3);
        assertThat(testCoalConf.getUp_M4()).isEqualTo(UPDATED_UP_M_4);
        assertThat(testCoalConf.getUp_A()).isEqualTo(UPDATED_UP_A);
        assertThat(testCoalConf.getUp_A1()).isEqualTo(UPDATED_UP_A_1);
        assertThat(testCoalConf.getUp_A2()).isEqualTo(UPDATED_UP_A_2);
        assertThat(testCoalConf.getUp_A3()).isEqualTo(UPDATED_UP_A_3);
        assertThat(testCoalConf.getUp_A4()).isEqualTo(UPDATED_UP_A_4);
        assertThat(testCoalConf.getUp_V()).isEqualTo(UPDATED_UP_V);
        assertThat(testCoalConf.getUp_V1()).isEqualTo(UPDATED_UP_V_1);
        assertThat(testCoalConf.getUp_V2()).isEqualTo(UPDATED_UP_V_2);
        assertThat(testCoalConf.getUp_V3()).isEqualTo(UPDATED_UP_V_3);
        assertThat(testCoalConf.getUp_V4()).isEqualTo(UPDATED_UP_V_4);
        assertThat(testCoalConf.getLow_m()).isEqualTo(UPDATED_LOW_M);
        assertThat(testCoalConf.getLow_m1()).isEqualTo(UPDATED_LOW_M_1);
        assertThat(testCoalConf.getLow_m2()).isEqualTo(UPDATED_LOW_M_2);
        assertThat(testCoalConf.getLow_m3()).isEqualTo(UPDATED_LOW_M_3);
        assertThat(testCoalConf.getLow_m4()).isEqualTo(UPDATED_LOW_M_4);
        assertThat(testCoalConf.getEggCone1()).isEqualTo(UPDATED_EGG_CONE_1);
        assertThat(testCoalConf.getEggCone2()).isEqualTo(UPDATED_EGG_CONE_2);
        assertThat(testCoalConf.getEggCone3()).isEqualTo(UPDATED_EGG_CONE_3);
        assertThat(testCoalConf.getPrice1()).isEqualTo(UPDATED_PRICE_1);
        assertThat(testCoalConf.getPrice2()).isEqualTo(UPDATED_PRICE_2);
    }

    @Test
    @Transactional
    void putNonExistingCoalConf() throws Exception {
        int databaseSizeBeforeUpdate = coalConfRepository.findAll().size();
        coalConf.setId(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restCoalConfMockMvc
            .perform(
                put(ENTITY_API_URL_ID, coalConf.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(coalConf))
            )
            .andExpect(status().isBadRequest());

        // Validate the CoalConf in the database
        List<CoalConf> coalConfList = coalConfRepository.findAll();
        assertThat(coalConfList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchCoalConf() throws Exception {
        int databaseSizeBeforeUpdate = coalConfRepository.findAll().size();
        coalConf.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restCoalConfMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(coalConf))
            )
            .andExpect(status().isBadRequest());

        // Validate the CoalConf in the database
        List<CoalConf> coalConfList = coalConfRepository.findAll();
        assertThat(coalConfList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamCoalConf() throws Exception {
        int databaseSizeBeforeUpdate = coalConfRepository.findAll().size();
        coalConf.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restCoalConfMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(coalConf)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the CoalConf in the database
        List<CoalConf> coalConfList = coalConfRepository.findAll();
        assertThat(coalConfList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateCoalConfWithPatch() throws Exception {
        // Initialize the database
        coalConfRepository.saveAndFlush(coalConf);

        int databaseSizeBeforeUpdate = coalConfRepository.findAll().size();

        // Update the coalConf using partial update
        CoalConf partialUpdatedCoalConf = new CoalConf();
        partialUpdatedCoalConf.setId(coalConf.getId());

        partialUpdatedCoalConf
            .note1(UPDATED_NOTE_1)
            .up_M(UPDATED_UP_M)
            .up_M2(UPDATED_UP_M_2)
            .up_M3(UPDATED_UP_M_3)
            .up_A1(UPDATED_UP_A_1)
            .up_V(UPDATED_UP_V)
            .up_V2(UPDATED_UP_V_2)
            .up_V3(UPDATED_UP_V_3)
            .low_m(UPDATED_LOW_M)
            .low_m1(UPDATED_LOW_M_1)
            .low_m2(UPDATED_LOW_M_2);

        restCoalConfMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedCoalConf.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedCoalConf))
            )
            .andExpect(status().isOk());

        // Validate the CoalConf in the database
        List<CoalConf> coalConfList = coalConfRepository.findAll();
        assertThat(coalConfList).hasSize(databaseSizeBeforeUpdate);
        CoalConf testCoalConf = coalConfList.get(coalConfList.size() - 1);
        assertThat(testCoalConf.getProject()).isEqualTo(DEFAULT_PROJECT);
        assertThat(testCoalConf.getPhone()).isEqualTo(DEFAULT_PHONE);
        assertThat(testCoalConf.getSend()).isEqualTo(UPDATED_SEND);
        assertThat(testCoalConf.getSoft2()).isEqualTo(UPDATED_SOFT_2);
        assertThat(testCoalConf.getSoft3()).isEqualTo(DEFAULT_SOFT_3);
        assertThat(testCoalConf.getPayStatus()).isEqualTo(UPDATED_PAY_STATUS);
        assertThat(testCoalConf.getNote1()).isEqualTo(UPDATED_NOTE_1);
        assertThat(testCoalConf.getNote2()).isEqualTo(DEFAULT_NOTE_2);
        assertThat(testCoalConf.getCoalType()).isEqualTo(DEFAULT_COAL_TYPE);
        assertThat(testCoalConf.getUp_M()).isEqualTo(UPDATED_UP_M);
        assertThat(testCoalConf.getUp_M1()).isEqualTo(DEFAULT_UP_M_1);
        assertThat(testCoalConf.getUp_M2()).isEqualTo(UPDATED_UP_M_2);
        assertThat(testCoalConf.getUp_M3()).isEqualTo(UPDATED_UP_M_3);
        assertThat(testCoalConf.getUp_M4()).isEqualTo(DEFAULT_UP_M_4);
        assertThat(testCoalConf.getUp_A()).isEqualTo(DEFAULT_UP_A);
        assertThat(testCoalConf.getUp_A1()).isEqualTo(UPDATED_UP_A_1);
        assertThat(testCoalConf.getUp_A2()).isEqualTo(DEFAULT_UP_A_2);
        assertThat(testCoalConf.getUp_A3()).isEqualTo(DEFAULT_UP_A_3);
        assertThat(testCoalConf.getUp_A4()).isEqualTo(DEFAULT_UP_A_4);
        assertThat(testCoalConf.getUp_V()).isEqualTo(UPDATED_UP_V);
        assertThat(testCoalConf.getUp_V1()).isEqualTo(DEFAULT_UP_V_1);
        assertThat(testCoalConf.getUp_V2()).isEqualTo(UPDATED_UP_V_2);
        assertThat(testCoalConf.getUp_V3()).isEqualTo(UPDATED_UP_V_3);
        assertThat(testCoalConf.getUp_V4()).isEqualTo(DEFAULT_UP_V_4);
        assertThat(testCoalConf.getLow_m()).isEqualTo(UPDATED_LOW_M);
        assertThat(testCoalConf.getLow_m1()).isEqualTo(UPDATED_LOW_M_1);
        assertThat(testCoalConf.getLow_m2()).isEqualTo(UPDATED_LOW_M_2);
        assertThat(testCoalConf.getLow_m3()).isEqualTo(DEFAULT_LOW_M_3);
        assertThat(testCoalConf.getLow_m4()).isEqualTo(DEFAULT_LOW_M_4);
        assertThat(testCoalConf.getEggCone1()).isEqualTo(UPDATED_EGG_CONE_1);
        assertThat(testCoalConf.getEggCone2()).isEqualTo(UPDATED_EGG_CONE_2);
        assertThat(testCoalConf.getEggCone3()).isEqualTo(DEFAULT_EGG_CONE_3);
        assertThat(testCoalConf.getPrice1()).isEqualTo(DEFAULT_PRICE_1);
        assertThat(testCoalConf.getPrice2()).isEqualTo(DEFAULT_PRICE_2);
    }

    @Test
    @Transactional
    void fullUpdateCoalConfWithPatch() throws Exception {
        // Initialize the database
        coalConfRepository.saveAndFlush(coalConf);

        int databaseSizeBeforeUpdate = coalConfRepository.findAll().size();

        // Update the coalConf using partial update
        CoalConf partialUpdatedCoalConf = new CoalConf();
        partialUpdatedCoalConf.setId(coalConf.getId());

        partialUpdatedCoalConf
            .project(UPDATED_PROJECT)
            .phone(UPDATED_PHONE)
            .note1(UPDATED_NOTE_1)
            .note2(UPDATED_NOTE_2)
            .coalType(UPDATED_COAL_TYPE)
            .up_M(UPDATED_UP_M)
            .up_M1(UPDATED_UP_M_1)
            .up_M2(UPDATED_UP_M_2)
            .up_M3(UPDATED_UP_M_3)
            .up_M4(UPDATED_UP_M_4)
            .up_A(UPDATED_UP_A)
            .up_A1(UPDATED_UP_A_1)
            .up_A2(UPDATED_UP_A_2)
            .up_A3(UPDATED_UP_A_3)
            .up_A4(UPDATED_UP_A_4)
            .up_V(UPDATED_UP_V)
            .up_V1(UPDATED_UP_V_1)
            .up_V2(UPDATED_UP_V_2)
            .up_V3(UPDATED_UP_V_3)
            .up_V4(UPDATED_UP_V_4)
            .low_m(UPDATED_LOW_M)
            .low_m1(UPDATED_LOW_M_1)
            .low_m2(UPDATED_LOW_M_2)
            .low_m3(UPDATED_LOW_M_3)
            .low_m4(UPDATED_LOW_M_4)
            .price1(UPDATED_PRICE_1)
            .price2(UPDATED_PRICE_2);

        restCoalConfMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedCoalConf.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedCoalConf))
            )
            .andExpect(status().isOk());

        // Validate the CoalConf in the database
        List<CoalConf> coalConfList = coalConfRepository.findAll();
        assertThat(coalConfList).hasSize(databaseSizeBeforeUpdate);
        CoalConf testCoalConf = coalConfList.get(coalConfList.size() - 1);
        assertThat(testCoalConf.getProject()).isEqualTo(UPDATED_PROJECT);
        assertThat(testCoalConf.getPhone()).isEqualTo(UPDATED_PHONE);
        assertThat(testCoalConf.getSend()).isEqualTo(UPDATED_SEND);
        assertThat(testCoalConf.getSoft2()).isEqualTo(UPDATED_SOFT_2);
        assertThat(testCoalConf.getSoft3()).isEqualTo(UPDATED_SOFT_3);
        assertThat(testCoalConf.getPayStatus()).isEqualTo(UPDATED_PAY_STATUS);
        assertThat(testCoalConf.getNote1()).isEqualTo(UPDATED_NOTE_1);
        assertThat(testCoalConf.getNote2()).isEqualTo(UPDATED_NOTE_2);
        assertThat(testCoalConf.getCoalType()).isEqualTo(UPDATED_COAL_TYPE);
        assertThat(testCoalConf.getUp_M()).isEqualTo(UPDATED_UP_M);
        assertThat(testCoalConf.getUp_M1()).isEqualTo(UPDATED_UP_M_1);
        assertThat(testCoalConf.getUp_M2()).isEqualTo(UPDATED_UP_M_2);
        assertThat(testCoalConf.getUp_M3()).isEqualTo(UPDATED_UP_M_3);
        assertThat(testCoalConf.getUp_M4()).isEqualTo(UPDATED_UP_M_4);
        assertThat(testCoalConf.getUp_A()).isEqualTo(UPDATED_UP_A);
        assertThat(testCoalConf.getUp_A1()).isEqualTo(UPDATED_UP_A_1);
        assertThat(testCoalConf.getUp_A2()).isEqualTo(UPDATED_UP_A_2);
        assertThat(testCoalConf.getUp_A3()).isEqualTo(UPDATED_UP_A_3);
        assertThat(testCoalConf.getUp_A4()).isEqualTo(UPDATED_UP_A_4);
        assertThat(testCoalConf.getUp_V()).isEqualTo(UPDATED_UP_V);
        assertThat(testCoalConf.getUp_V1()).isEqualTo(UPDATED_UP_V_1);
        assertThat(testCoalConf.getUp_V2()).isEqualTo(UPDATED_UP_V_2);
        assertThat(testCoalConf.getUp_V3()).isEqualTo(UPDATED_UP_V_3);
        assertThat(testCoalConf.getUp_V4()).isEqualTo(UPDATED_UP_V_4);
        assertThat(testCoalConf.getLow_m()).isEqualTo(UPDATED_LOW_M);
        assertThat(testCoalConf.getLow_m1()).isEqualTo(UPDATED_LOW_M_1);
        assertThat(testCoalConf.getLow_m2()).isEqualTo(UPDATED_LOW_M_2);
        assertThat(testCoalConf.getLow_m3()).isEqualTo(UPDATED_LOW_M_3);
        assertThat(testCoalConf.getLow_m4()).isEqualTo(UPDATED_LOW_M_4);
        assertThat(testCoalConf.getEggCone1()).isEqualTo(UPDATED_EGG_CONE_1);
        assertThat(testCoalConf.getEggCone2()).isEqualTo(UPDATED_EGG_CONE_2);
        assertThat(testCoalConf.getEggCone3()).isEqualTo(UPDATED_EGG_CONE_3);
        assertThat(testCoalConf.getPrice1()).isEqualTo(UPDATED_PRICE_1);
        assertThat(testCoalConf.getPrice2()).isEqualTo(UPDATED_PRICE_2);
    }

    @Test
    @Transactional
    void patchNonExistingCoalConf() throws Exception {
        int databaseSizeBeforeUpdate = coalConfRepository.findAll().size();
        coalConf.setId(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restCoalConfMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, coalConf.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(coalConf))
            )
            .andExpect(status().isBadRequest());

        // Validate the CoalConf in the database
        List<CoalConf> coalConfList = coalConfRepository.findAll();
        assertThat(coalConfList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchCoalConf() throws Exception {
        int databaseSizeBeforeUpdate = coalConfRepository.findAll().size();
        coalConf.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restCoalConfMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(coalConf))
            )
            .andExpect(status().isBadRequest());

        // Validate the CoalConf in the database
        List<CoalConf> coalConfList = coalConfRepository.findAll();
        assertThat(coalConfList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamCoalConf() throws Exception {
        int databaseSizeBeforeUpdate = coalConfRepository.findAll().size();
        coalConf.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restCoalConfMockMvc
            .perform(patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(TestUtil.convertObjectToJsonBytes(coalConf)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the CoalConf in the database
        List<CoalConf> coalConfList = coalConfRepository.findAll();
        assertThat(coalConfList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteCoalConf() throws Exception {
        // Initialize the database
        coalConfRepository.saveAndFlush(coalConf);

        int databaseSizeBeforeDelete = coalConfRepository.findAll().size();

        // Delete the coalConf
        restCoalConfMockMvc
            .perform(delete(ENTITY_API_URL_ID, coalConf.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<CoalConf> coalConfList = coalConfRepository.findAll();
        assertThat(coalConfList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
