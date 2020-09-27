/*quicks3.java*/
package c21;
import algs4.*;
import java.util.Arrays;
public class quicks3 {
	/*一次性跑通 喜极而泣 今天真是我的幸运日呜呜
	空欢喜一场 用的昨晚的编译文件草 还以为自己发现了什么猴子算法233*/
	/*overwatcher version 1.0*/
/*	public static void sort_quick3way(int[] a,int lo,int hi) {
		if(lo>=hi) return;
		int i=lo,j=hi,m=lo+1,v=a[lo];
		while(m<=hi)  {
			if(a[m]<v) exch(a,m++,lo++);
			else if(a[m]>v) exch(a,m,hi--);
				else m++;
		}
		sort(a,i,lo-1);
		sort(a,hi+1,j);
	}*/
	/*3 ways partition with overwatcher*/
	public static void sortBD(int[] a,int lo,int hi) {
		if(lo>=hi) return;
		int[] part=partition(a,lo,hi);//i,j of part[] == a[lo]
		//StdOut.println(Arrays.toString(part)+" "+lo+" "+hi);
		sortBD(a,lo,part[0]);
		sortBD(a,part[1],hi);
	}
	private static int[] partition(int[] a,int lo,int hi) {
		int p=lo+1,i=lo+1,q=hi,j=hi,v=a[lo];
		while(i<=j) {
			/*[lo,p]==v [q.hi]==v [p+1,q-1]!=v*/
			if(a[i]==v) exch(a,p++,i++);
			else i++;
			if(a[j]==v) exch(a,j--,q--);
			else j--;
			/*if(a[p]==v) { exch(a,p++,s++);i++; }
			if(a[q]==v) { exch(a,p,q--);j--;}*/
			//注意两端同时比较
			//p,q => i,j
			/*if(a[i]==v) exch(a,p++,i++);
			else if(a[i]<v) exch(a,p++,i++);
			///else exch(a,i,++i);//?
			else exch(a,i,i+1);
			if(a[j]==v) exch(a,q--,j--);
			else if(a[j]>v) exch(a,q--,j--);
			else exch(a,j,j-1);*/
			/*if(a[i]<v) exch(a,i++,p);
			if(a[j]>v) exch(a,j--,q);*/
		}
		StdOut.println(Arrays.toString(a));
		/*int s=p-1,t=q+1;
		int m=lo,n=hi;*/
		//int m=i,n=j;
		int lf=p,rt=q;
		int s=lo,t=hi;
		while(lf<=rt) {
			//需要分拣 <v,>v in [p+1,q-1]
			//update: 其实这样并不互补
			if(a[lf]<v) exch(a,lf++,s++);
			else exch(a,lf,rt);
			if(lf>rt) break;//哭 终于跑通 多参数太难控制了呜呜
			if(a[rt]>v) exch(a,rt--,t--);
			else exch(a,lf,rt);
			//实现信息量最小(与前置内循环互补?)
			//i,j实现
			/*while(a[i]>v) exch(a,i++,n--);
			while(a[j]<v) exch(a,j--,m++);
			if(i>q&&j<p) break;*/
			//s,t实现
			/*while(a[q]>v) exch(a,q--,++t);
			while(a[p]<v) exch(a,p++,--s);
			if(p>q) break;*/
		}
		StdOut.println(Arrays.toString(a));
		int[] part=new int[2];
		part[0]=s-1;
		part[1]=t+1;
		return part;
	}
	private static void exch(int[] a,int i,int j) {
		int temp=a[i];a[i]=a[j];a[j]=temp;
	}
	private static boolean isSorted(int[] a) {
		for(int i=1;i<a.length;i++) {
			if(a[i]<a[i-1]) return false;
		}
		return true;
	}
	public static void main(String[] args) {
		int n=StdIn.readInt();
		int[] a =//{9,8,7,6,5,4,3,2,1,0};
		//{2,1,2,1,2,2,3,3,1,1,1,3,2,3,1,2};
		new int[n];
		for(int i=0;i<n;i++) a[i]=(int)(StdRandom.uniform()*1000);
		//StdOut.println("main before: "+Arrays.toString(a));
		sortBD(a,0,a.length-1);
		//StdOut.println("main after:  "+Arrays.toString(a));
		assert isSorted(a);
	}
}