import java.util.ArrayList;
import java.util.Random;

public class Person {
    public int myId; //Identificativo univoco
    public int myDay = 0; //Giorno in cui si trova la persona (usato per i calcoli per il progresso dell'infezione)
    public boolean isQuarantined = false; //É in quarantena?
    public boolean isSymptomatic = false; //É sintomatico?
    private ArrayList<Integer> recentContacts = new ArrayList<Integer>(); //Lista degli id con cui la persona ha avuto contatti recenti
    private Random randomSeed = new Random(); //Seed di randomizzazione
    private World myWorld; //Oggetto World genitore
    public int infectionStatus = 2; //0=Nero (Morto), 1=Blu (Immune), 2=Verde (Sano), 3=Giallo (Infetto asintomatico), 4=Rosso (Infetto sintomatico)
    public int dateInfectionStart = -1;
    public int dateSymptomsStart = -1; //Data in cui inizieranno i sintomi (se inizieranno)
    public int dateInfectionEnd = -1;
    public boolean isDeadly = false;
    public int dailyMeetings;

    public Person(int id, World parent){
        myWorld = parent;
        myId = id;
        calculateMeetings();
    }

    void nextDay(){
        myDay++;
        calculateMeetings();
        if(infectionStatus == 2 && myDay == dateInfectionStart){
            infectionStatus = 3;
        }

        if(infectionStatus >= 3){
            if(myDay == dateSymptomsStart){
                infectionStatus = 4;
            }
            else if(myDay >= dateInfectionEnd){
                if(isDeadly && infectionStatus == 4){
                    infectionStatus = 0;
                }
                else{
                    infectionStatus = 1;
                }
            }

        }
    }
    public boolean willInfect(){
        if(randomSeed.nextInt(99) < myWorld.infectivity-1 & infectionStatus >= 3){
            return true;
        }
        else{return false;}
    }
    void infect(){
        if(infectionStatus == 2){
            dateInfectionStart = myDay + myWorld.duration/6;
            infectionPlanner();
        }
    }
    private void infectionPlanner(){
        //dateIncubationStart = localDay;
        if (randomSeed.nextInt(99) <= myWorld.sintomaticity) {
            dateSymptomsStart = myDay + randomSeed.nextInt((myWorld.duration - 1) / 3);
            if (randomSeed.nextInt(99) < myWorld.letality-1) {
                isDeadly = true;
            }
        }


        if(isDeadly){
            dateInfectionEnd = dateSymptomsStart + randomSeed.nextInt(myWorld.duration - (dateSymptomsStart - myDay));
        }
        else {
            dateInfectionEnd = myDay + myWorld.duration;
        }
    }
    void meet(int id){
        if (myWorld.population.containsKey(id)){
            if (recentContacts.size() == myWorld.dailyMeetings * myWorld.historyMeetings && recentContacts.size()>0 ){
                recentContacts.remove(0);
            }
            if(!recentContacts.contains(id)) {
                recentContacts.add(id);
            }
            if(willInfect()){
                myWorld.population.get(id).infect();
            }
            if(myWorld.population.get(id).willInfect()){
                infect();
            }
            myWorld.population.get(id).recentContacts.add(myId);
            }
        else{System.out.println("No person with ID:"+id);}

    }
    public boolean test(){
        if (infectionStatus > 2){
            return true;
        }
        else{
            return false;
        }
    }
    private void calculateMeetings(){
        dailyMeetings = myWorld.dailyMeetings + (randomSeed.nextInt(myWorld.dailyMeetingsOffset + 1) -1);


    }
}


