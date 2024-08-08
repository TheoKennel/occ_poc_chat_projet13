package com.backend.infrastructure;

import com.backend.domain.use_cases.UseCase;
import com.backend.domain.use_cases.UseCaseExecutor;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;
import java.util.function.Function;

/**
 * Implementation of the UseCaseExecutor interface responsible for executing use cases asynchronously.
 */
@Service
public class UseCaseExecutorImpl implements UseCaseExecutor {

    @Override
    public <RX, I extends UseCase.InputValues, O extends UseCase.OutputValues> CompletableFuture<RX> execute(
            UseCase<I, O> useCase, I input, Function<O, RX> outputMapper) {
        return CompletableFuture
                .supplyAsync(() -> input)
                .thenApplyAsync(useCase::execute)
                .thenApplyAsync(outputMapper);
    }
}
