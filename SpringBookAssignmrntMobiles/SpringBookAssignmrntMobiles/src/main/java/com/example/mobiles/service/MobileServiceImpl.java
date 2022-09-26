package com.example.mobiles.service;

import com.example.mobiles.dao.MobileDao;
import com.example.mobiles.exceptions.MissingRequiredFieldsException;
import com.example.mobiles.exceptions.MobileAlreadyExistsException;
import com.example.mobiles.exceptions.MobileNotFoundException;
import com.example.mobiles.exceptions.PriceExceededException;
import com.example.mobiles.model.Mobile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MobileServiceImpl implements MobileService{

    @Autowired
    MobileDao mobileDao;


    @Override
    public Mobile addMobile(Mobile mobile) throws MissingRequiredFieldsException, PriceExceededException,MobileAlreadyExistsException {

        if(mobileDao.findById(mobile.getImei()).isPresent()){throw new MobileAlreadyExistsException();}

        else {
            return validateRequiredFields(mobile);
        }
    }

    @Override
    public Mobile updateMobile(int imei,Mobile mobile) throws MobileNotFoundException, MissingRequiredFieldsException, PriceExceededException {
        Optional<Mobile> searchedMobile = mobileDao.findById(imei);
        if(searchedMobile.isEmpty()){ throw new MobileNotFoundException(); }
        else{
            mobile.setImei(searchedMobile.get().getImei());
            return validateRequiredFields(mobile);
        }

    }

    private Mobile validateRequiredFields(Mobile mobile) throws MissingRequiredFieldsException, PriceExceededException {
        if (mobile.getImei() <= 0 || mobile.getMaker() == null || mobile.getModel() == null) {
            throw new MissingRequiredFieldsException();
        } else {
            if (mobile.getPrice() > 150000) {
                throw new PriceExceededException();
            } else {
                return mobileDao.save(mobile);
            }
        }
    }

    @Override
    public void deleteMobileByIMEI(int imei) throws MobileNotFoundException {
        Optional<Mobile> mobile = mobileDao.findById(imei);
        if(mobile.isEmpty()){ throw new MobileNotFoundException();}
        else{
            mobileDao.deleteById(imei);
        }

    }

    @Override
    public List<Mobile> getAllMobiles() {
        return (List<Mobile>) mobileDao.findAll();
    }

    @Override
    public List<Mobile> getMobilesWithinRange(int start, int end) throws MobileNotFoundException {
        List<Mobile> mobilesInRange = mobileDao.findMobileByPriceBetween(start,end);
        if(mobilesInRange.size()==0){
            throw new MobileNotFoundException();
        }
        return mobilesInRange;
    }

    @Override
    public List<Mobile> findMobileByBrand(String brand) throws MobileNotFoundException {

        List<Mobile> mobilesByBrand = mobileDao.findMobileByMakerIgnoreCase(brand);
        if(mobilesByBrand.size()==0){
            throw new MobileNotFoundException();
        }
        return mobilesByBrand;
    }

    @Override
    public List<Mobile> findMobileByColor(String color) throws MobileNotFoundException {
        List<Mobile> mobilesByColor = mobileDao.findMobileByColorIgnoreCase(color);
        if(mobilesByColor.size()==0){
            throw new MobileNotFoundException();
        }
        return mobilesByColor;
    }

    @Override
    public Mobile findMobileByModelAndColor(String model, String color) throws MobileNotFoundException {
        Optional<Mobile> mobilesBymodelNcolor = mobileDao.findMobileByModelIgnoreCaseAndColorIgnoreCase(model,color);
        if(mobilesBymodelNcolor.isEmpty()){
            throw new MobileNotFoundException();
        }
        return mobilesBymodelNcolor.get();
    }

    @Override
    public List<Mobile> findMobileByProcessor(String processor) throws MobileNotFoundException {
        List<Mobile> mobilesByProcessor = mobileDao.findMobileByProcessorIgnoreCase(processor);
        if(mobilesByProcessor.size()==0){
            throw new MobileNotFoundException();
        }
        return mobilesByProcessor;
    }

    @Override
    public List<Mobile> findMobileByCameraMegaPixel(byte camera) throws MobileNotFoundException {
        List<Mobile> mobilesByCam = mobileDao.findMobileByCameraGreaterThanEqual(camera);
        if(mobilesByCam.size()==0){
            throw new MobileNotFoundException();
        }
        return mobilesByCam;
    }

    @Override
    public List<Mobile> findMobileByMakerIgnoreCaseAndColorIgnoreCase(String maker, String color) throws MobileNotFoundException {
        List<Mobile> mobiles = mobileDao.findMobileByMakerIgnoreCaseAndColorIgnoreCase(maker,color);
        if(mobiles.size()==0){throw new MobileNotFoundException();}
        else{
            return mobiles;
        }
    }
}
