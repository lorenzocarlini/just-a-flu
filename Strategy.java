import java.util.ArrayList;

public class Strategy {
    public World myWorld;
    public int quarantineStrategy=0;  //0 = nessuno; 1 = solo rossi; 2 = contatti recenti; 3 = tutti;
    public int tracementStrategy=0;  //0 = niente; 1 = blocca incontri giorno prima;
    public int swabStrategy=0;   //0 = nessuno; 1 = incontri giorno prima; 2 = incontrati dai rossi per tutto l'historyMeetings (se attivo);
    public boolean tracementStrategyComplete = false;  //ha bisogno di tot giorni per diventare true, dai quali in poi puoi abilitare i tracciamenti per tutto l'historyMeetings
    public boolean vaccineStrategyComplete = false;

    public boolean TracementResearch=false;
    private boolean isStartUpTracement=true; //riferito ai tracciamenti
    private int TracementDay = -1;

    public boolean vaccineResearch=false;   
    private boolean isStartUpVaccine=true;  //riferito al vaccino
    private int vaccineDay = -1;

    Strategy(World myWorld){
        this.myWorld=myWorld;
    }

    public void applyStrategies(Person currentPerson){

        vaccine();
        historyMeetingsResearch();
        //implementare con i threads!
        quarantine(currentPerson);
        //tracement(currentPerson);
        swab(currentPerson);
    }

    public void quarantine(Person currentPerson){
        //0 = nessuno; 1 = solo rossi; 2 = metà della popolazione; 3 = tutti;
        switch(quarantineStrategy){
            case 0:
                currentPerson.isQuarantined=false;
                break;
            case 1:
                if(currentPerson.infectionStatus==4){
                    currentPerson.isQuarantined=true;
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
        //STABILIAMO INSIEME I CRITERI!
        //per adesso blocco solo persone giorno prima!
        for(Integer id : currentPerson.recentContacts.get(currentPerson.recentContacts.size()-1)){
            myWorld.population.get(id).isQuarantined=true;
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
                TracementDay = myWorld.day + 30;
                isStartUpTracement=false;
            }
            if(TracementDay==myWorld.day){
                tracementStrategyComplete=true;
            }
        }
        return tracementStrategyComplete;

    }

    public void swab(Person currentPerson){
        ///0 = nessuno; 1 = incontri giorno prima(dei rossi e identificati asinotmatici); 2 = per tutto l'historyMeetings (se attivo) dei rossi e identificati asinotmatici;
        switch(swabStrategy){
            case 0:
                break;
            case 1:
                if(currentPerson.isVisible || currentPerson.infectionStatus==4){
                    for(Integer id : currentPerson.recentContacts.get(currentPerson.recentContacts.size()-1)){
                        if(myWorld.population.get(id).test())
                            myWorld.swabTestCost();
                            //adesso la persona dovrebbe essere identificata come malata asintomatica
                            myWorld.population.get(id).isVisible=true;  //vedi te fra come gestire i visibili
                    }
                }
                break;
            case 2:
                if(tracementStrategyComplete){
                    if(currentPerson.isVisible || currentPerson.infectionStatus==4){
                        //CONTROLLARE SE IL PRIMO CICLO NON SFORA DI GIORNI!
                        for(int i = 0; i < currentPerson.recentContacts.size(); i++){
                            for(Integer id : currentPerson.recentContacts.get(i)){
                                if(myWorld.population.get(id).test())
                                    myWorld.swabTestCost();
                                    //adesso la persona dovrebbe essere identificata come malata asintomatica
                                    myWorld.population.get(id).isVisible=true;  //vedi te fra come gestire i visibili
                            }
                        }
                    }
                }
                break;
        }
    }
}