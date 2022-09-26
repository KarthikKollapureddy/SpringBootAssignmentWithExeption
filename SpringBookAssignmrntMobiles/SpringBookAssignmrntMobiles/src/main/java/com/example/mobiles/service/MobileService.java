package com.example.mobiles.service;

import com.example.mobiles.exceptions.MissingRequiredFieldsException;
import com.example.mobiles.exceptions.MobileAlreadyExistsException;
import com.example.mobiles.exceptions.MobileNotFoundException;
import com.example.mobiles.exceptions.PriceExceededException;
import com.example.mobiles.model.Mobile;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface MobileService {

    Mobile addMobile(Mobile mobile) throws MissingRequiredFieldsException, PriceExceededException, MobileAlreadyExistsException;

    Mobile updateMobile(int imei,Mobile mobile) throws MobileNotFoundException, MissingRequiredFieldsException, PriceExceededException;

    void deleteMobileByIMEI(int imei) throws MobileNotFoundException;

    List<Mobile> getAllMobiles();

    List<Mobile> getMobilesWithinRange(int start,int end) throws MobileNotFoundException;

    List<Mobile> findMobileByBrand(String brand) throws MobileNotFoundException;

    List<Mobile> findMobileByColor(String color) throws MobileNotFoundException;

    Mobile findMobileByModelAndColor(String model,String color) throws MobileNotFoundException;

    List<Mobile> findMobileByProcessor(String processor) throws MobileNotFoundException;

    List<Mobile> findMobileByCameraMegaPixel(byte camera) throws MobileNotFoundException;

    List<Mobile> findMobileByMakerIgnoreCaseAndColorIgnoreCase(String maker,String color) throws MobileNotFoundException;



}
