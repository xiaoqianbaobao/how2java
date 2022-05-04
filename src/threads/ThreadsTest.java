package threads;

public class ThreadsTest {
    public static final int DELAY = 10;
    public static final int STEPS = 100;
    public static final double MAX_AMOUNT = 1000;
    public static final int NACCOUNTS = 100;
    public static final double INITIAL_BALANCE = 1000;


    public static void main(String[] args) {
        var bank = new Bank(4, 100000);
        for (int i = 0; i < NACCOUNTS; i++) {
            int fromAccount = i;
            Runnable runnable = () -> {
                try {
                    while (true) {
                        int toAccount = (int) (bank.size() * Math.random());
                        double amount = MAX_AMOUNT * Math.random();
                        bank.transfer(fromAccount, toAccount, amount);
                        Thread.sleep((int) (DELAY * Math.random()));
                    }
                }catch (InterruptedException e){}
            };
            var t = new Thread(runnable);
            t.start();
        }
    }
}
