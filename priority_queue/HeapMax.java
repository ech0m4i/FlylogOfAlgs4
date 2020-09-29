package priority_queue;
import algs4.*;
import java.util.Arrays;
public class HeapMax{
	//a[]: {0,"S","O","R","T","E","X","A","M","P","L","E"}
	public static void sort(Comparable[] a) {
		int n=a.length;//12? a.len-1 {0,"SOMESTRING"}
		//from the last node which have kids node.
		for(int k=n/2;k>=1;k--) sink(a,k,n);//!!! ==1
		//for(int k=n/2;k<=n;k++) swim(a,k);
		//StdOut.println(Arrays.toString(a));
		while(n>1){
			exch(a,1,n--);
			sink(a,1,n);//exc with the next bigger.
			//swim(a,1);
		}
		//while(n)
	}
	private static void sink(Comparable[] a,int k,int n) {
		//build small top heap. => big heap totally.
		while(k+k<=n) {//think k from 1 to n/2.
			int dk=k+k;
			//find the bigger one.
			if(dk+1<=n&&less(a,dk,dk+1)) dk++;
			if(!less(a,k,dk)) break;
			exch(a,k,dk);
			k=dk;
		}
	}
	//try swim()
	//totally incorrect way.n/2 always at lgn-1 layer.
	/*private static void swim(Comparable[] a,int k) {
		while(k/2>=1) {//assume statement.
			//int t=k;
			if(less(a,k,k/2)) exch(a,k,k/2);
			k=k/2;//god damn it will loop all node before.
			//StdOut.println(t+" "+k+"	"+Arrays.toString(a));
		}
	}*/
	private static void exch(Comparable[] a,int i,int j) {//update real index.
		Comparable tmp=a[i-1];a[i-1]=a[j-1];a[j-1]=tmp;
	}
	private static boolean less(Comparable[] a,int v,int w) {
		return a[v-1].compareTo(a[w-1])<0;//real index at a[].
	}
	public static boolean isSorted(Comparable[] a) {
		for(int i=2;i<=a.length;i++)
			if(less(a,i,i-1)) return false;
		return true;
	}
	public static void main(String[] args) {
		Comparable[] a={"S","O","R","T","E","X","A","M","P","L","E"};
		//expect {A,E,E,L,M,O,P,R,S,T,X}
		sort(a);
		StdOut.println(Arrays.toString(a));
		assert isSorted(a);
	}
}