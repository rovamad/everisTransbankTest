package com.rovamad.everis.repository;

import com.rovamad.everis.entity.Sale;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("SaleRepository")
public interface SaleRepository extends JpaRepository<Sale, Long> {
    
    /**
     * 
     * @param date
     * @return
     */
    @Query(value = "select * from sale where date = :date", nativeQuery = true)
    List<Sale> findAllByDate(@Param("date") String date);
    
}
