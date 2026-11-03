import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
/** Concurrency orchestration is isolated from model and visitor algorithms. */
public final class VisitorTaskRunner implements AutoCloseable {
    private final ExecutorService executor = Executors.newFixedThreadPool(2);
    public <T extends FileSystemVisitor> Future<T> run(FileSystemElement root, T visitor) {
        Callable<T> task = () -> { root.accept(visitor); return visitor; };
        return executor.submit(task);
    }
    @Override public void close() { executor.shutdown(); }
}
