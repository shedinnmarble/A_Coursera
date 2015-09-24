import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class UF {
	private int[] id;
	public UF(int n){
		id=new int[n];
		for(int i=0;i<n;i++){
			id[i]=i;
		}
	}
	public void union(int p,int q){
		int pid=id[p];
		int qid=id[q];;
		for(int i=0;i<id.length;i++){
			if(id[i]==pid) id[i]=qid;
		}
	}
	public boolean connected(int p,int q){
		
		return id[p]==id[q];
	}
	public void printid(){
		for(int i=0;i<id.length;i++){
			StdOut.print(id[i]+"-");
		}
		StdOut.println();
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int n=StdIn.readInt();
		UF uf=new UF(n);
		while(!StdIn.isEmpty()){
			int p=StdIn.readInt();
			int q=StdIn.readInt();
			if(!uf.connected(p, q)){
				uf.union(p, q);
				StdOut.println(p+" "+q);
			}
			else StdOut.println(p+""+q+"already connected");
			uf.printid();
		}
	}

}
