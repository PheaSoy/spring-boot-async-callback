package org.soyphea.job;

import lombok.extern.slf4j.Slf4j;
import org.soyphea.domain.StackOverflowTagList;
import org.soyphea.worker.TaskCallbackStorageWorker;
import org.soyphea.utils.UrlBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.CompletableFuture;

@Slf4j
@Service
public class STUserTagFetchingAsync extends STUserTagFetchingService {

    @Autowired
    private UrlBuilder urlBuilder;

    @Qualifier("bootifulRestTemplate")

    @Autowired
    private RestTemplate restTemplate;

    public STUserTagFetchingAsync(TaskCallbackStorageWorker taskCallbackStorageWorker) {
        super(taskCallbackStorageWorker);
    }

    public CompletableFuture<StackOverflowTagList> fetchTagsFromStackOverFlow(String userId) throws Exception {

        log.info("Fetching StackOverflow tags using RestTemplate+CompletableFuture with user id:{}", userId);
        CompletableFuture<StackOverflowTagList> stackOverflowTagResponse =
                CompletableFuture.supplyAsync(() -> restTemplate.getForObject(urlBuilder.fullApiEndpoint(userId),
                        StackOverflowTagList.class));
        log.info("StackOverflow tags of user id:{} is done:{}",
                userId, stackOverflowTagResponse.isDone());
        return stackOverflowTagResponse;

    }

    public CompletableFuture<StackOverflowTagList> processBusinessLogic(String callbackId) throws Exception{

        CompletableFuture<StackOverflowTagList> result = fetchTagsFromStackOverFlow(callbackId)
                .thenApplyAsync(stackOverflowTagList -> {
                    return applyMyBusinessLogic(stackOverflowTagList);
                });
        return result;
    }

    @Async
    public  StackOverflowTagList applyMyBusinessLogic(StackOverflowTagList stackOverflowTagList) {
        log.info("Apply my business logic from result might take some time.");
        return stackOverflowTagList;
    }

    public void execute(String userId, String callBackId) throws Exception {

        taskCallbackStorageWorker.processing(callBackId);
        processBusinessLogic(userId)
                .thenAcceptAsync(stackOverflowTagList ->
                    taskCallbackStorageWorker.completed(callBackId, stackOverflowTagList)
                );
    }
}
