package com.rovamad.everis.repository;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;


import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class SalesRepositoryTest {

    @Autowired
    SaleRepository saleRepository;

    @Test
    public void findAllByDateTest() {
        assertThat(saleRepository).isNotNull();
        assertThat(saleRepository.findAllByDate("11-01-2020")).isNotNull();
    }
}
