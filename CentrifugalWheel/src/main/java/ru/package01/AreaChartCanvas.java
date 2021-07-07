package ru.package01;

import java.awt.*;

import static ru.package01.MeridionalCalculation.COORDINATES;
import static ru.package01.Window01.zoomOfAreaChartX;
import static ru.package01.Window01.zoomOfAreaChartY;

public class AreaChartCanvas extends Canvas {

    AreaChartCanvas() {

    }

    @Override
    public void paint(Graphics g) {
        int[][] MeridionalAreaChartArray = new int[COORDINATES.size()][2];
        double len = 0;
        int maxValueOfArea = 0;
        for (int i = 0; i < COORDINATES.size(); i++) {
            MeridionalAreaChartArray[i][0] = (int) (zoomOfAreaChartX * len);
            MeridionalAreaChartArray[i][1] = (int) (zoomOfAreaChartY * COORDINATES.get(i).getF());
            if (i != COORDINATES.size() - 1) {
                len = len + Math.sqrt(Math.pow((COORDINATES.get(i).getUx() - COORDINATES.get(i + 1).getUx()), 2)
                        + Math.pow((COORDINATES.get(i).getVx() - COORDINATES.get(i + 1).getVx()), 2));
            }
            if (maxValueOfArea < MeridionalAreaChartArray[i][1]) {
                maxValueOfArea = MeridionalAreaChartArray[i][1];
            }
        }

        int x0 = MeridionalAreaChartArray[MeridionalAreaChartArray.length - 2][0];
        int y0 = maxValueOfArea;

        g.setColor(Color.GREEN);
        for (int i = 0; i < MeridionalAreaChartArray.length - 2; i++) {
            g.drawLine(x0 - MeridionalAreaChartArray[i][0], y0 - MeridionalAreaChartArray[i][1], x0 - MeridionalAreaChartArray[i + 1][0], y0 - MeridionalAreaChartArray[i + 1][1]);
        }
        g.setColor(Color.BLACK);
        g.drawLine(0, maxValueOfArea, x0, maxValueOfArea);

        g.drawLine(0, y0 - MeridionalAreaChartArray[MeridionalAreaChartArray.length - 1][1], x0, y0 - MeridionalAreaChartArray[0][1]);
        g.drawLine(0, 0, 0, y0);
    }
}
