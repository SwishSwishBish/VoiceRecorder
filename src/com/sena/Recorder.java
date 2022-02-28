package com.sena;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.*;

public class Recorder extends JFrame {
    static ImageIcon icon= new ImageIcon("src/com/sena/images/mic.png");
    private JPanel recorderPanel;
    private JButton recordButton;
    private JButton stopButton;
    private JTextField pathFile;
    private JButton selectButton;
    private JPanel tittleBar;
    private JButton exitButton;
    private JLabel Icon;
    private JButton minimizeButton;
    boolean isRestart= false;
    JFileChooser selectFolder;
    String folderPath;
    Sound mySound;
    int select = 0;
    int x, y;

    public void select() {
        selectFolder = new JFileChooser("J:\\");
        selectFolder.setAcceptAllFileFilterUsed(false);
        selectFolder.setDialogTitle("Save File");
        FileNameExtensionFilter restrict = new FileNameExtensionFilter("Audio Format mp3", "mp3");
        selectFolder.addChoosableFileFilter(restrict);
        select = selectFolder.showSaveDialog(null);
        try {
            if (select == JFileChooser.APPROVE_OPTION) {
                folderPath = selectFolder.getSelectedFile().getAbsolutePath();
                pathFile.setText(folderPath + ".mp3");
                recordButton.setEnabled(true);
                select = 1;

                mySound = new Sound();
                mySound.file(folderPath, recorderPanel);
            }
        } catch (Exception ignored) {
        }
    }

    public Recorder() {
        add(recorderPanel);
        setTitle("Voice Recorder");
        setIconImage(icon.getImage());
        setSize(430, 150);
        pathFile.setEditable(false);
        pathFile.setText("Recording location");
        setUndecorated(true);
        recordButton.setEnabled(false);
        stopButton.setEnabled(false);
        setOpacity(0.9f);



        exitButton.addActionListener(e -> Recorder.super.dispose());

        minimizeButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Recorder.super.setState(ICONIFIED);
            }
        });

        tittleBar.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                x = e.getX();
                y = e.getY();
            }
        });

        tittleBar.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                int xx = e.getXOnScreen();
                int yy = e.getYOnScreen();
                Recorder.super.setLocation(xx - x, yy - y);
            }
        });

        selectButton.addActionListener(e -> select());

        recordButton.addActionListener(e -> {
            if (select > 0) {
                mySound.start();
                recordButton.setEnabled(false);
                stopButton.setEnabled(true);
            } else {
                Toolkit.getDefaultToolkit().beep();
                JOptionPane.showMessageDialog(recorderPanel, "Please Select File Name");
            }
        });
        stopButton.addActionListener(e -> {
            mySound.stopRec();
            stopButton.setEnabled(false);
            select = 0;
            isRestart = true;
        });

    }
}