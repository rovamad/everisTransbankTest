package com.rovamad.everis.controller;

import com.rovamad.everis.entity.Sale;
import com.rovamad.everis.service.SaleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Slf4j
@RestController
public class SaleController {

    @Autowired
    SaleService service;

    public static final String DATE_DD_MM_YYYY = "dd-MM-yyyy";

    @ResponseBody
    @GetMapping(value = "/api/sales", produces = {MediaType.APPLICATION_JSON_VALUE})
    public List<Sale> getAllSales(@RequestParam(required = false) String date) {
        LocalDate localDate;

        if (date == null || date.isEmpty()) {
            localDate = LocalDate.now();
        } else {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_DD_MM_YYYY);
            localDate = LocalDate.parse(date, formatter);
        }

        return service.getAllSales(localDate);
    }

    @PostMapping(value = "/api/sales", produces = {MediaType.APPLICATION_JSON_VALUE})
    public Sale createSale(@RequestBody Sale sale) {

        return service.createSale(sale);
    }
}
