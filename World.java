import java.util.ArrayList;
public class World{
    ArrayList<Person> population = new ArrayList<Person>();
    int availableCredits;
    int testCost;
    static int dailyMeetings;
    static int historyMeetings; //In days
    int duration = 0;
    int incubation;
    int r0;  //se < 1, endgame
    int infectivity; //da 1 a 100
    int sintomaticity;
    int letality;

    public World(Data input){ 
        for(int i = 0; i<input.population; i++){
            population.add(new Person());
        }
            availableCredits = input.resources;
            testCost = input.testCost;
            dailyMeetings = input.dailyMeetings;
            infectivity = input.infectivity;
            sintomaticity = input.sintomaticity;
            letality = input.letality;
            incubation = input.incubation;
            historyMeetings = input.historyMeetings;
    }
}