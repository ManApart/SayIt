package org.iceburg.sayit;

import java.util.HashMap;

import org.iceburg.InstallOperation;

/**
 * To say a single line, use the static sayLineAndEnd method.
 * To say multiple lines / make repeated calls from another class, create a SayIt operation to cache some of the work / reuse the object
 * Then use the cleanup method when you are done with the calls
 * @author Austin
 *
 */
public class VoiceResponse implements VoiceOperation{
	private InstallOperation install;
	private HashMap<String, String> cleanupWords = buildCleanupWords();

	public VoiceResponse(){
		String title ="Say";
		String[] resources = new String[]{
				"say.vbs"
		};
		this.install = new InstallOperation(title, resources, null, null, true);
	}
	
	private HashMap<String, String> buildCleanupWords() {
		cleanupWords = new HashMap<String, String>();
		cleanupWords.put(".lnk", " shortcut");
		
		return cleanupWords;
	}

	@Override
	public boolean meetsCriteria(String command) {
		return (command.startsWith("say"));
	}

	@Override
	public void execute(String command) {
		String text = command.substring("say".length());
		sayLine(text);
	}
	
	public void sayLineAndEnd(String line){
		sayLine(line);
		install.cleanup();
	}
	
	public void sayLine(String line){
//		cscript say.vbs "hello there" (also enclose path in quotes)
		line = cleanupLineForAudio(line);
		String cmd = "cscript \"" +install.getUnpackDirectory()+ "\\say.vbs\" \"" + line + "\"";
		install.runSingleLineCommand(cmd);
	}
	
	
	private String cleanupLineForAudio(String line) {
		for (String find : cleanupWords.keySet()){
			line = line.replace(find, cleanupWords.get(find));
		}
		return line;
	}


	public void cleanup(){
		install.cleanup();
	}
	
	
}
