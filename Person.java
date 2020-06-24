import java.util.ArrayList;
import java.util.Random;
import java.util.HashMap;

public class Person {
    public int myId; //Identificativo univoco
    public int myDay = 0; //Giorno in cui si trova la persona (usato per i calcoli per il progresso dell'infezione)
    public boolean isQuarantined = false; //É in quarantena? ( o fermo)
    public boolean isVisible = false; //É possibile vedere il suo stato? Per gialli solo dopo tampone
    public ArrayList<ArrayList<Integer>> recentContacts = new ArrayList<ArrayList<Integer>>(); //Lista degli id con cui la persona ha avuto contatti recenti
    private Random randomSeed = new Random(); //Seed di randomizzazione
    private World myWorld; //Oggetto World genitore
    public int infectionStatus = 2; //0=Nero (Morto), 1=Blu (Immune), 2=Verde (Sano), 3=Giallo (Infetto asintomatico), 4=Rosso (Infetto sintomatico)
    public int dateInfectionStart = -1;
    public int dateSymptomsStart = -1; //Data in cui inizieranno i sintomi (se inizieranno)
    public int dateInfectionEnd = -1;
    public int quarantineTimer = -1;
    public boolean isDeadly = false;
    public boolean hasBeenInfected=false;
    public int dailyMeetings;
    public int dailyMeetingsDone;
    public boolean hasBeenTested=false;  //controllo per vedere se hanno già testato i contatti che ha incontrato
    public boolean testedToday=false;   //per vedere se è già stato testato oggi

    public Person(int id, World parent){
        myWorld = parent;
        myId = id;
        calculateMeetings();
    }

    void nextDay(){
        //calcola incontri che dovrà fare persona, controlla stato malattia se malato
        myDay++;
        calculateMeetings();
        if(quarantineTimer!=-1){
            quarantineTimer--;
            if(quarantineTimer==0){
                isQuarantined=false;
                quarantineTimer--;
            }
        }
        testedToday=false;
        if(isQuarantined){
            //se sta fermo, toglie crediti
            myWorld.removeCredit();
        }

        //se è rosso, costi da sostenere
        if(infectionStatus==4){
            myWorld.medicalCare();
        }
        
        if(infectionStatus == 2 && myDay == dateInfectionStart){
            infectionStatus = 3;
        }

        if(infectionStatus >= 3){
            if(myDay >= dateSymptomsStart && infectionStatus!=4){
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
        //se è giallo o rosso, può infettare
        if(randomSeed.nextInt(99) < myWorld.infectivity-1 & infectionStatus >= 3){
            return true;
        }
        else{return false;}
    }
    void infect(){
        //se sei stato infettato da verde
        //ESSENDO CHE RIMANGONO VERDI, AGGIUNGO BOOLEANA PER NON RIPETERE L'INFEZIONE
        if(infectionStatus == 2 && !hasBeenInfected){
            dateInfectionStart = myDay + myWorld.duration/6;
            System.out.println(myId+" è stato infettato");
            hasBeenInfected=true;
            infectionPlanner();
        }
    }
    private void infectionPlanner(){
        //quando prendi la malattia, come si svilupperà
        //dateIncubationStart = localDay;
        if (randomSeed.nextInt(99) <= myWorld.sintomaticity) {
            dateSymptomsStart = dateInfectionStart + randomSeed.nextInt((myWorld.duration - 1) / 3 - (dateInfectionStart - myDay) );
            if(dateSymptomsStart < myDay + myWorld.duration/6){
                dateSymptomsStart = myDay + 1 + myWorld.duration/6;

            }
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
    boolean meet(int id){
        //torna true se l'incontro è stato possibile, false altrimenti
        if (myWorld.population.containsKey(id)
         && myWorld.population.get(id).dailyMeetingsDone < myWorld.population.get(id).dailyMeetings
         && id!=myId){
            //contatti recenti si aggiungono
            addToRecentContacts(id);
            myWorld.population.get(id).addToRecentContacts(myId);

            if(willInfect()){
                myWorld.population.get(id).infect();
            }
            if(myWorld.population.get(id).willInfect()){
                infect();
            }
            System.out.println("incontro tra " + myId + " e " + id);
            this.dailyMeetingsDone+=1;
            myWorld.population.get(id).dailyMeetingsDone+=1;
            myWorld.vd+=2;
            return true;
        }
        //se non lo incontra, vuol dire che è morto, oppure ha già fatto il suo numero di incontri
        else{return false;}


    }
    public boolean test(){
        //tampone!
        if (infectionStatus > 2){
            return true;
        }
        else{
            return false;
        }
    }
    private void calculateMeetings(){
        //calcolo degli incontri che farà una persona
        if(isQuarantined)
            dailyMeetings=0;
        else
            dailyMeetings = myWorld.dailyMeetings + (randomSeed.nextInt(myWorld.dailyMeetingsOffset + 1) -1);
            if(dailyMeetings==0)
                dailyMeetings=1;
        dailyMeetingsDone = 0;

    }

    public void addToRecentContacts(int id){
        
        //aggiungo ai contatti recenti l'id di input
        if (recentContacts.size() == myWorld.historyMeetings){
            recentContacts.remove(0);
        }
        if (dailyMeetingsDone==0){
            recentContacts.add(new ArrayList<Integer>());
            hasBeenTested=false;
        }
        recentContacts.get(recentContacts.size()-1).add(id);

    }
}


