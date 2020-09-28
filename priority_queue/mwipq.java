package priority_queue;
import algs4.*;
import java.util.Arrays;
public class mwipq<Item extends Comparable<Item>>{//without qp[] 1st try.
		private Item[] kv;
		private static int[] pq;
		private int n;
		private static int[] ivpq;
		public mwipq(int argslen) {
			kv=(Item[]) new Comparable[argslen+1];
			pq=new int[argslen+1];
			for(int i=0;i<pq.length;i++) pq[i]=-1;
			ivpq=new int[argslen+1];
			for(int i=0;i<ivpq.length;i++) ivpq[i]=-1;
		}
		public void insert(int ki,Item kval) {
			n++;
			kv[ki]=kval;
			pq[n]=ki;
			ivpq[ki]=n;//invert from pq[].
			//StdOut.println(Arrays.toString(pq));
			swim(n); //aim to sort pq[].
			//StdOut.println("insert() "+Arrays.toString(pq));
		}
		public Item min() { return kv[pq[1]]; }
		public int delMin() {
			int mioh=pq[1];//get kv[] index. == min index of heap.
			exch(1,n); //pq[]: 1<=>n
			kv[mioh]=null;//gc helpful.
			ivpq[mioh]=-1;//pq[] has not value==mioh.
			pq[n]=-1;//not need may be. -->
			n--;
			//StdOut.println(Arrays.toString(pq));
			sink(1);// -->
			return mioh;
		}
		public void changekv(int ki,Item kval) {
			//change val at kv[ki].
			kv[ki]=kval;
			swim(ivpq[ki]);
			//head first swim(). (if at heap bottom then -> )
			sink(ivpq[ki]);
			//if can't swim(). (at heap top.)
		}
		public boolean contains(int ki) { return ivpq[ki]!=-1; }
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
				if(x+1<=n&&(less(x,x+1))) x++;//x+2==len(not with len but n) --> //x+1<n
				//StdOut.println("sink() s:x      			|");
				//StdOut.println(Arrays.toString(pq)+" 				-");//why last so wide?
				if(!less(s,x)) break;//no need else. s<=x(s is the smallest one) then break.
				exch(x,s); //x<other sibling node & x<s.
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
			//pq[] layer exch().
			int t=pq[i];
			int arrivtemp=pq[i]=pq[j];
			int arrjvtemp=pq[j]=t;
			//ivpq[] update.
			ivpq[arrivtemp]=i;
			ivpq[arrjvtemp]=j;
		}
		public boolean isEmpty() { return n==0; }
		//public void contains(int s) { }
		public int size() { return n;}

		public static void main(String[] args) { //a.txt, b.txt, c.txt...
			int len=args.length;
			In[] streams=new In[len];
			//test useful.
			int[] acsiindex=new int[52];
			int cap=65,low=97;
			for(int i=0;i<26;i++) acsiindex[i]=cap++;
			for(int i=26;i<52;i++) acsiindex[i]=low++;
			StdOut.println(Arrays.toString(acsiindex));
			//end.
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
				StdOut.println("min(): "+pqn.min());
				StdOut.println("ivpq[]: "+Arrays.toString(ivpq));
				int i=pqn.delMin();// -->
				/*StdOut.println("s0: "+streams[0].isEmpty()+
					" s1: "+streams[1].isEmpty()+
					" s2: "+streams[2].isEmpty()+
					" i: "+i);*/
				//pqn.changekv(1,"S");
				int ti=StdRandom.uniform(0,3);
				String tv=String.valueOf((char)acsiindex[StdRandom.uniform(0,52)]);
				//StdOut.println(ti+" "+tv);
				if(pqn.contains(ti)) pqn.changekv(ti,tv);
				if(!streams[i].isEmpty())
					pqn.insert(i,streams[i].readString());
			}
	}
}