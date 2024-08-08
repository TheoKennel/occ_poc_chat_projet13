package com.backend.infrastructure.controller.messages;

import com.backend.infrastructure.responses.ApiResponse;
import com.backend.infrastructure.responses.MessageResponse;
import com.backend.domain.models.Message;
import com.backend.domain.use_cases.UseCaseExecutor;
import com.backend.domain.use_cases.messages.GetMessages;
import com.backend.domain.use_cases.messages.SaveMessage;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public class MessagesController implements MessageResources {

    private final UseCaseExecutor useCaseExecutor;
    private final GetMessages getMessages;
    private final SaveMessage saveMessage;

    public MessagesController(
            UseCaseExecutor useCaseExecutor,
            GetMessages getMessages,
            SaveMessage saveMessage) {
        this.useCaseExecutor = useCaseExecutor;
        this.getMessages = getMessages;
        this.saveMessage = saveMessage;
    }

    @Override
    public CompletableFuture<List<MessageResponse>> getMessages(@RequestBody Long conversationUUID) {
        return useCaseExecutor.execute(
                getMessages,
                new GetMessages.InputValues(conversationUUID),
                outputValues -> MessageResponse.from(outputValues.messages())
        );
    }

    @Override
    public CompletableFuture<ApiResponse> saveMessage(@RequestBody Message message) {
        return useCaseExecutor.execute(
                saveMessage,
                new SaveMessage.InputValues(message),
                outputValues -> new ApiResponse(outputValues.isSave(), "Save message successfully")
        );
    }
}
