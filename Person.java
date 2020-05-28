import java.util.ArrayList;
import java.util.Random;

//Classe per la gestione di ogni singola persona all'interno della simulazione
public class Person{
    ArrayList<Integer> recentContacts = new ArrayList<Integer>(); //Lista degli id con cui la persona ha avuto contatti recenti
    private Random chance = new Random(); //Seed per generazione di numeri
    private World myParent; //Identitá dell'oggetto World genitore
    int day = 0; //Giorno in cui si trova la persona (Per gestione degli stage dell'infezione
    public int myId;

    int infectionStatus = 0;  //0=G, 1=Y, 2=R, 3=B, 4=N (Magari rimuovere del tutto?)
    private int incubationStart = -1; //Giorno di inizio dell'incubazione, se -1 non é stato ancora esposto
    private int symptomsStart = -1; //Giorno in cui inizieranno i sintomi (mai se -1)
    private int relativeSymptomsStart = -1; //Variabile di debug, dopo quanti giorni RELATIVI all' inizio infezione si mostreranno i primi sintomi
    int infectionEnd = -1; //Data della fine della sua malattia
    private boolean willDie = false; //Morirá alla fine della sua infezione?
    boolean isQuarantined = false;  //Se quarantenato puó incontrare / essere incontrato
    boolean isIncubating = false; //Se attualmente in fase di incubazione / progressione del virus
    boolean isSymptomatic = false; //Se sintomatico



    public Person(int id,World parent){
         myParent = parent;
         myId = id;
    }


    void nextDay() {
        day++;
        if(isIncubating) {
            if (day == incubationStart + myParent.incubation) {
                infectionStatus = 1;
                futureSymptoms();
                infectionOutcome();
            }
            if (infectionStatus == 1 && day == symptomsStart) {
                infectionStatus = 2;
                isSymptomatic = true;
            }
            if (day == infectionEnd) {
                if (willDie && infectionStatus == 2) {
                    infectionStatus = 4;
                    //Richiesta obitorio
                } else {
                    infectionStatus = 3;
                }
                isIncubating = false;
            }
        }


    } //Funzione che gestite il progresso dell'infezione
    void infect(){
        if (!isIncubating && infectionStatus == 0){ //Se il soggetto non é né guarito né morto
            incubationStart = day; //Avvia il processo di incubazione
            isIncubating = true;
        }

    } //Avvio del processo di infezione
    void meet(int id){
        if (myParent.population.containsKey(id)){
            if (recentContacts.size() == myParent.dailyMeetings * myParent.historyMeetings && recentContacts.size()>0 ){
                recentContacts.remove(0);
            }
            if(!recentContacts.contains(id)){ recentContacts.add(id);}
            if(chance.nextInt(99) <= myParent.infectivity-1 && infectionStatus > 0 && infectionStatus < 3){
                myParent.population.get(id).infect();

            }
        }
        else{System.out.println("No person with ID:"+id);}
        myParent.population.get(id).recentContacts.add(myId);


    } //Funzione che gestisce il processo di incontro, logging e possibile contagio
    boolean test(){

        return infectionStatus == 1 || infectionStatus == 2;
    }

    private void futureSymptoms(){
        if(chance.nextInt(99) <= myParent.sintomaticity-1){
            relativeSymptomsStart = chance.nextInt((myParent.duration-1)/3);
            symptomsStart = day + relativeSymptomsStart;
        }

    } //Determinazione della sintomaticitá o meno (ed in caso positivo dell'inizio dei sintomi) dell'individuo
    private void infectionOutcome(){
        if(symptomsStart != -1 && chance.nextInt(99) <= myParent.letality-1){
            infectionEnd = symptomsStart + chance.nextInt(myParent.duration - relativeSymptomsStart);
            willDie = true;
        }
        else{infectionEnd = day + myParent.duration;}
    } //Determina la fine dell' infezione (normale decorso normalmente, qualsiasi giorno tra l' apparizione dei sintomi e fine del decorso normale in caso di morte)
}

