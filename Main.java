public class Main {
    public static void main(String[] args){
        InputData pp = new InputData();
        World Terra = new World(pp);
        Terra.population.get(0).infect();
        Terra.nextDay();
        Terra.nextDay();
        Terra.nextDay();
        Terra.nextDay();
        Terra.nextDay();
        Terra.nextDay();
        Terra.nextDay();
        Terra.nextDay();
        Terra.nextDay();



        System.out.println("ciao");
    }
}