package com.rovamad.everis.service;

import com.rovamad.everis.controller.SaleController;
import com.rovamad.everis.entity.Sale;
import com.rovamad.everis.repository.SaleRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@SpringBootTest
public class SalesServiceTest {

    @InjectMocks
    SaleService service;

    @Mock
    SaleRepository repository;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void getAllSalesTest() {
        Sale sale = new Sale();
        sale.setDate("11-01-2020");
        Sale sale2 = new Sale();
        sale2.setDate("11-01-2020");

        List<Sale> list = new ArrayList<>();
        list.add(sale);
        list.add(sale2);

        LocalDate localDate = LocalDate.now();

        when(repository.findAllByDate(ArgumentMatchers.anyString())).thenReturn(list);

        assertThat(service.getAllSales(localDate)).isNotNull();
    }

    @Test
    public void createSaleTest() {
        Sale sale = new Sale();
        sale.setDate("11-01-2020");

        when(repository.save(ArgumentMatchers.any())).thenReturn(sale);

        assertThat(service.createSale(sale)).isNotNull();
    }
}
