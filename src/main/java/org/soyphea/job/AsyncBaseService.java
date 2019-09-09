package org.soyphea.job;

import org.soyphea.worker.TaskCallbackStorageWorker;
import org.springframework.scheduling.annotation.Async;

public abstract class AsyncBaseService {

    protected TaskCallbackStorageWorker taskCallbackStorageWorker;

    @Async
    abstract public void execute(String userId, String callBackId) throws Exception;

    public AsyncBaseService(TaskCallbackStorageWorker taskCallbackStorageWorker) {
        this.taskCallbackStorageWorker = taskCallbackStorageWorker;
    }
}
