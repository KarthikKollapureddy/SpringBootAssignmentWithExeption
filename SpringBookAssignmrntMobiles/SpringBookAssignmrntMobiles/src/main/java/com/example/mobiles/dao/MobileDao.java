package com.example.mobiles.dao;

import com.example.mobiles.model.Mobile;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MobileDao extends CrudRepository<Mobile,Integer> {

//    @Query("Select m from Mobile m where m.price between :start and :endd")
//    List<Mobile> findMobileByPriceBetween(@Param("start") int start,@Param("endd") int end);
    List<Mobile> findMobileByPriceBetween(int start,int end);

    List<Mobile> findMobileByMakerIgnoreCase(String brand);

    List<Mobile> findMobileByColorIgnoreCase(String color);

    Optional<Mobile> findMobileByModelIgnoreCaseAndColorIgnoreCase(String model, String color);

    List<Mobile> findMobileByProcessorIgnoreCase(String processor);

    List<Mobile> findMobileByCameraGreaterThanEqual(byte camera);

    List<Mobile> findMobileByMakerIgnoreCaseAndColorIgnoreCase(String maker,String color);
}

