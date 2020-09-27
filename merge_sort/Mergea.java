/*Mergea.java*/
package c21;
import algs4.*;
public class Mergea {
	//private static Comparable[] aux;
	public static void sort(Comparable[] a) {
		Comparable[] aux = new Comparable[a.length];
		sort(aux,a,0,a.length-1);
	}
	private static void sort(Comparable[] aux,Comparable[] a,int lo,int hi) {
		if(hi<=lo) return;
		int mid = (hi+lo)/2;
		sort(aux,a,lo,mid);
		sort(aux,a,mid+1,hi);
		merge(aux,a,lo,mid,hi);
	}
	public static void merge(Comparable[] aux,Comparable[] a,int lo,int mid,int hi) {
		StdOut.printf(" | "+lo+" "+hi+"\n");
		int i=lo, j=mid+1;
		for(int k=lo;k<=hi;k++) aux[k]=a[k];
		for(int k=lo;k<=hi;k++) {
			/*if(lo==0&&hi==5&&mid==2) {
				for(int t=0;t<=hi;t++) StdOut.printf(a[t]+" ");
				StdOut.printf(" | "+j+" "+i+"\n");*/
			if(i > mid) {
				a[k] = aux[j++];
				//StdOut.printf("i > mid "+i+" "+mid+"\n");
				}
			//if(j > hi) a[k] = aux[i++];
				else { if(j > hi) {
					a[k] = aux[i++];
				//StdOut.printf("j > hi "+j+" "+hi+"\n");
					}
				//if(i > mid) a[k] = aux[j++];
					else { if(less(aux[j],aux[i])) {
				StdOut.printf("less now "+j+" "+i+"\n"); 
						a[k] = aux[j++];} //!!! aux[]比较
						else {
				StdOut.printf("less then "+j+" "+i+"\n"); 
							a[k] = aux[i++];
				/*if(lo==0&&hi==5&&mid==2){
				for(int t=0;t<=hi;t++) StdOut.printf(a[t]+" ");
				StdOut.printf(" end "+j+" "+i+"\n");
				}*/
						}
					}
				}
		} // 233 这错落有致的if{...}else{...}哈哈
	}
	private static boolean less(Comparable v,Comparable w) {
		return v.compareTo(w)<0;
	}
	public static boolean isrted(Comparable[] a) {
		for(int i=1;i<a.length;i++)
			if(less(a[i],a[i-1])) return false;
		return true;
	}
	public static void show(Comparable[] a) {
		for(int i=0;i<a.length;i++)
			StdOut.printf(a[i]+" ");
		StdOut.println();
	}
	public static void main(String[] args) {
		//int n = StdIn.readInt();
		Integer[] arr = {1,2,3,8,4,5,6,7};//new Integer[n];
		//Integer[] it= {2,4,6,1,3,5};//{5,3,1,6,4,2};
		//new Mergea().merge(it,0,2,5);
		//for(int i=0;i<n;i++) arr[i]=(int)(StdRandom.uniform()*100);
		sort(arr);
		//Integer[] iu= {2,4,6,1,3,5};
		//merge(iu,0,2,5);
		assert isrted(arr);
		//new Mergea().
		show(arr);	
	}
}