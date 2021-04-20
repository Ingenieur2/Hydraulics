package ru.package01;

import java.awt.event.KeyEvent;


public class InputNumberRules {                                    ////Checking Whether the INPUT IS CORRECT
    public static void inputNumberRules(KeyEvent e) {

        char c = e.getKeyChar();

        if (((c == ',') | (c == '.'))) {
            e.setKeyChar('.');
            c = e.getKeyChar();
        }

        if (((c == '0') | (c == '1') | (c == '2') | (c == '3') | (c == '4') | (c == '5') | (c == '6') | (c == '7') | (c == '8') | (c == '9') | (c == '.') ||
                (c == KeyEvent.VK_BACK_SPACE) ||
                (c == KeyEvent.VK_DELETE))) {
        } else {
            e.consume();
        }
    }
}
