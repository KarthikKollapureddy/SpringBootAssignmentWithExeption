package com.example.mobiles.controller;

import com.example.mobiles.exceptions.MissingRequiredFieldsException;
import com.example.mobiles.exceptions.MobileAlreadyExistsException;
import com.example.mobiles.exceptions.MobileNotFoundException;
import com.example.mobiles.exceptions.PriceExceededException;
import com.example.mobiles.model.Mobile;
import com.example.mobiles.service.MobileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("mobiles")
public class MobileController {

    @Autowired
    MobileService mobileService;

    @GetMapping
    public ResponseEntity<List<Mobile>> getAllMobiles(){
        return new ResponseEntity<List<Mobile>>(mobileService.getAllMobiles(),HttpStatus.OK);
    }

    @PostMapping("addmobile")
    public ResponseEntity<Mobile> addNewMobile(@RequestBody Mobile mobile) throws MissingRequiredFieldsException, PriceExceededException, MobileAlreadyExistsException {
        Mobile newMobile = mobileService.addMobile(mobile);
        return new ResponseEntity<Mobile>(newMobile, HttpStatus.CREATED);
    }

    @PutMapping("update/{imei}")
    public ResponseEntity<Mobile> updateMobile(@PathVariable int imei,@RequestBody Mobile mobile) throws MobileNotFoundException, MissingRequiredFieldsException, PriceExceededException {
        Mobile updatedMobile = mobileService.updateMobile(imei,mobile);
        return new ResponseEntity<Mobile>(updatedMobile,HttpStatus.OK);
    }

    @DeleteMapping("delete/{imei}")
    public ResponseEntity<String> deleteMobileById(@PathVariable int imei) throws MobileNotFoundException {
        mobileService.deleteMobileByIMEI(imei);
        return new ResponseEntity<String>("Mobile with imei "+imei+" deleted Successfully",HttpStatus.OK);
    }

    @GetMapping("range/{start}/{end}")
    public ResponseEntity<List<Mobile>> findWithinRange(@PathVariable int start,@PathVariable int end) throws MobileNotFoundException {
        List<Mobile> mobiles = mobileService.getMobilesWithinRange(start, end);
        return new ResponseEntity<List<Mobile>>(mobiles,HttpStatus.FOUND);
    }

    @GetMapping("brand/{brand}")
    public ResponseEntity<List<Mobile>> findByBrand(@PathVariable String brand) throws MobileNotFoundException {
        List<Mobile> mobiles = mobileService.findMobileByBrand(brand);
        return new ResponseEntity<List<Mobile>>(mobiles,HttpStatus.FOUND);
    }

    @GetMapping("color/{color}")
    public ResponseEntity<List<Mobile>> findByColor(@PathVariable String color) throws MobileNotFoundException {
        List<Mobile> mobiles = mobileService.findMobileByColor(color);
        return new ResponseEntity<List<Mobile>>(mobiles,HttpStatus.FOUND);
    }

    @GetMapping("modelNcolor/{model}/{color}")
    public ResponseEntity<Mobile> findByColor(@PathVariable String model,@PathVariable String color) throws MobileNotFoundException {
        Mobile mobiles = mobileService.findMobileByModelAndColor(model, color);
        return new ResponseEntity<Mobile>(mobiles,HttpStatus.FOUND);
    }

    @GetMapping("processor/{processor}")
    public ResponseEntity<List<Mobile>> findByProcessor(@PathVariable String processor) throws MobileNotFoundException {
        List<Mobile> mobiles = mobileService.findMobileByProcessor(processor);
        return new ResponseEntity<List<Mobile>>(mobiles,HttpStatus.FOUND);
    }

    @GetMapping("camera/{camera}")
    public ResponseEntity<List<Mobile>> findByColor(@PathVariable byte camera) throws MobileNotFoundException {
        List<Mobile> mobiles = mobileService.findMobileByCameraMegaPixel(camera);
        return new ResponseEntity<List<Mobile>>(mobiles,HttpStatus.FOUND);
    }

    @GetMapping("brandNcolor/{brand}/{color}")
    public ResponseEntity<List<Mobile>> findBybrandNcolor(@PathVariable String brand,@PathVariable String color) throws MobileNotFoundException {
        List<Mobile> mobiles = mobileService.findMobileByMakerIgnoreCaseAndColorIgnoreCase(brand, color);
        return new ResponseEntity<List<Mobile>>(mobiles,HttpStatus.FOUND);
    }


}
