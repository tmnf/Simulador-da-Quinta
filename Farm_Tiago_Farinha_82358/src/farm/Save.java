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
			Window.aviso("Jogo Salvo");
		} catch (IOException e) {
			Window.aviso("Erros na gravação do ficheiro!");
		}
	}

	public static Farm loadGame(String filename) {
		Farm farm = null;
		File save = new File(filename);
		try {
			FileInputStream saveGame = new FileInputStream(save);
			ObjectInputStream objInput = new ObjectInputStream(saveGame);
			farm = (Farm) objInput.readObject();
			objInput.close();
			saveGame.close();
		} catch (IOException e) {
			Window.aviso("Erro na leitura do ficheiro!");
		} catch (ClassNotFoundException e) {
			Window.aviso("Erro na leitura do ficheiro!");
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
			Window.aviso("Ficheiro não encontrado.");
		}
		return info;
	}

}
