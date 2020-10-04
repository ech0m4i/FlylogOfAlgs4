package test;
import algs4.*;
import java.util.Arrays;
public class heaptest {
    public static int[] test(int[] arr,int k) {
        int n=arr.length;
        for(int m=n/2;m>=1;--m) {
            int i=m;
            while(2*i<=n) {
                int t=2*i;
                if(t+1<=n&&arr[t-1]>arr[t+1-1]) ++t;
                if(arr[i-1]<=arr[t-1]) break;
                exch(arr,i,t);
                i=t;
            }
        }
        for(int j=0;j<k;j++) {
            exch(arr,1,n--);
            optsink(arr,1,n);
        }
        int[] res=new int[k];
        System.arraycopy(arr,arr.length-k,res,0,k);
        return res;
    }
    private static void optsink(int[] a,int v,int w) {
        while(2*v<=w) {
            //StdOut.println(tv);
            int dv=2*v;
            if(dv+1<=w&&a[dv-1]>a[dv+1-1]) ++dv;
            exch(a,v,dv);
            v=dv;
        }
        while(v>1&&a[v-1]<a[v/2-1]) {
            exch(a,v/2,v);
            v=v/2;
        }
    }
    private static void exch(int[] a,int i,int j) {
        int temp=a[i-1];a[i-1]=a[j-1];a[j-1]=temp;
    }
    public static void main(String[] args) {
        int[] a=new int[100000];
        for(int i=0;i<100000;i++) a[i]=StdRandom.uniform(0,100000);
        StdOut.println(Arrays.toString(test(a,100)));
    }  
}
    

