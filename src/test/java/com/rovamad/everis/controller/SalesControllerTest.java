package com.rovamad.everis.controller;

import com.rovamad.everis.entity.Sale;
import com.rovamad.everis.service.SaleService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@SpringBootTest
public class SalesControllerTest {

    @InjectMocks
    SaleController saleController;

    @Mock
    SaleService service;

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

        when(service.getAllSales(ArgumentMatchers.any())).thenReturn(list);

        assertThat(saleController.getAllSales(null)).isNotNull();
    }

    @Test
    public void createSaleTest() {
        Sale sale = new Sale();
        sale.setDate("11-01-2020");

        when(service.createSale(ArgumentMatchers.any())).thenReturn(sale);

        assertThat(saleController.createSale(sale)).isNotNull();
    }
}
