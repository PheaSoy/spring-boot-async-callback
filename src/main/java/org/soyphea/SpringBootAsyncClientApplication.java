package org.soyphea;

import lombok.extern.slf4j.Slf4j;
import org.soyphea.domain.BaseCallBackResponse;
import org.soyphea.domain.BaseResultCallBack;
import org.soyphea.job.AsyncBaseService;
import org.soyphea.worker.MemoryCallbackStorageWorker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.Executor;

@SpringBootApplication
@EnableAsync
@RestController
@Slf4j
public class SpringBootAsyncClientApplication{

    @Autowired
    private AsyncBaseService asyncBaseService;

    @Autowired
    private MemoryCallbackStorageWorker memoryCallbackStorageWorker;

    public static void main(String[] args) {

        SpringApplication.run(SpringBootAsyncClientApplication.class, args);
    }

    @GetMapping("/api/stackoverflow/{user_id}/tags")
    BaseCallBackResponse create(@PathVariable("user_id") String userId) throws Exception {
        BaseCallBackResponse apiResponse = memoryCallbackStorageWorker.generateCallBackIdWithInitStatus();
        log.info("API Response with callback id:{}", apiResponse.getCallBackId());
        asyncBaseService.execute(userId, apiResponse.getCallBackId());
        return apiResponse;
    }

    @GetMapping("/api/stackoverflow/{callback_id}/callbacks")
    BaseResultCallBack get(@PathVariable("callback_id") String callBackId) {
        return memoryCallbackStorageWorker.findResultFromStorage(callBackId).get();
    }


}
