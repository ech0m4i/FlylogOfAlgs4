/*qucikv3.java*/
package c21;
import algs4.*;

public class quickv3 {
	public static void sort(int[] a) {
		int t=0;
		for(int i=0;i<a.length;i++) if(a[i]>a[t]) t=i;
		exch(a,t,a.length-1);
		sort(a,0,a.length-1);
	}
	private static void sort(int[] a,int lo,int hi) {
		if(hi<=lo) return;
		int j=partition(a,lo,hi);
		sort(a,lo,j-1);
		sort(a,j+1,hi);
	}
	private static int partition(int[] a,int lo,int hi) {
		int i=lo,j=hi+1,v=a[lo];//w=a[hi<a.length-1?hi+1:hi];
		while(true) {
			while(a[++i]<v);
			while(a[--j]>v);
			if(i>=j) break;
			exch(a,i,j);
		}
		exch(a,lo,j);
		return j;
	}
	private static void exch(int[] a,int i,int j) {
		int t=a[i];a[i]=a[j];a[j]=t;
	}
	private static boolean isSorted(int[] a) {
		for(int i=1;i<a.length;i++) {
			if(a[i]<a[i-1]) return false;
		}
		return true;
	}
	public static void main(String[] args) {
		//Scanner in=new Scanner(System.in);
		//int n=in.nextInt();
		int n=StdIn.readInt();
		int[] a=new int[1<2?n:n-1];
		for(int i=0;i<n;i++) a[i]=(int)(StdRandom.uniform()*1000);
		sort(a);
		assert isSorted(a);
	}
}