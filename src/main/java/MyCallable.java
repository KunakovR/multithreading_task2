import java.util.concurrent.Callable;

public class MyCallable implements Callable<Integer> {

    private String name;

    public MyCallable(String name) {
        this.name = name;
    }

    @Override
    public Integer call() throws Exception {
        Thread.currentThread().setName(name);
        int count = 0;
        while (count <= 4) {
            Thread.sleep(2500);
            count++;
            System.out.println("Я " + Thread.currentThread().getName() + ". Всем привет!");
        }
        System.out.printf("%s завершен.\n", Thread.currentThread().getName());
        return count;
    }


}
