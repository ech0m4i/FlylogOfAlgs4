package c21;
import algs4.*;
import java.util.Arrays;
public class quick3wip {
	public static void sort(int[] a,int lo,int hi) {
		if(hi<=lo) return;
		//int[] pt=
		int[] pt=partition(a,lo,hi);
		//from pt[0] to pt[1] (included) equaled to a[lo] now.
		sort(a,lo,pt[0]-1);
		sort(a,pt[1]+1,hi);
	}
	private static int[] partition(int[] a,int lo,int hi) {
		int i=lo,p=lo;
		int j=hi+1,q=hi+1;
		int v=a[lo];
		// 2 direction start with i,j.
		while(true) {
			while(a[++i]<v) if(i==hi) break;//left i pt filter <v.
			while(a[--j]>v) if(j==lo) break;//right j pt filter >v.
			if(i==j&&a[i]==v) exch(a,++p,i);//special state: i,j meet && this==v then add to p.
			// i==j&&a[i]!=v => i wil > j
			if(i>=j) break;// i,j meet and this!=v then break, >= is essential.
			exch(a,i,j);// a[i]>=v || a[j]<=v then exchange them.
			if(a[i]==v) exch(a,++p,i);//last step get the i equal v and add to p.
			if(a[j]==v) exch(a,--q,j);//same above with q,j.
		}
		//StdOut.println("afterthen:  "+Arrays.toString(a)+" "+p+" "+q);
		//i==j+1);
		//now a[] become [=v][<v][>v][=v]. [lo,p]&&[q,hi] =v.
		//possible happen: [=v][<v][>v]
		//i=j+1; may not need?
		for(int k=p+1;k<=j;k++) exch(a,k,lo++);//p,q were with front ++/--.
		for(int k=q-1;k>=i;k--) exch(a,k,hi--);
		//StdOut.println("after all:  "+Arrays.toString(a)+" "+p+" "+q);
		int[] part=new int[2];
		part[0]=lo;
		part[1]=hi;
		return part;
	}
	private static boolean isSorted(int[] a) {
		for(int k=1;k<a.length;k++)
			if(a[k]<a[k-1]) return false;
		return true;
	}
	private static void exch(int[] a,int i,int j) {
		int temp=a[i];a[i]=a[j];a[j]=temp;
	}
	public static void main(String[] args) {
		int n=StdIn.readInt();
		int[] a=
		//{1,0,0,1,1};
		/*for(int i=0;i<n;i++) {
			StdRandom.shuffle(a);*/
		new int[n];
		/*for(int s=0;s<n;s++) {
			for(int k=0;k<30;k++) a[k]=StdRandom.uniform(0,9);
			//int[] c=a.clone();
		//StdOut.println("before: "+Arrays.toString(a));
			sort(a,0,a.length-1);
			//assert a[0]==a[a.length-1]:"\nbefore: "+Arrays.toString(c)+"\n"+"after:  "+Arrays.toString(a);
		}*/
		for(int k=0;k<n;k++) a[k]=StdRandom.uniform(0,1000);
		sort(a,0,a.length-1);
		//StdOut.println(Arrays.toString(a));
		assert isSorted(a);
	}
}