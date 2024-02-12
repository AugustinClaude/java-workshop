package fr.epita.assistants.scheduler;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;
import java.util.function.Supplier;

public class MyTask<INPUT_TYPE, RETURN_TYPE> implements Task<RETURN_TYPE> {
    CompletableFuture<RETURN_TYPE> future;

    static <RETURN_TYPE> Task<RETURN_TYPE> of(Supplier<RETURN_TYPE> actionSupplier) {
        CompletableFuture<RETURN_TYPE> future = CompletableFuture.supplyAsync(actionSupplier);
        MyTask<Void, RETURN_TYPE> task = new MyTask<>();
        task.future = future;
        return task;
    }

    @Override
    public CompletableFuture<RETURN_TYPE> build() {
        return this.future;
    }

    @Override
    public Task<RETURN_TYPE> onErrorRecoverWith(Function<Throwable, RETURN_TYPE> recoveryFunction) {
        CompletableFuture<RETURN_TYPE> future = this.future.handle((t, e) -> recoveryFunction.apply(e));
        MyTask<INPUT_TYPE, RETURN_TYPE> task = new MyTask<>();
        task.future = future;
        return task;
    }

    @Override
    public <NEW_RETURN_TYPE> Task<NEW_RETURN_TYPE> andThenDo(Function<RETURN_TYPE, NEW_RETURN_TYPE> action) {
        CompletableFuture<NEW_RETURN_TYPE> newFuture = this.future.thenApplyAsync(action);
        MyTask<RETURN_TYPE, NEW_RETURN_TYPE> task = new MyTask<>();
        task.future = newFuture;
        return task;
    }

    @Override
    public Task<RETURN_TYPE> andThenWait(long number, TimeUnit timeUnit) {
        CompletableFuture<RETURN_TYPE> future = this.future.thenCompose(res -> CompletableFuture.supplyAsync(() -> res, CompletableFuture.delayedExecutor(number, timeUnit)));
        MyTask<INPUT_TYPE, RETURN_TYPE> task = new MyTask<>();
        task.future = future;
        return task;
    }
}
