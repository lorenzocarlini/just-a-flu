import java.util.ArrayList;
import java.util.HashMap;

public class World {
    HashMap<Integer,Person> population = new HashMap<Integer,Person>();
    int availableCredits;
    int testCost;
    static int dailyMeetings = 5;
    static int historyMeetings = 5; //In days
    int day = 0;
    int incubation;
    int duration;
    int r0;  //se < 1, endgame
    int infectivity; //da 1 a 100
    int sintomaticity;
    int letality;

        public World(InputData inputParameters) {
            for(int i = 0; i<inputParameters.population; i++){
                population.put(i,new Person(i,this));
            }
            this.availableCredits = inputParameters.availableCredits;
            this.testCost = inputParameters.testCost;
            this.duration = inputParameters.duration;
            this.incubation = this.duration/6;
            this.infectivity = inputParameters.infectivity;
            this.sintomaticity = inputParameters.sintomaticity;
            this.letality = inputParameters.letality;
        }
        public void nextDay(){
            for(Person individual : population.values()){
                individual.nextDay();
                day++;
            }
        }



}