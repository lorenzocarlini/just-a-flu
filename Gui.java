import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.gui2.*;
import com.googlecode.lanterna.gui2.dialogs.MessageDialog;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;

import javax.swing.*;
import java.io.IOException;
import java.util.Arrays;
import java.util.regex.Pattern;

public class Gui{
    public static Panel myPanel;
    public static Panel dataPanel;
    public static Panel stratPanel;
    public static Window myWindow;
    public static Screen screen;
    public static Label lblDay = new Label("");
    public static Label lblCredits = new Label("");
    public static Label lblGreen = new Label("");
    public static Label lblYellow = new Label("");
    public static Label lblOrange = new Label("");
    public static Label lblRed = new Label("");
    public static Label lblBlack = new Label("");
    public static Label lblBlue = new Label("");
    public static Label lblR0= new Label("");
    public static Label lblTracementStart = new Label("");
    public static Label lblQuarantined = new Label("");
    public static MultiWindowTextGUI gui;
    public static ComboBox<String> comboBoxQuarantena = new ComboBox<String>().setReadOnly(false);
    public static ComboBox<String> comboBoxTracement = new ComboBox<String>().setReadOnly(false);
    public static ComboBox<String> comboBoxSwab = new ComboBox<String>().setReadOnly(false);
    private static World myWorld;
    public static boolean createdGui = false;
    public static boolean addedStrat;
    public static boolean triggerNewStrat;
    public static boolean isTracementComplete;

    public static MultiWindowTextGUI textGUI;


    public static void main(String[] args) throws IOException {
        // Setup terminal and screen layers
        Terminal terminal = new DefaultTerminalFactory().createTerminal();
        Gui.screen = new TerminalScreen(terminal);
        screen.startScreen();
        //Gui.textGUI = new MultiWindowTextGUI(screen);

        textGUI = new MultiWindowTextGUI(screen);




        // Create panel to hold components
        //Panel panel = new Panel();
        Gui.myPanel = new Panel();
        Gui.dataPanel = new Panel();
        Gui.stratPanel = new Panel();
        myPanel.setLayoutManager(new GridLayout(2));
        dataPanel.setLayoutManager(new GridLayout(1));
        stratPanel.setLayoutManager(new GridLayout(1));





        Gui.comboBoxQuarantena.setPreferredSize(new TerminalSize(55, 1));
        Gui.comboBoxQuarantena.addItem("Revoca tutte le quarantene");
        Gui.comboBoxQuarantena.addItem("Quarantena solo rossi");
        Gui.comboBoxQuarantena.addItem("Quarantena 50% popolazione");
        Gui.comboBoxQuarantena.addItem("Quarantena 100% popolazione");
        Gui.comboBoxQuarantena.addItem("Nessuna nuova quarantena");




        Gui.comboBoxTracement.setPreferredSize(new TerminalSize(55, 1));
        Gui.comboBoxTracement.addItem("Nessun tracciamento");
        Gui.comboBoxTracement.addItem("Quarantena incontri giorno precedente di infetto");
        //Appare se TracementResearch = true


        Gui.comboBoxSwab.setPreferredSize(new TerminalSize(55, 1));
        Gui.comboBoxSwab.addItem("Non testare nessuno");
        Gui.comboBoxSwab.addItem("Tampona incontri giorno precedente di infetto");





        myPanel.addComponent(new Label("Popolazione"));
        final TextBox inputPopulation = new TextBox().setValidationPattern(Pattern.compile("[0-9]*")).addTo(myPanel);

        myPanel.addComponent(new Label("Risorse"));
        final TextBox inputCredits = new TextBox().setValidationPattern(Pattern.compile("[0-9]*")).addTo(myPanel);

        myPanel.addComponent(new Label("Infettivita"));
        final TextBox inputInfectivity = new TextBox().setValidationPattern(Pattern.compile("[0-9]*")).addTo(myPanel);

        myPanel.addComponent(new Label("Sintomaticita"));
        final TextBox inputSintomaticity = new TextBox().setValidationPattern(Pattern.compile("[0-9]*")).addTo(myPanel);

        myPanel.addComponent(new Label("Mortalita"));
        final TextBox inputMortality = new TextBox().setValidationPattern(Pattern.compile("[0-9]*")).addTo(myPanel);

        myPanel.addComponent(new Label("Durata"));
        final TextBox inputDuration = new TextBox().setValidationPattern(Pattern.compile("[0-9]*")).addTo(myPanel);

        myPanel.addComponent(new Label("Incontri/Giorno"));
        final TextBox inputDailyMeet = new TextBox().setValidationPattern(Pattern.compile("[0-9]*")).addTo(myPanel);

        myPanel.addComponent(new Label("Giorni Cronologia Incontri"));
        final TextBox inputHistoryMeet = new TextBox().setValidationPattern(Pattern.compile("[0-9]*")).addTo(myPanel);

        myPanel.addComponent(new Label("Costo Tamponi"));
        final TextBox inputTestCost = new TextBox().setValidationPattern(Pattern.compile("[0-9]*")).addTo(myPanel);

        myPanel.addComponent(new Label("Tempo Sviluppo APP di Tracciamento"));
        final TextBox inputTracementStart = new TextBox().setValidationPattern(Pattern.compile("[0-9]*")).addTo(myPanel);

        myPanel.addComponent(new EmptySpace(new TerminalSize(0,0)));

        myPanel.addComponent(new EmptySpace(new TerminalSize(0, 0)));
        new Button("Avvia Simulazione", new Runnable() {
            @Override
            public void run() {
                World myWorld = new World(GuiUtils.buildInput(inputPopulation, inputCredits, inputInfectivity, inputSintomaticity, inputMortality, inputDuration, inputDailyMeet, inputHistoryMeet, inputTestCost));
                Gui.myWorld = myWorld;
                GuiUtils.setupSimulation(myWorld);
                myWorld.currentStrategies.TracementDay = Integer.parseInt(inputTracementStart.getText());
                myWorld.currentStrategies.TracementResearch = true;
                GuiUtils.updateGui(myWorld);
                myPanel.removeAllComponents();
                dataPanel.addComponent(Gui.lblDay);
                dataPanel.addComponent(Gui.lblTracementStart);
                dataPanel.addComponent(Gui.lblCredits);
                dataPanel.addComponent(Gui.lblGreen);
                dataPanel.addComponent(Gui.lblYellow);
                dataPanel.addComponent(Gui.lblOrange);
                dataPanel.addComponent(Gui.lblRed);
                dataPanel.addComponent(Gui.lblBlack);
                dataPanel.addComponent(Gui.lblBlue);
                dataPanel.addComponent(Gui.lblR0);
                dataPanel.addComponent(Gui.lblQuarantined);
                stratPanel.addComponent(Gui.comboBoxQuarantena);
                stratPanel.addComponent(Gui.comboBoxTracement);
                stratPanel.addComponent(Gui.comboBoxSwab);

                myPanel.setLayoutManager(new GridLayout(3));
                myPanel.addComponent(dataPanel.withBorder(Borders.singleLine("Dati")));
                //myPanel.addComponent(stratPanel.withBorder(Borders.singleLine("Strategie")));

                myWindow.setComponent(myPanel.withBorder(Borders.singleLine("Simulazione")));
                //
                // gui.addWindow(myWindow);

                GuiUtils.buildPanel(myWorld);


            }
        }).addTo(myPanel);


        // Create Gui.myWindow to hold the panel
        Gui.myWindow = new BasicWindow();
        Gui.myWindow.setComponent(myPanel);
        myWindow.setHints(Arrays.asList(Window.Hint.CENTERED));


        // Create gui and start gui
        gui = new MultiWindowTextGUI(screen, new DefaultWindowManager(), new EmptySpace(TextColor.ANSI.BLUE));
        if(!createdGui) {
            gui.addWindowAndWait(Gui.myWindow);
            createdGui = true;
        }

        }
    }

    class GuiUtils{
        public static InputData buildInput(TextBox population, TextBox resources, TextBox infectivity, TextBox sintomaticity, TextBox lethality, TextBox duration, TextBox dailyMeetings, TextBox historyMeetings, TextBox testCost){
            InputData outputInputData = new InputData();
            outputInputData.population = Integer.parseInt(population.getText());
            outputInputData.availableCredits = Integer.parseInt(resources.getText());
            outputInputData.infectivity = Integer.parseInt(infectivity.getText());
            outputInputData.sintomaticity = Integer.parseInt(sintomaticity.getText());
            outputInputData.letality = Integer.parseInt(lethality.getText());
            outputInputData.duration = Integer.parseInt(duration.getText());
            outputInputData.dailyMeetings = Integer.parseInt(dailyMeetings.getText());
            outputInputData.historyMeetings = Integer.parseInt(historyMeetings.getText());
            outputInputData.testCost = Integer.parseInt(testCost.getText());
            return outputInputData;
        }
        public static void setupSimulation(World inputWorld){
            inputWorld.population.get(1).infect();
        }
        public static void advanceSimulation(World inputWorld){
            inputWorld.currentStrategies.quarantineStrategy = Gui.comboBoxQuarantena.getSelectedIndex();
            inputWorld.currentStrategies.tracementStrategy = Gui.comboBoxTracement.getSelectedIndex();
            inputWorld.currentStrategies.swabStrategy = Gui.comboBoxSwab.getSelectedIndex();
            if(inputWorld.currentStrategies.tracementStrategyComplete && !Gui.triggerNewStrat){
                Gui.comboBoxSwab.addItem("Tampona tutti incontri salvati di infetto");
                Gui.comboBoxTracement.addItem("Quarantena tutti incontri salvati di infetto");
                Gui.triggerNewStrat = true;
                if(Gui.addedStrat){
                    MessageDialog.showMessageDialog(Gui.textGUI, "App di tracciamento completata", "Le strategie avanzate sono ora disponibili.");
                }
            }

            inputWorld.nextDay();
            inputWorld.Meetings();

            if(inputWorld.availableCredits <= 0){
                MessageDialog.showMessageDialog(Gui.textGUI, "Simulazione terminata", "Le risorse sono esaurite.");
                new Ending(inputWorld.startingPopulation,inputWorld.availableCredits,inputWorld.green,inputWorld.yellow+inputWorld.orange,inputWorld.blue,inputWorld.red,inputWorld.black,inputWorld.r0,inputWorld.day);
                Gui.myWindow.close();
            }

            else if(inputWorld.infectionEnded){
                MessageDialog.showMessageDialog(Gui.textGUI, "Simulazione terminata", "La malattia é stata debellata.");
                new Ending(inputWorld.startingPopulation,inputWorld.availableCredits,inputWorld.green,inputWorld.yellow+inputWorld.orange,inputWorld.blue,inputWorld.red,inputWorld.black,inputWorld.r0,inputWorld.day);
                Gui.myWindow.close();
            }
        }

        public static void buildPanel(World inputWorld){
            new Button("+1 Giorno", new Runnable() {
                @Override
                public void run() {
                    advanceSimulation(inputWorld);
                    updateGui(inputWorld);

                }
            }).addTo(Gui.myPanel);
            new Button("+5 Giorni", new Runnable() {
                @Override
                public void run() {
                    for(int i=0;i<5;i++) {
                        advanceSimulation(inputWorld);
                    }
                    updateGui(inputWorld);
                }
            }).addTo(Gui.myPanel);


        }

        public static void updateGui(World inputWorld){
            Gui.lblDay.setText("Giorno:" + Integer.toString(inputWorld.day));
            Gui.lblCredits.setText("Crediti:" + Integer.toString(inputWorld.availableCredits));
            Gui.lblGreen.setText("Sani:" + Integer.toString(inputWorld.green));
            Gui.lblYellow.setText("Asintomatici:" + Integer.toString(inputWorld.yellow));
            Gui.lblOrange.setText("Asintomatici individuati:" + Integer.toString(inputWorld.orange));
            Gui.lblRed.setText("Rossi:" + Integer.toString(inputWorld.red));
            Gui.lblBlack.setText("Morti:" + Integer.toString(inputWorld.black));
            Gui.lblBlue.setText("Guariti:" + Integer.toString(inputWorld.blue));
            Gui.lblR0.setText("R0:" + Float.toString(inputWorld.r0));
            Gui.lblQuarantined.setText("Quarantenati: " + Integer.toString(inputWorld.quarantinedPeople));
            if(inputWorld.red > 0 && !Gui.addedStrat)
            {Gui.myPanel.addComponent(Gui.stratPanel.withBorder(Borders.singleLine("Strategie")));
                Gui.addedStrat = true;
                MessageDialog.showMessageDialog(Gui.textGUI, "Primo infetto sintomatico", "Le strategie sono ora disponibili.");
            }
            if(inputWorld.currentStrategies.TracementDay <= -1 && !Gui.isTracementComplete ){
                Gui.lblTracementStart = new Label("Giorno inizio tracciamento: ?");
                Gui.isTracementComplete = true;
            }
            else if(inputWorld.currentStrategies.TracementDay > -1 ){ //Gui.isTracementComplete
                Gui.lblTracementStart = new Label("Giorno inizio tracciamento: " + Integer.toString(inputWorld.currentStrategies.TracementDay));
            }



        }



    }
