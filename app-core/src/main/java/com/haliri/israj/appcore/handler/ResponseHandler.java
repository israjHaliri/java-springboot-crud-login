package com.haliri.israj.appcore.handler;

import com.haliri.israj.appcore.constant.ResponseStatus;
import com.haliri.israj.appcore.domain.common.ResultMessage;
import org.springframework.stereotype.Service;

/**
 * Created by israjhaliri on 12/6/17.
 */
@Service
public interface ResponseHandler {

    ResultMessage setResult(ResponseStatus responseStatus, String erroMessage, Object data);
}
