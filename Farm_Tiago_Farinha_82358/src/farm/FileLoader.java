package farm;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Scanner;

public class FileLoader {

	public static void saveGame(String filename) {
		try {
			ObjectOutputStream objOutput = new ObjectOutputStream(new FileOutputStream(filename));
			objOutput.writeObject(Farm.getInstance());
			objOutput.close();
			Window.aviso("Jogo Salvo");
		} catch (IOException e) {
			Window.aviso("Erros na gravação do ficheiro!");
		}
	}

	public static Farm loadGame(String filename) {
		Farm farm = null;
		try {
			ObjectInputStream objInput = new ObjectInputStream(new FileInputStream(filename));
			farm = (Farm) objInput.readObject();
			objInput.close();
		} catch (IOException e) {
			Window.aviso("Erro na leitura do ficheiro!");
		} catch (ClassNotFoundException e) {
			Window.aviso("Erro na leitura do ficheiro!");
		}
		return farm;
	}

	public static String[] readTextFile(String s) {
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
