import org.jfree.chart.ChartColor;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PiePlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;

import javax.swing.*;
import java.awt.*;
import java.awt.Dimension;

public class Ending extends JFrame{

    public Ending(int popolazione, int risorse, int verdi, int gialli, int blu, int rossi, int neri, double valore_r0, int giorno) {
        JFrame f=new JFrame("Resoconto");
        JPanel EP = new JPanel();
        JPanel CP = new JPanel();

        JPanel P1 = new JPanel();
        JPanel P2 = new JPanel();
        JPanel P3 = new JPanel();
        JPanel P4 = new JPanel();
        JPanel P5 = new JPanel();
        JPanel P6 = new JPanel();
        JPanel P7 = new JPanel();
        JPanel P8 = new JPanel();
        JPanel P9 = new JPanel();

        JLabel Scre = new JLabel("CREDITI:");
        JLabel Spop = new JLabel("POPOLAZIONE:");
        JLabel Ssan = new JLabel("SANI:");
        JLabel Sasi = new JLabel("ASINTOMATICI:");
        JLabel Ssin = new JLabel("SINTOMATICI:");
        JLabel Sgua = new JLabel("GUARITI:");
        JLabel Smor = new JLabel("MORTI:");
        JLabel Sr0 = new JLabel("R0:");
        JLabel Sgio = new JLabel("GIORNO:");


        ////////////////////////////////////////////////////////////////////

        P1.add(Scre);
        P1.setBackground(Color.decode("#f2e5bc"));
        P2.add(Spop);
        P2.setBackground(Color.decode("#fbf1c7"));
        P3.add(Ssan);
        P3.setBackground(Color.decode("#f2e5bc"));
        P4.add(Sasi);
        P4.setBackground(Color.decode("#fbf1c7"));
        P5.add(Ssin);
        P5.setBackground(Color.decode("#f2e5bc"));
        P6.add(Sgua);
        P6.setBackground(Color.decode("#fbf1c7"));
        P7.add(Smor);
        P7.setBackground(Color.decode("#f2e5bc"));
        P8.add(Sr0);
        P8.setBackground(Color.decode("#fbf1c7"));
        P9.add(Sgio);
        P9.setBackground(Color.decode("#f2e5bc"));


        EP.setLayout(new GridLayout(9,1));
        EP.add(P1);
        EP.add(P2);
        EP.add(P3);
        EP.add(P4);
        EP.add(P5);
        EP.add(P6);
        EP.add(P7);
        EP.add(P8);
        EP.add(P9);

        Scre.setText("CREDITI: "+risorse);
        Spop.setText("POPOLAZIONE: "+popolazione);
        Ssan.setText("SANI: "+verdi);
        Sasi.setText("ASINTOMATICI: "+gialli);
        Ssin.setText("SINTOMATICI: "+rossi);
        Sgua.setText("GUARITI: "+blu);
        Smor.setText("MORTI: "+neri);
        Sr0.setText("R0: "+valore_r0);
        Sgio.setText("GIORNO: "+giorno);


        ///////////////////////////////////////////

        DefaultPieDataset dataset = new DefaultPieDataset();

        dataset.setValue("SINTOMATICI",rossi);
        dataset.setValue("GUARITI",blu);
        dataset.setValue("SANI",verdi);
        dataset.setValue("ASINTOMATICI",gialli);
        dataset.setValue("MORTI",neri);

        JFreeChart chart = ChartFactory.createPieChart("Popolazione", dataset, true, true, false);
        chart.setBackgroundPaint(Color.decode("#f9f5d7"));
        PiePlot plot = (PiePlot) chart.getPlot();
        plot.setInteriorGap(0.0);
        plot.setLabelGenerator(null);
        plot.setBackgroundPaint(Color.decode("#f9f5d7"));
        ChartPanel chartPanel = new ChartPanel(chart);


        CP.setLayout(new GridLayout(1,1));
        CP.add(chartPanel);

        //////////////////////////////////////////////

        EP.setBackground(Color.decode("#f2e5bc"));
        CP.setBackground(Color.decode("#f9f5d7"));

        f.add(EP,BorderLayout.EAST);
        f.add(CP,BorderLayout.CENTER);

        //////////////////////////////////////////////

        f.setSize(850,650);
        f.setVisible(true);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        f.setLocation((int) ((dim.getWidth()-f.getWidth())/2),(int) ((dim.getHeight()-f.getHeight())/2));
    }


}
