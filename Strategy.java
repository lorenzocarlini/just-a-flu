import java.util.ArrayList;

public class Strategy {
    public World myWorld;

    //mette in quarantena le persone
    public static int quarantineStrategy=0;  //0 = libera dalla quarantena; 1 = solo rossi; 2 = metà popolazione; 3 = tutti; 4 = non fa nulla
    //tracement mette in quarantena gli incontri recenti del soggetto, senza testarli!
    public static int tracementStrategy=0;  // 0 = nessuno; 1 = incontri giorno prima(dei rossi e identificati asinotmatici); 2 = per tutto l'historyMeetings (se attivo) dei rossi e identificati asinotmatici;
    //swab mette in quarantena i positivi al tampone e identifica gli asintomatici
    public static int swabStrategy=0;   //0 = nessuno; 1 = incontri giorno prima; 2 = incontrati dai rossi per tutto l'historyMeetings (se attivo);
    public static boolean tracementStrategyComplete = false;  //ha bisogno di tot giorni per diventare true, dai quali in poi puoi abilitare i tracciamenti per tutto l'historyMeetings
    public static boolean vaccineStrategyComplete = false;  

    public boolean TracementResearch=false;
    private boolean isStartUpTracement=true; //riferito ai tracciamenti
    public int TracementDay = -1; //this

    public boolean vaccineResearch=false;   
    private boolean isStartUpVaccine=true;  //riferito al vaccino
    private int vaccineDay = -1;

    Strategy(World myWorld){
        this.myWorld=myWorld;
    }

    public void applyStrategies(Person currentPerson){

        vaccine();
        historyMeetingsResearch();

        quarantine(currentPerson);
        tracement(currentPerson);
        swab(currentPerson);
    }

    public void quarantine(Person currentPerson){
        //0 = libera dalla quarantena; 1 = solo rossi; 2 = metà popolazione; 3 = tutti; 4 = non fa nulla
        if(currentPerson.infectionStatus==1){
            currentPerson.isQuarantined=false;
        }
        switch(quarantineStrategy){
            case 0:
                currentPerson.isQuarantined=false;
                break;
            case 1:
                if(currentPerson.infectionStatus==4 || currentPerson.isVisible){
                    currentPerson.isQuarantined=true;
                }
                else{
                    currentPerson.isQuarantined=false;

                }
                break;
            case 2:
                if(currentPerson.myId%2==0){
                    currentPerson.isQuarantined=true;
                }
                break;
            case 3:
                currentPerson.isQuarantined=true;
        }
    }

    public void tracement(Person currentPerson){
        // 0 = nessuno; 1 = incontri giorno prima(dei rossi e identificati asinotmatici);
        // 2 = per tutto l'historyMeetings (se attivo) dei rossi e identificati asinotmatici;
        if(!currentPerson.hasBeenTested){
            switch(tracementStrategy){
                case 0:
                    break;
                case 1:
                    if(currentPerson.isVisible || currentPerson.infectionStatus==4){
                        for(Integer id : currentPerson.recentContacts.get(currentPerson.recentContacts.size()-1)){
                            if(myWorld.population.get(id).quarantineTimer == -1 && myWorld.population.get(id).infectionStatus>1){
                                //System.out.println(id+ " è stato messo in quarantena");
                               myWorld.population.get(id).isQuarantined=true;
                               myWorld.population.get(id).quarantineTimer = myWorld.duration;
                            }
                        }
                    }
                    break;
                case 2:
                    if(tracementStrategyComplete){
                        if(currentPerson.isVisible || currentPerson.infectionStatus==4){
                            //CONTROLLARE SE IL PRIMO CICLO NON SFORA DI GIORNI!
                            for(int i = 0; i < currentPerson.recentContacts.size(); i++){
                                try {
                                    for (Integer id : currentPerson.recentContacts.get(i)) {
                                        if (myWorld.population.get(id).quarantineTimer == -1 && myWorld.population.get(id).infectionStatus > 1) {
                                            myWorld.population.get(id).isQuarantined = true;
                                            myWorld.population.get(id).quarantineTimer = myWorld.duration;
                                        }
                                    }
                                }
                                catch(NullPointerException e){
                                    continue;
                                }
                            }
                        }
                    }
                    break;
            }
        }
    }    
    
    public void swab(Person currentPerson){
        ///0 = nessuno; 1 = incontri giorno prima(dei rossi e identificati asinotmatici); 2 = per tutto l'historyMeetings (se attivo) dei rossi e identificati asinotmatici;
        if(!currentPerson.hasBeenTested || currentPerson.testedToday){
            switch(swabStrategy){
                case 0:
                    break;
                case 1:
                    if(currentPerson.isVisible || currentPerson.infectionStatus==4){
                        //System.out.println("controllo gli incontri recenti di " + currentPerson.myId);
                        for(Integer id : currentPerson.recentContacts.get(currentPerson.recentContacts.size()-1)) {
                            try {
                                if (myWorld.population.get(id).infectionStatus == 3 || myWorld.population.get(id).infectionStatus == 2) {

                                    myWorld.swabTestCost();
                                    if (myWorld.population.get(id).test()) {
                                        //adesso la persona dovrebbe essere identificata come malata asintomatica
                                        //System.out.println(id + " è risultato positivo al test!");
                                        myWorld.population.get(id).testedToday = true;
                                        myWorld.population.get(id).isQuarantined = true;
                                        myWorld.population.get(id).isVisible = true;
                                    }
                                }
                            }
                            catch(NullPointerException e){
                                continue;}

                        }


                        }

                    break;
                case 2:
                    if(tracementStrategyComplete){
                        if(currentPerson.isVisible || currentPerson.infectionStatus==4){
                            //System.out.println("controllo gli incontri recenti di " + currentPerson.myId);
                            for(int i = 0; i < currentPerson.recentContacts.size(); i++){
                                for(Integer id : currentPerson.recentContacts.get(i)){
                                    try {
                                        if (myWorld.population.get(id).infectionStatus == 3 || myWorld.population.get(id).infectionStatus == 2) {
                                            myWorld.swabTestCost();
                                            if (myWorld.population.get(id).test()) {
                                                //adesso la persona dovrebbe essere identificata come malata asintomatica
                                                //System.out.println(id + " è risultato positivo al test!");
                                                myWorld.population.get(id).testedToday = true;
                                                myWorld.population.get(id).isQuarantined = true;
                                                myWorld.population.get(id).isVisible = true;
                                            }
                                        }
                                    }
                                    catch (NullPointerException e){
                                        continue;
                                    }
                                }
                            }
                        }
                    }
                    break;
            }
            currentPerson.hasBeenTested=true;
        }
    }
    
    public boolean vaccine(){
        //deve impiegare un tempo e costo proporzionale alla popolazione (o altri parametri), decidiamo più avanti in testing
        if(vaccineResearch){
            if(isStartUpVaccine){
                vaccineDay = myWorld.day + 60;
                myWorld.availableCredits-=myWorld.availableCredits%3;
                isStartUpVaccine=false;
            }
            if(vaccineDay==myWorld.day){
                //IL VACCINO è PRONTO
                //decidere cosa fare, se vittoria o distribuzione
                vaccineStrategyComplete = true;
            }
        }
        return vaccineStrategyComplete;
    }

    public boolean historyMeetingsResearch(){
        if(TracementResearch){
            if(isStartUpTracement){
                //TracementDay = myWorld.day + 3; //InputVariabile invece di 3 //
                isStartUpTracement=false;
            }
            if(TracementDay==myWorld.day){
                tracementStrategyComplete=true;
                //System.out.println("i tracciamenti sono ora disponibili!");
            }
        }
        return tracementStrategyComplete;

    }


}