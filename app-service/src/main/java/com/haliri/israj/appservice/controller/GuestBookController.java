package com.haliri.israj.appservice.controller;

import com.haliri.israj.appcore.constant.ResponseStatus;
import com.haliri.israj.appcore.domain.content.GuestBook;
import com.haliri.israj.appcore.handler.ResponseHandler;
import com.haliri.israj.appcore.strategy.content.impl.GuestBookStrategy;
import com.haliri.israj.appcore.utils.AppUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by israjhaliri on 10/16/17.
 */
@RestController
public class GuestBookController {

    @Autowired
    GuestBookStrategy guestBookStrategy;

    @Autowired
    ResponseHandler responseHandler;

    @RequestMapping(value = "/public/get/guest_book", method = RequestMethod.GET)
    public Object getGuestBook() {
        List<GuestBook> guestBookList = null;

        try{
            guestBookList = guestBookStrategy.getListData();
            return responseHandler.setResult(ResponseStatus.SUCCESS, null, guestBookList);
        } catch (Exception e){
            return responseHandler.setResult(ResponseStatus.FAILED,e.getMessage(),null);
        }
    }

    @RequestMapping(value = "/secret/get/guest_book_one_month", method = RequestMethod.GET)
    public Object getGuestBookOneMonth() {
        List<GuestBook> guestBookList = null;

        try{
            guestBookList = guestBookStrategy.getOneMonthListData();
            return responseHandler.setResult(ResponseStatus.SUCCESS, null, guestBookList);
        } catch (Exception e){
            return responseHandler.setResult(ResponseStatus.FAILED,e.getMessage(),null);
        }
    }

    @RequestMapping(value = "/secret/get_by_id/guest_book/{idGuestBook}", method = RequestMethod.GET)
    public Object getGuestBookById(@PathVariable(value = "idGuestBook") Integer idGuestBook) {
        try{
            List<GuestBook> guestBookList = guestBookStrategy.getListDataById(idGuestBook);
            return responseHandler.setResult(ResponseStatus.SUCCESS, null, guestBookList);
        } catch (Exception e){
            return responseHandler.setResult(ResponseStatus.FAILED,e.getMessage(),null);
        }
    }

    @RequestMapping(value = "/secret/get/guest_book", method = RequestMethod.GET)
    public Object getGuestBookPerPage(@RequestParam(value = "draw", defaultValue = "0") int draw,
                               @RequestParam(value = "start", defaultValue = "0") int start,
                               @RequestParam(value = "length", defaultValue = "10") int length,
                               @RequestParam(value = "columns[0][data]", defaultValue = "") String firstColumn,
                               @RequestParam(value = "order[0][column]", defaultValue = "0") int sortIndex,
                               @RequestParam(value = "order[0][dir]", defaultValue = "ASC") String sortDir,
                               @RequestParam(value = "search[value]", defaultValue = "") String search
    ) {
        AppUtils.getLogger(this).debug(
                "datatable info = draw : {} , start : {}, length : {}, firstColumn : {}, sortIndex : {}, sortDir : {}, search : {},",
                draw, start, length, firstColumn, sortIndex, sortDir, search
        );

        Map<String, Object> parameters = new HashMap<>();
        parameters.put("start",(start + 1));
        parameters.put("length",length + start);
        parameters.put("search",search);

        List<GuestBook> guestBookList = null;

        Map<String,Object> result = new HashMap();
        result.put("draw", draw);
        result.put("search[value]", search);
        try{
            guestBookList = guestBookStrategy.getListDataPerPage(parameters);
            result.put("data", guestBookList);
            if(guestBookList.size() > 0){
                result.put("recordsTotal", guestBookList.get(0).getTotalCount());
                result.put("recordsFiltered", guestBookList.get(0).getTotalCount());
            }else{
                result.put("recordsTotal", 0);
                result.put("recordsFiltered", 0);
            }

            return responseHandler.setResult(ResponseStatus.SUCCESS,null,result);
        } catch (Exception e){
            return responseHandler.setResult(ResponseStatus.FAILED,e.getMessage(),result);
        }
    }

    @RequestMapping(value = "/public/insert/guest_book", method = RequestMethod.POST)
    public Object insertGuestBook(
            @RequestParam(value = "username") String username
    ) {
        GuestBook guestBook = new GuestBook();
        guestBook.setUsername(username);

        try {
            guestBookStrategy.saveData(guestBook);
            return responseHandler.setResult(ResponseStatus.SUCCESS, null, null);
        } catch (Exception e) {
            e.printStackTrace();
            return responseHandler.setResult(ResponseStatus.FAILED, e.getMessage(), null);
        }
    }

    @RequestMapping(value = "/secret/delete/guest_book/{idGuestBook}", method = RequestMethod.DELETE)
    public Object deleteGuestBook(
            @PathVariable(value = "idGuestBook") Integer idGuestBook
    ) {
        try {
            guestBookStrategy.deleteData(idGuestBook);
            return responseHandler.setResult(ResponseStatus.SUCCESS,null,null);
        } catch (Exception e) {
            e.printStackTrace();
            return responseHandler.setResult(ResponseStatus.FAILED,e.getMessage(),null);
        }
    }
}
