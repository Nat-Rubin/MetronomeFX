package com.example.metronomefx;

import java.awt.*;
import java.util.TimerTask;

public class Beep extends TimerTask {

    @Override
    public void run() {

        Toolkit.getDefaultToolkit().beep();

    }
}
