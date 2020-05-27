public class Main {
    public static void main(String[] args){
        InputData pp = new InputData();
        World Terra = new World(pp);
        Terra.population.get(0).infect();
        //Terra.population.get(0).meet(101);
        Terra.population.get(0).meet(4);
        for(int i = 0;i < 30; i++){
            Terra.population.get(0).meet(4);
            Terra.nextDay();
            //System.out.println(Terra.population.get(0).test());
            //System.out.println(Terra.population.get(0).test());
            //System.out.println(Terra.population.get(4).test());
            //System.out.println("//");
        }

    }
}