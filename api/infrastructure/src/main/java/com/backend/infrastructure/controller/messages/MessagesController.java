package com.backend.infrastructure.controller.messages;

import com.backend.infrastructure.mapper.MessageMapperPresenter;
import com.backend.infrastructure.requests.MessageRequest;
import com.backend.infrastructure.responses.ApiResponse;
import com.backend.infrastructure.responses.MessageResponse;
import com.backend.domain.use_cases.UseCaseExecutor;
import com.backend.domain.use_cases.messages.GetMessages;
import com.backend.domain.use_cases.messages.SaveMessage;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@Component
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
    public CompletableFuture<List<MessageResponse>> getMessages(Long conversationId) {
        return useCaseExecutor.execute(
                getMessages,
                new GetMessages.InputValues(conversationId),
                outputValues -> MessageResponse.from(outputValues.messages())
        );
    }

    @Override
    public CompletableFuture<ApiResponse> saveMessage(MessageRequest message,  Long conversationId) {
        return useCaseExecutor.execute(
                saveMessage,
                new SaveMessage.InputValues(MessageMapperPresenter.INSTANCE.toDomain(message), conversationId),
                outputValues -> new ApiResponse(outputValues.isSave(), "Save message successfully")
        );
    }
}
