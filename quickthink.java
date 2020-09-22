/*quickthink.java*/
package c21;
import algs4.*;

public class quickthink {
	public static void sort(int[] a) {
		//StdRandom.shufftle(a);
		sort(a,0,a.length-1);
	}
	private static void sort(int[] a,int lo,int hi)  {
		if(hi<=lo) return; //recursion out.
		int j=partition(a,lo,hi); //get the rule value.
		sort(a,lo,j-1);
		sort(a,j+1,hi);
	}
	private static int partition(int[] a,int lo,int hi) {
		int i=lo,j=hi+1;
		int v=a[lo];
		while(true) {
			while(less(a[++i],v)) if(i==hi) break; //to avoid ++i=>hi+1 overflow.
			while(less(v,a[--j])) if(j==lo) break; //to aoid --j=>lo-1 o.f.
			if(i>=j) break;
			exch(a,i,j);
		}
		exch(a,lo,j);
		return j;
	}
	private static boolean less(int a,int b) {
		if(a<b) return true;
		else return false;
	}
	private static void exch(int[] a,int i,int j) {
		int temp=a[i];
		a[i]=a[j];
		a[j]=temp;
	}
	private static boolean isSorted(int[] a) {
		for(int i=1;i<a.length;i++)
			if(less(a[i],a[i-1])) return false;
		return true;
	}
	public static void main(String[] args) {
		int n=StdIn.readInt();
		int[] a=new int[n];
		for(int i=0;i<n;i++) a[i]=(int)(StdRandom.uniform()*100);
		sort(a);
		assert isSorted(a);
	}
}