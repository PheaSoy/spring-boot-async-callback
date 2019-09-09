package org.soyphea.worker;

import org.soyphea.domain.BaseCallBackResponse;
import org.soyphea.domain.BaseResultCallBack;

import java.util.Optional;
import java.util.UUID;


public interface TaskCallbackStorageWorker<T> {


    void initStorage(BaseResultCallBack baseResultCallBack);

    void processing(String callBackId);

    void completed(String callBackId, T returnResult);


    void updateStatus(String callBackId, String status, T baseResultCallBack);

    Optional<T> findResultFromStorage(String callBackId);

    default BaseCallBackResponse generateCallBackIdWithInitStatus() {

        String callBackId = UUID.randomUUID().toString();
        BaseCallBackResponse apiResponse = new BaseCallBackResponse(callBackId);
        initStorage(new BaseResultCallBack(
                callBackId, Status.INIT.toString(), null
        ));
        return apiResponse;

    }


}
