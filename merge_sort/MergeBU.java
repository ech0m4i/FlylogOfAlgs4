 /*MergeBU.java*/
package c21;
import algs4.*;
public class MergeBU {
	public static void sort(Comparable[] a) {
		int n = a.length;
		Comparable[] aux = new Comparable[n];
		for(int sz=1;sz<n;sz=sz+sz) 
			for(int lo=0;lo<n-sz;lo+=sz+sz)
				merge(aux,a,lo,lo+sz-1,Math.min(lo+sz+sz-1,n-1));//ensure len==sz.
	}
	private static void merge(Comparable[] aux,Comparable[] a,int lo,int mid,int hi) {
		int i=lo,j=mid+1;
		for(int k=lo;k<=hi;k++) aux[k]=a[k];
		for(int k=lo;k<=hi;k++) {
			if(j>hi) a[k]=aux[i++];
			else if(i>mid) a[k]=aux[j++];
				else if(less(aux[j],aux[i])) a[k]=aux[j++];
					else a[k]=aux[i++];
		}
	}
	private static boolean less(Comparable a,Comparable b) {
		return a.compareTo(b)<0;
	}
	private static boolean isSorted(Comparable[] a) {
		for(int i=1;i<a.length;i++)
			if(less(a[i],a[i-1])) return false;
		return true;
	}
	public static void main(String[] args) {
		int n=StdIn.readInt();
		Integer[] arr=new Integer[n];
		for(int i=0;i<n;i++) arr[i]=(int)(StdRandom.uniform()*100);
		sort(arr);
		assert isSorted(arr);
	}
}