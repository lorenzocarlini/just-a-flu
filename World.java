import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import java.util.Scanner;

public class World {
    Scanner myObj = new Scanner(System.in);  // Create a Scanner object

    static int black=0;
    static int blue=0;
    static int green=0;
    static int yellow=0;
    static int orange=0;
    static int red=0;
    static int quarantinedPeople=0;


    HashMap<Integer,Person> population = new HashMap<Integer,Person>();
    //HashMap<Integer, Integer> region = new HashMap<Integer,Integer>();  //id regione, id persone dentro
    private Random randomSeed = new Random(); //Seed di randomizzazione
    static volatile int availableCredits;
    int testCost;
    int dailyMeetings;
    int dailyMeetingsOffset;
    int historyMeetings; //In days, giorni di memoria del tracer
    static int day = 0;
    int incubation;
    int duration;
    float r0;  //se < 1, endgame
    int infectivity; //da 1 a 100
    int sintomaticity;
    int letality;
    float vd; //incontri medi
    Strategy currentStrategies = new Strategy(this);

        public World(InputData inputParameters) {
            //generazione mondo
            this.availableCredits = inputParameters.availableCredits;
            this.testCost = inputParameters.testCost;
            this.duration = inputParameters.duration;
            this.incubation = this.duration/6;
            this.infectivity = inputParameters.infectivity;
            this.sintomaticity = inputParameters.sintomaticity;
            this.letality = inputParameters.letality;
            this.dailyMeetings = inputParameters.dailyMeetings;
            this.historyMeetings = inputParameters.historyMeetings;
            this.dailyMeetingsOffset = getMeetingsOffset();

            for(int i = 1; i<=inputParameters.population; i++){
                population.put(i,new Person(i,this));
                green+=1;
            }
            
        }
        //A INIZIO GIORNATA ABBIAMO GLI STATI DELLE PERSONE, A FINE GIORNATA GLI INCONTRI!
        public void nextDay(){
            r0 = vd * duration * infectivity;

            //System.out.println("ecco i dati di ieri");
            //System.out.println("r0 è "+ r0);
            //System.out.println("i crediti a disposizione sono "+ availableCredits);
            /*
            QUA VANNO PRINTATI I DATI DEL GIORNO PRECEDENTE!!
            */

            blue=0;
            green=0;
            yellow=0;
            orange=0;
            red=0;
            quarantinedPeople=0;
            ArrayList<Integer> deaths = new ArrayList<Integer>(); 
            //passiamo al giorno dopo
            for(Person individual : population.values()){
                if(individual.isQuarantined)
                    quarantinedPeople++;
                switch(individual.infectionStatus){
                    case 0:
                        //quando qualcuno muore
                        deaths.add(individual.myId);
                        continue;
                    case 1:
                        blue++;
                        individual.isQuarantined = false;
                        individual.quarantineTimer = -1;
                    break;
                    case 2:
                        green++;
                    break;
                    case 3:
                        //System.out.println(individual.myId + " is yellow");
                        if(individual.isVisible)
                            orange++;
                        else
                            yellow++;
                    break;
                    case 4:
                        //System.out.println(individual.myId + " is red");
                        red++;
                    break;
                }
                individual.nextDay();
                currentStrategies.applyStrategies(individual);
            }
            for(Integer death : deaths){
                //System.out.println(death + " é morto");
                population.remove(death);
                black++;
            }
            day++;
        }

        public void removeCredit(){
            availableCredits-=1;
        }
        public void medicalCare(){
            availableCredits-=testCost*3;
        }
        public void swabTestCost(){
            availableCredits-=testCost;
        }
        public int getMeetingsOffset(){
            float temp = dailyMeetings;
            temp = (temp/100)*20;
            return (int)temp;
        }

        public void Meetings(){
            //fa avvenire gli incontri tra persone
            vd=0;
            ArrayList<Integer> peopleCanMeetId = new ArrayList<Integer>();

            for(int i = 1; i<population.size(); i++){
                if(population.containsKey(i)){
                    if(population.get(i).isQuarantined)
                        continue;
                }
                peopleCanMeetId.add(i);
            }
            for (Person individual : population.values()){
                //se sono rossi, non incontrano, ma possono essere incontrati
                if(individual.infectionStatus==4)
                    continue;
                for(int i=0; i<individual.dailyMeetings;i++){
                    if(peopleCanMeetId.size()<=0){
                        vd=vd/population.size();
                        //nemmeno un incontro era libero
                        return;
                    }
                    int currentId=peopleCanMeetId.get(randomSeed.nextInt(peopleCanMeetId.size()));
                    while(individual.meet(currentId)==false || (peopleCanMeetId.size()==1 && peopleCanMeetId.get(0)!=individual.myId)){
                        if(currentId!=individual.myId){
                            peopleCanMeetId.remove(peopleCanMeetId.indexOf(currentId));                     
                            if(peopleCanMeetId.size()==0
                             || (peopleCanMeetId.size()==1 && peopleCanMeetId.get(0)==individual.myId)){
                                vd=vd/population.size();
                                //nemmeno un incontro era libero
                                return;
                            }
                        }
                        currentId=peopleCanMeetId.get(randomSeed.nextInt(peopleCanMeetId.size()));

                    }
                }
            }
            vd=vd/(population.size());
        }




}