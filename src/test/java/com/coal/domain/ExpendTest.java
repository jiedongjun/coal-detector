package com.coal.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.coal.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class ExpendTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Expend.class);
        Expend expend1 = new Expend();
        expend1.setId(1L);
        Expend expend2 = new Expend();
        expend2.setId(expend1.getId());
        assertThat(expend1).isEqualTo(expend2);
        expend2.setId(2L);
        assertThat(expend1).isNotEqualTo(expend2);
        expend1.setId(null);
        assertThat(expend1).isNotEqualTo(expend2);
    }
}
