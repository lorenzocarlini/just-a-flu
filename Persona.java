/* 
• Popolazione iniziale (P), il numero di individui esistenti all’inizio della simulazione; 
• Risorse iniziali (R), la quantità di risorse inizialmente disponibili; 
• Costo delle cure (C), il costo della somministrazione di un tampone; 
• Velocità (V), il numero medio di incontri che ciascun individuo in movimento ha nel corso di una giornata.



• Infettività (I), la probabilità (maggiore di 0) che un individuo sano venga infettato a seguito di 
    un incontro con un contagiato asintomatico o sintomatico; 
• Sintomaticità (S), la probabilità (maggiore di 0) che un contagiato sviluppi sintomi; 
• Letalità (L), la probabilità (maggiore di 0) che un malato sintomatico muoia; 
• Durata (D), il numero di giorni che intercorrono fra il momento del contagio e quello della guarigione.

R < 10*P∗C.
R < P∗D.
R0 = Vd∗D ∗I.  (dove Vd è la media di incontri di tutti i soggetti, in movimento o no) 
dove R0 è il fattore di contagiosità
R0 < 1 la malattia si estingue

Il costo giornaliero delle cure somministrate a un ammalato sintomatico è 3C, tre volte quello di un tampone. 
all'inizio tutti sani, tranne un giallo
In un giorno di movimento un individuo aggiunge una unità alle risorse collettive.

contagio  com.sintom     morte
G   ->   Y    ->      R     ->      N
          \           |
            >        \/ 
          guarigione  B
*/
import java.util.ArrayList;

public class Persona{
    int id;
    int stato_malattia;  //0=G, 1=Y, 2=R, 3=B, 4=N
    boolean movimento;  //legato alla produzione
    ArrayList<Integer> id_incontrati;
    int incontri;  //corrispondenti a velocità v
}