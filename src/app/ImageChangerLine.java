package app;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class ImageChangerLine {
	
	public static void main(String[] args) throws IOException {
		
		String dirPath = "C:/obsidian-notes/Facultad/100 - XXX Facultad/Redes De Computadoras/";
		String destinatinPath = "C:\\Users\\USUARIO\\Desktop\\Cosas pc Viejo\\TPI\\CopyObsidianFiles" + "\\";
		String originPath = "C:/obsidian-notes/Facultad/100 - XXX Facultad/Redes De Computadoras/";
		

		File dir = new File(dirPath);
		
		File[] files = dir.listFiles();
		List<String >fileNames = new ArrayList<>();
		fileNames.addAll(fileNames(files));

		System.out.println(fileNames);
		
		for(String name : fileNames) {
			File fileOriginal = new File(originPath + name);
			File fileUpdated = new File(destinatinPath + name);

			FileReader reader = new FileReader(fileOriginal);
			FileWriter writer = new FileWriter(fileUpdated);
			
			BufferedReader fileReader = new BufferedReader(reader);
			String line;
			while((line = fileReader.readLine()) != null) {
				String data = line;
				if(data.startsWith("!")) {
					writer.write(changeImgLine(data)+ "\n\n");
				} else {
					writer.write(data + "\n\n");
				}
			}
			System.out.println("Listo: " + name);
			writer.close();
			reader.close();
		}

	}
	


	private static List<String>fileNames(File[] files) {
		List<String> fileNames = new ArrayList<>();
		for(File f : files) {
			if(f.isFile()) {
				fileNames.add(f.getName());
			}
		}
		return fileNames;
	}



	public static String changeImgLine(String data) {
		String imgName;
		if(data.contains("|")) {
			imgName = String.valueOf(data.subSequence(3, data.length()-6));
		} else {
			imgName =  String.valueOf(data.subSequence(3, data.length()-2));;
		}
		String imgNameNoSpace = imgName.replace(" ", "%20");
		return "![" + imgName.replace(".png", "") + "](Images/" + imgNameNoSpace + ")";
	}
}
