/*Merge.java*/
package c21;
import algs4.*;

public class Merge {
	private static Comparable[] aux;
	
	public static void sort(Comparable[] a) {
		aux = new Comparable[a.length+1];
		sort(a, 0, a.length-1);
	}

	private static void sort(Comparable[] a, int lo, int hi) {
		if (hi<=lo) return; //递归返回
		int mid = lo + (hi - lo )/2; //计算mid值
		sort(a, lo, mid); //左半递归分解排序
		sort(a, mid+1, hi); //右半递归分解排序
		merge(a, lo, mid, hi); //归并所有已分解的
	}

	public static void merge(Comparable[] a, int lo, int mid, int hi) {
		int i = lo, j = mid + 1;

		for (int k = lo; k <= hi; k++) aux[k] = a[k]; // a[] copyto aux[]

		for (int k = lo; k <= hi; k++)
			if (j>hi) {a[k] = aux[i++];} // left full
			else if(i>mid) {a[k] = aux[j++];} // right full
			else if(less(a[j], a[i])) {a[k] = aux[j++];} // right < left
			else {a[k] = aux[i++];} // left <= right
	}

	public static boolean less(Comparable a, Comparable b) {
		StdOut.printf(a+" | "+b+" "+(a.compareTo(b)<0)+"\n");
		return a.compareTo(b) < 0;
	}

	public static void show(Comparable[] a) {
		for (int i=0; i<a.length; i++) StdOut.printf("%d" + " ",a[i]);
		StdOut.println();
	}

	public static boolean issorted(Comparable[] a) {
		for (int i=1; i<a.length; i++)
			if(less(a[i],a[i-1])) return false;
			//StdOut.println();
			/*if(less(a[i+1],a[i])) { 
				StdOut.println("error"); 
				return false; 
			}*/ // so what 'assert' meaning?
			//else break;
/*			else return true;//?no return? 局部变量? (x) -> if a.length=0 :D*/
		return true;
	}

	public static void main(String[] yeap) {
		//String[] a = In.readInts();
		int n = StdIn.readInt();
		Integer[] d = new Integer[n];
		for (int i=0; i<n; i++) d[i] = (int)(StdRandom.uniform()*100);
		sort(d);
		//StdOut.printf(aux[1]+" "+aux[d.length]+"\n");
		//aux[1]=1;
		//StdOut.printf(less(aux[1],aux[d.length])+"\n");
		//for (int i=0; i<n-1; i++) StdOut.printf(less(d[i],d[i+1]) + " " + issorted(d) +"\n");
		assert issorted(d); //无作用? -> java -ea
		show(d);
		//n = 10 时随机排序故障
/*		if(0>1) return;
		else return;
		StdOut.println();
*/	}
}