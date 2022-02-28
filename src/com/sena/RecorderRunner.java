package com.sena;

import javax.swing.*;

/**
 * @author: sena
 * E-mail: senaatakosker@gmail.com
 */
public class RecorderRunner {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Recorder recorder = new Recorder();
            recorder.setVisible(true);
        });
    }
}
