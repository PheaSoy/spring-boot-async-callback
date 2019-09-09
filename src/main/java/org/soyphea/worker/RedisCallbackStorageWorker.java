package org.soyphea.worker;

import org.soyphea.domain.BaseResultCallBack;

import java.util.Optional;

public class RedisCallbackStorageWorker implements TaskCallbackStorageWorker {

    @Override
    public void initStorage(BaseResultCallBack baseResultCallBack) {

    }

    @Override
    public void processing(String callBackId) {

    }

    @Override
    public void completed(String callBackId, Object returnResult) {

    }

    @Override
    public void updateStatus(String callBackId, String status, Object baseResultCallBack) {

    }

    @Override
    public Optional findResultFromStorage(String callBackId) {
        return Optional.empty();
    }
}
