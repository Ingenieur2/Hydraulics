package ru.package01;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class WindowVanes {
    private static final String TEXT_FILE = "Coordinates of guide apparatus curve.txt";
    private static final double STRAIGHT_ANGLE_IN_RADIANS = Math.PI / 2.0;
    private static final int MAX_EXPORTING_CURVE_POINTS = 200;
    private static final double STOP_CALCULATING_CRITERIA = 0.000000000005;

    private JFrame frame;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    WindowVanes window = new WindowVanes();
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
    public WindowVanes() {
        initialize();
    }

    /**
     * Initialize the contents of the frame.
     */
    public void initialize() {
        frame = new JFrame();
        frame.setForeground(Color.GRAY);
        frame.setTitle("Calculation of guide vanes (reverse)");
        frame.setBounds(200, 250, 700, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);

        Font labelFont = new Font("Nyala", Font.PLAIN, 18);
        List<JLabel> listOfLabels0 = new ArrayList<>();
        listOfLabels0.add(new JLabel("Inlet"));
        listOfLabels0.add(new JLabel("Outlet"));
        for (int i = 0; i < listOfLabels0.size(); i++) {
            listOfLabels0.get(i).setFont(labelFont);
            listOfLabels0.get(i).setBounds(380 + i * 85, 5, 350, 20);
            frame.getContentPane().add(listOfLabels0.get(i));
        }

        List<JLabel> listOfLabels1 = new ArrayList<>();
        listOfLabels1.add(new JLabel("Middle line angle, deg"));
        listOfLabels1.add(new JLabel("Diameter, mm"));
        listOfLabels1.add(new JLabel("Channel height, mm"));
        listOfLabels1.add(new JLabel("Rounding radius, mm"));
        listOfLabels1.add(new JLabel("Number of channels"));
        listOfLabels1.add(new JLabel("Number of curve points (optimal: 10e4 - 10e6)"));
        listOfLabels1.add(new JLabel("Channel width (approximate), mm"));
        listOfLabels1.add(new JLabel("Segment length, mm"));
        listOfLabels1.add(new JLabel("Coverage angle, deg"));

        listOfLabels1.add(new JLabel("Diameter of the entrance along the middle line, mm"));
        listOfLabels1.add(new JLabel("Outlet diameter (by calculation), mm"));
        listOfLabels1.add(new JLabel("Calculation time, sec"));
        listOfLabels1.add(new JLabel("Ratio of Input and Output areas"));
        listOfLabels1.add(new JLabel("Exporting time in *.txt, sec"));

        for (int i = 0; i < listOfLabels1.size(); i++) {
            listOfLabels1.get(i).setFont(labelFont);
            listOfLabels1.get(i).setBounds(5, 30 + i * 25, 350, 20);
            frame.getContentPane().add(listOfLabels1.get(i));
        }

        Font textFieldFont = new Font("Courier New", Font.BOLD, 14);
        List<JTextField> listOfValues = new ArrayList<>();
        for (int i = 0; i < listOfLabels1.size(); i++) {
            listOfValues.add(new JTextField());
            listOfValues.get(i).setFont(textFieldFont);
            listOfValues.get(i).setBounds(355, 30 + i * 25, 85, 20);
            if ((i >= 7) && (i <= 13)) {
                listOfValues.get(i).setBounds(355, 30 + i * 25, 180, 20);
                listOfValues.get(i).setEditable(false);
            }
            listOfValues.get(i).addKeyListener(new KeyAdapter() {  //Checking Whether the INPUT IS CORRECT
                public void keyTyped(KeyEvent e) {
                    InputNumberRules.inputNumberRules(e);
                }
            });
            frame.getContentPane().add(listOfValues.get(i));
        }

        listOfValues.get(0).setText("15");
        listOfValues.get(1).setText("400");
        listOfValues.get(2).setText("10");
        listOfValues.get(3).setText("1");
        listOfValues.get(4).setText("13");
        listOfValues.get(5).setText("100000");
        listOfValues.get(6).setText("15");
        listOfValues.get(7).setText("0.1");
        listOfValues.get(8).setText("0.0");
        listOfValues.get(9).setText("0.0");
        listOfValues.get(10).setText("0.0");
        listOfValues.get(11).setText("0.0");
        listOfValues.get(12).setText("0.0");
        listOfValues.get(13).setText("0.0");

        List<JTextField> listOfValues1 = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            listOfValues1.add(new JTextField());
            listOfValues1.get(i).setFont(textFieldFont);
            listOfValues1.get(i).setBounds(450, 30 + i * 25, 85, 20);
            if (i == 4) {
                listOfValues1.get(i).setBounds(450, 80 + i * 25, 85, 20);
                listOfValues1.get(i).setEditable(false);
            }
            listOfValues1.get(i).addKeyListener(new KeyAdapter() {  //Checking Whether the INPUT IS CORRECT
                public void keyTyped(KeyEvent e) {
                    InputNumberRules.inputNumberRules(e);
                }
            });
            frame.getContentPane().add(listOfValues1.get(i));
        }
        listOfValues1.get(0).setText("100");
        listOfValues1.get(1).setText("200");
        listOfValues1.get(2).setText("10");
        listOfValues1.get(3).setText("1");
        listOfValues1.get(4).setText("0.0");

        JButton btnNewButton = new JButton("Calculation");
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                final double g = 9.81;
                double beginTime = System.currentTimeMillis();

                double alpha = Double.parseDouble(listOfValues.get(0).getText()) / 180.0 * Math.PI;
                double entranceRadius = Double.parseDouble(listOfValues.get(1).getText()) / 2.0;
                double entranceHeight = Double.parseDouble(listOfValues.get(2).getText());
                double entranceRoundingRadius = Double.parseDouble(listOfValues.get(3).getText());
                double numberOfChannels = Double.parseDouble(listOfValues.get(4).getText());
                int numberOfCurvePoints = Integer.parseInt(listOfValues.get(5).getText());
                double EntranceChannelWidth = Double.parseDouble(listOfValues.get(6).getText());

                double exitRadius = Double.parseDouble(listOfValues1.get(1).getText()) / 2.0;
                double exitHeight = Double.parseDouble(listOfValues1.get(2).getText());
                double exitRoundingRadius = Double.parseDouble(listOfValues1.get(3).getText());

                double stepOfCurve =
                        (Double.parseDouble(listOfValues1.get(0).getText()) -
                                Double.parseDouble(listOfValues.get(0).getText())
                        ) * Math.PI / (180.0 * (numberOfCurvePoints - 1.0));
                double exitChannelWidth = 2.0 * (Math.PI * exitRadius / numberOfChannels - exitRoundingRadius);
                double ratioInputOutputAreas = (exitChannelWidth * exitHeight) / (EntranceChannelWidth * entranceHeight);

                double helperValue1 = (EntranceChannelWidth / 2.0 + entranceRoundingRadius) / (entranceRadius - entranceRoundingRadius) * Math.sin(alpha);
                double helperValue2 = Math.atan(helperValue1 / (Math.sqrt(1.0 - helperValue1 * helperValue1)));
                double beginningCurveRadius = (entranceRadius - entranceRoundingRadius) * Math.sin(alpha - helperValue2) / Math.sin(alpha);

                double SegmentLength = entranceRadius * 0.28 / numberOfCurvePoints; //empirical coefficient for speeding up the calculation
                double coverageAngle = 0.0;
                entranceRadius = beginningCurveRadius;
                double radius = entranceRadius;
                double delta;
                double calculationStep = SegmentLength;

                while ((Math.abs(radius - exitRadius)) > STOP_CALCULATING_CRITERIA) {
                    alpha = Double.parseDouble(listOfValues.get(0).getText()) / 180.0 * Math.PI;
                    radius = entranceRadius;
                    coverageAngle = 0.0;
                    int i = 0;
                    while (i < numberOfCurvePoints) {
                        if (alpha < STRAIGHT_ANGLE_IN_RADIANS) {
                            radius = Math.sqrt((radius * radius) + (SegmentLength * SegmentLength) - 2.0 * radius * SegmentLength * Math.sin(alpha));
                            delta = Math.atan(1.0 / Math.sqrt(1.0 / (Math.pow((SegmentLength * Math.cos(alpha) / radius), 2.0)) - 1.0));
                            coverageAngle = coverageAngle + delta;
                        }
                        if (alpha > STRAIGHT_ANGLE_IN_RADIANS) {
                            radius = Math.sqrt((radius * radius) + (SegmentLength * SegmentLength) - 2.0 * radius * SegmentLength * Math.sin(alpha));
                            delta = Math.atan(1.0 / Math.sqrt(1.0 / (Math.pow((SegmentLength * Math.cos(alpha) / radius), 2.0)) - 1.0));
                            coverageAngle = coverageAngle - delta;
                        }
                        if (alpha == STRAIGHT_ANGLE_IN_RADIANS) {
                            radius = radius - SegmentLength;
                            delta = 0.0;
                            coverageAngle = coverageAngle - delta;
                        }
                        alpha = alpha + stepOfCurve;
                        i++;
                    }
                    if ((radius - exitRadius) > STOP_CALCULATING_CRITERIA) {
                        SegmentLength = SegmentLength + calculationStep;
                    } else {
                        SegmentLength = SegmentLength - calculationStep;
                        calculationStep = calculationStep * 0.1;
                    }
                }

                listOfValues.get(9).setText(String.valueOf(beginningCurveRadius * 2.0));
                listOfValues.get(12).setText(String.valueOf(ratioInputOutputAreas));
                listOfValues.get(7).setText(String.valueOf(SegmentLength));
                listOfValues.get(8).setText(String.valueOf(coverageAngle * 180.0 / Math.PI));
                listOfValues.get(10).setText(String.valueOf(2.0 * radius));
                listOfValues1.get(4).setText(String.valueOf(Math.round(exitChannelWidth * 10000.0) / 10000.0));

                double time = (System.currentTimeMillis() - beginTime) / 1000.0;
                listOfValues.get(11).setText(String.valueOf(time));
            }
        });
        btnNewButton.setBounds(400, 385, 120, 20);
        frame.getContentPane().add(btnNewButton);


        JButton btnNewButton1 = new JButton("Export in *.txt");
        btnNewButton1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                final double g = 9.81;
                double beginTime = System.currentTimeMillis();

                double alpha = Double.parseDouble(listOfValues.get(0).getText()) / 180.0 * Math.PI;
                double entranceRadius = Double.parseDouble(listOfValues.get(1).getText()) / 2.0;
                double entranceRoundingRadius = Double.parseDouble(listOfValues.get(3).getText());
                int numberOfCurvePoints = Integer.parseInt(listOfValues.get(5).getText());
                double EntranceChannelWidth = Double.parseDouble(listOfValues.get(6).getText());

                double stepOfCurve = (Double.parseDouble(listOfValues1.get(0).getText()) -
                        Double.parseDouble(listOfValues.get(0).getText())
                ) * Math.PI / (180.0 * (numberOfCurvePoints - 1.0));
                double helperValue1 = (EntranceChannelWidth / 2.0 + entranceRoundingRadius) / (entranceRadius - entranceRoundingRadius) * Math.sin(alpha);
                double helperValue2 = Math.atan(helperValue1 / (Math.sqrt(1.0 - helperValue1 * helperValue1)));
                double beginningCurveRadius = (entranceRadius - entranceRoundingRadius) * Math.sin(alpha - helperValue2) / Math.sin(alpha);

                double SegmentLength = entranceRadius * 0.1;
                double coverageAngle = 0.0;
                entranceRadius = beginningCurveRadius;
                double radius = entranceRadius;
                double delta;
                double stepOfExportPoints = numberOfCurvePoints / (double) MAX_EXPORTING_CURVE_POINTS;
                try (var bufferedWriter = new BufferedWriter(new FileWriter(TEXT_FILE))) {
                    int i = 0;
                    while (i < numberOfCurvePoints) {
                        if (alpha < STRAIGHT_ANGLE_IN_RADIANS) {
                            radius = Math.sqrt((radius * radius) + (SegmentLength * SegmentLength) - 2.0 * radius * SegmentLength * Math.sin(alpha));
                            delta = Math.atan(1.0 / Math.sqrt(1.0 / (Math.pow((SegmentLength * Math.cos(alpha) / radius), 2.0)) - 1.0));
                            coverageAngle = coverageAngle + delta;
                        }
                        if (alpha > STRAIGHT_ANGLE_IN_RADIANS) {
                            radius = Math.sqrt((radius * radius) + (SegmentLength * SegmentLength) - 2.0 * radius * SegmentLength * Math.sin(alpha));
                            delta = Math.atan(1.0 / Math.sqrt(1.0 / (Math.pow((SegmentLength * Math.cos(alpha) / radius), 2.0)) - 1.0));
                            coverageAngle = coverageAngle - delta;
                        }
                        if (alpha == STRAIGHT_ANGLE_IN_RADIANS) {
                            radius = radius - SegmentLength;
                            delta = 0.0;
                            coverageAngle = coverageAngle - delta;
                        }

                        if ((i == 0) || ((i % stepOfExportPoints) == 0) || (i == numberOfCurvePoints - 1)) {
                            String line1 = i + "     " + (radius * Math.sin(coverageAngle)) + " " + (radius * Math.cos(coverageAngle)) + " " + 0.0;
                            bufferedWriter.write(line1);
                            bufferedWriter.newLine();
                        }
                        alpha = alpha + stepOfCurve;
                        i = i + 1;
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }

                double time = (System.currentTimeMillis() - beginTime) / 1000.0;
                listOfValues.get(13).setText(String.valueOf(time));
            }
        });
        btnNewButton1.setBounds(400, 415, 120, 20);
        frame.getContentPane().add(btnNewButton1);
    }
}