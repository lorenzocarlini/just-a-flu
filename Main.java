import java.util.Scanner;

public class Main {
    public static void main(String[] args){
        InputData pp = new InputData();
        World Terra = new World(pp);
        
        Terra.population.get(1).infect();
        for(int i = 0;i < 100; i++){
            System.out.println("");
            System.out.println("giorno "+ Terra.day);
            Terra.nextDay();
            Terra.Meetings();
            
           
        }
        System.out.println("Done");
    }
}