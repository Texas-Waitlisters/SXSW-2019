import java.util.*;

import javax.sound.midi.*;

public class F {
	public static void main(String[] args) throws MidiUnavailableException {
		//so uhhh i want to read in a midi file
		//then i need to make those notes light up on the keyboard
		//simple enough right?
		//haha jk
		//oh wait it also needs to wait for the user to press those keys before proceeding
		//should it also emit the notes before the user plays them?
		
		//also need to find music thats within a limited range
		
		//finds available Sequencer devices
		Vector synthInfos = new Vector();
		MidiDevice device = null;
		MidiDevice keyboard = null;
		MidiDevice.Info[] infos = MidiSystem.getMidiDeviceInfo();
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
		keyboard = MidiSystem.getMidiDevice(infos[4]);
		
	}
}
