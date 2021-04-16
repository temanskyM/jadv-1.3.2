import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.LongAdder;
import java.util.stream.IntStream;

public class App {
    private static final int COUNT_SHOP = 3;
    private static final long TIMEOUT = 10L;

    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
        LongAdder stat = new LongAdder();
        IntStream.range(0, COUNT_SHOP)
                .forEach(i -> executorService.execute(new Shop(stat)));
        executorService.awaitTermination(TIMEOUT, TimeUnit.SECONDS);
        System.out.println("Общий результат: " + stat.sum());
        executorService.shutdown();
    }
}
