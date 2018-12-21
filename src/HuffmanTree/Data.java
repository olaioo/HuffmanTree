package HuffmanTree;

public class Data implements Comparable<Data> {
	char info;
	int freq;
	boolean noShow;
	Data right=null;
	Data left=null;
	
	Data(int freq){
		noShow=true;
		this.freq=freq;
	}
	
	Data(char info,int freq){
		this.info=info;
		this.freq=freq;
	}
	
	public String toString(){
		return String.format("HuffmanTree.Data: %c, freq: %d",info,freq);
	}

	@Override
	public int compareTo(Data o) {
		if(freq==o.freq){
			if(info!='\u0000'&&o.info!='\u0000'){
				return info-o.info;
			}else{
				return 1;
			}
		}else{
			return freq-o.freq;
		}
	}
}
