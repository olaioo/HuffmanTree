package HuffmanTree;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class LerArquivo {
	private String text="";
	public LerArquivo(String s) throws IOException{
		Scanner in = new Scanner(new File(s), "UTF-8");
		while (in.hasNext()) {
			text+=in.nextLine();
		}
	}
	public String getText(){
		return text;
	}
}
