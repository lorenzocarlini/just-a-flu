import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PiePlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
import java.util.ArrayList;

import javax.swing.*;
import java.awt.*;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

class MyFrame1 extends JFrame {

    public static Object myMonitor = new Object();

    JPanel nordPnl = new JPanel();
    JPanel centroPnl = new JPanel();
    JPanel sudPnl = new JPanel();

    JPanel Jptesto2 = new JPanel();
    JPanel Jptesto3 = new JPanel();
    JPanel Jptesto4 = new JPanel();
    JPanel Jptesto5 = new JPanel();
    JPanel Jptesto6 = new JPanel();
    JPanel Jptesto7 = new JPanel();
    JPanel Jptesto8 = new JPanel();
    JPanel Jptesto9 = new JPanel();
    JPanel Jptesto10 = new JPanel();


    JLabel Testo1 = new JLabel("INSERISCI:");
    JLabel Testo2 = new JLabel("Popolazione");
    JLabel Testo3 = new JLabel("Risorse");
    JLabel Testo4 = new JLabel("Costo tampone");
    JLabel Testo5 = new JLabel("Incontri giornalieri");
    JLabel Testo6 = new JLabel("Storia degli incontri (in giorni)");
    JLabel Testo7 = new JLabel("Infettività (da 1 a 100)");
    JLabel Testo8 = new JLabel("Sintomaticità");
    JLabel Testo9 = new JLabel("Letalità");
    JLabel Testo10 = new JLabel("Durata");

    JTextField CampoT1=new JTextField();
    JTextField CampoT2=new JTextField();
    JTextField CampoT3=new JTextField();
    JTextField CampoT4=new JTextField();
    JTextField CampoT5=new JTextField();
    JTextField CampoT6=new JTextField();
    JTextField CampoT7=new JTextField();
    JTextField CampoT8=new JTextField();
    JTextField CampoT9=new JTextField();


    JButton okBtn=new JButton("OK");

    static int popolazione=0;
    static int risorse=0;
    static int costo_cure=0;
    static int incontri_gio=0;
    static int storia_inc=0;
    static int infettivita=0;
    static int sintomaticita=0;
    static int letalita=0;
    static int durata=0;

    public MyFrame1() {

        super("Menù");

        Jptesto2.setLayout(new FlowLayout());
        Jptesto2.add(Testo2);
        Jptesto3.setLayout(new FlowLayout());
        Jptesto3.add(Testo3);
        Jptesto4.setLayout(new FlowLayout());
        Jptesto4.add(Testo4);
        Jptesto5.setLayout(new FlowLayout());
        Jptesto5.add(Testo5);
        Jptesto6.setLayout(new FlowLayout());
        Jptesto6.add(Testo6);
        Jptesto7.setLayout(new FlowLayout());
        Jptesto7.add(Testo7);
        Jptesto8.setLayout(new FlowLayout());
        Jptesto8.add(Testo8);
        Jptesto9.setLayout(new FlowLayout());
        Jptesto9.add(Testo9);
        Jptesto10.setLayout(new FlowLayout());
        Jptesto10.add(Testo10);


        centroPnl.setLayout(new GridLayout(6,3));

        centroPnl.add(Jptesto2);
        centroPnl.add(Jptesto3);
        centroPnl.add(Jptesto4);
        centroPnl.add(CampoT1);
        centroPnl.add(CampoT2);
        centroPnl.add(CampoT3);

        centroPnl.add(Jptesto5);
        centroPnl.add(Jptesto6);
        centroPnl.add(Jptesto7);
        centroPnl.add(CampoT4);
        centroPnl.add(CampoT5);
        centroPnl.add(CampoT6);

        centroPnl.add(Jptesto8);
        centroPnl.add(Jptesto9);
        centroPnl.add(Jptesto10);
        centroPnl.add(CampoT7);
        centroPnl.add(CampoT8);
        centroPnl.add(CampoT9);

        nordPnl.add(Testo1);

        sudPnl.add(okBtn);

        nordPnl.setBackground(Color.decode("#fbf1c7"));
        centroPnl.setBackground(Color.decode("#fbf1c7"));
        sudPnl.setBackground(Color.decode("#fbf1c7"));

        Jptesto2.setBackground(Color.decode("#fbf1c7"));
        Jptesto3.setBackground(Color.decode("#fbf1c7"));
        Jptesto4.setBackground(Color.decode("#fbf1c7"));
        Jptesto5.setBackground(Color.decode("#fbf1c7"));
        Jptesto6.setBackground(Color.decode("#fbf1c7"));
        Jptesto7.setBackground(Color.decode("#fbf1c7"));
        Jptesto8.setBackground(Color.decode("#fbf1c7"));
        Jptesto9.setBackground(Color.decode("#fbf1c7"));
        Jptesto10.setBackground(Color.decode("#fbf1c7"));


        Testo1.setForeground(Color.decode("#3c3836"));
        Testo2.setForeground(Color.decode("#3c3836"));
        Testo3.setForeground(Color.decode("#3c3836"));
        Testo4.setForeground(Color.decode("#3c3836"));
        Testo5.setForeground(Color.decode("#3c3836"));
        Testo6.setForeground(Color.decode("#3c3836"));
        Testo7.setForeground(Color.decode("#3c3836"));
        Testo8.setForeground(Color.decode("#3c3836"));
        Testo9.setForeground(Color.decode("#3c3836"));
        Testo10.setForeground(Color.decode("#3c3836"));


        CampoT1.setBackground(Color.decode("#d5c4a1"));
        CampoT2.setBackground(Color.decode("#d5c4a1"));
        CampoT3.setBackground(Color.decode("#d5c4a1"));
        CampoT4.setBackground(Color.decode("#d5c4a1"));
        CampoT5.setBackground(Color.decode("#d5c4a1"));
        CampoT6.setBackground(Color.decode("#d5c4a1"));
        CampoT7.setBackground(Color.decode("#d5c4a1"));
        CampoT8.setBackground(Color.decode("#d5c4a1"));
        CampoT9.setBackground(Color.decode("#d5c4a1"));


        okBtn.setBackground(Color.decode("#d5c4a1"));


        getContentPane().add(nordPnl,BorderLayout.NORTH);
        getContentPane().add(centroPnl,BorderLayout.CENTER);
        getContentPane().add(sudPnl,BorderLayout.SOUTH);


        okBtn.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                try {
                    popolazione= Integer.parseInt(CampoT1.getText());
                    risorse= Integer.parseInt(CampoT2.getText());
                    costo_cure= Integer.parseInt(CampoT3.getText());
                    incontri_gio= Integer.parseInt(CampoT4.getText());
                    storia_inc= Integer.parseInt(CampoT5.getText());
                    infettivita= Integer.parseInt(CampoT6.getText());
                    sintomaticita= Integer.parseInt(CampoT7.getText());
                    letalita= Integer.parseInt(CampoT8.getText());
                    durata= Integer.parseInt(CampoT9.getText());




                    if (popolazione <= 0 || risorse <= 0 || costo_cure <= 0 || incontri_gio <= 0 || storia_inc <= 0 || infettivita <= 0 || sintomaticita <= 0 || letalita <= 0 || durata<= 0) {
                        JOptionPane.showMessageDialog(getContentPane(), "Inserire un valore maggiore o uguale a 1 in ogni informazione");
                    }
                    else {
                        if (risorse > (10 * popolazione * costo_cure) || risorse > (popolazione * durata)) {
                            if ((10 * popolazione * costo_cure) < (popolazione * durata)) {
                                JOptionPane.showMessageDialog(getContentPane(),"Valore delle risorse troppo alto. Reinserire un valore minore o uguale a : "+(10*popolazione*costo_cure));
                            }
                            else {
                                JOptionPane.showMessageDialog(getContentPane(),"Valore delle risorse troppo alto. Reinserire un valore minore o uguale a : "+(popolazione * durata));
                            }

                        }
                        else {

                            setVisible(false);
                            //home1.secondaFinestra = new MyFrame2(popolazione, risorse, costo_cure, incontri_gio, storia_inc, infettivita, sintomaticita, letalita, durata);
                            MyFrame2.startSimulation(popolazione,risorse,costo_cure,incontri_gio,storia_inc,infettivita,sintomaticita,letalita,durata);


                        }

                    }

                } catch (Exception k) {
                    //JOptionPane.showMessageDialog(getContentPane(), "inserire un valore maggiore o uguale a 1 in ogni informazione");
                }
            }// grafico a barre aggiornato
        });


        setSize(550,250);
        setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation((int) ((dim.getWidth()-this.getWidth())/2),(int) ((dim.getHeight()-this.getHeight())/2));

    }
}

class MyFrame2 extends JFrame {
    JPanel NP = new JPanel();
    JPanel CP = new JPanel();
    JPanel OP = new JPanel();
    JPanel P1 = new JPanel();
    JPanel P2 = new JPanel();
    JPanel P3 = new JPanel();
    JPanel P4 = new JPanel();
    JPanel P5 = new JPanel();
    JPanel P6 = new JPanel();
    JPanel P7 = new JPanel();
    JPanel P8 = new JPanel();
    JPanel P9 = new JPanel();

    static JLabel Spop = new JLabel("POPOLAZIONE:");
    static JLabel Sris = new JLabel("RISORSE:");
    static JLabel Ssan = new JLabel("SANI:");
    static JLabel Sasi = new JLabel("ASINTOMATICI:");
    static JLabel SasiTest = new JLabel("ASINTOMATICI TESTATI:");
    static JLabel Ssin = new JLabel("SINTOMATICI:");
    static JLabel Sgua = new JLabel("GUARITI:");
    static JLabel Smor = new JLabel("MORTI:");
    static JLabel Sgio = new JLabel("GIORNO:");

    static JLabel Testo_T1 = new JLabel("QUARANTENA:");
    static JLabel Testo_T2 = new JLabel("TRACCIAMENTO:");
    static JLabel Testo_T3 = new JLabel("TRAC. COMPLETO:");
    static JLabel Testo_T4 = new JLabel("TAMPONE:");
    static JLabel Testo_T5 = new JLabel("VACCINO:");
    static JLabel Testo_T6 = new JLabel("News");
    static JLabel news = new JLabel("...");


    String ST1[]={"Nessuno","Sintomatici","Contatti recenti","Tutti"};
    String ST2[]={"No","Blocca incontri (giorno prima)"};
    String ST3[]={"No","Blocca incontri (tutti i giorni)"};
    String ST4[]={"Nessuno","Incontri (giorno prima)","Incontrati dai rossi"};
    String ST5[]={"No","Si"};

    JComboBox cb1=new JComboBox(ST1);
    JComboBox cb2=new JComboBox(ST2);
    JComboBox cb3=new JComboBox(ST3);
    JComboBox cb4=new JComboBox(ST4);
    JComboBox cb5=new JComboBox(ST5);
// grafico a barre aggiornato
    JButton PausaBtn=new JButton("||"); //Prende controllo thread
    JButton PlayBtn=new JButton("⤇"); //Speed1
    JButton Speed2=new JButton("⤇⤇"); //Speed2



    public MyFrame2(int popolazione,int risorse, int costo_cure, int incontri_gio, int storia_inc, int infettivita, int sintomaticita, int letalita, int durata) throws InterruptedException {
        super("Simulatore");
        setSize(1300,650);
        setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation((int) ((dim.getWidth()-this.getWidth())/2),(int) ((dim.getHeight()-this.getHeight())/2));



        NP.setLayout(new GridLayout(1,9));
        NP.add(Spop);
        NP.add(Sris);
        NP.add(Ssan);
        NP.add(Sasi);
        NP.add(SasiTest);
        NP.add(Ssin);
        NP.add(Sgua);
        NP.add(Smor);
        NP.add(Sgio);

        MyFrame2.Spop.setText("POPOLAZIONE: "+MyFrame1.popolazione);
        MyFrame2.Sris.setText("RISORSE: "+World.availableCredits);
        MyFrame2.Ssan.setText("SANI: "+World.green);
        MyFrame2.Sasi.setText("ASINTOMATICI: "+World.yellow);
        MyFrame2.Sasi.setText("ASINTOMATICI TESTATI: "+World.orange);
        MyFrame2.Ssin.setText("SINTOMATICI: "+World.red);
        MyFrame2.Sgua.setText("GUARITI: "+World.blue);
        MyFrame2.Smor.setText("MORTI: "+World.black);
        MyFrame2.Sgio.setText("GIORNO: "+World.day);


        P1.add(Testo_T1);
        P1.add(cb1);
        P1.setBackground(Color.decode("#ebdbb2"));

        P2.add(Testo_T2);
        P2.add(cb2);
        P2.setBackground(Color.decode("#ebdbb2"));

        P3.add(Testo_T3);
        P3.add(cb3);
        P3.setBackground(Color.decode("#ebdbb2"));

        P4.add(Testo_T4);
        P4.add(cb4);
        P4.setBackground(Color.decode("#ebdbb2"));

        P5.add(Testo_T5);
        P5.add(cb5);
        P5.setBackground(Color.decode("#ebdbb2"));

        P6.setLayout(new GridLayout(2,1));
        P8.setLayout(new FlowLayout());
        P8.add(Testo_T6);
        P9.setLayout(new FlowLayout());
        P9.add(news);
        P6.add(P8);
        P6.add(P9);
        P6.setBackground(Color.decode("#ebdbb2"));
        P8.setBackground(Color.decode("#ebdbb2"));
        P9.setBackground(Color.decode("#ebdbb2"));


        PausaBtn.setBackground(Color.decode("#a89984"));
        P7.add(PausaBtn);
        PlayBtn.setBackground(Color.decode("#a89984"));
        P7.add(PlayBtn);
        Speed2.setBackground(Color.decode("#a89984"));
        P7.add(Speed2);
        P7.setBackground(Color.decode("#ebdbb2"));

        OP.setLayout(new GridLayout(7,1));
        OP.add(P6);
        OP.add(P1);
        OP.add(P2);
        OP.add(P3);
        OP.add(P4);
        OP.add(P5);
        OP.add(P7);

        NP.setBackground(Color.decode("#fbf1c7"));
        CP.setBackground(Color.decode("#f9f5d7"));
        OP.setBackground(Color.decode("#ebdbb2"));

        //aggiorna();

        DefaultPieDataset dataset = new DefaultPieDataset();

        //Loop update
        dataset.setValue("SINTOMATICI",World.red);
        dataset.setValue("GUARITI",World.blue);
        dataset.setValue("SANI",World.green);
        dataset.setValue("ASINTOMATICI",World.yellow);
        dataset.setValue("ASINTOMATICI TESTATI",World.orange);
        dataset.setValue("MORTI",World.black);

        JFreeChart chart = ChartFactory.createPieChart("Popolazione", dataset, true, true, false);
        chart.setBackgroundPaint(Color.decode("#f9f5d7"));
        PiePlot plot = (PiePlot) chart.getPlot();
        plot.setInteriorGap(0.0);
        plot.setLabelGenerator(null);
        plot.setBackgroundPaint(Color.decode("#f9f5d7"));
        ChartPanel chartPanel = new ChartPanel(chart);

        // crei isto
        DefaultCategoryDataset dataset2 = new DefaultCategoryDataset();

        //Cancelli pannello centro, lo ricrei con dati nuovi
        dataset2.setValue(19, "MALATI", "giorno "+1);
        dataset2.setValue(6, "GUARITI", "giorno "+1);
        dataset2.setValue(0, "MORTI", "giorno "+1);
        dataset2.setValue(35, "MALATI", "giorno "+2);
        dataset2.setValue(11, "GUARITI", "giorno "+2);
        dataset2.setValue(9, "MORTI", "giorno "+2);
        dataset2.setValue(16, "MALATI", "giorno "+3);
        dataset2.setValue(8, "GUARITI", "giorno "+3);
        dataset2.setValue(6, "MORTI", "giorno "+3);
        dataset2.setValue(12, "MALATI", "giorno "+4);
        dataset2.setValue(17, "GUARITI", "giorno "+4);
        dataset2.setValue(3, "MORTI", "giorno "+4);


        JFreeChart chart2 = ChartFactory.createBarChart("Evoluzione malattia","giorni", "persone", dataset2, PlotOrientation.VERTICAL, true, true, false);
        chart2.setBackgroundPaint(Color.decode("#f9f5d7"));
        CategoryPlot plot2 = (CategoryPlot) chart2.getPlot();
        plot2.setBackgroundPaint(Color.decode("#f9f5d7"));
        ChartPanel chartPanel2 = new ChartPanel(chart2);
// fine copia


        CP.setLayout(new GridLayout(1,2));

        // aggiungi al pannelo centrale i grafici
        CP.add(chartPanel); //Ricrea torta
        CP.add(chartPanel2); //Ricrea isto
        //CP.removeAll(); cancella i componenti del pannello

        getContentPane().add(NP,BorderLayout.NORTH);
        getContentPane().add(CP,BorderLayout.CENTER);
        getContentPane().add(OP,BorderLayout.WEST);



        // inizio bottoni
        cb1.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                switch(String.valueOf(cb1.getItemAt(cb1.getSelectedIndex()))) {                 //QUARANTENA
                    case "Nessuno":
                        Strategy.quarantineStrategy=0;
                        break;

                    case "Sintomatici":
                        Strategy.quarantineStrategy=1;
                        break;

                    case "Contatti recenti":
                        Strategy.quarantineStrategy=2;
                        break;

                    case "Tutti":
                        Strategy.quarantineStrategy=3;
                        break;

                }
                System.out.println(Strategy.quarantineStrategy);
            }
        });
        cb2.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                switch(String.valueOf(cb2.getItemAt(cb2.getSelectedIndex()))) {                 //TRACCIAMENTO
                    case "No":
                        Strategy.tracementStrategy=0;
                        break;

                    case "Blocca incontri (giorno prima)":
                        Strategy.tracementStrategy=1;
                        break;

                }
                System.out.println(Strategy.tracementStrategy);
            }
        });
        cb3.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                switch(String.valueOf(cb3.getItemAt(cb3.getSelectedIndex()))) {             //TRACCIAMENTO COMPLETO
                    case "No":
                        Strategy.tracementStrategyComplete= false;
                        break;

                    case "Blocca incontri (tutti i giorni)":
                        Strategy.tracementStrategyComplete= true;
                        break;
                }
                System.out.println(Strategy.tracementStrategyComplete);
            }
        });
        cb4.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                switch(String.valueOf(cb4.getItemAt(cb4.getSelectedIndex()))) {                 //TAMPONE
                    case "Nessuno":
                        Strategy.swabStrategy=0;
                        break;

                    case "Incontri (giorno prima)":
                        Strategy.swabStrategy=1;
                        break;

                    case "Incontrati dai rossi":
                        Strategy.swabStrategy=2;
                        break;
                }
                System.out.println(Strategy.swabStrategy);
            }
        });
        cb5.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                switch(String.valueOf(cb5.getItemAt(cb5.getSelectedIndex()))) {                 //VACCINO
                    case "No":
                        Strategy.vaccineStrategyComplete= false;
                        break;

                    case "Si":
                        Strategy.vaccineStrategyComplete= true;
                        break;
                }
                System.out.println(Strategy.vaccineStrategyComplete);
            }
        });


        PausaBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

            }
        });
        PlayBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

            }
        });
        Speed2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

            }
        });
        // fine bottoni

        //MyFrame2.startSimulation(popolazione,risorse,costo_cure,incontri_gio,storia_inc,infettivita,sintomaticita,letalita,durata);  //Dati ricevuti quasi tutti 1
    }




    public static void startSimulation(int popolazione, int risorse, int costo_cure, int incontri_gio, int storia_inc, int infettivita, int sintomaticita, int letalita, int durata) throws InterruptedException {
        InputData myData = new InputData();
        myData.population = popolazione;
        myData.availableCredits = risorse;
        //myData.population = costo_cure;
        myData.dailyMeetings = incontri_gio;
        myData.historyMeetings = storia_inc;
        myData.infectivity = infettivita;
        myData.sintomaticity = sintomaticita;
        myData.letality = letalita ;
        myData.duration = durata;

        Main.threadSetup(MyFrame1.myMonitor);
        World Terra = new World(myData);
        home1.myWorld = Terra;
        Terra.population.get(1).infect();

        home1.secondaFinestra = new MyFrame2(popolazione, risorse, costo_cure, incontri_gio, storia_inc, infettivita, sintomaticita, letalita, durata);
        MainThread t1 = new MainThread(Terra);
        GuiUpdate t2 = new GuiUpdate();
        while(true) {
            t1.start();
            t2.start();
            System.out.println("out???");
        }


    }

    static ArrayList<Integer> listavalori = new ArrayList<Integer>();
    static ArrayList<String> listatipo = new ArrayList<String>();
    static ArrayList<Integer> listagiorni = new ArrayList<Integer>();
    static ArrayList<ArrayList> lista = new ArrayList<ArrayList>();

    public static void aggiorna () {

        /*
        home1.secondaFinestra.Spop.setText("POPOLAZIONE: "+home1.myWorld.population);
        home1.secondaFinestra.Sris.setText("RISORSE: "+home1.myWorld.availableCredits);
        home1.secondaFinestra.Ssan.setText("SANI: "+home1.myWorld.green);
        home1.secondaFinestra.Sasi.setText("ASINTOMATICI: "+home1.myWorld.yellow);
        home1.secondaFinestra.Sasi.setText("ASINTOMATICI TESTATI: "+home1.myWorld.orange);
        home1.secondaFinestra.Ssin.setText("SINTOMATICI: "+home1.myWorld.red);
        home1.secondaFinestra.Sgua.setText("GUARITI: "+home1.myWorld.blue);
        home1.secondaFinestra.Smor.setText("MORTI: "+home1.myWorld.black);
        home1.secondaFinestra.Sgio.setText("GIORNO: "+home1.myWorld.day);
         */


        home1.secondaFinestra.Spop.setText("POPOLAZIONE: "+ home1.myWorld.population);
        home1.secondaFinestra.Sris.setText("RISORSE: "+home1.myWorld.availableCredits);
        home1.secondaFinestra.Ssan.setText("SANI: "+home1.myWorld.green);
        home1.secondaFinestra.Sasi.setText("ASINTOMATICI: "+home1.myWorld.yellow);
        //home1.secondaFinestra.("ASINTOMATICI TESTATI: "+home1.myWorld.orange);
        home1.secondaFinestra.Ssin.setText("SINTOMATICI: "+home1.myWorld.red);
        home1.secondaFinestra.Sgua.setText("GUARITI: "+home1.myWorld.blue);
        home1.secondaFinestra.Smor.setText("MORTI: "+home1.myWorld.black);
        home1.secondaFinestra.Sgio.setText("GIORNO: "+home1.myWorld.day);

        /*
        home1.secondaFinestra.P1.add(MyFrame2.Testo_T1);
        P1.add(cb1);
        P1.setBackground(Color.decode("#ebdbb2"));

        P2.add(Testo_T2);
        P2.add(cb2);
        P2.setBackground(Color.decode("#ebdbb2"));

        P3.add(Testo_T3);
        P3.add(cb3);
        P3.setBackground(Color.decode("#ebdbb2"));

        P4.add(Testo_T4);
        P4.add(cb4);
        P4.setBackground(Color.decode("#ebdbb2"));

        P5.add(Testo_T5);
        P5.add(cb5);
        P5.setBackground(Color.decode("#ebdbb2"));

         */

        // grafico a torta aggiornato
        DefaultPieDataset dataset = new DefaultPieDataset();
        dataset.setValue("SINTOMATICI",World.red);
        dataset.setValue("GUARITI",World.blue);
        dataset.setValue("SANI",World.green);
        dataset.setValue("ASINTOMATICI",World.yellow);
        dataset.setValue("ASINTOMATICI TESTATI",World.orange);
        dataset.setValue("MORTI",World.black);

        JFreeChart chart = ChartFactory.createPieChart("Popolazione", dataset, true, true, false);
        chart.setBackgroundPaint(Color.decode("#f9f5d7"));
        PiePlot plot = (PiePlot) chart.getPlot();
        plot.setInteriorGap(0.0);
        plot.setLabelGenerator(null);
        plot.setBackgroundPaint(Color.decode("#f9f5d7"));
        ChartPanel chartPanel = new ChartPanel(chart);

        home1.secondaFinestra.CP.removeAll();

        // grafico a barre aggiornato
        //             malati , guariti, morti
        listavalori.add(World.red); listavalori.add(World.blue); listavalori.add(World.black);
        listatipo.add("MALATI"); listatipo.add("GUARITI"); listatipo.add("MORTI");
        listagiorni.add(World.day); listagiorni.add(World.day); listagiorni.add(World.day);
        lista.add(lista.size(),listavalori);

        DefaultCategoryDataset dataset2 = new DefaultCategoryDataset();

        //Cancelli pannello centro, lo ricrei con dati nuovi
        dataset2.setValue(19, "MALATI", "giorno "+1);
        dataset2.setValue(6, "GUARITI", "giorno "+1);
        dataset2.setValue(0, "MORTI", "giorno "+1);
        dataset2.setValue(35, "MALATI", "giorno "+2);
        dataset2.setValue(11, "GUARITI", "giorno "+2);
        dataset2.setValue(9, "MORTI", "giorno "+2);
        dataset2.setValue(16, "MALATI", "giorno "+3);
        dataset2.setValue(8, "GUARITI", "giorno "+3);
        dataset2.setValue(6, "MORTI", "giorno "+3);
        dataset2.setValue(12, "MALATI", "giorno "+4);
        dataset2.setValue(17, "GUARITI", "giorno "+4);
        dataset2.setValue(3, "MORTI", "giorno "+4);




        JFreeChart chart2 = ChartFactory.createBarChart("Evoluzione malattia","giorni", "persone", dataset2, PlotOrientation.VERTICAL, true, true, false);
        chart2.setBackgroundPaint(Color.decode("#f9f5d7"));
        CategoryPlot plot2 = (CategoryPlot) chart2.getPlot();
        plot2.setBackgroundPaint(Color.decode("#f9f5d7"));
        ChartPanel chartPanel2 = new ChartPanel(chart2);

    }
}


public class home1 {
    public static MyFrame2 secondaFinestra;
    public static World myWorld;
    public static final Object myKey = new Object();
    public static void main(String[] args) {

    }
}

class GuiUpdate extends Thread {
    GuiUpdate() throws InterruptedException {
            synchronized (home1.myKey) {
                System.out.println("Gui start");
                System.out.println(Main.isSimulating);
                    while (Main.isSimulating) {
                        try {
                            System.out.println("gui wait");
                            home1.myKey.wait();
                        } catch (InterruptedException e) {
                            System.out.println("exception gui5");
                        }
                    }

                    System.out.println("aiuto");
                    home1.secondaFinestra.aggiorna();
                    System.out.println("42 cavalli bianchi");
                    Main.isSimulating = true;
                    home1.myKey.notifyAll();
            }
        }
    }
