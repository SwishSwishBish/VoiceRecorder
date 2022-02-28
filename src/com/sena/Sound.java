package com.sena;

import java.io.File;
import java.io.IOException;
import javax.sound.sampled.*;
import javax.swing.*;

public class Sound extends Thread {
    File fileName;
    AudioFileFormat.Type fileType;
    TargetDataLine line;
    JPanel recorderPanel;

    public void file(String name, JPanel recorderPanel) {
        fileName = new File(name + ".mp3");
        this.recorderPanel = recorderPanel;
    }

    public void run() {
        try {
            fileType = AudioFileFormat.Type.WAVE;
            AudioFormat format = new AudioFormat(AudioFormat.Encoding.PCM_SIGNED,44100, 16, 2, 4, 44100, false);
            DataLine.Info info = new DataLine.Info(TargetDataLine.class, format);
            if (!AudioSystem.isLineSupported(info)) {
                JOptionPane.showMessageDialog(recorderPanel, "Line not support");
            }
            line = (TargetDataLine) AudioSystem.getLine(info);
            line.open(format);
            line.start();
            AudioInputStream ais = new AudioInputStream(line);
            AudioSystem.write(ais, fileType, fileName);
        } catch (LineUnavailableException | IOException ignored) {

        }
    }


    public void stopRec() {
        line.stop();
        line.close();
    }


}