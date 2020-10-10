package test;
import algs4.*;
import java.util.Arrays;
public class test {
	private static int ts=2;
	public static int plus(int a) { a=a*2; return a; }
	/*private static int n;
	public static void sort(Comparable[] a) {
		n=a.length;
		for(int k=n/2;k<=n;k++) { //n/2,n
			//swim();
			//StdOut.println("all right?");
			int s=k;
			while(s/2>=1) {
				Comparable t=a[s/2-1],tt=a[s-1];
				//StdOut.println(t+" "+tt);
				//exch();
				if(t.compareTo(tt)>0) {
					Comparable tmp=t;
					a[s/2-1]=tt;
					a[s-1]=tmp;
				}
				s=s/2;
				//StdOut.println((s=s/2)+" "+1/2);
			}
		}
	}*/
	/*public static boolean sorted(Comparable[] a) {
		//int d=(Math.log(n))/(Math.log(2));
		for(int i=0;(i*2+2<n)?(i*2+2<n):(i*2+1<n);i++)
			if(a[i].compareTo(a[2*i+1])>0||(i*2+2<n)?(a[i].compareTo(a[2*i+2])>0):false) {
				StdOut.println(i);
				return false;
			}
		return true;
	}*/
	public static void main(String[] args) {
		int x=2;
		test t=new test();
		//StdOut.println(t.plus(t.ts));
		StdOut.println(plus(t.ts)+" "+t.ts);
		//StdOut.println(true|(false&false));
		//int x=2;
		//StdOut.println(x-(x>>1));
		//Comparable[] a={"S","O","R","T","E","X","A","M","P","L","E"};
		/*int[] a={1,2,3,4,5};
		System.out.println(a[-1]);*/
		/*int[] a={1,2,3};
		int s=2<3?1:2;
		int k=a[2<3?1:2];*/
		//System.out.println(a.length);
		/*int x=StdIn.readInt();
		int len=(int)Math.pow(2,x);
		Integer[] arr=new Integer[len];
		for(int i=0;i<len;i++) arr[i]=StdRandom.uniform(0,10);*/
		//StdOut.println("ok seed arr[]");
		//StdOut.println(Arrays.toString(arr));
		//String[] arr={"S","O","R","T","E","X","A","M","P","L","E"};
		//sort(arr);
		//StdOut.println("ok sort()");
		//StdOut.println(Arrays.toString(arr));
		//assert sorted(arr);
	}
	/*private static int m3(int i,int j,int k) {
		return 	(i<j) ?
					    ((j<k) ?  //min(j,x).
					    	j:
					    	(i<k ? k:i)) //max(k,i).
					  : ((k<j) ?  //max(j,y).
					  		j:
					  		(k<i ? k:i)); //min(k,i).
		/*return i<j?
				 ((j<k) ? j:(k<i?i:k))
				:((k<j) ? j:(k<i?k:i));

	}*/
}