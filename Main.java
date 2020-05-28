public class Main {
    public static void main(String[] args){
        InputData pp = new InputData();
        World Terra = new World(pp);
        Terra.population.get(0).infect();
        //Terra.population.get(0).meet(101);
        Terra.population.get(0).meet(4);
        for(int i = 0;i < 50; i++){
            Terra.population.get(0).meet(4);
            Terra.nextDay();
            System.out.println("[0:Status] " + Terra.population.get(0).infectionStatus);
            System.out.println("[0:End] " + Terra.population.get(0).infectionEnd);
            //System.out.println(Terra.population.get(0).test());
            System.out.println("[4:Status] " + Terra.population.get(4).infectionStatus);
            System.out.println("[4:End]" + Terra.population.get(4).infectionEnd);
            System.out.println("Current day: " + Terra.day);
            System.out.println("//");
        }
        System.out.println("Done");
    }
}