package org.iceburg.sayit;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class OpenFolderOperation implements VoiceOperation{

	@Override
	public boolean meetsCriteria(String command) {
		return (command.startsWith("open") && command.endsWith("folder"));
	}

	@Override
	public void execute(String command) {
		String folderName = getFolderName(command);
		ArrayList<File> foundFolders = new ArrayList<File>();
		
		for (File parentLocation : getSearchLocations()){
			checkSubFolders(parentLocation, folderName, foundFolders);
		}
		
		openOrSuggestFolders(foundFolders, folderName);
	}

	private String getFolderName(String command){
		String folder = command.substring(0, command.indexOf("folder"));
		folder = folder.substring("open".length());
		folder = folder.trim();
		System.out.println(folder);
		return folder;
	}
	

	private ArrayList<File> getSearchLocations() {
		ArrayList<File> locations = new ArrayList<File>();
		locations.add(new File("A:\\Games\\Default"));
		locations.add(new File("A:\\Games\\Steam Library\\steamapps\\common"));
		locations.add(new File("C:\\Users\\Austin\\Links"));
		locations.add(new File("D:\\Quick Games\\Quick Steam Library\\steamapps\\common"));
		return locations;
	}
	
	private void checkSubFolders(File parentLocation, String folderName, ArrayList<File> foundFolders) {
		if (!parentLocation.exists() || !parentLocation.isDirectory()){
			System.out.println("Skipping " +parentLocation.getAbsolutePath());
			return;
		}
		for (File folder : parentLocation.listFiles()){
			if (folder.getName().toLowerCase().contains(folderName)){
				System.out.println("found " + folder.getName());
				foundFolders.add(folder);
			}
		}
		
	}
	
	private void openOrSuggestFolders(ArrayList<File> foundFolders, String searchedForFolder) {
		if (foundFolders.size() == 1){
			openFile(foundFolders.get(0));
			return;
		} else if (foundFolders.size() == 0){
			String message = "I couldn't find a " +searchedForFolder + " folder.";
			Main.getVoiceResponse().sayLine(message);
			return;
		}
		
		String message = "I found " + foundFolders.size() +" folders: ";
		for (int i=0; i<foundFolders.size(); i++){
			File folder = foundFolders.get(i);
			if (i == foundFolders.size()-1){
				message += "and " + folder.getName() + " in " + folder.getParentFile().getName();
			} else {
				message += folder.getName() + " in " + folder.getParentFile().getName() + ", ";
			}
		}
		Main.getVoiceResponse().sayLine(message);
		
	}

	
	private void openFile(File file){
		Desktop desktop = Desktop.getDesktop();
		try {
			desktop.open(file);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void cleanup() {
	}

}
