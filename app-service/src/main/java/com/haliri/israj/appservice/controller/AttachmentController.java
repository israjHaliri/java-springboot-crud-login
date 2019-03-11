package com.haliri.israj.appservice.controller;

import com.haliri.israj.appcore.constant.ContentType;
import com.haliri.israj.appcore.domain.content.Attachment;
import com.haliri.israj.appcore.handler.ResponseHandler;
import com.haliri.israj.appcore.strategy.content.impl.AttachmentStrategy;
import com.haliri.israj.appcore.utils.AppUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * Created by israjhaliri on 10/16/17.
 */
@RestController
public class AttachmentController {

    @Autowired
    AttachmentStrategy attachmentStrategy;

    @Autowired
    ResponseHandler responseHandler;

    @Value("${patFile}")
    String pathFile;

    @RequestMapping(value = "/secret/get/attachment", method = RequestMethod.GET)
    public Object getTestimonialPerPage(
            @RequestParam(value = "start", defaultValue = "0") int start,
            @RequestParam(value = "search", defaultValue = "") String search,
            @RequestParam(value = "type", defaultValue = "") String type
    ) {
        AppUtils.getLogger(this).debug("datatable info = start : {}, search : {}, tyoe : {}", start, search, type);

        int perPage = 10;

        Map<String, Object> parameters = new HashMap<>();
        parameters.put("start", ((start - 1) * 10));
        parameters.put("search", search);
        parameters.put("type", type);
        parameters.put("perPage", perPage);

        List<Attachment> attachmentList = null;

        Map<String, Object> result = new HashMap();
        result.put("search", search);
        result.put("perPage", perPage);
        try {
            attachmentList = attachmentStrategy.getListDataPerPage(parameters);
            result.put("attachments", attachmentList);
            if (attachmentList.size() > 0) {
                result.put("totalData", attachmentList.get(0).getTotalCount());
            } else {
                result.put("totalData", 0);
            }

            return responseHandler.setResult(com.haliri.israj.appcore.constant.ResponseStatus.SUCCESS, null, result);
        } catch (Exception e) {
            return responseHandler.setResult(com.haliri.israj.appcore.constant.ResponseStatus.FAILED, e.getMessage(), result);
        }
    }

    @RequestMapping(value = "/secret/insert/attachment", method = RequestMethod.POST)
    public Object saveTestimonial(
            @RequestParam(value = "contentId", defaultValue = "") String contentId,
            @RequestParam(value = "file", defaultValue = "") String file,
            @RequestParam(value = "contentType", defaultValue = "") ContentType contentType
    ) {

        Attachment attachment = new Attachment();
        attachment.setItemId(Integer.valueOf(contentId));
        attachment.setFile(file);
        attachment.setNameFile(String.valueOf(UUID.randomUUID()) + ".jpg");
        attachment.setContentType(contentType);

        try {
            if (AppUtils.saveImageBase64(attachment.getNameFile(),attachment.getFile(), pathFile)) {
                attachmentStrategy.saveData(attachment);
                return responseHandler.setResult(com.haliri.israj.appcore.constant.ResponseStatus.SUCCESS, null, null);
            } else {
                return responseHandler.setResult(com.haliri.israj.appcore.constant.ResponseStatus.FAILED, "Failed To Save File Make Sure Paramter is Correct", null);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return responseHandler.setResult(com.haliri.israj.appcore.constant.ResponseStatus.FAILED, e.getMessage(), null);
        }
    }

    @RequestMapping(value = "/secret/delete/attachment/{idAttachment}/{idContent}/{type}", method = RequestMethod.DELETE)
    public Object insertTestimonial(
            @PathVariable(value = "idAttachment") Integer idAttachment,
            @PathVariable(value = "idContent") Integer idContent,
            @PathVariable(value = "type") String type
    ) {
        Map parameter = new HashMap();
        parameter.put("type", type);
        parameter.put("idAttachment", idAttachment);
        parameter.put("idContent", idContent);

        try {
            String nameFile = attachmentStrategy.getFileNameById(parameter);

            if (AppUtils.deleletFile(nameFile,pathFile)) {
                attachmentStrategy.deleteData(parameter);
                return responseHandler.setResult(com.haliri.israj.appcore.constant.ResponseStatus.SUCCESS, null, null);
            } else {
                return responseHandler.setResult(com.haliri.israj.appcore.constant.ResponseStatus.FAILED, "Failed Delete File Make Sure Paramter is Correct", null);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return responseHandler.setResult(com.haliri.israj.appcore.constant.ResponseStatus.FAILED, e.getMessage(), null);
        }
    }
}
