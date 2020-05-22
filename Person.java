import java.util.ArrayList;
import java.lang.Object;
import java.util.Random;

public class Person{
    int day = 0;
    Random chance = new Random();
    private World myParent;
    int infectionStatus = 0;  //0=G, 1=Y, 2=R, 3=B, 4=N (Magari rimuovere del tutto?)
    int incubationStart = -1;
    int infectionStart = -1;
    boolean isQuarantined = false;  //legato alla produzione
    ArrayList<Integer> recentContacts;

    public Person(World parent){
         myParent = parent;
    }

    void nextDay() {
        day++;
        if (day == incubationStart + myParent.incubation) {
            infectionStatus = 1;
        }
        if (infectionStatus == 1 && day <= infectionStart + myParent.duration / 3) {
            if (chance.nextInt(99) <= myParent.sintomaticity - 1) {
                infectionStatus = 2;
            }
        }
    }


    void infect(){
        incubationStart = myParent.day;
        infectionStart = myParent.day;
    }
    void meet(int id){
        if (recentContacts.size() == World.dailyMeetings * World.historyMeetings ){
            recentContacts.remove(0);
        }
        if(chance.nextInt(99) <= myParent.infectivity-1 && infectionStatus > 0 && infectionStatus < 3){
            myParent.population.get(id).infect();
        recentContacts.add(id);


        }


    }

}

