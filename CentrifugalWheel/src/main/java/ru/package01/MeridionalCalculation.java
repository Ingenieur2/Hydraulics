package ru.package01;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


public class MeridionalCalculation {

    public static final Map<Integer, TableRowCoordinates> COORDINATES = new ConcurrentHashMap<>();
    private final double R_2;
    private final double b_2;
    private final double R_hub;
    private final double R_1;
    private final double R_a;
    private final double R_b;
    private final double alpha;
    private final double betta;
    private final double step_shroud;
    private final double step_hub;
    private final static double INFINITELY_SMALL_VALUE = 0.0001;


    public MeridionalCalculation(double R_2, double b_2, double R_hub, double R_1, double R_a, double R_b, double alpha, double betta, double step_shroud, double step_hub) {
        this.R_2 = R_2;
        this.b_2 = b_2;
        this.R_hub = R_hub;
        this.R_1 = R_1;
        this.R_a = R_a;
        this.R_b = R_b;
        this.alpha = alpha;
        this.betta = betta;
        this.step_shroud = step_shroud;
        this.step_hub = step_hub;
    }

    public void calc(int currentThread, int threadsCount, double R_2, double b_2, double R_hub, double R_1, double R_a, double R_b, double alpha, double betta, double step_shroud, double step_hub) {

        if (betta == 0.0) {
            betta = INFINITELY_SMALL_VALUE;
        }
        double a1 = R_2 - R_1 - R_a * (1.0 - Math.sin(alpha));
        double y_a = R_1 + R_a;
        double b1 = R_2 - R_hub - R_b * (1.0 - Math.sin(betta));
        double y_b = R_hub + R_b;
        double x_a = R_a * Math.cos(alpha) + b_2 + a1 * (Math.tan(alpha));
        double x_b = R_b * (Math.cos(betta)) + b1 * (Math.tan(betta));
        double a2 = R_a * (1.0 - Math.sin(alpha)) + R_1 + b_2 / (Math.tan(alpha)) + a1;
        double b2 = R_b * (1.0 - Math.sin(betta)) + R_hub + b1;

        if (step_shroud == 0.0) step_shroud = 0.1;
        if (step_hub == 0.0) step_hub = 0.001;
        double countOfStepsShroud = 1.0 / step_shroud;
        double countOfStepsHub = 10.0 / step_hub;
        double x01 = b_2 + 0.00000001;
        double inlet = 0.0;
        if (x_a >= x_b) {
            inlet = x_a;
        } else {
            inlet = x_b;
        }
        int i = 0;

        double S_inlet = (Math.round(Math.PI * (R_1 * R_1 - R_hub * R_hub)));
        double b3 = b_2 + a1 * (Math.tan(alpha));
        double b4 = b1 * (Math.tan(betta));
        double f1 = 0.0, diff1 = 0.0,
                f2 = 0.0, diff2 = 0.0,
                x_R = 0.0, y_R = 0.0, R1 = 0.0, R2 = 0.0, R_dugi = 0.0, x_dugi = 0.0, y_dugi = 0.0, x02_dugi = 0.0, AM = 0.0, lambda = 0.0, fi = 0.0, y_center = 0.0, F = 0.0;
        double omega, omega_0 = 0.0, fx11, ux = 0.0, vx = 0.0;

        int rounding = 1000;
        double x02 = 0.0;

        double threadsCountDouble = threadsCount;
        double currentThreadDouble = currentThread;
        i = (int) (currentThreadDouble / threadsCountDouble * ((inlet - x01) / step_shroud));

        x01 = x01 + currentThreadDouble * (inlet - b_2) / threadsCountDouble;
        double endOfInterval = 0;
        if ((currentThread + 1) == threadsCount) {
            endOfInterval = b_2 + (currentThreadDouble + 1.0) * (inlet - b_2) / threadsCountDouble;
        } else {
            endOfInterval = b_2 + (currentThreadDouble + 1.0) * (inlet - b_2) / threadsCountDouble - step_shroud;
        }

        while (x01 <= endOfInterval) {
            TableRowCoordinates tableRowCoordinates = new TableRowCoordinates();
            tableRowCoordinates.setIndex(i);

            if (x01 <= b_2) f1 = R_2;
            if ((x01 <= b3) && (x01 > b_2)) f1 = (-1.0 * x01) / (Math.tan(alpha)) + a2; // f1(x01)
            if ((x01 <= x_a) && (x01 > b3)) f1 = -1.0 * Math.sqrt(Math.pow(R_a, 2.0) - Math.pow(x01 - x_a, 2.0)) + y_a;
            if (x01 > x_a) f1 = R_1;

            if (x01 <= b_2) diff1 = 0.0;
            if ((x01 <= b3) && (x01 > b_2)) diff1 = -1.0 / (Math.tan(alpha));
            if ((x01 <= x_a) && (x01 > b3))
                diff1 = (2.0 * x01 - 2.0 * x_a) / (2.0 * Math.sqrt(Math.pow(R_a, 2.0) - Math.pow(x01 - x_a, 2.0))); //f1'(x01)
            if (x01 > x_a) diff1 = 0.0;

            while (x02 < inlet) {
                if (x02 <= 0.0) f2 = R_2;
                if ((x02 <= b4) && (x02 > 0.0)) f2 = (-1.0 * x02) / (Math.tan(betta)) + b2;//f2(x02)
                if ((x02 > b4) && (x02 <= x_b))
                    f2 = -1.0 * Math.sqrt(Math.pow(R_b, 2.0) - Math.pow(x02 - x_b, 2.0)) + y_b;
                if (x02 > x_b) f2 = R_hub;
                if (x02 <= 0.0) diff2 = 0.0;
                if ((x02 <= b4) && (x02 > 0.0)) diff2 = -1.0 / (Math.tan(betta));
                if ((x02 > b4) && (x02 <= x_b))
                    diff2 = (2.0 * x02 - 2.0 * x_b) / (2.0 * Math.sqrt(Math.pow(R_b, 2.0) - Math.pow(x02 - x_b, 2.0)));
                if (x02 > x_b) diff2 = 0.0;

                if (Math.abs(diff2 - diff1) > (1.0 / (10.0 * rounding))) {

                    x_R = (-1.0) * (f1 - f2 - diff1 * x01 + diff2 * x02) / (diff1 - diff2);
                    y_R = diff1 * x_R - diff1 * x01 + f1;
                    R1 = Math.sqrt(Math.pow(x_R - x01, 2.0) + Math.pow(y_R - f1, 2.0));
                    R2 = Math.sqrt(Math.pow(x_R - x02, 2.0) + Math.pow(y_R - f2, 2.0));

                    if ((Math.abs(R2 - R1)) <= (1.0 / (rounding))) {
                        R_dugi = (R2 + R1) / 2.0;
                        x_dugi = x_R;
                        y_dugi = y_R;
                        x02_dugi = x02;
                        x02 = inlet;
                    }
                }
                if ((x02 <= b4) && (x02 > 0.0)) {
                    x02 = (x02 + step_hub / rounding);
                } else {
                    x02 = x02 + 10.0 * step_hub / rounding;
                }
            }
            x02 = x02_dugi;
            AM = Math.sqrt(Math.pow(f2 - f1, 2.0) + Math.pow(x02 - x01, 2.0));
            lambda = Math.atan(Math.abs(AM / R_dugi * Math.sqrt(1.0 - Math.pow(AM / (2.0 * R_dugi), 2.0)) / (1.0 - 0.5 * Math.pow(AM / R_dugi, 2.0))));

            if (x_dugi < x01) {
                fi = Math.atan(Math.abs((x02 - x_dugi) / (y_dugi - f2)));
                y_center = y_dugi - 2.0 * R_dugi * Math.sin(0.5 * lambda) * Math.cos(fi + 0.5 * lambda) / lambda;
                F = 2.0 * Math.PI * R_dugi * lambda * y_center;
            }
            if (x_dugi > x01) {
                fi = Math.atan(Math.abs((f2 - y_dugi) / (x02 - x_dugi)));
                y_center = y_dugi + 2.0 * R_dugi * Math.sin(0.5 * lambda) * Math.sin(fi + 0.5 * lambda) / lambda;
                F = 2.0 * Math.PI * R_dugi * lambda * y_center;
            }
            // Calculation of central line
            double F_05 = F / 2.0;
            if (x_dugi <= x01) {
                fi = Math.atan(Math.abs((x_dugi - x02) / (f2 - y_dugi)));
                fx11 = (F_05 - 2.0 * Math.PI * R_dugi * (R_dugi * (Math.sin(fi) - Math.sin(omega_0 + fi)) + omega_0 * y_dugi)) / (2.0 * Math.PI * R_dugi * (R_dugi * Math.cos(omega_0 + fi) - y_dugi));
                omega = omega_0 - fx11;

                while ((Math.abs(omega - omega_0)) >= 0.0000001) {
                    omega_0 = omega;
                    fx11 = (F_05 - 2.0 * Math.PI * R_dugi * (R_dugi * (Math.sin(fi) - Math.sin(omega_0 + fi)) + omega_0 * y_dugi)) / (2.0 * Math.PI * R_dugi * (R_dugi * Math.cos(omega_0 + fi) - y_dugi));
                    omega = omega_0 - fx11;
                }

                ux = (x_dugi + R_dugi * Math.sin(omega + fi));
                vx = (y_dugi - R_dugi * Math.cos(omega + fi));
            }
            if (x_dugi > x01) {
                fi = Math.atan(Math.abs((f2 - y_dugi) / (x_dugi - x02)));
                fx11 = (-1.0 * (F_05 - 2.0 * Math.PI * R_dugi * (R_dugi * (Math.cos(fi) - Math.cos(omega_0 + fi)) + omega_0 * y_dugi)) / (2.0 * Math.PI * R_dugi * (R_dugi * Math.sin(omega_0 + fi) + y_dugi)));
                omega = omega_0 - fx11;

                while ((Math.abs(omega_0 - omega)) > 0.0000001) {
                    omega_0 = omega;
                    fx11 = (-1.0 * (F_05 - 2.0 * Math.PI * R_dugi * (R_dugi * (Math.cos(fi) - Math.cos(omega_0 + fi)) + omega_0 * y_dugi)) / (2.0 * Math.PI * R_dugi * (R_dugi * Math.sin(omega_0 + fi) + y_dugi)));
                    omega = omega_0 - fx11;
                }
                ux = (x_dugi - R_dugi * Math.cos(omega + fi));
                vx = (y_dugi + R_dugi * Math.sin(omega + fi));
            }

            tableRowCoordinates.setX01(Math.round(x01 * countOfStepsShroud) / countOfStepsShroud);
            tableRowCoordinates.setF1(Math.round(f1 * countOfStepsHub) / countOfStepsHub);
            tableRowCoordinates.setX02(Math.round(x02 * countOfStepsHub) / countOfStepsHub);
            tableRowCoordinates.setF2(Math.round(f2 * countOfStepsHub) / countOfStepsHub);
            tableRowCoordinates.setX_arc(Math.round(x_dugi * countOfStepsHub) / countOfStepsHub);
            tableRowCoordinates.setY_arc(Math.round(y_dugi * countOfStepsHub) / countOfStepsHub);
            tableRowCoordinates.setR_arc(Math.round(R_dugi * countOfStepsHub) / countOfStepsHub);
            tableRowCoordinates.setF(Math.round(F * countOfStepsShroud) / countOfStepsShroud);
            tableRowCoordinates.setUx(Math.round(ux * 10000.0) / 10000.0);
            tableRowCoordinates.setVx(Math.round(vx * 10000.0) / 10000.0);
            COORDINATES.put(i, tableRowCoordinates);
            x01 = x01 + step_shroud;
            i = i + 1;
        }
    }
}