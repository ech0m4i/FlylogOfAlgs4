package priority_queue;
import algs4.*;
import java.util.Arrays;
public class mwipq<Item extends Comparable<Item>>{//without qp[] 1st try.
		private Item[] kv;
		private static int[] pq;
		private static int n;
		public mwipq(int argslen) {
			kv=(Item[]) new Comparable[argslen+1];
			pq=new int[argslen+1];
			for(int i=0;i<pq.length;i++) pq[i]=-1;
		}
		public void insert(int ki,Item kvt) {
			n++;
			kv[ki]=kvt;
			pq[n]=ki;
			//StdOut.println(Arrays.toString(pq));
			swim(n); //aim to sort pq[].
			//StdOut.println("insert() "+Arrays.toString(pq));
		}
		public Item min() { return kv[pq[1]]; }
		public int delMin() {
			int i=pq[1];//get kv[] index.
			exch(1,n); //pq[]: 1<=>n
			kv[pq[n]]=null;
			pq[n]=-1;//not need may be. -->
			n--;
			//StdOut.println(Arrays.toString(pq));
			sink(1);// -->
			return i;
		}
		private void swim(int s) {
			//StdOut.println("swim() "+s);
			while(s>1&&less(s/2,s)) { // small top heap.
				exch(s,s/2);s=s/2;
			}
		}
		private void sink(int s) {
			//StdOut.println("------------sink() begin-----------------");
			while(s+s<=n) {// pq.length --> =n !!!
				int x=s*2;
				//if 2 sons find the smaller one.
				//StdOut.println("sink() s: "+s+" x: "+x+"			^");//+" pqlen: "+pq.length);//+" less(x,x+1): ");
				if(x+1<=n&&(less(x,x+1))) x++;//x+2==len --> //x+1<n
				//StdOut.println("sink() s:x      			|");
				//StdOut.println(Arrays.toString(pq)+" 				-");//why last so wide?
				if(!less(s,x)) break;//no need else. x>=s
				exch(x,s); //x<o & x<s.
				s=x;
			}
			//StdOut.println("------------sink() over------------------");
		}
		private boolean less(int i,int j) {//fake greater() hah.
			//StdOut.println(i+" "+j+" / "+pq[i]+" "+pq[j]);
			//StdOut.println(Arrays.toString(pq));
			return kv[pq[i]].compareTo(kv[pq[j]])>0;// -->
		}
		private void exch(int i,int j) {
			int t=pq[i];pq[i]=pq[j];pq[j]=t;
		}
		public boolean isEmpty() { return n==0; }
		//public void contains(int s) { }
		public int size() { return n;}

		public static void main(String[] args) { //a.txt, b.txt, c.txt...
			int len=args.length;
			In[] streams=new In[len];
			for(int i=0;i<len;i++) streams[i]=new In(args[i]); //In like.
			//StdOut.println(Arrays.toString(streams));
			mwipq<String> pqn=new mwipq<String>(len);
			for(int i=0;i<len;i++)
				if(!streams[i].isEmpty())
					pqn.insert(i,streams[i].readString());
			//StdOut.println(Arrays.toString(pq));
			//pq output stream...
			while(!pqn.isEmpty()) {
				//StdOut.println(pqn.isEmpty()+" "+pqn.size());
				StdOut.println("min(): "+n+" "+pqn.min());
				int i=pqn.delMin();// -->
				/*StdOut.println("s0: "+streams[0].isEmpty()+
					" s1: "+streams[1].isEmpty()+
					" s2: "+streams[2].isEmpty()+
					" i: "+i);*/
				if(!streams[i].isEmpty())
					pqn.insert(i,streams[i].readString());
			}
	}
}