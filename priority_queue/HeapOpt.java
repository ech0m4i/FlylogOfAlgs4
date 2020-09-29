/*HeapOpt.java*/
package priority_queue;
import algs4.*;
import java.util.Arrays;
public class HeapOpt {
	private static void sort(int[] a) {
		int n=a.length;
		for(int i=n/2;i>=1;i--) sink(a,i,n);//1~n/2
		//StdOut.println("ok?");
		//StdOut.println(Arrays.toString(a));
		while(n>1) {
			exch(a,1,n--);
			sinkwithswim(a,1,n);
		}
	}
	//heap build.
	private static void sink(int[] a,int i,int n) {
		while(2*i<=n) {//di -> double i
			int di=2*i;
			if(di+1<=n&&less(a,di,di+1)) di++;
			if(!less(a,i,di)) break;
			exch(a,i,di);
			i=di;
		}
	} 
	private static void sinkwithswim(int[] a,int i,int n) {
		//directly sink() update with the hier.
		//StdOut.println("--------------------s");
		while(2*i<=n) {
			//StdOut.println("N value: "+n);
			int di=2*i;
			if(di+1<=n&&less(a,di,di+1)) di++;
			exch(a,di,i); //n->i TAT!!
			//StdOut.println("sink() mod: "+Arrays.toString(a));
			i=di;
		}
		//StdOut.println("b"+i);
		//swim().
		while(i>1&&less(a,i/2,i)) { exch(a,i/2,i);i=i/2; }//i need swim...sad
		/*StdOut.println("swim(): "+Arrays.toString(a));
		StdOut.println("a"+i);
		StdOut.println("--------------------e");*/
	}
	private static void exch(int[] a,int i,int j) {
		int temp=a[i-1];a[i-1]=a[j-1];a[j-1]=temp;
	}
	private static boolean less(int[] a,int v,int w) {
		return a[v-1]<a[w-1];
	}
	private static boolean sorted(int[] a) {
		for(int i=2;i<=a.length;i++)
			if(less(a,i,i-1)) return false;
		return true;
	}
	public static void main(String[] args) {
		int x=StdIn.readInt();
		int alen=(int)Math.pow(2,x);
		int[] a=
		//{0,3,7,7,6,9,2,0,5,10,3,7,7,6,9,2,0,5,1};
		//{8, 1, 8, 4, 17, 2, 19, 2, 10, 6, 3, 14, 9, 9, 1, 1};
		new int[alen];
		for(int i=0;i<alen;i++) a[i]=StdRandom.uniform(0,100);
		//StdOut.println(Arrays.toString(a));
		sort(a);
		//StdOut.println("result: "+Arrays.toString(a));
		assert sorted(a);
	}
}