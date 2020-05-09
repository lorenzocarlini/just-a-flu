import java.util.ArrayList;
public class Person{
    int infectionStatus = 0;  //0=G, 1=Y, 2=R, 3=B, 4=N
    boolean isQuarantined = false;  //legato alla produzione
    ArrayList<Integer> recentContacts;
}