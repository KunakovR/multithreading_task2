import java.util.Arrays;
import java.util.List;
import java.util.concurrent.*;

public class Main {

    private static int count = 0;

    public static void main(String[] args) throws InterruptedException, ExecutionException {

        System.out.println("Создаю потоки!..");

        final Callable<Integer> myCallable1 = new MyCallable("Поток 1");
        final Callable<Integer> myCallable2 = new MyCallable("Поток 2");
        final Callable<Integer> myCallable3 = new MyCallable("Поток 3");
        final Callable<Integer> myCallable4 = new MyCallable("Поток 4");

        final ExecutorService threadPool = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());

        final List<Callable<Integer>> myCallables = Arrays.asList(myCallable1, myCallable2, myCallable3, myCallable4);

        try {
            List<Future<Integer>> futures = threadPool.invokeAll(myCallables);
            for (Future<Integer> future : futures) {
                count += future.get();
            }
            System.out.println("Кол-во отправленных сообщений: " + count);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        try {
            Integer result = threadPool.invokeAny(myCallables);
            System.out.println("Результат самой быстрой задачи: " + result);
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }

        System.out.println("Завершаю все потоки...");
        threadPool.shutdown();
    }
}
