/*IndexMinPQ.java*/
package priority_queue;
import algs4.*;

public class IndexMinPQ<Key extends Comparable<Key>> {
	private int n;
	private int[] pq;//index binary-heap from 1.
	private int[] qp;//qp[pq[i]]=pq[qp[i]]=i.
	private Key[] keys;//store elements.
	//inital keys,pq,qp to [maxn+1]
	public IndexMinPQ(int maxn) {
		//to ensure [] can store maxn elements from 1.
		keys=(Key[]) new Comparable[maxn+1];
		pq=new int[maxn+1];
		qp=new int[maxn+1];//qp[i]==0.
		for(int i=0;i<=maxn;i++) qp[i]=-1;
	}
	public void insert(int k,Key key) {
		//k=i of streams[i],key=some value in streams[i]
		n++;//n=n+1, from 1.
		//qp[k]=n+1, means 'k' has existed & qp[k]!=-1.
		qp[k]=n;
		pq[n]=k;//pq[n+1]=k.
		keys[k]=key; //keys[0,1,2]=...
		swim(n);
	}
	public Key min() { return keys[pq[1]]; }
	public int delMin() {
		int indexOfMin=pq[1];
		exch(1,n--);
		sink(1);
		keys[pq[n+1]]=null;
		qp[pq[n+1]]=-1;//qp[x]==-1
		return indexOfMin;
	}
	private static void merge(In[] streams) {//what's In[]
		int n=streams.length;//limited stream? yeap.
		IndexMinPQ<String> pq=new IndexMinPQ<String>(n);//structure func.
		for(int i=0;i<n;i++)
			if(!streams[i].isEmpty())
				pq.insert(i,streams[i].readString());
		//output.
		while(!pq.isEmpty()) {
			StdOut.printf(pq.min()+" ");
			int i=pq.delMin();
			if(!streams[i].isEmpty())
				pq.insert(i,streams[i].readString());
		}
	}
	private void swim(int k) { //k index from 1, down to up.
		while(k>1&&less(k/2,k)) {
			exch(k/2,k);
			k=k/2;
		}
	}
	private void sink(int k) { //up to down 
		while(k+k<pq.length) { 
			int t=k+k;
			//pq[x+1] exist and find the bigger one.
			if(t+1<pq.length&&less(t,t+1)) t++;
			//pq:k>=t, branch prediction.
			if(!less(k,t)) break;
			exch(k,t);
			//t=t+t;
			k=t;//k+k.
	    }
	}
	public boolean isEmpty() { return n==0;}
	public boolean contains(int k) { return qp[k]!=-1; }
	//private boolean less(int i,int j) { return pq[i].compareTo(pq[j])<0;}
	private boolean less(int i,int j){
    		return keys[pq[i]].compareTo(keys[pq[j]])<0;
    }
	//private void exch(int i,int j) { Key tmp=pq[i];pq[i]=pq[j];pq[j]=temp; }
	private void exch(int i,int j){
    		int swap=pq[i];pq[i]=pq[j];pq[j]=swap;
    		qp[pq[i]]=i;
    		qp[pq[j]]=j;
   	}
	public static void main(String[] args) {
		int n=args.length;
		In[] streams=new In[n];
		//each input file as stream[] element.
		for(int i=0;i<n;i++)
			streams[i]=new In(args[i]);
		merge(streams);
	}
}