import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class QuickUF {
	//quick union use tree.
	//connected element use root.
	//method:union,root,connected.
	private int[] id;
	private int[] sz;
	//init
	public QuickUF(int n){
		id=new int[n];
		sz=new int[n];
		for(int i=0;i<n;i++){
			id[i]=i;
			sz[i]=0;
		}
	}
	public int root(int i){
		while(i!=id[i]){
			//id[i]=id[id[i]];//path compression
			i=id[i];		
		}
		return i;
	}
	public boolean connected(int p,int q){
		return root(p)==root(q);
	}
	public void union(int p,int q){
		int i=root(p);
		int j=root(q);
		if(i==j) return;
		if(sz[i]<sz[j]){
			id[i]=j;
			sz[j]+=sz[i];
		}		
		else{
			id[j]=i;
			sz[i]+=sz[j];
		}
		//id[i]=j;
		
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
		QuickUF uf=new QuickUF(n);
		while(!StdIn.isEmpty()){
			int p=StdIn.readInt();
			int q=StdIn.readInt();
			if(!uf.connected(p, q)){
				uf.union(p, q);
				StdOut.println(p+" "+q);
			}
			else StdOut.println(p+"-"+q+"already connected");
		uf.printid();
		}
	}

}
