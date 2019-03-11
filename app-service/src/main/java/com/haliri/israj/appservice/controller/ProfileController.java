package com.haliri.israj.appservice.controller;

import com.haliri.israj.appcore.domain.content.Profile;
import com.haliri.israj.appcore.handler.ResponseHandler;
import com.haliri.israj.appcore.strategy.content.impl.ProfileStrategy;
import com.haliri.israj.appcore.utils.AppUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by israjhaliri on 10/16/17.
 */
@RestController
public class ProfileController {

    @Autowired
    ProfileStrategy profileStrategy;

    @Autowired
    ResponseHandler responseHandler;

    @RequestMapping(value = "/secret/get/profile", method = RequestMethod.GET)
    public Object getProfile() {
        try{
            Profile profile = profileStrategy.getData();
            return responseHandler.setResult(com.haliri.israj.appcore.constant.ResponseStatus.SUCCESS, null, profile);
        } catch (Exception e){
            return responseHandler.setResult(com.haliri.israj.appcore.constant.ResponseStatus.FAILED,e.getMessage(),null);
        }
    }

    @RequestMapping(value = "/secret/update/profile", method = RequestMethod.PUT)
    public Object updateProfile(@RequestBody Profile profile) {
        try {
            profileStrategy.saveOrUpdate(profile);
            return responseHandler.setResult(com.haliri.israj.appcore.constant.ResponseStatus.SUCCESS,null,null);
        } catch (Exception e) {
            AppUtils.getLogger(this).error("ERROR PROFILE LOG SAVE OR UPDATE: {}", e.getMessage());
            return responseHandler.setResult(com.haliri.israj.appcore.constant.ResponseStatus.FAILED,e.getMessage(),null);
        }
    }
}
