package com.example.metronomefx;

import java.util.Arrays;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import javax.sound.midi.*;

public class Metronome {
    int tempo = 60;
    int volume = 50;
    int ms;
    Timer timer = new Timer();
    TimerTask task = null;
    Synthesizer synth;
    private static List<String> notes = Arrays.asList("C", "C#", "D", "D#", "E", "F", "F#", "G", "G#", "A", "A#", "B");
    private static MidiChannel[] channels;
    private static int INSTRUMENT = 10;
    private static int VOLUME = 120;
    TimerTask taskA = null;
    boolean isPlayingA = false;

    public Metronome() {
        changeTempo();
        playOrPause();

    }

    private void changeTempo() {
        double period = 1/(tempo/60.0);
        period *= 1000;
        ms = (int) Math.round(period);

    }

    public void changeTempo(int newTempo) {
        this.tempo = newTempo;
        double period = 1/(tempo/60.0);
        period *= 1000;
        ms = (int) Math.round(period);
        if (task != null) play();

    }

    public void increaseTempo() {
        if (tempo < 300) tempo++;
        changeTempo();
        if (task != null) play();

    }

    public void decreaseTempo() {
        if (tempo > 30) tempo--;
        changeTempo();
        if (task != null) play();

    }

    public void playOrPause() {

        if (task == null) {
            task = new Beep();
            timer.schedule(task, 0, ms);

        } else {

            task.cancel();
            System.out.println("Here");
            task = null;

        }

    }

    private void play() {
        task.cancel();
        task = new Beep();
        timer.schedule(task, 0, ms);

    }

    public void changeVolume() {
        System.out.println("Vol changed");

    }

    public void playA() throws InterruptedException, MidiUnavailableException {
        System.out.println("Playing A");

        synth = MidiSystem.getSynthesizer();
        channels = synth.getChannels();

        if (task != null) {
            task.cancel();
            task = null;
        }

        if (isPlayingA) {

            taskA = new PlayATask();
            timer.schedule(taskA, 0, ms);

        } else {

            rest(1);
            channels[INSTRUMENT].noteOff(id("4A"));
            taskA.cancel();
            taskA = null;

        }

    }

    private static void rest(int duration) throws InterruptedException {
        Thread.sleep(duration);
    }

    private static int id(String note) {
        int octave = Integer.parseInt(note.substring(0,1));
        return notes.indexOf(note.substring(1)) + 12 * octave + 12;
    }

}
