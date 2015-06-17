package ua.com.hedgehogsoft.metronome;

import javax.sound.midi.MidiChannel;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.MidiUnavailableException;
import javax.sound.midi.Synthesizer;

public class MidiPlayer
{
   private MidiChannel[] channels;

   public MidiPlayer()
   {
      try
      {
         Synthesizer synth = MidiSystem.getSynthesizer();
         synth.open();
         synth.loadAllInstruments(synth.getDefaultSoundbank());
         channels = synth.getChannels();
         channels[0].programChange(0, 115);
      }
      catch (MidiUnavailableException e)
      {
         e.printStackTrace();
      }
   }

   public void noteOn()
   {
      channels[0].noteOn(75, 75);
   }

   public void noteOff()
   {
      channels[0].noteOff(75, 75);
   }
}
