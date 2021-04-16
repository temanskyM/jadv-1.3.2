import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.atomic.LongAdder;

public class Shop implements Runnable {
    private LongAdder stat;
    private final int MAX_COUNT_NUMBERS = 2_000;
    private final int BOUND = 1_000;

    public Shop(LongAdder stat) {
        this.stat = stat;
    }

    @Override
    public void run() {
        //Генерируем массив случайных чисел
        Random random = new Random();
        int[] ints = random.ints(0, BOUND).limit(MAX_COUNT_NUMBERS).toArray();

        //Суммируем его
        int result = Arrays.stream(ints).sum();
        stat.add(result);

        System.out.println("Результат у магазина " + Thread.currentThread().getName() + ": " + result);
    }
}
