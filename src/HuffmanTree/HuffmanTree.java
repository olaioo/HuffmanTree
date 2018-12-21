package HuffmanTree;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Stack;
import java.util.TreeSet;

public class HuffmanTree {
	Random rand=new Random();
	TreeSet<Data> ts=new TreeSet<Data>();
	Stack<String> p=new Stack<String>();
	ArrayList<Character> textR=new ArrayList<Character>();
	ArrayList<String> table=new ArrayList<String>();
	String aux="";
	Data root,d1,d2,d3;
	
	HuffmanTree(String text){
		charCounter(text);
		buildTree();
		buildTable();
		writeCode(text);
		//recover(writeCode(text));
	}
	
	HuffmanTree(String[] table,String text){
		ts.clear();
		String[] aux=new String[3];
		for(String s:table){
			aux=s.split(";");
			ts.add(new Data(aux[0].charAt(0),Integer.parseInt(aux[1])));
		}
		buildTree();
		System.out.println("Code: "+text);
		recover(text);
	}
	
	public String writeCode(String text){
		System.out.println("Text: "+text);
		String code="";
		for(int i=0;i<text.length();i++){
			for(String s:table){
				if(s.split(";")[0].contains(""+text.charAt(i))){
					code+=s.split(";")[2];
				}
			}
		}
		PrintWriter writer;
		try {
			writer = new PrintWriter(HuffmanTreeTest.dir+"Compress", "UTF-8");
			writer.print(code);
			writer.close();
		} catch (FileNotFoundException | UnsupportedEncodingException e) {
			System.out.println("Imposs�vel criar arquivo!");
		}
		return code;
	}
	
	public void buildTree(){
		Data aux[]=new Data[3];
		while(ts.size()>1){
			aux[0]=ts.pollFirst();
			aux[1]=ts.pollFirst();
			aux[2]=new Data(aux[0].freq+aux[1].freq);
			aux[2].left=aux[0];
			aux[2].right=aux[1];
			ts.add(aux[2]);
		}
		root=aux[2];
	}
	
	public void charCounter(String text){
		ArrayList<String> toSend=new ArrayList<String>();
		for(int i=0;i<text.length();i++){
			toSend.add(""+text.charAt(i));
		}
		while (toSend.size() != 0) {
			int count = 0;
			String nome = toSend.get(0);
			for (int i = 0; i < toSend.size();) {
				if (nome.equals(toSend.get(i))) {
					count++;
					toSend.remove(i);
				} else {
					i++;
				}
			}
			ts.add(new Data(nome.charAt(0),count));	
		} 
	}
	
	public void printTree(){
		printAsTree(root, 0);
	}
	
	private void printAsTree(Data root, int nivel){
		String res="";
	    if(root==null)
	         return;
	    printAsTree(root.right, nivel+1);
	    if(nivel!=0){
	        for(int i=0;i<nivel-1;i++)
	            System.out.print("|\t");
	            System.out.printf("|-------%s\n",(root.noShow)?""+root.freq:""+root.info);
	    }
	    else
	        System.out.println((root.noShow)?root.freq:root.info);
	    printAsTree(root.left, nivel+1);
	}  
	
	public void buildTable(){
		p.clear();
		table.clear();
		buildTable2(root);
		PrintWriter writer;
		try {
			writer = new PrintWriter(HuffmanTreeTest.dir+"CompressTable", "UTF-8");
			for(String s:table){
				writer.print(s.split(";")[0]+";"+s.split(";")[1]+"\n");
			}
			writer.close();
		} catch (FileNotFoundException | UnsupportedEncodingException e) {
			System.out.println("Imposs�vel criar arquivo!");
		}
	}
	
	private void buildTable2(Data root){
		if(!root.noShow){
			aux=String.format("%c;%d;",root.info,root.freq);
			for (String bit : p){
                aux+=bit;
			}
			table.add(aux);
		}
		if (root != null) {
			if(root.right!=null){
				p.push("1");
				buildTable2(root.right);
				p.pop();
			}
			if(root.left!=null){
				p.push("0");
				buildTable2(root.left); 
				p.pop();
			}
		}
	}
	
	public void printTable(){
		for(String s:table){
			System.out.println(s);
		}
	}
	
	public void recover(String s){
		textR.clear();
		aux="";
		for(int i=0;i<s.length();i++){
			textR.add(s.charAt(i));
		}
		recoverData(root);
		PrintWriter writer;
		try {
			writer = new PrintWriter(HuffmanTreeTest.dir.replace("Compress",""), "UTF-8");
			writer.print(aux);
			writer.close();
		} catch (FileNotFoundException | UnsupportedEncodingException e) {
			System.out.println("Imposs�vel criar arquivo!");
		}
	}
	
	public void recoverData(Data root){
		if(!root.noShow){
			aux+=String.format("%c",root.info);
			root=this.root;
		}
		if(!textR.isEmpty()&&textR.get(0)=='1'){
			textR.remove(0);
			recoverData(root.right);
		}
		if(!textR.isEmpty()&&textR.get(0)=='0'){
			textR.remove(0);
			recoverData(root.left); 
		}
	}
	
}
