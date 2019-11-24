import java.util.concurrent.ThreadLocalRandom;

public class MyUtils {
    public static void sleep(int millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static int randomInt() {
        return ThreadLocalRandom.current().nextInt(100000);
    }
}
