import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import java.util.Scanner;

public class World {
    Scanner myObj = new Scanner(System.in);  // Create a Scanner object

    HashMap<Integer,Person> population = new HashMap<Integer,Person>();
    int totalDeaths=0;
    //HashMap<Integer, Integer> region = new HashMap<Integer,Integer>();  //id regione, id persone dentro
    private Random randomSeed = new Random(); //Seed di randomizzazione
    volatile int availableCredits;
    int testCost;
    int dailyMeetings;
    int dailyMeetingsOffset;
    int historyMeetings; //In days, giorni di memoria del tracer
    int day = 0;
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
            }

        }
        //A INIZIO GIORNATA ABBIAMO GLI STATI DELLE PERSONE, A FINE GIORNATA GLI INCONTRI!
        public void nextDay(){
            r0 = vd * duration * infectivity;

            System.out.println("ecco i dati di ieri");
            System.out.println("r0 è "+ r0);
            System.out.println("i crediti a disposizione sono "+ availableCredits);

            ArrayList<Integer> deaths = new ArrayList<Integer>(); 
            //passiamo al giorno dopo
            for(Person individual : population.values()){
                if(individual.infectionStatus==0){
                    //quando qualcuno muore
                    deaths.add(individual.myId);
                    continue;
                }
                currentStrategies.quarantine(individual);

                    if(individual.infectionStatus==3){
                        System.out.println(individual.myId+" is sick, yellow");
                    }
                    if(individual.infectionStatus==4){
                        System.out.println(individual.myId+" is sick, red");
                        System.out.println("Strategia per oggi?");
                        String userInput = myObj.nextLine();  // Read user input, GESTISCI TE FRA!
                        currentStrategies.quarantineStrategy = Integer.parseInt(userInput);
                    }

                individual.nextDay();
                
            }
            for(Integer death : deaths){
                System.out.println(death + " is ded");
                population.remove(death);
                totalDeaths++;
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
                peopleCanMeetId.add(i);
            }
            for (Person individual : population.values()){
                //se sono rossi, non incontrano, ma possono essere incontrati
                if(individual.infectionStatus==4)
                    continue;
                for(int i=0; i<individual.dailyMeetings;i++){
                    int currentId=peopleCanMeetId.get(randomSeed.nextInt(peopleCanMeetId.size()));
                    while(individual.meet(currentId)==false){
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