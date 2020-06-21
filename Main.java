import java.util.Scanner;

public class Main extends Thread{
    public static boolean isRunning = true;
    public static Object monitor;
    public static long timeForDay = 20;
    public static void main(String[] args) throws InterruptedException {
        InputData pp = new InputData();
        World Terra = new World(pp);
        Terra.population.get(1).infect();
        threadSetup(new Object());
        MainThread t1 = new MainThread(Terra);
        System.out.println("Done");
    }
    public static void threadSetup(Object newMonitor){
        Main.monitor = newMonitor;
    }
    public static void timeChange(int newTime){
        Main.timeForDay = newTime;
    }

}
class MainThread extends Thread {
    Object myMonitor = Main.monitor;
    MainThread(World Terra) throws InterruptedException {
        synchronized (myMonitor) {
            while (Main.isRunning) {
                long start = System.currentTimeMillis();
                System.out.println("");
                System.out.println("giorno " + Terra.day);
                Terra.nextDay();
                Terra.Meetings();
                long elapsedTime = System.currentTimeMillis() - start;
                if (elapsedTime < Main.timeForDay){
                    sleep(Main.timeForDay-elapsedTime);
                }

            }
        }
    }
}