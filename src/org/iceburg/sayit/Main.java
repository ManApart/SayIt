package org.iceburg.sayit;

import java.util.ArrayList;

public class Main {
	private static VoiceResponse voiceResponse = new VoiceResponse();
	
	
	public static void main(String[] args) {
		if (args.length == 0){
			voiceResponse.sayLineAndEnd("No arguments given");
			return;
		}
		String command = cleanCommand(args[0]);
		
		ArrayList<VoiceOperation> operations = collectOperations();
		
		for (VoiceOperation operation : operations){
			if (operation.meetsCriteria(command)){
				operation.execute(command);
				break;
			}
		}
		for (VoiceOperation operation : operations){
			operation.cleanup();
		}
	}

	private static String cleanCommand(String in) {
		String command = in.toLowerCase();
		return command;
	}

	private static ArrayList<VoiceOperation> collectOperations() {
		ArrayList<VoiceOperation> operations = new ArrayList<VoiceOperation>();
		operations.add(voiceResponse);
		operations.add(new OpenFolderOperation());
		return operations;
	}
	
	public static VoiceResponse getVoiceResponse(){
		return voiceResponse;
	}

}
