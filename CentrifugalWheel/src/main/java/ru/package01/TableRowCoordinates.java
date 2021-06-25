package ru.package01;

public class TableRowCoordinates {
    private int index;
    private double x01;
    private double f1;
    private double x02;
    private double f2;
    private double x_arc;
    private double y_arc;
    private double R_arc;
    private double F;
    private double ux;
    private double vx;

    public TableRowCoordinates() {

    }

    public TableRowCoordinates(int index, double x01, double f1, double x02, double f2, double x_arc, double y_arc, double R_arc, double F, double ux, double vx) {
        this.index = index;
        this.x01 = x01;
        this.f1 = f1;
        this.x02 = x02;
        this.f2 = f2;
        this.x_arc = x_arc;
        this.y_arc = y_arc;
        this.R_arc = R_arc;
        this.F = F;
        this.ux = ux;
        this.vx = vx;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public double getX01() {
        return x01;
    }

    public void setX01(double x01) {
        this.x01 = x01;
    }

    public double getF1() {
        return f1;
    }

    public void setF1(double f1) {
        this.f1 = f1;
    }

    public double getX02() {
        return x02;
    }

    public void setX02(double x02) {
        this.x02 = x02;
    }

    public double getF2() {
        return f2;
    }

    public void setF2(double f2) {
        this.f2 = f2;
    }

    public double getX_arc() {
        return x_arc;
    }

    public void setX_arc(double x_arc) {
        this.x_arc = x_arc;
    }

    public double getY_arc() {
        return y_arc;
    }

    public void setY_arc(double y_arc) {
        this.y_arc = y_arc;
    }

    public double getR_arc() {
        return R_arc;
    }

    public void setR_arc(double r_arc) {
        R_arc = r_arc;
    }

    public double getF() {
        return F;
    }

    public void setF(double f) {
        F = f;
    }

    public double getUx() {
        return ux;
    }

    public void setUx(double ux) {
        this.ux = ux;
    }

    public double getVx() {
        return vx;
    }

    public void setVx(double vx) {
        this.vx = vx;
    }
}
