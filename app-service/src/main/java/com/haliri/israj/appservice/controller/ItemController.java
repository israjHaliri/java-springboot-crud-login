package com.haliri.israj.appservice.controller;

import com.haliri.israj.appcore.domain.content.Item;
import com.haliri.israj.appcore.handler.ResponseHandler;
import com.haliri.israj.appcore.strategy.content.impl.ItemStrategy;
import com.haliri.israj.appcore.utils.AppUtils;
import com.haliri.israj.appservice.utils.WebUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by israjhaliri on 10/16/17.
 */
@RestController
public class ItemController {

    @Autowired
    ItemStrategy itemStrategy;

    @Autowired
    ResponseHandler responseHandler;

    @RequestMapping(value = "/public/get/item", method = RequestMethod.GET)
    public Object getTestimonial(@RequestParam(value = "type", defaultValue = "") String type) {
        try{
            List<Item> result = itemStrategy.getListData(type);
            return responseHandler.setResult(com.haliri.israj.appcore.constant.ResponseStatus.SUCCESS, null, result);
        } catch (Exception e){
            return responseHandler.setResult(com.haliri.israj.appcore.constant.ResponseStatus.FAILED,e.getMessage(),null);
        }
    }

    @RequestMapping(value = "/secret/get_by_id/item", method = RequestMethod.GET)
    public Object getTestimonial(
            @RequestParam(value = "type", defaultValue = "") String type,
            @RequestParam(value = "idItem", defaultValue = "") String idItem
    ) {
        Map parameter = new HashMap();
        parameter.put("type",type);
        parameter.put("idItem",idItem);

        try{
            List<Item>  result = itemStrategy.getListDataById(parameter);
            return responseHandler.setResult(com.haliri.israj.appcore.constant.ResponseStatus.SUCCESS, null, result);
        } catch (Exception e){
            return responseHandler.setResult(com.haliri.israj.appcore.constant.ResponseStatus.FAILED,e.getMessage(),null);
        }
    }

    @RequestMapping(value = "/secret/get/item", method = RequestMethod.GET)
    public Object getTestimonialPerPage(
            @RequestParam(value = "start", defaultValue = "0") int start,
            @RequestParam(value = "search", defaultValue = "") String search,
            @RequestParam(value = "type", defaultValue = "") String type
    ) {
        AppUtils.getLogger(this).debug("datatable info = start : {}, search : {}, tyoe : {}", start, search, type);

        int perPage = 10;

        Map<String, Object> parameters = new HashMap<>();
        parameters.put("start", ((start - 1) * 10));
        parameters.put("search",search);
        parameters.put("type",type);
        parameters.put("perPage", perPage);

        List<Item> itemList = null;

        Map<String,Object> result = new HashMap();
        result.put("search", search);
        result.put("perPage", perPage);
        try{
            itemList = itemStrategy.getListDataPerPage(parameters);
            result.put("item", itemList);
            if (itemList.size() > 0) {
                result.put("totalData", itemList.get(0).getTotalCount());
            } else {
                result.put("totalData", 0);
            }

            return responseHandler.setResult(com.haliri.israj.appcore.constant.ResponseStatus.SUCCESS,null,result);
        } catch (Exception e){
            return responseHandler.setResult(com.haliri.israj.appcore.constant.ResponseStatus.FAILED,e.getMessage(),result);
        }
    }

    @RequestMapping(value = "/secret/insert/item", method = RequestMethod.POST)
    public Object saveTestimonial(@RequestBody Item Item) {
        try {
            Item.setCreateBy(WebUtil.getUserLogin());
            itemStrategy.saveData(Item);
            return responseHandler.setResult(com.haliri.israj.appcore.constant.ResponseStatus.SUCCESS,null,null);
        } catch (Exception e) {
            e.printStackTrace();
            return responseHandler.setResult(com.haliri.israj.appcore.constant.ResponseStatus.FAILED,e.getMessage(),null);
        }
    }

    @RequestMapping(value = "/secret/update/item", method = RequestMethod.PUT)
    public Object updateTestimonial(@RequestBody Item Item) {
        try {
            itemStrategy.updateData(Item);
            return responseHandler.setResult(com.haliri.israj.appcore.constant.ResponseStatus.SUCCESS,null,null);
        } catch (Exception e) {
            e.printStackTrace();
            return responseHandler.setResult(com.haliri.israj.appcore.constant.ResponseStatus.FAILED,e.getMessage(),null);
        }
    }

    @RequestMapping(value = "/secret/delete/item/{idItem}", method = RequestMethod.DELETE)
    public Object insertTestimonial(
            @PathVariable(value = "idItem") String idItem
    ) {
        try {
            itemStrategy.deleteData(Integer.valueOf(idItem));
            return responseHandler.setResult(com.haliri.israj.appcore.constant.ResponseStatus.SUCCESS,null,null);
        } catch (Exception e) {
            e.printStackTrace();
            return responseHandler.setResult(com.haliri.israj.appcore.constant.ResponseStatus.FAILED,e.getMessage(),null);
        }
    }
}
