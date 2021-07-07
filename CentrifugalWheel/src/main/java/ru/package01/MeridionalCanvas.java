package ru.package01;

import java.awt.*;

import static ru.package01.MeridionalCalculation.COORDINATES;
import static ru.package01.Window01.zoomOfMeridional;

public class MeridionalCanvas extends Canvas {

    MeridionalCanvas() {

    }

    @Override
    public void paint(Graphics g) {
        double[][] MeridionalShroudLineArray = new double[COORDINATES.size()][2];
        double[][] MeridionalHubLineArray = new double[COORDINATES.size()][2];
        double[][] MeridionalMediumLineArray = new double[COORDINATES.size()][2];
        for (int i = 0; i < COORDINATES.size() - 1; i++) {
            MeridionalShroudLineArray[i][0] = zoomOfMeridional * COORDINATES.get(i).getX01();
            MeridionalShroudLineArray[i][1] = zoomOfMeridional * COORDINATES.get(i).getF1();
            MeridionalHubLineArray[i][0] = zoomOfMeridional * COORDINATES.get(i).getX02();
            MeridionalHubLineArray[i][1] = zoomOfMeridional * COORDINATES.get(i).getF2();
            MeridionalMediumLineArray[i][0] = zoomOfMeridional * COORDINATES.get(i).getUx();
            MeridionalMediumLineArray[i][1] = zoomOfMeridional * COORDINATES.get(i).getVx();
        }

        int x0 = (int) (1.8 * MeridionalShroudLineArray[MeridionalShroudLineArray.length - 2][0]);
        int y0 = (int) (1.1 * MeridionalShroudLineArray[0][1]);
        g.clearRect(0, 0, x0, y0);

        g.setColor(Color.GREEN);
        for (int i = 0; i < MeridionalShroudLineArray.length - 2; i++) {
            g.drawLine(x0 - (int) MeridionalShroudLineArray[i][0], y0 - (int) MeridionalShroudLineArray[i][1], x0 - (int) MeridionalShroudLineArray[i + 1][0], y0 - (int) MeridionalShroudLineArray[i + 1][1]);
        }

        g.setColor(Color.ORANGE);
        for (int i = 0; i < MeridionalShroudLineArray.length - 2; i++) {
            g.drawLine(x0 - (int) MeridionalHubLineArray[i][0], y0 - (int) MeridionalHubLineArray[i][1], x0 - (int) MeridionalHubLineArray[i + 1][0], y0 - (int) MeridionalHubLineArray[i + 1][1]);
        }

        g.setColor(Color.BLUE);
        for (int i = 0; i < MeridionalShroudLineArray.length - 2; i++) {
            g.drawLine(x0 - (int) MeridionalMediumLineArray[i][0], y0 - (int) MeridionalMediumLineArray[i][1], x0 - (int) MeridionalMediumLineArray[i + 1][0], y0 - (int) MeridionalMediumLineArray[i + 1][1]);
        }
        g.setColor(Color.BLACK);

        g.drawLine(0, y0, x0 + 10, y0);
        g.drawLine(x0 + 10, 0, x0 + 10, y0);
    }
}
