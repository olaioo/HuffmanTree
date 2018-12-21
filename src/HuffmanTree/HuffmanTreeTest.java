package HuffmanTree;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.Scanner;

public class HuffmanTreeTest {

	private static String text;
	private static String[] table;
	public static String dir;
	private static HuffmanTree ht;
	
	public static void main(String[] args) {
		Scanner in=new Scanner(System.in);
		PrintWriter writer;
		int choice;
		System.out.print("Escolha uma op��o abaixo:\n1-Comprimir\n2-Descomprimir\n0-Sair\n> ");
		choice=in.nextInt();
		in.nextLine();
		
		while(choice!=0){
			switch(choice){
				case 1:
					System.out.print("Digite o caminho do arquivo que deseja comprimir: ");
					try {
						dir=in.nextLine();
						text=new LerArquivo(dir).getText();
						ht=new HuffmanTree(text);
						System.out.println("Arvore gerada:");
						ht.printTree();
						System.out.println();
						System.out.println("Compressão realizada com sucesso, os seguintes arquivos foram gerados:\n"+HuffmanTreeTest.dir+"Compress"+"\n"+HuffmanTreeTest.dir+"CompressTable"+"\n");
					} catch (FileNotFoundException e2){
						System.out.println("Esse arquivon�o existe, deseja criar? [S/N]");
						if(in.nextLine().startsWith("s")){
							try {
								writer = new PrintWriter(dir, "UTF-8");
								System.out.println("Digite o conte�do do arquivo: ");
								text=in.nextLine();
								writer.print(text);
								writer.close();
							} catch (FileNotFoundException | UnsupportedEncodingException e) {
								System.out.println("Imposs�vel criar arquivo!");
							}
						}
					} catch (IOException e) {
						System.out.println("Imposs�vel ler arquivo!");
					}
					break;
				case 2:
					System.out.print("Digite o caminho do arquivo que deseja descomprimir: ");
					try {
						dir=in.nextLine();
						text=new LerArquivo(dir).getText();
						String aux2="";
						Scanner in2 = new Scanner(new File(dir+"Table"), "UTF-8");
						while (in2.hasNext()) {
							aux2+=in2.nextLine()+"\n";
						}
						table=aux2.split("\n");
						ht=new HuffmanTree(table,text);
						ht.printTree();
						System.out.println();
						System.out.println("Descompressão realizada com sucesso, os seguintes arquivos foram gerados:\n"+HuffmanTreeTest.dir.replace("Compress", "")+"\n");
					} catch (IOException e) {
						System.out.println("Imposs�vel ler arquivo!");
					}
					break;
				case 0:
					break;
				default:
					System.out.println("Op��o inv�lida!");
			}
			System.out.print("Escolha uma op��o abaixo:\n1-Comprimir\n2-Descomprimir\n0-Sair\n> ");
			choice=in.nextInt();
			in.nextLine();
		}
	}
}
