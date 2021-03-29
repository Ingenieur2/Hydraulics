package ru.package01;

import java.awt.*;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

public class Window01 {

    private JFrame frame;
    private JTextField textField_6;

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
        frame = new JFrame();
        frame.setForeground(Color.GRAY);
        frame.setTitle("Calculation of centrifugal wheel");
        frame.setBounds(200, 250, 1300, 800);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JMenuBar menuBar = new JMenuBar();
        frame.setJMenuBar(menuBar);

        JMenuItem mntmMenuItem = new JMenuItem("Main dimensions and parameters");
        mntmMenuItem.setFont(new Font("Nyala", Font.PLAIN, 20));
        if (mntmMenuItem.isSelected()) {
            //		frame.setVisible(true);
        } else {
            //		frame.setVisible(false);
        }
        menuBar.add(mntmMenuItem);

        JMenuItem mntmMenuItem_1 = new JMenuItem("Meridional cross section");
        mntmMenuItem_1.setFont(new Font("Nyala", Font.PLAIN, 20));
        menuBar.add(mntmMenuItem_1);

        frame.getContentPane().setLayout(null);

        mntmMenuItem.setSelected(true);
        if (mntmMenuItem.isSelected()) {
            mntmMenuItem_1.setSelected(false);
        } else {
            mntmMenuItem_1.setSelected(true);
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

        Font textFieldFont = new Font("Courier New", Font.BOLD, 14);
        List<JTextField> listOfValues = new ArrayList<>();
        for (int i = 0; i < listOfLabels1.size(); i++) {
            listOfValues.add(new JTextField());
            listOfValues.get(i).setFont(textFieldFont);
            listOfValues.get(i).setBounds(205, 5 + i * 25, 55, 20);
            if ((i == 0) || (i == 16) || (i == 17) || (i == 19)) {
                listOfValues.get(i).setBounds(205, 5 + i * 25, 165, 20);
            }
            if ((i == 6) || (i == 7)) {
                listOfValues.get(i).setBounds(205, 5 + i * 25, 75, 20);
            }
            listOfValues.get(i).addKeyListener(new KeyAdapter() {  //Checking Whether the INPUT IS CORRECT
                public void keyTyped(KeyEvent e) {
                    InputNumberRules.inputNumberRules(e);
                }
            });
            frame.getContentPane().add(listOfValues.get(i));
        }
        listOfValues.get(0).setText("72.76");
        listOfValues.get(1).setText("78");
        listOfValues.get(2).setText("4100");
        listOfValues.get(3).setText("5.5");
        listOfValues.get(4).setText("998.2");
        listOfValues.get(5).setText("0.785");
        listOfValues.get(6).setText("-----");
        listOfValues.get(6).setEditable(false);
        listOfValues.get(7).setText("-----");
        listOfValues.get(7).setEditable(false);
        listOfValues.get(8).setText("189");
        listOfValues.get(9).setText("9.5");
        listOfValues.get(10).setText("3");
        listOfValues.get(11).setText("6");
        listOfValues.get(12).setText("25");
        listOfValues.get(13).setText("5");
        listOfValues.get(14).setText("90");
        listOfValues.get(15).setText("0.785");
        listOfValues.get(16).setText("-----");
        listOfValues.get(16).setEditable(false);
        listOfValues.get(17).setText("-----");
        listOfValues.get(17).setEditable(false);
        listOfValues.get(18).setText("0.1");
        listOfValues.get(19).setText("-----");
        listOfValues.get(19).setEditable(false);


        for (int i = 0; i < listOfLabels1.size(); i++) {
            listOfLabels1.get(i).setFont(labelFont);
            listOfLabels1.get(i).setBounds(5, 5 + i * 25, 200, 20);
            frame.getContentPane().add(listOfLabels1.get(i));
        }

        List<JLabel> listOfLabels2 = new ArrayList<>();
        listOfLabels2.add(new JLabel("Wheel's flow rate, m3/hr"));
        listOfLabels2.add(new JLabel("Value of gap seal, mm"));
        listOfLabels2.add(new JLabel("Length of gap seal, mm"));
        listOfLabels2.add(new JLabel("Coefficient of Swirling flow"));
        listOfLabels2.add(new JLabel("Viscosity, mm2/s"));
        listOfLabels2.add(new JLabel("Approximate wheel efficiency"));
        listOfLabels2.add(new JLabel("Coefficient of friction resistance"));
        listOfLabels2.add(new JLabel("Flow coefficient MYU"));
        listOfLabels2.add(new JLabel("Reynolds number Re"));
        listOfLabels2.add(new JLabel("Leakage flow, m3/hr"));
        listOfLabels2.add(new JLabel("Q_summ, mm"));
        listOfLabels2.add(new JLabel("Volume Efficiency"));
        listOfLabels2.add(new JLabel("H_stat, m"));
        listOfLabels2.add(new JLabel("Hub diameter after wheel, mm"));
        listOfLabels2.add(new JLabel("Seal's diameter, mm"));
        listOfLabels2.add(new JLabel("Output flow velosity (Abs), m/s"));
        listOfLabels2.add(new JLabel("Output angle of flow, deg"));

        for (int i = 0; i < listOfLabels2.size(); i++) {
            listOfLabels2.get(i).setFont(labelFont);
            listOfLabels2.get(i).setBounds(405, 5 + i * 25, 215, 20);
            frame.getContentPane().add(listOfLabels2.get(i));
        }

        List<JTextField> listOfValues2 = new ArrayList<>();
        for (int i = 0; i < listOfLabels2.size(); i++) {
            listOfValues2.add(new JTextField());
            listOfValues2.get(i).setFont(textFieldFont);
            if ((i < 6) || (i == 13) || (i == 14)) {
                listOfValues2.get(i).setBounds(620, 5 + i * 25, 55, 20);
            } else {
                listOfValues2.get(i).setBounds(620, 5 + i * 25, 165, 20);
                listOfValues2.get(i).setEditable(false);
            }
            if ((i == 6)) {
                listOfValues2.get(i).setBounds(620, 5 + i * 25, 115, 20);
                listOfValues2.get(i).setEditable(true);
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
        listOfValues2.get(5).setText("0.9");
        listOfValues2.get(6).setText("0.031");
        listOfValues2.get(7).setText("-----");
        listOfValues2.get(8).setText("-----");
        listOfValues2.get(9).setText("-----");
        listOfValues2.get(10).setText("-----");
        listOfValues2.get(11).setText("-----");
        listOfValues2.get(12).setText("-----");
        listOfValues2.get(13).setText("75");
        listOfValues2.get(14).setText("102");
        listOfValues2.get(15).setText("-----");
        listOfValues2.get(16).setText("-----");

        List<JTextField> listOfValues3 = new ArrayList<>();
        for (int i = 0; i < 8; i++) {
            listOfValues3.add(new JTextField());
            listOfValues3.get(i).setEnabled(false);
            listOfValues3.get(i).setVisible(false);
            listOfValues3.get(i).setFont(textFieldFont);

            if ((i < 3)) {
                listOfValues3.get(i).setBounds(740, 30 + i * 25, 55, 20);
            }
            if (i == 3) {
                listOfValues3.get(i).setBounds(740, 80 + i * 25, 115, 20);
            }
            if ((i > 3) && (i < 7)) {
                listOfValues3.get(i).setBounds(790, 80 + i * 25, 165, 20);
                listOfValues3.get(i).setEditable(false);
            }
            if (i >= 7) {
                listOfValues3.get(i).setBounds(740, 180 + i * 25, 55, 20);
            }
            listOfValues3.get(i).addKeyListener(new KeyAdapter() {  //Checking Whether the INPUT IS CORRECT
                public void keyTyped(KeyEvent e) {
                    InputNumberRules.inputNumberRules(e);
                }
            });
            frame.getContentPane().add(listOfValues3.get(i));
        }
        listOfValues3.get(0).setText("0.25");
        listOfValues3.get(1).setText("20");
        listOfValues3.get(2).setText("0.5");
        listOfValues3.get(3).setText("0.031");
        listOfValues3.get(4).setText("-----");
        listOfValues3.get(5).setText("-----");
        listOfValues3.get(6).setText("-----");
        listOfValues3.get(7).setText("102");

        List<JTextField> listOfValues4 = new ArrayList<>();
        for (int i = 0; i < 8; i++) {
            listOfValues4.add(new JTextField());
            listOfValues4.get(i).setEnabled(false);
            listOfValues4.get(i).setVisible(false);
            listOfValues4.get(i).setFont(textFieldFont);

            if ((i < 3)) {
                listOfValues4.get(i).setBounds(960, 30 + i * 25, 55, 20);
            }
            if (i == 3) {
                listOfValues4.get(i).setBounds(960, 80 + i * 25, 115, 20);
            }
            if ((i > 3) && (i < 7)) {
                listOfValues4.get(i).setBounds(960, 80 + i * 25, 165, 20);
                listOfValues4.get(i).setEditable(false);
            }
            if (i >= 7) {
                listOfValues4.get(i).setBounds(960, 180 + i * 25, 55, 20);
            }
            listOfValues4.get(i).addKeyListener(new KeyAdapter() {  //Checking Whether the INPUT IS CORRECT
                public void keyTyped(KeyEvent e) {
                    InputNumberRules.inputNumberRules(e);
                }
            });
            frame.getContentPane().add(listOfValues4.get(i));
        }
        listOfValues4.get(0).setText("0.25");
        listOfValues4.get(1).setText("20");
        listOfValues4.get(2).setText("0.5");
        listOfValues4.get(3).setText("0.031");
        listOfValues4.get(4).setText("-----");
        listOfValues4.get(5).setText("-----");
        listOfValues4.get(6).setText("-----");
        listOfValues4.get(7).setText("75");

        //----------------------------------------------------------
        JLabel lblNewLabel_8 = new JLabel("Safety factor");
        lblNewLabel_8.setFont(labelFont);
        lblNewLabel_8.setBounds(265, 80, 85, 20);
        frame.getContentPane().add(lblNewLabel_8);

        JLabel lblNewLabel_49 = new JLabel("K_0 =");
        lblNewLabel_49.setFont(labelFont);
        lblNewLabel_49.setBounds(265, 355, 45, 20);
        frame.getContentPane().add(lblNewLabel_49);

        JLabel lblNewLabel_46 = new JLabel("-----");                            //for help : D_2
        lblNewLabel_46.setEnabled(false);
        lblNewLabel_46.setVisible(false);
        lblNewLabel_46.setFont(textFieldFont);
        lblNewLabel_46.setBounds(285, 205, 185, 20);
        frame.getContentPane().add(lblNewLabel_46);

        JLabel lblNewLabel_47 = new JLabel("-----");                            //for help : b_2
        lblNewLabel_47.setEnabled(false);
        lblNewLabel_47.setVisible(false);
        lblNewLabel_47.setFont(textFieldFont);
        lblNewLabel_47.setBounds(285, 230, 185, 20);
        frame.getContentPane().add(lblNewLabel_47);

        JLabel lblNewLabel_48 = new JLabel("");                                    //for K_0
        lblNewLabel_48.setFont(textFieldFont);
        lblNewLabel_48.setBounds(310, 355, 85, 20);
        frame.getContentPane().add(lblNewLabel_48);

        JCheckBox chckbxNewCheckBox = new JCheckBox("Presence of the second seal");
        chckbxNewCheckBox.setFont(labelFont);
        chckbxNewCheckBox.setBounds(685, 5, 220, 20);
        chckbxNewCheckBox.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                for (int i = 0; i < listOfValues3.size(); i++) {
                    if (chckbxNewCheckBox.isSelected()) {
                        listOfValues3.get(i).setEnabled(true);
                        listOfValues3.get(i).setVisible(true);
                    } else {
                        listOfValues3.get(i).setEnabled(false);
                        listOfValues3.get(i).setVisible(false);
                    }
                }
            }
        });
        frame.getContentPane().add(chckbxNewCheckBox);

        JCheckBox chckbxNewCheckBox_1 = new JCheckBox("Presence of the next stage");
        chckbxNewCheckBox_1.setEnabled(true);
        chckbxNewCheckBox_1.setFont(labelFont);
        chckbxNewCheckBox_1.setBounds(920, 5, 220, 20);
        chckbxNewCheckBox_1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                String d_seal_2 = listOfValues2.get(13).getText();            //Seal's diameter 1, mm

                for (int i = 0; i < listOfValues4.size(); i++) {
                    if (chckbxNewCheckBox_1.isSelected()) {
                        listOfValues4.get(i).setEnabled(true);
                        listOfValues4.get(i).setVisible(true);
                    } else {
                        listOfValues4.get(i).setEnabled(false);
                        listOfValues4.get(i).setVisible(false);
                    }
                }
            }
        });
        frame.getContentPane().add(chckbxNewCheckBox_1);

        JCheckBox chckbxNewCheckBox_3 = new JCheckBox("Automatic");
        chckbxNewCheckBox_3.setFont(labelFont);
        chckbxNewCheckBox_3.setBounds(855, 155, 100, 20);
        chckbxNewCheckBox_3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                if (chckbxNewCheckBox_3.isSelected()) {

                } else {
                    //{"1","2","3","4","5","6","7","8","9","0","."}
                }
            }
        });
        frame.getContentPane().add(chckbxNewCheckBox_3);

        JCheckBox chckbxNewCheckBox_2 = new JCheckBox("Help");

        chckbxNewCheckBox_2.setFont(labelFont);
        chckbxNewCheckBox_2.setBounds(285, 180, 60, 20);
        chckbxNewCheckBox_2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                if (chckbxNewCheckBox_2.isSelected()) {
                    chckbxNewCheckBox_2.setToolTipText("Switch twice to refresh");
                    lblNewLabel_46.setEnabled(true);
                    lblNewLabel_46.setVisible(true);
                    lblNewLabel_47.setEnabled(true);
                    lblNewLabel_47.setVisible(true);
                    double Q = Double.parseDouble(listOfValues.get(0).getText()) / 3600.0;         //Pump flow rate, m3/hr-->
                    double H = Double.parseDouble(listOfValues.get(1).getText());                //Pump head, m
                    double n = Double.parseDouble(listOfValues.get(2).getText());                //Rotation speed, rpm
                    double n_s = 3.65 * n * Math.sqrt(Q) / Math.pow(H, 0.75);            //Specific speed

                    double D_head = Math.sqrt(2.0 * 9.81 * H) / n;                              //	D_napor:= sqrt(2*9.81*H)/n;
                    double D_2_head = 18.75 * D_head * Math.pow((n_s / 100.0), (1.0 / 6.0));    //	 D_2_napor:=18.75*D_napor*Exp((1/6)*Ln(n_s/100));
                    double b_2_head = 1.3 * D_head * Math.pow((n_s / 100.0), (1.0 / 6.0));      //	 b_2_napor:=1.3*D_napor*Exp((1/6)*Ln(n_s/100));

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
                    chckbxNewCheckBox_2.setToolTipText("Switch to calculate");
                    lblNewLabel_46.setEnabled(false);
                    lblNewLabel_46.setVisible(false);
                    lblNewLabel_47.setEnabled(false);
                    lblNewLabel_47.setVisible(false);
                }
            }
        });
        frame.getContentPane().add(chckbxNewCheckBox_2);

        JButton btnNewButton = new JButton("Calculation");
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                final double g = 9.81;

                double Q = Double.parseDouble(listOfValues.get(0).getText()) / 3600;                //Pump flow rate, m3/hr-->m3/SEC
                double Q_summ_1 = 0.05 * Q;
                //	123456789012345678
                while (Math.abs((Q_summ_1 - Q)) > 0.000000000000000001) {
                    Q = Q_summ_1;
                    double H = Double.parseDouble(listOfValues.get(1).getText());                //Pump head, m
                    double n = Double.parseDouble(listOfValues.get(2).getText());                //Rotation speed, rpm
                    double NPSH = Double.parseDouble(listOfValues.get(3).getText());                //Net positive suction head, m
                    double Density = Double.parseDouble(listOfValues.get(4).getText());                //Density, kg/m3
                    double S_F = Double.parseDouble(textField_6.getText());                //Safety factor
                    double n_s = (3.65 * n * Math.sqrt(Q)) / Math.pow(H, 0.75);            //Specific speed
                    double SSS = (n * Math.sqrt(Q)) / Math.pow((0.1 * NPSH / S_F), 0.75);    //Suction-specific speed
                    double D_2 = Double.parseDouble(listOfValues.get(8).getText()) / 1000.0;            //Impeller diameter, mm --> m
                    double b_2 = Double.parseDouble(listOfValues.get(9).getText()) / 1000.0;            //Outlet width, mm  --> m
                    double sigma_2 = Double.parseDouble(listOfValues.get(10).getText()) / 1000.0;            //Blade thickness, mm --> m
                    double z = Double.parseDouble(listOfValues.get(11).getText());                //Number of blades
                    double betta_2 = Math.toRadians(Double.parseDouble(listOfValues.get(12).getText()));    //Outlet blade angle, deg --> radians
                    double d_hub = Double.parseDouble(listOfValues.get(13).getText()) / 1000.0;            //Hub diameter, mm --> m
                    double d_suc = Double.parseDouble(listOfValues.get(14).getText()) / 1000.0;            //Suction diameter, mm --> m
                    double Eff_Hyd = Double.parseDouble(listOfValues.get(15).getText());                //Hydraulic efficiency
                    double fi_1 = Double.parseDouble(listOfValues2.get(3).getText());                //Coefficient of swirling flow 1
                    double fi_2 = Double.parseDouble(listOfValues3.get(2).getText());                //Coefficient of swirling flow 1
                    double P_st_in = Double.parseDouble(listOfValues.get(18).getText()) * 1000000.0;        //Pressure static INLET, MPa --> Pa
                    double y = 1.0 - (Math.sin(betta_2) / (z * Math.sqrt(3.0) / (2.0 * Math.PI) + Math.sin(betta_2)));
                    double ksi_2 = 1.0 - z * sigma_2 / (Math.PI * D_2 * Math.sin(betta_2));
                    double U_2 = Math.PI * n * D_2 / 60.0;
                    double V_2m = Q / (Math.PI * D_2 * b_2 * ksi_2);
                    double V_2u = y * U_2 - V_2m * Math.cos(betta_2) / Math.sin(betta_2);
                    double H_t = U_2 * V_2u / g;                                            //Theoretical head, m
                    double H_res = H_t * Eff_Hyd;                                            //Pump head, m
                    double V_abs2 = Math.sqrt(Math.pow(V_2m, 2.0) + Math.pow(V_2u, 2.0));        //Output flow velosity (Abs), m/s
                    double bett_out = Math.toDegrees(Math.atan(V_2m / V_2u));                                    //Output angle of flow, rad--> deg
                    double n_s_1 = new BigDecimal(n_s).setScale(3, RoundingMode.UP).doubleValue();  //rounding of Specific speed
                    double SSS_1 = new BigDecimal(SSS).setScale(3, RoundingMode.UP).doubleValue();  //rounding of Suction-specific speed
                    listOfValues.get(6).setText(String.valueOf(n_s_1));
                    listOfValues.get(7).setText(String.valueOf(SSS_1));
                    listOfValues.get(16).setText(String.valueOf(H_t));
                    listOfValues.get(17).setText(String.valueOf(H_res));

                    double Q_nom = Double.parseDouble(listOfValues2.get(0).getText()) / 3600.0;            //Seal's diameter, m3/hr --> m3/s    	//				Double d_seal_3	= Double.parseDouble(textField_34.getText())/1000.0;			//Seal's diameter 3, mm --> m
                    double d_seal_1 = Double.parseDouble(listOfValues2.get(14).getText()) / 1000.0;            //Seal's diameter 1, mm --> m    		//				Double delta_3	= Double.parseDouble(textField_30.getText())/1000.0;			//Value of gap seal 3, mm --> m
                    double d_seal_2 = Double.parseDouble(listOfValues3.get(7).getText()) / 1000.0;            //Seal's diameter 2, mm --> m    		//				Double lyambda3	= Double.parseDouble(textField_33.getText())		;		//Coefficient of friction resistance 3
                    double delta_1 = Double.parseDouble(listOfValues2.get(1).getText()) / 1000.0;            //Value of gap seal 1, mm --> m 		//				Double l_gap3	= Double.parseDouble(textField_31.getText())/1000.0;			//Length of gap seal 3, mm --> m
                    double delta_2 = Double.parseDouble(listOfValues3.get(0).getText()) / 1000.0;            //Value of gap seal 2, mm --> m			//				Double myu31		= 0.01;
                    double nyu = Double.parseDouble(listOfValues2.get(4).getText()) / 1000000.0;        //Viscosity, mm2/s --> m2/s    				//				Double u_y3		= 0.12;
                    double lyambda1 = Double.parseDouble(listOfValues2.get(6).getText());        //Coefficient of friction resistance 1		//				Double v_03		= 0.15;
                    double lyambda2 = Double.parseDouble(listOfValues3.get(3).getText());        //Coefficient of friction resistance 2		//				Double	Q_leak3	= myu31*Math.PI* (d_seal_3+delta_3)* delta_3*Math.sqrt(2*g*H_leak3);
                    double l_gap1 = Double.parseDouble(listOfValues2.get(2).getText()) / 1000.0;            //Length of gap seal 1, mm --> m		//		        u_y3	= 0.5*omega*(d_seal_3+delta_3);
                    double l_gap2 = Double.parseDouble(listOfValues3.get(1).getText()) / 1000.0;            //Length of gap seal 2, mm --> m		//				v_03	= Q_leak3/ (Math.PI*(d_seal_3+delta_3)* delta_3);
                    double Eff_wh = Double.parseDouble(listOfValues2.get(5).getText());                //Approximate wheel efficiency				//				lyambda3	= 0.28316210710108*Math.pow(Rey_2, -0.239175686607085);	textField_33.setText(lyambda1.toString());
                    double H_leak1 = H_t - (V_2u * V_2u) / (2.0 * g) - Math.pow(U_2, 2.0) * (1 - Math.pow(((d_seal_1 + delta_1) / D_2), 2.0)) / (8.0 * g);        //				Double lyambda_gap3	= lyambda3*Math.sqrt(1+	0.25/Math.pow(1.0+1.3*Math.sqrt(lyambda3), 2.0)	*	Math.pow(u_y3/v_03, 2.0));
                    double H_leak2 = H_t - (V_2u * V_2u) / (2.0 * g) - Math.pow(U_2, 2.0) * (1 - Math.pow(((d_seal_2 + delta_2) / D_2), 2.0)) / (8.0 * g);        //				lyambda*Math.sqrt(1+1/(4*Math.pow((1+1.3*Math.sqrt(lyambda)), 2)*Math.pow(u_y/v_0, 2)));
                    double myu11 = 0.01, myu12 = 1.2, myu21 = 0.01, myu22 = 1.2;
                    double u_y1 = 0.1, u_y2 = 0.11;
                    double v_01 = 0.13, v_02 = 0.14;
                    double Rey_1 = 1.0, Rey_2 = 2.0;
                    double omega = Math.PI * n / 30.0;
                    if (chckbxNewCheckBox.isSelected()) {//0.000000000000001
                        while (Math.abs(myu22 - myu21) >= 0.000000001) {
                            double Q_leak1 = myu11 * Math.PI * (d_seal_1 + delta_1) * delta_1 * Math.sqrt(2.0 * g * H_leak1);
                            double Q_leak2 = myu21 * Math.PI * (d_seal_2 + delta_2) * delta_2 * Math.sqrt(2.0 * g * H_leak2);
                            u_y1 = 0.5 * omega * (d_seal_1 + delta_1);
                            u_y2 = 0.5 * omega * (d_seal_2 + delta_2);
                            v_01 = Q_leak1 / (Math.PI * (d_seal_1 + delta_1) * delta_1);
                            v_02 = Q_leak2 / (Math.PI * (d_seal_2 + delta_2) * delta_2);
                            Rey_1 = 2.0 * delta_1 * Math.sqrt(Math.pow(v_01, 2.0) + Math.pow(0.5 * u_y1, 2.0)) / nyu;
                            Rey_2 = 2.0 * delta_2 * Math.sqrt(Math.pow(v_02, 2.0) + Math.pow(0.5 * u_y2, 2.0)) / nyu;
                            if (chckbxNewCheckBox_3.isSelected()) {

                                lyambda1 = Math.rint(0.28316210710108 * Math.pow(Rey_1, -0.239175686607085) * 100000000000.0) / (100000000000.0);
                                listOfValues2.get(6).setText(String.valueOf(lyambda1));    //12345678901

                                lyambda2 = Math.rint(0.28316210710108 * Math.pow(Rey_2, -0.239175686607085) * 100000000000.0) / (100000000000.0);
                                listOfValues3.get(3).setText(String.valueOf(lyambda1));

                                //12345678901
                                // 10000000000.0
                            }
                            double lyambda_gap1 = lyambda1 * Math.sqrt(1.0 + 0.25 / Math.pow(1.0 + 1.3 * Math.sqrt(lyambda1), 2.0) * Math.pow(u_y1 / v_01, 2.0));
                            double lyambda_gap2 = lyambda2 * Math.sqrt(1.0 + 0.25 / Math.pow(1.0 + 1.3 * Math.sqrt(lyambda2), 2.0) * Math.pow(u_y2 / v_02, 2.0));
                            myu11 = myu12;
                            myu21 = myu22;
                            myu12 = 1.0 / Math.sqrt(1.35 + lyambda_gap1 * l_gap1 / (2.0 * delta_1));
                            myu22 = 1.0 / Math.sqrt(1.35 + lyambda_gap2 * l_gap2 / (2.0 * delta_2));
                        }
                    } else {  //0.00000000000001
                        while (Math.abs(myu12 - myu11) > 0.000000001) {
                            double Q_leak = myu11 * Math.PI * (d_seal_1 + delta_1) * delta_1 * Math.sqrt(2.0 * g * H_leak1);
                            u_y1 = 0.5 * omega * (d_seal_1 + delta_1);
                            v_01 = Q_leak / (Math.PI * (d_seal_1 + delta_1) * delta_1);

                            Rey_1 = 2.0 * delta_1 * Math.sqrt(Math.pow(v_01, 2.0) + Math.pow(0.5 * u_y1, 2.0)) / nyu;

                            if (chckbxNewCheckBox_3.isSelected()) {
                                lyambda1 = Math.rint(0.28316210710108 * Math.pow(Rey_1, -0.239175686607085) * 100000000000.0) / (100000000000.0);
                                listOfValues2.get(6).setText(String.valueOf(lyambda1));
                            }
                            double lyambda_gap1 = lyambda1 * Math.sqrt(1.0 + 0.25 / Math.pow(1.0 + 1.3 * Math.sqrt(lyambda1), 2.0) * Math.pow(u_y1 / v_01, 2.0));

                            //lyambda*Math.sqrt(1+1/(4*Math.pow((1+1.3*Math.sqrt(lyambda)), 2)*Math.pow(u_y/v_0, 2)));
                            myu11 = myu12;
                            myu12 = 1.0 / Math.sqrt(1.35 + lyambda_gap1 * l_gap1 / (2.0 * delta_1));
                        }
                        myu22 = 0.0;
                    }

                    double myu1 = myu12;
                    double myu2 = myu22;
                    double Q_leak1 = myu1 * Math.PI * d_seal_1 * delta_1 * Math.sqrt(2.0 * g * H_leak1);
                    double Q_leak2 = myu2 * Math.PI * d_seal_2 * delta_2 * Math.sqrt(2.0 * g * H_leak2);
                    double Eff_vol = Q_nom / (Q_nom + Q_leak1 + Q_leak2);
                    double Q_leak_1 = Q_leak1 * 3600.0;
                    double Q_leak_2 = Q_leak2 * 3600.0;
                    Q_summ_1 = (Q_nom + Q_leak1 + Q_leak2);
                    double Q_summ = (Q_nom + Q_leak1 + Q_leak2) * 3600.0;

                    double H_stat = H_t * Eff_wh - 0.5 * Math.pow(V_abs2, 2.0) / g;
                    listOfValues2.get(7).setText(String.valueOf(myu1));
                    listOfValues3.get(4).setText(String.valueOf(myu2));
                    listOfValues2.get(8).setText(String.valueOf(Rey_1));
                    listOfValues3.get(5).setText(String.valueOf(Rey_2));
                    listOfValues2.get(9).setText(String.valueOf(Q_leak_1));
                    listOfValues3.get(6).setText(String.valueOf(Q_leak_2));
                    listOfValues2.get(10).setText(String.valueOf(Q_summ));
                    listOfValues2.get(11).setText(String.valueOf(Eff_vol));
                    listOfValues2.get(12).setText(String.valueOf(H_stat));
                    listOfValues2.get(15).setText(String.valueOf(V_abs2));
                    listOfValues2.get(16).setText(String.valueOf(bett_out));
                    listOfValues.get(0).setText(String.valueOf(Q_summ));

                    double v_suc = 4.0 * Q_summ_1 / (Math.PI * (Math.pow(d_suc, 2.0) - Math.pow(d_hub, 2.0)));
                    double p_abs = P_st_in + Math.pow(v_suc, 2.0) * 0.5 * Density;
                    double P_st_out = P_st_in + H_stat * Density * g;
                    double F_1, F_2, F_3, F_sum;
                    F_2 = Math.PI * 0.25 * (D_2 * D_2 - d_seal_1 * d_seal_1) * (P_st_out - Math.pow((0.25 * fi_1 * omega), 2.0) * Density * (D_2 * D_2 - d_seal_1 * d_seal_1));
                    if (chckbxNewCheckBox.isSelected()) {
                        F_1 = 0.0;
                        F_3 = Math.PI * 0.25 * (D_2 * D_2 - d_seal_2 * d_seal_2) * (P_st_out - Math.pow((0.25 * fi_2 * omega), 2.0) * Density * (D_2 * D_2 - d_seal_2 * d_seal_2));
                    } else {
                        F_1 = p_abs * Math.PI * 0.25 * (d_seal_1 * d_seal_1 - d_hub * d_hub);
                        F_3 = Math.PI * 0.25 * (D_2 * D_2 - d_hub * d_hub) * (P_st_out - Math.pow((0.25 * fi_2 * omega), 2.0) * Density * (D_2 * D_2 - d_hub * d_hub));
                    }
                    F_sum = (F_1 + F_2) - F_3;
                    listOfValues.get(19).setText(String.valueOf(F_sum));
                }
            }
        });
        btnNewButton.setBounds(450, 430, 100, 20);
        frame.getContentPane().add(btnNewButton);

        textField_6 = new JTextField();
        textField_6.addKeyListener(new KeyAdapter() {  //Checking Whether the INPUT IS CORRECT
            public void keyTyped(KeyEvent e) {
                InputNumberRules.inputNumberRules(e);
            }
        });
        textField_6.setText("1.2");
        textField_6.setFont(textFieldFont);
        textField_6.setBounds(350, 80, 50, 20);
        frame.getContentPane().add(textField_6);
        textField_6.setColumns(15);

        listOfValues.get(14).setToolTipText("Press Enter to get K_0");
        listOfValues.get(14).setText("90");
        listOfValues.get(14).addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                double Q_nom = Double.parseDouble(listOfValues2.get(0).getText()) / 3600.0;            //Seal's diameter, m3/hr --> m3/s
                double n = Double.parseDouble(listOfValues.get(2).getText());                //Rotation speed, rpm
                double D_flow = Math.pow((Q_nom / n), (1.0 / 3.0));
                double d_hub = Double.parseDouble(listOfValues.get(13).getText()) / 1000.0;
                double d_suc = Double.parseDouble(listOfValues.get(14).getText()) / 1000.0;            //Suction diameter, mm --> m
                if (d_suc > d_hub) {
                    double K_0 = Math.sqrt(d_suc * d_suc - d_hub * d_hub) / D_flow;
                    double K_01 = new BigDecimal(K_0).setScale(5, RoundingMode.UP).doubleValue();  //rounding of K_01

                    lblNewLabel_48.setText(String.valueOf(K_01));
                } else {
                    lblNewLabel_48.setText("0.0");
                }
            }
        });

        Scrollbar scrollbar = new Scrollbar();
        scrollbar.setBounds(1167, 0, 17, 734);
        frame.getContentPane().add(scrollbar);
    }
}