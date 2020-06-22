import java.util.Scanner;

public class Main extends Thread{
    //public static Object monitor;
    public static long timeForDay = 2000;
    public static boolean isSimulating = true;

    public static void main(String[] args) throws InterruptedException {
        new MyFrame1();
        //System.out.println("aaaa");
        //InputData pp = new InputData();
        //World Terra = new World(pp);
        //
        //threadSetup(new Object());
        //MainThread t1 = new MainThread(Terra);
        System.out.println("Done");
    }
    public static void threadSetup(Object newMonitor){
        //Main.monitor = newMonitor;

    }
    public static void timeChange(int newTime){
        Main.timeForDay = newTime;
    }

}
class MainThread extends Thread {
    //Object myMonitor = Main.monitor;



    MainThread(World Terra) throws InterruptedException {
        synchronized (home1.myKey) {
            System.out.println("Sim start");
            while (!Main.isSimulating) {
                try {
                    System.out.println("sim wait");
                    home1.myKey.wait();
                } catch (InterruptedException e) {
                    System.out.println("exception sim");
                }
            }
            if (Terra.day == 1) {
                Terra.population.get(1).infect();
            }
            long start = System.currentTimeMillis();
            System.out.println("");
            System.out.println("giorno " + Terra.day);
            Terra.nextDay();
            Terra.Meetings();
            long elapsedTime = System.currentTimeMillis() - start;
            if (elapsedTime < Main.timeForDay) {
                sleep(Main.timeForDay - elapsedTime);
            }
            Main.isSimulating = false;
            home1.myKey.notifyAll();
            }
        }
    }