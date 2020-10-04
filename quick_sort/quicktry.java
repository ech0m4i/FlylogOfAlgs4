package quick_sort;
import algs4.*;
import java.util.Arrays;
public class quicktry {
	//this are all bullshit! remember assert test with "-ea" god damn it!
	public static void sort(int[] a) {
		StdRandom.shuffle(a);
		sort(a,0,a.length-1);
	}
	private static void sort(int[] a,int lo,int hi)  {
		if(hi<=lo) { /*StdOut.println("hi "+hi+" <= "+"lo "+lo);*/return;}
		//if(hi<=lo+15) { insert(a,lo,hi); return; } //recursion out.
		int j=partition(a,lo,hi); //get the rule value.(j is sorted ensure.)
		//StdOut.println(Arrays.toString(a)+" lo "+lo+" hi "+hi+" j => "+j+" parted");
		sort(a,lo,j-1); //s.o.f. with the next sort()
		//StdOut.println(Arrays.toString(a)+" lo "+lo+" j-1 "+(j-1)+" 1st");
		sort(a,j+1,hi); //stackoverflow if length>100,000 why?
		//StdOut.println(Arrays.toString(a)+" j+1 "+(j+1)+" hi "+hi+" 2rd");
	}
	private static int partition(int[] a,int lo,int hi) {
		int v=a[lo],w=lo/*,t=hi*/;
		hi+=1;
		while(true) {
			//if(lo>=hi) break;
			//遍历1如下 唯一触发if的条件只能是lo++到数组右端点处 (cause hi+=1)
			while(a[++lo]<v) if(/*lo=hi*/lo==hi-1) break;//lo=>hi arr sample: {n,n-1,n-2,...,1}
			//while(a[++lo]<=v) if(lo==hi) break;
			//while(a[hi--]>=v) if(hi==lo) break;
			//自右向左遍历合法时 hi值不可能小于lo++
			while(a[--hi]>v) {}//if(hi<lo) break;//then ignore it
			if(lo>=hi) break;//next loop preparing...
			exch(a,lo,hi);
		}
		exch(a,w,hi);
		return hi;
	}
/*	private static void insert(int[] a,int lo,int hi) {
		for(int i=lo+1;i<=hi;i++)
			for(int j=i;j>0&&a[j]<a[j-1];--j)
				exch(a,j-1,j);
	}*/
	private static void exch(int[] a,int i,int j) {
		int temp=a[i];a[i]=a[j];a[j]=temp;
	}
	private static boolean isSorted(int[] a) {
		for(int i=1;i<a.length;i++)
			if(a[i]<a[i-1]) return false;
		return true;
	}
	public static void main(String[] args) {
		int n=StdIn.readInt();
		int[] a=new int[n];
		//for(int i=0;i<n;i++) a[i]=n-i;
		for(int i=0;i<n;i++) a[i]=(int)(StdRandom.uniform()*10);
		//StdOut.println(Arrays.toString(a));
		sort(a);
		//StdOut.println(Arrays.toString(a));
		assert isSorted(a);
	}
}