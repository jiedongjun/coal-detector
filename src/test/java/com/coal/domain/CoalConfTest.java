package com.coal.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.coal.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class CoalConfTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(CoalConf.class);
        CoalConf coalConf1 = new CoalConf();
        coalConf1.setId(1L);
        CoalConf coalConf2 = new CoalConf();
        coalConf2.setId(coalConf1.getId());
        assertThat(coalConf1).isEqualTo(coalConf2);
        coalConf2.setId(2L);
        assertThat(coalConf1).isNotEqualTo(coalConf2);
        coalConf1.setId(null);
        assertThat(coalConf1).isNotEqualTo(coalConf2);
    }
}
