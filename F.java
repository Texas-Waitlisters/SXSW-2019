import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;

import javax.sound.midi.*;

public class F {
	public static void main(String[] args) throws MidiUnavailableException, InvalidMidiDataException, IOException {
		//so uhhh i want to read in a midi file
		//then i need to make those notes light up on the keyboard
		//simple enough right?
		//haha jk
		//oh wait it also needs to wait for the user to press those keys before proceeding
		//should it also emit the notes before the user plays them?
		
		//also need to find music thats within a limited range		
		
		Vector synthInfos = new Vector();
		MidiDevice device = null;
		MidiDevice keyboard = null;
		MidiDevice.Info[] infos = MidiSystem.getMidiDeviceInfo();
		Sequencer sequencer;
		Receiver receiver;
		Transmitter transmitter = MidiSystem.getTransmitter();
		InputStream stream = new FileInputStream(new File("src/minuet.mid"));
		//new Sequence(Sequence.SMPTE_24, 0, 1);
		
		//finds available devices
		System.out.println(infos.length);
		for (int i = 0; i < infos.length; i++) {
		    try {
		        device = MidiSystem.getMidiDevice(infos[i]);
		    } catch (MidiUnavailableException e) {
		          // Handle or throw exception...
		    }
		    if (device != null) 
		    {
			        synthInfos.add(infos[i]);
			        System.out.println(infos[i].getName());
			        System.out.println(infos[i].getVendor());
			        System.out.println(infos[i].getDescription());
			        System.out.println("number of transmitters " + device.getMaxTransmitters());
			        System.out.println("number of receivers " + device.getMaxReceivers());	
			        System.out.println();
		    }
		}
		keyboard = MidiSystem.getMidiDevice(infos[7]);
		sequencer = MidiSystem.getSequencer();
		sequencer.setSequence(stream);
		receiver = keyboard.getReceiver();
		sequencer.getTransmitter().setReceiver(receiver);
		Track track = sequencer.getSequence().getTracks()[0];		
		int trackSize = track.size();
		sequencer.open();
		int i = 0;
		while (i < trackSize)
		{
			MidiEvent midiEvent = track.get(i);
			MidiMessage midiMessage = midiEvent.getMessage();
			receiver.send(midiMessage, -1);
			i++;
		}
		sequencer.close();
		
	}
}
