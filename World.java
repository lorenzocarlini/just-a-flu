import java.util.ArrayList;
import java.util.Collection;

public class World {
    ArrayList<Person> population = new ArrayList<Person>();
    int availableCredits;
    int testCost;
    static int dailyMeetings;
    static int historyMeetings; //In days
    int day = 0;
    int incubation;
    int duration;
    int r0;  //se < 1, endgame
    int infectivity; //da 1 a 100
    int sintomaticity;
    int letality;

        public World(InputData inputParameters) {
            for(int i = 0; i<inputParameters.population; i++){
                population.add(new Person(this));
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
            for(Person individual : population){
                individual.nextDay();
            }
        }



}