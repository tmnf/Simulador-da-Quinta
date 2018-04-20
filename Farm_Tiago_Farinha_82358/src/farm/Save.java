package farm;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Scanner;

public class Save {

	public static void saveGame(String filename) {
		File save = new File(filename);
		try {
			FileOutputStream saveGame = new FileOutputStream(save);
			ObjectOutputStream objOutput = new ObjectOutputStream(saveGame);

			objOutput.writeObject(Farm.getInstance());
			objOutput.close();
			saveGame.close();
			System.out.println("Jogo salvo com sucesso.");
		} catch (FileNotFoundException e) {
			System.out.println("Ficheiro nao encontrado.");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static Farm loadGame(String filename) {
		Farm farm = null;
		File save = new File(filename);
		try {
			FileInputStream saveGame = new FileInputStream(save);
			ObjectInputStream objInput = new ObjectInputStream(saveGame);
			// list = (ArrayList<FarmObject>) objInput.readObject();
			farm = (Farm) objInput.readObject();
			objInput.close();
			saveGame.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return farm;
	}

	public static String[] readFile(String s) {
		String[] info = new String[100];
		try {
			Scanner fileScanner = new Scanner(new File(s));
			int i = 0;
			while (fileScanner.hasNext()) {
				info[i] = fileScanner.next();
				i++;
			}
			fileScanner.close();
		} catch (FileNotFoundException e) {
			System.out.println("Ficheiro não encontrado.");
		}
		return info;
	}

}
