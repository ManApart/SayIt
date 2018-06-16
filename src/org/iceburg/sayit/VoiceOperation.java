package org.iceburg.sayit;

public interface VoiceOperation {
	
	
	public boolean meetsCriteria(String command);
	public void execute(String command);
	public void cleanup();

}
