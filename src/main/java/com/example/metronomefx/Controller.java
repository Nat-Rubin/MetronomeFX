package com.example.metronomefx;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

import javax.sound.midi.MidiUnavailableException;

public class Controller {
    @FXML
    public Label tempo;
    public Metronome metronome;

    public Controller() {
        metronome = new Metronome();
        //displayTempo();

    }

    @FXML
    private Label metronomeLabel;
    @FXML
    private Button playPauseButton;
    @FXML
    private Button increaseTempoButton;
    @FXML
    private Button decreaseTempoButton;
    @FXML
    private Button playA;

    @FXML
    private Slider tempoSlider;

    @FXML
    protected void playOrPause() {
        if (metronome.isPlayingA) return;
        metronome.playOrPause();
    }

    @FXML
    protected void increaseTempo() {
        if (metronome.isPlayingA) return;
        metronome.increaseTempo();
        displayTempo();

    }

    @FXML
    protected void decreaseTempo() {
        if (metronome.isPlayingA) return;
        metronome.decreaseTempo();
        displayTempo();

    }

    @FXML
    protected void setTempo() {
        if (metronome.isPlayingA) return;
        // DISABLE SLIDER IF PLAYING A
        int sliderValue = (int) tempoSlider.getValue();
        metronome.changeTempo(sliderValue);
        displayTempo();

    }

    @FXML
    public void displayTempo() {
        tempo.setText(Integer.toString(metronome.tempo));

    }

    @FXML
    public void playA() throws InterruptedException, MidiUnavailableException {
        metronome.isPlayingA = !metronome.isPlayingA;
        metronome.playA();

    }

}