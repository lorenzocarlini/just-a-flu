import java.util.Scanner;

public class Main extends Thread{
    //public static Object monitor;
    public static long timeForDay = 2000;
    public static boolean isSimulating = true;

    public static void main(String[] args) throws InterruptedException {
        System.out.println("Done");
        Gui myGui = new Gui();
    }
    public static void threadSetup(Object newMonitor){
        //Main.monitor = newMonitor;

    }
    public static void timeChange(int newTime){
        Main.timeForDay = newTime;
    }

}
class MainThread extends Thread {



    MainThread(World Terra) throws InterruptedException {
            System.out.println("Sim start");
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
        }
    }