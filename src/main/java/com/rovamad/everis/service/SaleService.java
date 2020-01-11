package com.rovamad.everis.service;

import com.rovamad.everis.entity.Sale;
import com.rovamad.everis.repository.SaleRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Slf4j
@Service
public class SaleService {

    @Qualifier("SaleRepository")
    @Autowired
    SaleRepository repository;

    public static final String DATE_DD_MM_YYYY = "dd-MM-yyyy";

    public List<Sale> getAllSales(LocalDate localDate) {

        return this.repository.findAllByDate(localDate.format(DateTimeFormatter.ofPattern(DATE_DD_MM_YYYY)));
    }

    public Sale createSale(Sale sale) {

        return this.repository.save(sale);
    }
}

