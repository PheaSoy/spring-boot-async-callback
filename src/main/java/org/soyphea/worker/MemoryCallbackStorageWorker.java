package org.soyphea.worker;

import lombok.extern.slf4j.Slf4j;
import org.soyphea.Status;
import org.soyphea.domain.BaseResponse;
import org.soyphea.domain.BaseResultCallBack;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.*;


@Service
@Slf4j
public class MemoryCallbackStorageWorker implements TaskCallbackStorageWorker {

    private static HashMap<String,BaseResultCallBack> myMemoryStorage = new HashMap<>();


    public void initStorage(BaseResultCallBack baseResultCallBack) {
        myMemoryStorage.put(baseResultCallBack.getCallBackId(),baseResultCallBack);

    }

    public void processing(String callBackId) {
        updateStatus(callBackId, Status.PROCESSING.toString(), null);
    }

    public void completed(String callBackId, Object returnResult) {
        updateStatus(callBackId, Status.COMPLETED.toString(), returnResult);
    }

    public BaseResponse generateCallBackIdWithInitStatus() {
        String callBackId = UUID.randomUUID().toString();
        log.info("Init callback with id:{}", callBackId);
        BaseResponse apiResponse = new BaseResponse(callBackId);
        initStorage(new BaseResultCallBack(
                callBackId, Status.INIT.toString(), null
        ));
        return apiResponse;
    }

    @Override
    public Optional<BaseResultCallBack> findResultFromStorage(String callBackId) {
        return Optional.ofNullable(myMemoryStorage.get(callBackId));
    }

    @Override
    public void updateStatus(String callBackId, String status, Object  baseResultCallBack) {
        log.info("Update callback id:{} with status:{}", callBackId, status);
        Optional<BaseResultCallBack> optionalStackOverflowTagListStorage = findResultFromStorage(callBackId);
        if (optionalStackOverflowTagListStorage.isPresent()) {
            BaseResultCallBack baseResultCallBackResult = new BaseResultCallBack();
            baseResultCallBackResult.setStatus(status);
            baseResultCallBackResult.setResult(baseResultCallBack);
            baseResultCallBackResult.setCallBackId(callBackId);
            myMemoryStorage.put(callBackId,baseResultCallBackResult);
        }
    }


}
