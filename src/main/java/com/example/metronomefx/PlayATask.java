/* TODO: - fix playA */
package com.example.metronomefx;

import javax.sound.midi.MidiChannel;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.Synthesizer;
import java.awt.*;
import java.util.Arrays;
import java.util.List;
import java.util.TimerTask;

public class PlayATask extends TimerTask {
    Synthesizer synth;
    private static List<String> notes = Arrays.asList("C", "C#", "D", "D#", "E", "F", "F#", "G", "G#", "A", "A#", "B");
    private static MidiChannel[] channels;
    private static int INSTRUMENT = 10;
    private static int VOLUME = 120;


    @Override
    public void run() {
        try {

            synth = MidiSystem.getSynthesizer();
            synth.open();
            channels = synth.getChannels();

            playAPlay("4A", 1);

        } catch (Exception e) {

        throw new RuntimeException(e);

        }
    }

    private static void playAPlay(String note, int duration) throws InterruptedException {
        channels[INSTRUMENT].noteOn(id(note), VOLUME);
        Thread.sleep(duration);

    }



    private static int id(String note) {
        int octave = Integer.parseInt(note.substring(0,1));
        return notes.indexOf(note.substring(1)) + 12 * octave + 12;
    }

}
