package ru.package01;

import java.awt.*;
import javax.swing.*;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

import static ru.package01.MeridionalCalculation.COORDINATES;

public class Window01 {

    private final static double PRECISION = 0.00000000000001;
    private final static double ALPHA_1 = 0.28316210710108; //experimental coefficients
    private final static double ALPHA_2 = -0.239175686607085;//experimental coefficients
    private JFrame frame;
    private JTextField textField_6;
    private MeridionalCanvas meridionalCanvas;
    private AreaChartCanvas areaChartCanvas;
    private JTable table;
    private JScrollPane scrollPane;

    public static Double zoomOfMeridional = 1.0;
    public static Double zoomOfAreaChartX = 1.0;
    public static Double zoomOfAreaChartY = 1.0;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Window01 window = new Window01();
                    window.frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the application.
     */
    public Window01() {
        initialize();
    }

    /**
     * Initialize the contents of the frame.
     */
    public void initialize() {
        int x_element = 5;
        int y_element = 40;
        frame = new JFrame();
        frame.setForeground(Color.GRAY);
        frame.setTitle("Calculation of centrifugal wheel");
        frame.setBounds(100, 100, 2000, 1000);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        meridionalCanvas = new MeridionalCanvas();
        areaChartCanvas = new AreaChartCanvas();

        frame.getContentPane().setLayout(null);

        Font headerFont = new Font("Nyala", Font.PLAIN, 22);
        List<JLabel> listOfHeaders = new ArrayList<>();
        listOfHeaders.add(new JLabel("Main dimensions and parameters"));
        listOfHeaders.add(new JLabel("Meridional cross section"));
        listOfHeaders.add(new JLabel("Plot of meridional areas"));
        listOfHeaders.get(0).setBounds(x_element + 200, y_element - 30, 350, 20);
        listOfHeaders.get(1).setBounds(1185, y_element - 30, 250, 20);
        listOfHeaders.get(2).setBounds(1455, y_element - 30, 300, 20);
        for (int i = 0; i < listOfHeaders.size(); i++) {
            listOfHeaders.get(i).setFont(headerFont);
            frame.getContentPane().add(listOfHeaders.get(i));
        }

        ////////////NAMES OF LABELS/////////////
        Font labelFont = new Font("Nyala", Font.PLAIN, 18);
        List<JLabel> listOfLabels1 = new ArrayList<>();
        listOfLabels1.add(new JLabel("Pump flow rate, m3/hr"));
        listOfLabels1.add(new JLabel("Pump head, m"));
        listOfLabels1.add(new JLabel("Rotation speed, rpm"));
        listOfLabels1.add(new JLabel("Net positive suction head, m"));
        listOfLabels1.add(new JLabel("Fluid density, kg/m3"));
        listOfLabels1.add(new JLabel("Approximate pump efficiency"));
        listOfLabels1.add(new JLabel("Specific speed"));
        listOfLabels1.add(new JLabel("Suction-specific speed"));
        listOfLabels1.add(new JLabel("Impeller diameter, mm"));
        listOfLabels1.add(new JLabel("Outlet width, mm"));
        listOfLabels1.add(new JLabel("Blade thickness, mm"));
        listOfLabels1.add(new JLabel("Number of blades"));
        listOfLabels1.add(new JLabel("Outlet blade angle, deg"));
        listOfLabels1.add(new JLabel("Hub diameter, mm"));
        listOfLabels1.add(new JLabel("Suction diameter, mm"));
        listOfLabels1.add(new JLabel("Hydraulic efficiency"));
        listOfLabels1.add(new JLabel("Theoretical head, m"));
        listOfLabels1.add(new JLabel("Pump head, m"));
        listOfLabels1.add(new JLabel("Static inlet pressure, MPa"));
        listOfLabels1.add(new JLabel("Summary Axial Force, N"));

        for (int i = 0; i < listOfLabels1.size(); i++) {
            listOfLabels1.get(i).setFont(labelFont);
            listOfLabels1.get(i).setBounds(x_element, y_element + i * 25, 200, 20);
            frame.getContentPane().add(listOfLabels1.get(i));
        }

        Font textFieldFont = new Font("Courier New", Font.BOLD, 14);
        List<JTextField> listOfValues1 = new ArrayList<>();
        for (int i = 0; i < listOfLabels1.size(); i++) {
            listOfValues1.add(new JTextField());
            listOfValues1.get(i).setFont(textFieldFont);
            listOfValues1.get(i).setBounds(x_element + 200, y_element + i * 25, 55, 20);
            if ((i == 0) || (i == 16) || (i == 17) || (i == 19)) {
                listOfValues1.get(i).setBounds(x_element + 200, y_element + i * 25, 165, 20);
            }
            if ((i == 6) || (i == 7)) {
                listOfValues1.get(i).setBounds(x_element + 200, y_element + i * 25, 75, 20);
            }
            listOfValues1.get(i).addKeyListener(new KeyAdapter() {  //Checking Whether the INPUT IS CORRECT
                public void keyTyped(KeyEvent e) {
                    InputNumberRules.inputNumberRules(e);
                }
            });
            frame.getContentPane().add(listOfValues1.get(i));
        }
        listOfValues1.get(0).setText("72.76");
        listOfValues1.get(1).setText("78");
        listOfValues1.get(2).setText("4100");
        listOfValues1.get(3).setText("5.5");
        listOfValues1.get(4).setText("998.2");
        listOfValues1.get(5).setText("0.785");
        listOfValues1.get(6).setText("-----");
        listOfValues1.get(6).setEditable(false);
        listOfValues1.get(7).setText("-----");
        listOfValues1.get(7).setEditable(false);
        listOfValues1.get(8).setText("189");
        listOfValues1.get(9).setText("9.5");
        listOfValues1.get(10).setText("3");
        listOfValues1.get(11).setText("6");
        listOfValues1.get(12).setText("25");
        listOfValues1.get(13).setText("5");
        listOfValues1.get(14).setText("90");
        listOfValues1.get(15).setText("0.785");
        listOfValues1.get(16).setText("-----");
        listOfValues1.get(16).setEditable(false);
        listOfValues1.get(17).setText("-----");
        listOfValues1.get(17).setEditable(false);
        listOfValues1.get(18).setText("0.1");
        listOfValues1.get(19).setText("-----");
        listOfValues1.get(19).setEditable(false);


        List<JLabel> listOfLabels2 = new ArrayList<>();
        listOfLabels2.add(new JLabel("Wheel's flow rate, m3/hr"));
        listOfLabels2.add(new JLabel("Value of gap seal, mm"));
        listOfLabels2.add(new JLabel("Length of gap seal, mm"));
        listOfLabels2.add(new JLabel("Coefficient of Swirling flow"));
        listOfLabels2.add(new JLabel("Viscosity, mm2/s"));
        listOfLabels2.add(new JLabel("Approximate wheel efficiency"));
        listOfLabels2.add(new JLabel("Leakage flow, m3/hr"));
        listOfLabels2.add(new JLabel("Q_summ, mm"));
        listOfLabels2.add(new JLabel("Volume Efficiency"));
        listOfLabels2.add(new JLabel("H_stat, m"));
        listOfLabels2.add(new JLabel("Hub diameter after wheel, mm"));
        listOfLabels2.add(new JLabel("Seal's diameter, mm"));
        listOfLabels2.add(new JLabel("Output flow velosity (Abs), m/s"));
        listOfLabels2.add(new JLabel("Output angle of flow, deg"));
        listOfLabels2.add(new JLabel("Position's diameter of discharge holes, mm"));
        listOfLabels2.add(new JLabel("Diameter of discharge holes, mm"));
        listOfLabels2.add(new JLabel("Length of discharge holes, mm"));
        listOfLabels2.add(new JLabel("Number of discharge holes"));

        for (int i = 0; i < listOfLabels2.size(); i++) {
            listOfLabels2.get(i).setFont(labelFont);
            listOfLabels2.get(i).setBounds(x_element + 400, y_element + i * 25, 215, 20);
            frame.getContentPane().add(listOfLabels2.get(i));
        }

        List<JTextField> listOfValues2 = new ArrayList<>();
        for (int i = 0; i < listOfLabels2.size(); i++) {
            listOfValues2.add(new JTextField());
            listOfValues2.get(i).setFont(textFieldFont);
            if ((i < 6) || (i == 10) || (i == 11) || (i > 13)) {
                listOfValues2.get(i).setBounds(x_element + 615, y_element + i * 25, 55, 20);
            } else {
                listOfValues2.get(i).setBounds(x_element + 615, y_element + i * 25, 165, 20);
                listOfValues2.get(i).setEditable(false);
            }
            listOfValues2.get(i).addKeyListener(new KeyAdapter() {  //Checking Whether the INPUT IS CORRECT
                public void keyTyped(KeyEvent e) {
                    InputNumberRules.inputNumberRules(e);
                }
            });
            frame.getContentPane().add(listOfValues2.get(i));
        }
        listOfValues2.get(0).setText("67");
        listOfValues2.get(1).setText("0.25");
        listOfValues2.get(2).setText("20");
        listOfValues2.get(3).setText("0.5");
        listOfValues2.get(4).setText("1");
        listOfValues2.get(5).setText("0.91");
        listOfValues2.get(6).setText("-----");
        listOfValues2.get(7).setText("-----");
        listOfValues2.get(8).setText("-----");
        listOfValues2.get(9).setText("-----");
        listOfValues2.get(10).setText("75");
        listOfValues2.get(11).setText("102");
        listOfValues2.get(12).setText("-----");
        listOfValues2.get(13).setText("-----");
        listOfValues2.get(14).setText("85");
        listOfValues2.get(15).setText("5");
        listOfValues2.get(16).setText("10");
        listOfValues2.get(17).setText("6");

        List<JTextField> listOfValues3 = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            listOfValues3.add(new JTextField());
            listOfValues3.get(i).setEnabled(false);
            listOfValues3.get(i).setVisible(false);
            listOfValues3.get(i).setFont(textFieldFont);
            if ((i < 3)) {
                listOfValues3.get(i).setBounds(x_element + 785, y_element + 25 + i * 25, 55, 20);
            }
            if ((i == 3)) {
                listOfValues3.get(i).setBounds(x_element + 785, y_element + 75 + i * 25, 165, 20);
                listOfValues3.get(i).setEditable(false);
            }
            if (i > 3) {
                listOfValues3.get(i).setBounds(x_element + 785, y_element + 175 + i * 25, 55, 20);
            }
            listOfValues3.get(i).addKeyListener(new KeyAdapter() {  //Checking Whether the INPUT IS CORRECT
                public void keyTyped(KeyEvent e) {
                    InputNumberRules.inputNumberRules(e);
                }
            });
            frame.getContentPane().add(listOfValues3.get(i));
        }
        listOfValues3.get(0).setText(String.valueOf(PRECISION));
        listOfValues3.get(1).setText("20");
        listOfValues3.get(2).setText("0.5");
        listOfValues3.get(3).setText("-----");
        listOfValues3.get(4).setText("102");

        List<JTextField> listOfValues4 = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            listOfValues4.add(new JTextField());
            listOfValues4.get(i).setEnabled(false);
            listOfValues4.get(i).setVisible(false);
            listOfValues4.get(i).setFont(textFieldFont);
            if ((i < 3)) {
                listOfValues4.get(i).setBounds(x_element + 955, y_element + 25 + i * 25, 55, 20);
            }
            if ((i == 3)) {
                listOfValues4.get(i).setBounds(x_element + 955, y_element + 75 + i * 25, 165, 20);
                listOfValues4.get(i).setEditable(false);
            }
            if (i > 3) {
                listOfValues4.get(i).setBounds(x_element + 955, y_element + 175 + i * 25, 55, 20);
            }
            listOfValues4.get(i).addKeyListener(new KeyAdapter() {  //Checking Whether the INPUT IS CORRECT
                public void keyTyped(KeyEvent e) {
                    InputNumberRules.inputNumberRules(e);
                }
            });
            frame.getContentPane().add(listOfValues4.get(i));
        }
        listOfValues4.get(0).setText(String.valueOf(PRECISION));
        listOfValues4.get(1).setText("20");
        listOfValues4.get(2).setText("0.5");
        listOfValues4.get(3).setText("-----");
        listOfValues4.get(4).setText("75");

        //----------------------------------------------------------
        JLabel lblNewLabel_8 = new JLabel("Safety factor");
        lblNewLabel_8.setFont(labelFont);
        lblNewLabel_8.setBounds(x_element + 260, y_element + 75, 85, 20);
        frame.getContentPane().add(lblNewLabel_8);

        JLabel lblNewLabel_49 = new JLabel("K_0 =");
        lblNewLabel_49.setFont(labelFont);
        lblNewLabel_49.setBounds(x_element + 260, y_element + 350, 45, 20);
        frame.getContentPane().add(lblNewLabel_49);

        JLabel lblNewLabel_46 = new JLabel("-----");                            //for help : D_2
        lblNewLabel_46.setEnabled(false);
        lblNewLabel_46.setVisible(false);
        lblNewLabel_46.setFont(textFieldFont);
        lblNewLabel_46.setBounds(x_element + 280, y_element + 200, 185, 20);
        frame.getContentPane().add(lblNewLabel_46);

        JLabel lblNewLabel_47 = new JLabel("-----");                            //for help : b_2
        lblNewLabel_47.setEnabled(false);
        lblNewLabel_47.setVisible(false);
        lblNewLabel_47.setFont(textFieldFont);
        lblNewLabel_47.setBounds(x_element + 280, y_element + 225, 185, 20);
        frame.getContentPane().add(lblNewLabel_47);

        JLabel lblNewLabel_48 = new JLabel("");                                    //for K_0
        lblNewLabel_48.setFont(textFieldFont);
        lblNewLabel_48.setBounds(x_element + 305, y_element + 350, 85, 20);
        frame.getContentPane().add(lblNewLabel_48);

        JCheckBox checkBoxSecondSeal = new JCheckBox("Presence of the second seal");
        checkBoxSecondSeal.setFont(labelFont);
        checkBoxSecondSeal.setBounds(x_element + 680, y_element, 220, 20);
        checkBoxSecondSeal.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                if (checkBoxSecondSeal.isSelected()) {
                    listOfValues3.get(0).setText("0.25");
                    for (int i = 0; i < listOfValues3.size(); i++) {
                        listOfValues3.get(i).setEnabled(true);
                        listOfValues3.get(i).setVisible(true);
                    }
                } else {
                    listOfValues3.get(0).setText(String.valueOf(PRECISION));
                    for (int i = 0; i < listOfValues3.size(); i++) {
                        listOfValues3.get(i).setEnabled(false);
                        listOfValues3.get(i).setVisible(false);
                    }
                }
            }
        });
        frame.getContentPane().add(checkBoxSecondSeal);

        JCheckBox checkBoxNextStage = new JCheckBox("Presence of the next stage");
        checkBoxNextStage.setEnabled(true);
        checkBoxNextStage.setFont(labelFont);
        checkBoxNextStage.setBounds(x_element + 915, y_element, 220, 20);
        checkBoxNextStage.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                if (checkBoxNextStage.isSelected()) {
                    listOfValues4.get(0).setText("0.25");
                    for (int i = 0; i < listOfValues4.size(); i++) {
                        listOfValues4.get(i).setEnabled(true);
                        listOfValues4.get(i).setVisible(true);
                    }
                } else {
                    listOfValues4.get(0).setText(String.valueOf(PRECISION));
                    for (int i = 0; i < listOfValues4.size(); i++) {
                        listOfValues4.get(i).setEnabled(false);
                        listOfValues4.get(i).setVisible(false);
                    }
                }
            }
        });
        frame.getContentPane().add(checkBoxNextStage);

        JCheckBox checkBoxHelp = new JCheckBox("Help");

        checkBoxHelp.setFont(labelFont);
        checkBoxHelp.setBounds(x_element + 280, y_element + 175, 60, 20);
        checkBoxHelp.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                if (checkBoxHelp.isSelected()) {
                    checkBoxHelp.setToolTipText("Switch twice to refresh");
                    lblNewLabel_46.setEnabled(true);
                    lblNewLabel_46.setVisible(true);
                    lblNewLabel_47.setEnabled(true);
                    lblNewLabel_47.setVisible(true);
                    double Q = Double.parseDouble(listOfValues1.get(0).getText()) / 3600.0;  //Pump flow rate, m3/hr-->
                    double H = Double.parseDouble(listOfValues1.get(1).getText());           //Pump head, m
                    double n = Double.parseDouble(listOfValues1.get(2).getText());           //Rotation speed, rpm
                    double n_s = 3.65 * n * Math.sqrt(Q) / Math.pow(H, 0.75);               //Specific speed

                    double D_head = Math.sqrt(2.0 * 9.81 * H) / n;
                    double D_2_head = 18.75 * D_head * Math.pow((n_s / 100.0), (1.0 / 6.0));
                    double b_2_head = 1.3 * D_head * Math.pow((n_s / 100.0), (1.0 / 6.0));

                    double D_flow = Math.pow((Q / n), (1.0 / 3.0));
                    double D_2_flow = 9.35 * D_flow / (Math.sqrt(n_s / 100.0));
                    double b_2_flow = 6.8 * 0.01 * D_2_flow * Math.pow((n_s / 100.0), (4.0 / 3.0));

                    double D_frequency = Math.sqrt(Q / (Math.sqrt(2.0 * 9.81 * H)));
                    double D_2_frequency = 6.536 * D_frequency / Math.pow((n_s / 100.0), (5.0 / 6.0));
                    double b_2_frequency = 0.443 * D_frequency * Math.sqrt(n_s / 100.0);

                    double D_2_average = 1000.0 * (D_2_head + D_2_flow + D_2_frequency) / 3.0;                // m -->>mm
                    double b_2_average = 1000.0 * (b_2_head + b_2_flow + b_2_frequency) / 3.0;                // m -->>mm

                    double D_2_average_1 = new BigDecimal(D_2_average).setScale(3, RoundingMode.UP).doubleValue();  //rounding of  D_2_average
                    double b_2_average_1 = new BigDecimal(b_2_average).setScale(3, RoundingMode.UP).doubleValue();  //rounding of  D_2_average

                    lblNewLabel_46.setText(String.valueOf(D_2_average_1));
                    lblNewLabel_47.setText(String.valueOf(b_2_average_1));

                } else {
                    checkBoxHelp.setToolTipText("Switch to calculate");
                    lblNewLabel_46.setEnabled(false);
                    lblNewLabel_46.setVisible(false);
                    lblNewLabel_47.setEnabled(false);
                    lblNewLabel_47.setVisible(false);
                }
            }
        });
        frame.getContentPane().add(checkBoxHelp);

        JButton btnNewButton = new JButton("Calculation");
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                final double g = 9.81;

                double Qx = Double.parseDouble(listOfValues1.get(0).getText()) / 3600.0;     //Pump flow rate, m3/hr-->m3/SEC

                double H = Double.parseDouble(listOfValues1.get(1).getText());               //Pump head, m
                double n = Double.parseDouble(listOfValues1.get(2).getText());               //Rotation speed, rpm
                double NPSH = Double.parseDouble(listOfValues1.get(3).getText());            //Net positive suction head, m
                double Density = Double.parseDouble(listOfValues1.get(4).getText());         //Density, kg/m3
                final double Eff_Hyd = Double.parseDouble(listOfValues1.get(5).getText());   //Hydraulic efficiency
                double S_F = Double.parseDouble(textField_6.getText());                     //Safety factor
                double n_s = (3.65 * n * Math.sqrt(Qx)) / Math.pow(H, 0.75);                //Specific speed
                double SSS = (n * Math.sqrt(Qx)) / Math.pow((0.1 * NPSH / S_F), 0.75);      //Suction-specific speed

                double D_2 = Double.parseDouble(listOfValues1.get(8).getText()) / 1000.0;                //Impeller diameter, mm --> m
                double b_2 = Double.parseDouble(listOfValues1.get(9).getText()) / 1000.0;                //Outlet width, mm  --> m
                double sigma_2 = Double.parseDouble(listOfValues1.get(10).getText()) / 1000.0;           //Blade thickness, mm --> m
                double z_blades = Double.parseDouble(listOfValues1.get(11).getText());                   //Number of blades
                double betta_2 = Math.toRadians(Double.parseDouble(listOfValues1.get(12).getText()));    //Outlet blade angle, deg --> radians
                double d_hub = Double.parseDouble(listOfValues1.get(13).getText()) / 1000.0;             //Hub diameter, mm --> m
                double d_suc = Double.parseDouble(listOfValues1.get(14).getText()) / 1000.0;             //Suction diameter, mm --> m

                double fi_1 = Double.parseDouble(listOfValues2.get(3).getText());                   //Coefficient of swirling flow 1
                double fi_2 = Double.parseDouble(listOfValues3.get(2).getText());                   //Coefficient of swirling flow 2
                double P_st_in = Double.parseDouble(listOfValues1.get(18).getText()) * 1000000.0;    //Pressure static INLET, MPa --> Pa

                double y = 1.0 - (Math.sin(betta_2) / (z_blades * Math.sqrt(3.0) / (2.0 * Math.PI) + Math.sin(betta_2)));
                double ksi_2 = 1.0 - z_blades * sigma_2 / (Math.PI * D_2 * Math.sin(betta_2));
                double U_2 = Math.PI * n * D_2 / 60.0;

                double Q_nom = Double.parseDouble(listOfValues2.get(0).getText()) / 3600.0;     //Seal's diameter, m3/hr --> m3/s
                double d_seal_1 = Double.parseDouble(listOfValues2.get(11).getText()) / 1000.0; //Seal's diameter 1, mm --> m
                double d_seal_2 = Double.parseDouble(listOfValues3.get(4).getText()) / 1000.0;  //Seal's diameter 2, mm --> m
                double d_seal_3 = Double.parseDouble(listOfValues4.get(4).getText()) / 1000.0;  //Seal's diameter after stage, mm
                double delta_1 = Double.parseDouble(listOfValues2.get(1).getText()) / 1000.0;   //Value of gap seal 1, mm --> m
                double delta_2 = Double.parseDouble(listOfValues3.get(0).getText()) / 1000.0;   //Value of gap seal 2, mm --> m
                double delta_3 = Double.parseDouble(listOfValues4.get(0).getText()) / 1000.0;   //Value of gap seal 3, mm --> m
                double nyu = Double.parseDouble(listOfValues2.get(4).getText()) / 1000000.0;    //Viscosity, mm2/s --> m2/s
                double l_gap1 = Double.parseDouble(listOfValues2.get(2).getText()) / 1000.0;    //Length of gap seal 1, mm --> m
                double l_gap2 = Double.parseDouble(listOfValues3.get(1).getText()) / 1000.0;    //Length of gap seal 2, mm --> m
                double l_gap3 = Double.parseDouble(listOfValues4.get(1).getText()) / 1000.0;    //Length of gap seal 3, mm --> m
                double Eff_wh = Double.parseDouble(listOfValues2.get(5).getText());             //Approximate wheel efficiency
                double D_otv = Double.parseDouble(listOfValues2.get(14).getText()) / 1000.0;    //diameter of discharge hole arrangement, mm
                double d_otv = Double.parseDouble(listOfValues2.get(15).getText()) / 1000.0;    //diameter of discharge hole, mm
                double l_otv = Double.parseDouble(listOfValues2.get(16).getText()) / 1000.0;    //Length of discharge hole, mm
                double z_otv = Double.parseDouble(listOfValues2.get(17).getText());             //Number of discharge holes

                if ((z_blades - 0.5) == Math.round(z_blades - 0.5) || (z_blades == Math.round(z_blades))) {
                    listOfValues1.get(11).setBackground(Color.WHITE);
                } else {
                    listOfValues1.get(11).setBackground(Color.RED);
                    listOfValues1.get(11).setToolTipText("The number of blades should be integer or ends with 0.5");
                }
                if (((z_blades - 0.5) == Math.round(z_blades - 0.5) && ((z_blades - 1.5) != z_otv))
                        || ((z_blades == Math.round(z_blades)) && (z_blades != z_otv))) {
                    listOfValues2.get(17).setBackground(Color.RED);
                    listOfValues2.get(17).setToolTipText("The number of holes should be equal to number of blades or the difference between them should be equal to 1.5");
                } else {
                    listOfValues2.get(17).setBackground(Color.WHITE);
                }

                double r_u1 = 0.5 * d_seal_1;
                double r_u1_avg = r_u1 + delta_1 / 2.0;
                double omega = Math.PI * n / 30.0;
                double del_H_omega_1 = Math.pow((0.5 * omega), 2.0) * (Math.pow((0.5 * D_2), 2.0) - Math.pow(r_u1_avg, 2.0)) / (2.0 * g);

                double r_u2 = 0.5 * d_seal_2;
                double r_u2_avg = r_u2 + delta_2 / 2.0;
                double del_H_omega_2 = Math.pow((0.5 * omega), 2.0) * (Math.pow((0.5 * D_2), 2.0) - Math.pow(r_u2_avg, 2.0)) / (2.0 * g);

                double r_u3 = 0.5 * d_seal_3;
                double r_u3_avg = r_u3 + delta_3 / 2.0;
                double del_H_omega_3 = Math.pow((0.5 * omega), 2.0) * (Math.pow(r_u2_avg, 2.0) - Math.pow(0.5 * D_otv, 2.0)) / (2.0 * g);
                double del_H_omega_4 = Math.pow((0.5 * omega), 2.0) * (Math.pow((0.5 * D_otv), 2.0) - Math.pow(0.5 * d_seal_3, 2.0)) / (2.0 * g);

                double dzettaInlet_0 = 1.0, dzettaInlet_1 = 0.3, dzettaInlet_2 = 0.3, dzettaInlet_3 = 0.3;
                double dzettaOutlet_0 = 1.0, dzettaOutlet_1 = 1.0, dzettaOutlet_2 = 1.0, dzettaOutlet_3 = 1.0;
                double Eff_Hyd_wheel = Double.parseDouble(listOfValues2.get(5).getText());
                double x1 = 0.0, x1_i = 1.0;
                double x2 = 0.0, x2_i = 1.0;
                double x3 = 0.0, x3_i = 1.0;

                double V_2m = 0.0;
                double del_H_otv = 0.0;
                double del_H_leak2 = 0.0;
                double del_H_leak3 = 0.0;
                double H_dyn1 = 0.0;
                double H_t1 = 0.0;

                while ((Math.abs(x1 - x1_i) > PRECISION) &&
                        (Math.abs(x2 - x2_i) > PRECISION) &&
                        (Math.abs(x3 - x3_i) > PRECISION)) {
                    x1 = x1_i;
                    x2 = x2_i;
                    x3 = x3_i;
                    double Q_summary = (Q_nom + x1 + x2 + x3);
                    double helpValue1 = Math.PI * D_2 * b_2 * ksi_2 * Math.tan(betta_2);
                    double helpValue2 = (Q_summary / Math.pow((Math.PI * D_2 * b_2 * ksi_2), 2.0) - (U_2 - Q_summary / helpValue1) / helpValue1) / g;

                    double v1 = x1 / (2.0 * Math.PI * r_u1_avg * delta_1);
                    double v_u1 = omega * r_u1_avg, v_u2 = omega * r_u2_avg, v_u3 = omega * r_u3_avg;
                    V_2m = Q_summary / (Math.PI * D_2 * b_2 * ksi_2);
                    H_t1 = (omega / g) * (Math.pow((0.5 * D_2), 2.0) * y * omega - Q_summary / (2.0 * Math.PI * b_2 * ksi_2 * Math.tan(betta_2)));
                    H_dyn1 = (Math.pow(V_2m, 2.0) + Math.pow((U_2 - V_2m / Math.tan(betta_2)), 2.0)) / (2.0 * g);
                    double FF1 = Math.pow(v1, 2.0) / (2.0 * g) *
                            (dzettaInlet_1 + 2.0 * l_gap1 / (Math.pow((2.0 * v1 * delta_1 / nyu), 0.45) * 2.0 * delta_1) + dzettaOutlet_1) *
                            (1.0 + 0.125 * v_u1 * 2.0 * Math.PI * r_u1_avg * delta_1 / (x1)) -
                            (H_t1 * Eff_Hyd_wheel - H_dyn1 - del_H_omega_1);

                    double v2 = x2 / (2.0 * Math.PI * r_u2_avg * delta_2);
                    double v3 = x3 / (2.0 * Math.PI * r_u3_avg * delta_3);

                    del_H_otv = Math.pow((4.0 * (x2 + x3) / (z_otv * Math.PI * d_otv * d_otv)), 2.0) / (2.0 * g) *
                            (dzettaInlet_0 + ALPHA_1 * Math.pow((x2 + x3) / (Math.PI * d_otv * nyu), ALPHA_2) * l_otv / d_otv + dzettaOutlet_0);
                    del_H_leak2 = Math.pow(v2, 2.0) / (2.0 * g) *
                            (dzettaInlet_2 + 2.0 * l_gap2 / (Math.pow((2.0 * v2 * delta_2 / nyu), 0.45) * 2.0 * delta_2) + dzettaOutlet_2) *
                            (1.0 + 0.125 * v_u2 * 2.0 * Math.PI * r_u2_avg * delta_2 / (x2));
                    del_H_leak3 = Math.pow(v3, 2.0) / (2.0 * g) *
                            (dzettaInlet_3 + 2.0 * l_gap3 / (Math.pow((2.0 * v3 * delta_3 / nyu), 0.45) * 2.0 * delta_3) + dzettaOutlet_3) *
                            (1.0 + 0.125 * v_u3 * 2.0 * Math.PI * r_u3_avg * delta_3 / (x3));

                    double FF2 = del_H_leak2 - (H_t1 * Eff_Hyd_wheel - H_dyn1 - del_H_omega_2 - del_H_omega_3 - del_H_otv);
                    double FF3 = del_H_leak3 - (H_t1 * Eff_Hyd + del_H_omega_4 - del_H_otv);

                    double dif11 = helpValue2 - 0.00994718394324345848561 * v_u1 * (dzettaInlet_1 + l_gap1 / (delta_1 * Math.pow((0.31830988618379067154 * x1 / (nyu * r_u1_avg)), 0.45)) + dzettaOutlet_1)
                            / (g * delta_1 * r_u1_avg) +
                            x1 * (0.78539816339744830962 * v_u1 * delta_1 * r_u1_avg / x1 + 1.0) * (dzettaInlet_1 + l_gap1 / (delta_1 * Math.pow((0.31830988618379067154 * x1 / (nyu * r_u1_avg)), 0.45)) + dzettaOutlet_1) /
                                    (Math.pow(2.0 * Math.PI * delta_1 * r_u1_avg, 2.0) * g) +
                            omega * Eff_Hyd_wheel / (2.0 * Math.PI * b_2 * g * ksi_2 * Math.tan(betta_2)) -
                            0.00181414881186747126664 * l_gap1 * Math.pow(x1, 2.0) * (0.78539816339744830962 * v_u1 * delta_1 * r_u1_avg / x1 + 1.0) /
                                    (g * nyu * Math.pow(delta_1 * r_u1_avg, 3.0) * Math.pow(0.31830988618379067154 * x1 / (nyu * r_u1_avg), 1.45));

                    double dif12 = helpValue2 + omega * Eff_Hyd_wheel / (2.0 * Math.PI * b_2 * g * ksi_2 * Math.tan(betta_2));

                    double dif13 = dif12;
                    double dif21 = dif12;
                    double dif22 = helpValue2 + 16.0 * (x2 + x3) * (dzettaInlet_0 + dzettaOutlet_0 + ALPHA_1 * l_otv * Math.pow((x2 + x3) / (Math.PI * nyu * d_otv), ALPHA_2) / d_otv) /
                            (g * Math.pow(Math.PI * d_otv * d_otv * z_otv, 2.0)) -
                            0.00994718394324345848561 * v_u2 * (dzettaInlet_2 + l_gap2 / (delta_2 * Math.pow(0.31830988618379067154 * x2 / (nyu * r_u2_avg), 0.45)) + dzettaOutlet_2) / (g * delta_2 * r_u2_avg) +
                            x2 * (0.78539816339744830962 * v_u2 * delta_2 * r_u2_avg / x2 + 1.0) * (dzettaInlet_2 + dzettaOutlet_2 + l_gap2 / (delta_2 * Math.pow(0.31830988618379067154 * x2 / (nyu * r_u2_avg), 0.45))) /
                                    (g * Math.pow(2.0 * Math.PI * delta_2 * r_u2_avg, 2.0)) +
                            omega * Eff_Hyd_wheel / (2.0 * Math.PI * b_2 * g * ksi_2 * Math.tan(betta_2)) -
                            0.00181414881186747126664 * l_gap2 * (x2 * 0.78539816339744830962 * v_u2 * delta_2 * r_u2_avg + x2 * x2) /
                                    (g * nyu * Math.pow(delta_2 * r_u2_avg, 3.0) * Math.pow(0.31830988618379067154 * x2 / (nyu * r_u2_avg), 1.45)) +
                            ALPHA_1 * ALPHA_2 * l_otv * Math.pow(4.0 * (x2 + x3), 2.0) * Math.pow((x2 + x3) / (Math.PI * nyu * d_otv), (ALPHA_2 - 1.0)) /
                                    (2.0 * g * nyu * Math.pow(Math.PI * d_otv * d_otv, 3.0) * z_otv * z_otv);

                    double dif23 = (Q_summary / Math.pow((Math.PI * D_2 * b_2 * ksi_2), 2.0) -
                            (U_2 - Q_summary / helpValue1) /
                                    helpValue1) / g +
                            16.0 * (x2 + x3) * (dzettaInlet_0 + dzettaOutlet_0 + ALPHA_1 * l_otv * Math.pow((x2 + x3) / (Math.PI * nyu * d_otv), ALPHA_2) / d_otv) /
                                    (g * Math.pow(Math.PI * d_otv * d_otv * z_otv, 2.0)) +
                            omega * Eff_Hyd_wheel / (2.0 * Math.PI * b_2 * g * ksi_2 * Math.tan(betta_2)) +
                            ALPHA_1 * ALPHA_2 * l_otv * Math.pow(4.0 * (x2 + x3), 2.0) * Math.pow((x2 + x3) / (Math.PI * nyu * d_otv), (ALPHA_2 - 1.0)) /
                                    (2.0 * g * nyu * Math.pow(Math.PI * d_otv * d_otv, 3.0) * z_otv * z_otv);
                    double dif31 = omega * Eff_Hyd / (2.0 * Math.PI * b_2 * g * ksi_2 * Math.tan(betta_2));
                    double dif32 = 16.0 * (x2 + x3) * (dzettaInlet_0 + dzettaOutlet_0 + ALPHA_1 * l_otv * Math.pow((x2 + x3) / (Math.PI * nyu * d_otv), ALPHA_2) / d_otv) /
                            (g * Math.pow(Math.PI * d_otv * d_otv * z_otv, 2.0)) +
                            omega * Eff_Hyd / (2.0 * Math.PI * b_2 * g * ksi_2 * Math.tan(betta_2)) +
                            ALPHA_1 * ALPHA_2 * l_otv * Math.pow(4.0 * (x2 + x3), 2.0) * Math.pow((x2 + x3) / (Math.PI * nyu * d_otv), (ALPHA_2 - 1.0)) /
                                    (2.0 * g * nyu * Math.pow(Math.PI * d_otv * d_otv, 3.0) * z_otv * z_otv);

                    double dif33 = 16.0 * (x2 + x3) * (dzettaInlet_0 + dzettaOutlet_0 + ALPHA_1 * l_otv * Math.pow((x2 + x3) / (Math.PI * nyu * d_otv), ALPHA_2) / d_otv) /
                            (g * Math.pow(Math.PI * d_otv * d_otv * z_otv, 2.0)) -
                            0.00994718394324345848561 * v_u3 * (dzettaInlet_3 + l_gap3 / (delta_3 * Math.pow((0.31830988618379067154 * x3 / (nyu * r_u3_avg)), 0.45)) + dzettaOutlet_3)
                                    / (g * delta_3 * r_u3_avg) +
                            (0.78539816339744830962 * v_u3 * delta_3 * r_u3_avg + x3) * (dzettaInlet_3 + dzettaOutlet_3 + l_gap3 / (delta_3 * Math.pow(0.31830988618379067154 * x3 / (nyu * r_u3_avg), 0.45))) /
                                    (g * Math.pow(2.0 * Math.PI * delta_3 * r_u3_avg, 2.0)) +
                            omega * Eff_Hyd / (2.0 * Math.PI * b_2 * g * ksi_2 * Math.tan(betta_2)) -
                            0.00181414881186747126664 * l_gap3 * Math.pow(x3, 2.0) * (0.78539816339744830962 * v_u3 * delta_3 * r_u3_avg / x3 + 1.0) /
                                    (g * nyu * Math.pow(delta_3 * r_u3_avg, 3.0) * Math.pow(0.31830988618379067154 * x3 / (nyu * r_u3_avg), 1.45)) +
                            ALPHA_1 * ALPHA_2 * l_otv * Math.pow(4.0 * (x2 + x3), 2.0) * Math.pow((x2 + x3) / (Math.PI * nyu * d_otv), (ALPHA_2 - 1.0)) /
                                    (2.0 * g * nyu * Math.pow(Math.PI * d_otv * d_otv, 3.0) * z_otv * z_otv);

                    double helpValue = dif11 * dif22 * dif33 - dif11 * dif23 * dif32 - dif12 * dif21 * dif33 + dif12 * dif31 * dif23 + dif21 * dif13 * dif32 - dif13 * dif22 * dif31;
                    double d11 = (dif22 * dif33 - dif23 * dif32) / helpValue;
                    double d12 = -(dif12 * dif33 - dif13 * dif32) / helpValue;
                    double d13 = (dif12 * dif23 - dif13 * dif22) / helpValue;

                    double d21 = -(dif21 * dif33 - dif31 * dif23) / helpValue;
                    double d22 = (dif11 * dif33 - dif13 * dif31) / helpValue;
                    double d23 = -(dif11 * dif23 - dif21 * dif13) / helpValue;

                    double d31 = (dif21 * dif32 - dif22 * dif31) / helpValue;
                    double d32 = -(dif11 * dif32 - dif12 * dif31) / helpValue;
                    double d33 = (dif11 * dif22 - dif12 * dif21) / helpValue;

                    x1_i = x1 - (d11 * FF1 + d12 * FF2 + d13 * FF3);
                    x2_i = x2 - (d21 * FF1 + d22 * FF2 + d23 * FF3);
                    x3_i = x3 - (d31 * FF1 + d32 * FF2 + d33 * FF3);
                }

//-----------------------------------------------------------------------------------------------------------------------------
                double Eff_vol = Q_nom / (Q_nom + x1 + x2 + x3);
                double Q_leak_1 = x1 * 3600.0;
                double Q_leak_2 = x2 * 3600.0;
                double Q_leak_3 = x3 * 3600.0;

                double Q_summ = (Q_nom + x1 + x2 + x3) * 3600.0;
                double V_2u = y * U_2 - V_2m * Math.cos(betta_2) / Math.sin(betta_2);
                double H_t = U_2 * V_2u / g;                                            //Theoretical head, m
                double H_res = H_t * Eff_Hyd;                                           //Pump head, m
                double V_abs2 = Math.sqrt(Math.pow(V_2m, 2.0) + Math.pow(V_2u, 2.0));   //Output flow velosity (Abs), m/s
                double bett_out = Math.toDegrees(Math.atan(V_2m / V_2u));               //Output angle of flow, rad--> deg
                double n_s_1 = new BigDecimal(n_s).setScale(3, RoundingMode.UP).doubleValue();  //rounding of Specific speed
                double SSS_1 = new BigDecimal(SSS).setScale(3, RoundingMode.UP).doubleValue();  //rounding of Suction-specific speed
                listOfValues1.get(6).setText(String.valueOf(n_s_1));
                listOfValues1.get(7).setText(String.valueOf(SSS_1));
                listOfValues1.get(16).setText(String.valueOf(H_t));
                listOfValues1.get(17).setText(String.valueOf(H_res));

                double H_stat = H_t * Eff_wh - 0.5 * Math.pow(V_abs2, 2.0) / g;
                listOfValues2.get(6).setText(String.valueOf(Q_leak_1));
                listOfValues3.get(3).setText(String.valueOf(Q_leak_2));
                listOfValues4.get(3).setText(String.valueOf(Q_leak_3));
                listOfValues2.get(7).setText(String.valueOf(Q_summ));
                listOfValues2.get(8).setText(String.valueOf(Eff_vol));
                listOfValues2.get(9).setText(String.valueOf(H_stat));
                listOfValues2.get(12).setText(String.valueOf(V_abs2));
                listOfValues2.get(13).setText(String.valueOf(bett_out));
                listOfValues1.get(0).setText(String.valueOf(Q_summ));

                double v_suc = 4.0 * (Q_nom + x1 + x2 + x3) / (Math.PI * (Math.pow(d_suc, 2.0) - Math.pow(d_hub, 2.0)));
                double p_abs = P_st_in + Math.pow(v_suc, 2.0) * 0.5 * Density;
                double P_st_out = P_st_in + H_stat * Density * g;
                double F_1, F_2, F_3, F_4, F_sum;
                F_2 = Math.PI * 0.25 * (D_2 * D_2 - d_seal_1 * d_seal_1) * (P_st_out - Math.pow((0.25 * fi_1 * omega), 2.0) * Density * (D_2 * D_2 - d_seal_1 * d_seal_1));
                if (checkBoxSecondSeal.isSelected()) {
                    F_1 = 0.0;
                    F_4 = Math.PI * 0.25 * (d_seal_2 * d_seal_2 - d_hub * d_hub) * ((H_t1 * Eff_Hyd_wheel - H_dyn1 - del_H_leak2 - del_H_omega_2) * Density * g - Math.pow((0.25 * fi_2 * omega), 2.0) * Density * (d_seal_2 * d_seal_2 - d_hub * d_hub));
                    F_3 = Math.PI * 0.25 * (D_2 * D_2 - d_seal_2 * d_seal_2) * (P_st_out - Math.pow((0.25 * fi_2 * omega), 2.0) * Density * (D_2 * D_2 - d_seal_2 * d_seal_2));
                } else {
                    F_1 = p_abs * Math.PI * 0.25 * (d_seal_1 * d_seal_1 - d_hub * d_hub);
                    F_3 = Math.PI * 0.25 * (D_2 * D_2 - d_hub * d_hub) * (P_st_out - Math.pow((0.25 * fi_2 * omega), 2.0) * Density * (D_2 * D_2 - d_hub * d_hub));
                    F_4 = 0.0;
                }
                F_sum = (F_1 + F_2) - (F_3 + F_4);
                listOfValues1.get(19).setText(String.valueOf(F_sum));
                System.out.println(P_st_out / (Density * g) - del_H_leak2);
            }
        });
        btnNewButton.setBounds(x_element + 445, y_element + 450, 100, 20);
        frame.getContentPane().add(btnNewButton);

        textField_6 = new JTextField();
        textField_6.addKeyListener(new KeyAdapter() {  //Checking Whether the INPUT IS CORRECT
            public void keyTyped(KeyEvent e) {
                InputNumberRules.inputNumberRules(e);
            }
        });
        textField_6.setText("1.2");
        textField_6.setFont(textFieldFont);
        textField_6.setBounds(x_element + 345, y_element + 75, 50, 20);
        frame.getContentPane().add(textField_6);
        textField_6.setColumns(15);

        listOfValues1.get(14).setToolTipText("Press Enter to get K_0");
        listOfValues1.get(14).setText("90");
        listOfValues1.get(14).addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                double Q_nom = Double.parseDouble(listOfValues2.get(0).getText()) / 3600.0;     //Seal's diameter, m3/hr --> m3/s
                double n = Double.parseDouble(listOfValues1.get(2).getText());                   //Rotation speed, rpm
                double D_flow = Math.pow((Q_nom / n), (1.0 / 3.0));
                double d_hub = Double.parseDouble(listOfValues1.get(13).getText()) / 1000.0;
                double d_suc = Double.parseDouble(listOfValues1.get(14).getText()) / 1000.0;     //Suction diameter, mm --> m
                if (d_suc > d_hub) {
                    double K_0 = Math.sqrt(d_suc * d_suc - d_hub * d_hub) / D_flow;
                    double K_01 = new BigDecimal(K_0).setScale(5, RoundingMode.UP).doubleValue();  //rounding of K_01
                    lblNewLabel_48.setText(String.valueOf(K_01));
                } else {
                    lblNewLabel_48.setText("0.0");
                }
            }
        });

        List<JLabel> listOfLabels3 = new ArrayList<>();
        listOfLabels3.add(new JLabel("R_a, mm"));
        listOfLabels3.add(new JLabel("R_b, mm"));
        listOfLabels3.add(new JLabel("alpha, deg"));
        listOfLabels3.add(new JLabel("betta, deg"));
        listOfLabels3.add(new JLabel("Step on the shroud, mm"));
        listOfLabels3.add(new JLabel("Step on the hub, mm"));
        listOfLabels3.add(new JLabel("Zoom of meridional, percent"));
        listOfLabels3.add(new JLabel("Zoom of area chart (x), percent"));
        listOfLabels3.add(new JLabel("Zoom of area chart (y), percent"));
        listOfLabels3.add(new JLabel("Calculation time, sec"));

        for (int i = 0; i < listOfLabels3.size(); i++) {
            listOfLabels3.get(i).setFont(labelFont);
            listOfLabels3.get(i).setBounds(5, y_element + 520 + i * 25, 200, 20);
            frame.getContentPane().add(listOfLabels3.get(i));
        }

        List<JTextField> listOfValues5 = new ArrayList<>();
        for (int i = 0; i < listOfLabels3.size(); i++) {
            listOfValues5.add(new JTextField());
            listOfValues5.get(i).setFont(textFieldFont);
            listOfValues5.get(i).setBounds(x_element + 200, y_element + 520 + i * 25, 65, 20);
            if (i == (listOfLabels3.size() - 1)) {
                listOfValues5.get(i).setBackground(Color.PINK);
                listOfValues5.get(i).setEditable(false);
            }
            listOfValues5.get(i).addKeyListener(new KeyAdapter() {  //Checking Whether the INPUT IS CORRECT
                public void keyTyped(KeyEvent e) {
                    InputNumberRules.inputNumberRules(e);
                }
            });
            frame.getContentPane().add(listOfValues5.get(i));
        }

        listOfValues5.get(0).setText("18");
        listOfValues5.get(1).setText("35");
        listOfValues5.get(2).setText("9");
        listOfValues5.get(3).setText("0");
        listOfValues5.get(4).setText("0.01");
        listOfValues5.get(5).setText("0.0001");
        listOfValues5.get(6).setText("400");
        listOfValues5.get(7).setText("400");
        listOfValues5.get(8).setText("10");
        listOfValues5.get(9).setText("0.0");

        JButton btnNewButton1 = new JButton("Calculation 1");
        btnNewButton1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {

                double beginTime = System.currentTimeMillis();
                double R_2 = Double.parseDouble(listOfValues1.get(8).getText()) / 2.0;               //Impeller radius, mm
                double b_2 = Double.parseDouble(listOfValues1.get(9).getText()) / 1.0;               //Outlet width, mm
                double R_hub = Double.parseDouble(listOfValues1.get(13).getText()) / 2.0;            //Hub radius, mm
                double R_1 = Double.parseDouble(listOfValues1.get(14).getText()) / 2.0;              //Suction radius, mm
                double R_a = Double.parseDouble(listOfValues5.get(0).getText()) / 1.0;
                double R_b = Double.parseDouble(listOfValues5.get(1).getText()) / 1.0;
                double alpha = Math.toRadians(Double.parseDouble(listOfValues5.get(2).getText()));
                double betta = Math.toRadians(Double.parseDouble(listOfValues5.get(3).getText()));
                double step_shroud = Double.parseDouble(listOfValues5.get(4).getText());
                double step_hub = Double.parseDouble(listOfValues5.get(5).getText());
                COORDINATES.clear();
                MeridionalCalculation meridionalCalculation = new MeridionalCalculation(R_2, b_2, R_hub, R_1, R_a, R_b, alpha, betta, step_shroud, step_hub);
//--------------------------------------------------------------------------------------------------------------------
                //parallel part of code.  There is no benefits for using it. Used for training multiThreading
                int threadNumber = 1;
                int flag = 0;
                List<Thread> calcThreads = new ArrayList<>();
                for (int i = 0; i < threadNumber; i++) {
                    int finalI = i;
                    calcThreads.add(new Thread(() -> {
                        meridionalCalculation.calc(finalI, threadNumber, R_2, b_2, R_hub, R_1, R_a, R_b, alpha, betta, step_shroud, step_hub);
                    }));
                    calcThreads.get(finalI).start();
//                    try {
//                        calcThreads.get(finalI).join();
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
                }
//alternative of using "join". A little faster then "join"-way, but not so obvious
                while (Thread.activeCount() > 2) {
                    try {
                        Thread.sleep(50);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
//--------------------------------------------------------------------------------------------------------------------
                if (table != null) {
                    frame.getContentPane().remove(scrollPane);
                }
                table = new JTable(COORDINATES.size(), 11);
                table.setRowHeight(20);
                table.getColumnModel().getColumn(0).setMaxWidth(45);
                table.getColumnModel().getColumn(1).setMaxWidth(60);

                table.getColumnModel().getColumn(0).setHeaderValue("NN");
                table.getColumnModel().getColumn(1).setHeaderValue("X_01, mm");
                table.getColumnModel().getColumn(2).setHeaderValue("F_1, mm");
                table.getColumnModel().getColumn(3).setHeaderValue("X_02, mm");
                table.getColumnModel().getColumn(4).setHeaderValue("F_2, mm");
                table.getColumnModel().getColumn(5).setHeaderValue("X_arc, mm");
                table.getColumnModel().getColumn(6).setHeaderValue("Y_arc, mm");
                table.getColumnModel().getColumn(7).setHeaderValue("R_arc, mm");
                table.getColumnModel().getColumn(8).setHeaderValue("F, mm^2");
                table.getColumnModel().getColumn(9).setHeaderValue("Ux, mm");
                table.getColumnModel().getColumn(10).setHeaderValue("Vx, mm");

                scrollPane = new JScrollPane(table);
                scrollPane.setBounds(x_element + 270, y_element + 520, 850, 220);
                frame.getContentPane().add(scrollPane);

                for (int i = 0; i < COORDINATES.size(); i++) {
                    COORDINATES.get(i).getIndex();
                    table.getModel().setValueAt(COORDINATES.get(i).getIndex(), i, 0);
                    table.getModel().setValueAt(COORDINATES.get(i).getX01(), i, 1);
                    table.getModel().setValueAt(COORDINATES.get(i).getF1(), i, 2);
                    table.getModel().setValueAt(COORDINATES.get(i).getX02(), i, 3);
                    table.getModel().setValueAt(COORDINATES.get(i).getF2(), i, 4);
                    table.getModel().setValueAt(COORDINATES.get(i).getX_arc(), i, 5);
                    table.getModel().setValueAt(COORDINATES.get(i).getY_arc(), i, 6);
                    table.getModel().setValueAt(COORDINATES.get(i).getR_arc(), i, 7);
                    table.getModel().setValueAt(COORDINATES.get(i).getF(), i, 8);
                    table.getModel().setValueAt(COORDINATES.get(i).getUx(), i, 9);
                    table.getModel().setValueAt(COORDINATES.get(i).getVx(), i, 10);
                }

                zoomOfMeridional = Double.parseDouble(listOfValues5.get(6).getText()) / 100.0;
                zoomOfAreaChartX = Double.parseDouble(listOfValues5.get(7).getText()) / 100.0;
                zoomOfAreaChartY = Double.parseDouble(listOfValues5.get(8).getText()) / 100.0;

                meridionalCanvas.setBackground(Color.WHITE);
                int widthOfMeridionalCanvas = (int) (2 * zoomOfMeridional * COORDINATES.get(COORDINATES.size() - 1).getX01());
                int heightOfMeridionalCanvas = (int) (1.2 * zoomOfMeridional * COORDINATES.get(0).getF1());

                meridionalCanvas.setBounds(1140, y_element, widthOfMeridionalCanvas, heightOfMeridionalCanvas);
                frame.getContentPane().add(meridionalCanvas);

                areaChartCanvas.setBackground(Color.WHITE);
                double maxValueOfArea = 0.0;
                for (int i = 0; i < COORDINATES.size(); i++) {
                    if (maxValueOfArea < COORDINATES.get(i).getF()) {
                        maxValueOfArea = COORDINATES.get(i).getF();
                    }
                }
                int widthOfAreaChartCanvas = (int) (1.1 * zoomOfAreaChartX * COORDINATES.get(0).getF1());
                int heightOfAreaChartCanvas = (int) (1.1 * zoomOfAreaChartY * maxValueOfArea);
                areaChartCanvas.setBounds(1160 + widthOfMeridionalCanvas, y_element, widthOfAreaChartCanvas, heightOfAreaChartCanvas);
                frame.getContentPane().add(areaChartCanvas);

                double time = (System.currentTimeMillis() - beginTime) / 1000.0;
                listOfValues5.get(9).setText(String.valueOf(time));
            }
        });
        btnNewButton1.setBounds(x_element + 145, y_element + 775, 120, 20);
        frame.getContentPane().add(btnNewButton1);

    }
}