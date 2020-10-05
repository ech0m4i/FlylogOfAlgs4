/*mdfd.java*/
package test;
import algs4.*;
import java.util.Arrays;
public class Mdfd {
    private int[] hlf;
    private int[] hrt;
    private int nlf;
    private int nrt;
    /** initialize your data structure here. */
    public Mdfd() {
        hlf=new int[1];
        hrt=new int[1];
        hlf[0]=hrt[0]=-1;
        nlf=nrt=0;
    }
    public void addNum(int num) {
    	if(nlf==hlf.length) ds(nlf,true);
        if(nrt==hrt.length) ds(nrt,false);
        if(nrt==0&&nlf==0) {hrt[0]=num;++nrt;}
        else if(num>hrt[0]) {
            //StdOut.println("@addNum\n"+"nrt "+nrt+" num "+num+" "+hrt[nrt]+" "+Arrays.toString(hrt));
            hrt[nrt]=num;swim(nrt,false);++nrt;}
        else {
            //StdOut.println("@addNum\n"+"nlf "+nlf+" num "+num+" "+hlf[nlf]+" "+Arrays.toString(hlf));
            hlf[nlf]=num;swim(nlf,true);++nlf;}
        int htv=0;
        if(Math.abs(nrt-nlf)<=1) 
        {//StdOut.println(Arrays.toString(hlf)+" @addNum "+Arrays.toString(hrt));
    		return;}
        else if(nrt>nlf) {//StdOut.println("nrt-nlf: "+(nrt-nlf));
            htv=popfrom(--nrt,false);
            insto(htv,true);}
        else {//StdOut.println("nlf-nrt: "+(nlf-nrt));
            htv=popfrom(--nlf,true);
            insto(htv,false);}
        //StdOut.println(Arrays.toString(hlf)+" @addNum "+Arrays.toString(hrt));
    }
    private void ds(int len,boolean isbig) { //trans 'ref' useless
        int[] temp=new int[2*len];
        for(int i=len;i<2*len;++i) temp[i]=-1;
    	System.arraycopy(isbig?hlf:hrt,0,temp,0,len);
    	if(isbig) hlf=temp;
        else hrt=temp;
    }
    private int popfrom(int idx,boolean isbig) {
        int[] arr=isbig?hlf:hrt;
        //StdOut.println("@popfrom\n"+Arrays.toString(arr));
        int v=arr[0];
        //StdOut.println(v+" @popfrom "+(isbig?"hlf":"hrt"));
        exch(arr,0,idx);
        arr[idx]=-1;
        //if(isbig) --nlf; else --nrt;
        //StdOut.println(Arrays.toString(arr));
        sinkopt(isbig);
        return v;
    }
    private void insto(int v,boolean isbig) {
        //StdOut.println("@insto");
        int[] arr=isbig?hlf:hrt;
        //StdOut.println("0 "+Arrays.toString(arr));
        int k=isbig?nlf:nrt;
        if(k==arr.length) ds(k,isbig);
        arr[k]=v;
        //StdOut.println("1 "+Arrays.toString(arr));
        swim(k,isbig);
        if(isbig) ++nlf;
        else ++nrt;
        //StdOut.println(Arrays.toString(arr)+" "+Arrays.toString(hlf)+" "+Arrays.toString(hrt));
        //StdOut.println(nlf+" "+nrt);
    }
    private boolean detectheap() {
    	boolean heapok;
		for(int i=0;(2*i+1)<nlf;++i) {
				if(2*i+2<nlf) {
					if(hlf[2*i+2]>hlf[i]) {
						StdOut.println((2*i+2)+" @hlf");return heapok=false;
					}
				}
				if(hlf[2*i+1]>hlf[i]) {
					StdOut.println((2*i+1)+" @hlf");return  heapok=false;
				}	
		}
		for(int i=0;(2*i+1)<nrt;++i) {
				if(2*i+2<nrt) {
					if(hrt[2*i+2]<hrt[i]) {
						StdOut.println((2*i+2)+" @hrt");return heapok=false;
					}
				}
				if(hrt[2*i+1]<hrt[i]) {
					StdOut.println((2*i+1)+" @hrt");return heapok=false;
				}	
		}
    	return heapok=true;
    }
    private void swim(int idx,boolean isbig) {
        //index from 0 bug... 2 days with casual think.
        int[] arr=isbig?hlf:hrt;
        int temp;
        //StdOut.println("@swim");
        if(isbig) {
            while(((idx+1)/2-1>=0)&&arr[idx]>arr[(idx+1)/2-1]) {
                temp=(idx+1)/2-1;
               // StdOut.println(idx+" "+temp+" "+Arrays.toString(arr));
                exch(arr,idx,temp);idx=temp;}
            //StdOut.println(isbig+Arrays.toString(arr));
        } else {
            while(((idx+1)/2-1)>=0&&arr[idx]<arr[(idx+1)/2-1]) {
                temp=(idx+1)/2-1;
                //StdOut.println(idx+" "+temp+" "+Arrays.toString(arr));
                exch(arr,idx,temp);idx=temp;}
           //StdOut.println(isbig+Arrays.toString(arr));
        }
    }
    private void sinkopt(boolean isbig) {
        int[] arr=isbig?hlf:hrt;
        int n=isbig?nlf:nrt;
        int ti;
        int tv;
        //StdOut.println("@sinkopt\n"+Arrays.toString(arr));
        if(isbig) {
            ti=0;
            while(ti*2+1<n) {
                tv=ti*2+1;
                if(tv+1<n&&arr[tv]<arr[tv+1]) ++tv;
                exch(arr,ti,tv);ti=tv;
            }
            //StdOut.println(Arrays.toString(arr)); 
            swim(ti,isbig);
            //StdOut.println(Arrays.toString(arr));
        } else {
            ti=0;
            while(ti*2+1<n) {
                tv=ti*2+1;
                if(tv+1<n&&arr[tv]>arr[tv+1]) ++tv;
                exch(arr,ti,tv);ti=tv;
            }
            //StdOut.println(Arrays.toString(arr)); 
            swim(ti,isbig);
            //StdOut.println(Arrays.toString(arr));
        }
    }
    private void exch(int[] a,int v,int w) {
        int temp=a[v];a[v]=a[w];a[w]=temp;
    }
    public double findMedian() {
    	//StdOut.println("@findMedian "+nlf+" "+nrt);
        if(nrt==nlf) return (double)(hrt[0]+hlf[0])/2;
        else if(nrt>nlf) return hrt[0];
        else return hlf[0];
    }
    public static void main(String[] args) {
        //int k=StdIn.readInt();
        int[] it={155,66,114,0,60,73,109,26,154,0,107,75,9,57,53,6,85,151,12,110,64,103,42,103,126,3,88,142,79,88,147,47,134,27,82,95,26,124,71,79,130,91,131,67,64,16,60,156,9,65,21,66,49,108,80,17,159,24,90,79,31,79,113,39,54,156,139,8,90,19,10,50,89,77,83,13,3,71,52,21,50,120,159,45,22,69,144,158,19,109,52,50,51,62,20,22,71,95,47,12,21,32,17,130,109,8,61,13,48,107,14,122,62,54,70,96,11,141,129,157,136,41,40,78,141,16,137,127,19,70,15,16,65,96,157,111,87,95,52,42,12,60,17,20,63,56,37,129,67,129,106,107,133,80,8,56,72,81,143,90,};
        double[] ans={155.00000,110.50000,114.00000,90.00000,66.00000,69.50000,73.00000,69.50000,73.00000,69.50000,73.00000,74.00000,73.00000,69.50000,66.00000,63.00000,66.00000,69.50000,66.00000,69.50000,66.00000,69.50000,66.00000,69.50000,73.00000,69.50000,73.00000,74.00000,75.00000,77.00000,79.00000,77.00000,79.00000,77.00000,79.00000,80.50000,79.00000,80.50000,79.00000,79.00000,79.00000,80.50000,82.00000,80.50000,79.00000,79.00000,79.00000,79.00000,79.00000,77.00000,75.00000,74.00000,73.00000,74.00000,75.00000,74.00000,75.00000,74.00000,75.00000,77.00000,75.00000,77.00000,79.00000,77.00000,75.00000,77.00000,79.00000,77.00000,79.00000,77.00000,75.00000,74.00000,75.00000,76.00000,77.00000,76.00000,75.00000,74.00000,73.00000,72.00000,71.00000,72.00000,73.00000,72.00000,71.00000,71.00000,71.00000,72.00000,71.00000,72.00000,71.00000,71.00000,71.00000,70.00000,69.00000,68.00000,69.00000,70.00000,69.00000,68.00000,67.00000,66.50000,66.00000,66.50000,67.00000,66.50000,66.00000,66.00000,66.00000,66.00000,66.00000,66.00000,66.00000,65.50000,66.00000,66.00000,66.00000,66.00000,66.00000,66.50000,67.00000,66.50000,66.00000,66.50000,67.00000,66.50000,67.00000,68.00000,67.00000,68.00000,67.00000,66.50000,66.00000,66.50000,67.00000,68.00000,69.00000,69.50000,69.00000,68.00000,67.00000,66.50000,66.00000,66.00000,66.00000,65.50000,65.00000,65.50000,66.00000,66.00000,66.00000,66.50000,67.00000,67.00000,67.00000,66.50000,67.00000,67.00000,67.00000,68.00000,67.00000};
        //{-1,-2,-3,-4,-5};
        //{6,10,2,6,5,0,6,3,1,0,0};
    	Mdfd mf=new Mdfd();
    	int cnt=0;
        double[] op=new double[it.length];
    	for(int i=0;i<it.length;i++) {
	    	mf.addNum(it[i]);               
            //StdRandom.uniform(0,100));
            //if(mf.nrt>3&&mf.nrt<6) {
            	//StdOut.println("@addNum: "+it[i]+"\n"+mf.nlf+" hlf: "+Arrays.toString(mf.hlf)+"\n"+mf.nrt+" hrt: "+Arrays.toString(mf.hrt)+"\n");
            //}
            op[i]=mf.findMedian();
            if(op[i]!=ans[i]) {
            	StdOut.println("i: "+i+" ans["+i+"] is "+ans[i]+" but val in op["+i+"] is "+op[i]+
            		"\n"+Arrays.toString(mf.hlf)+"\n"+Arrays.toString(mf.hrt)); break;
            }
            if(!mf.detectheap()){ StdOut.println(mf.nlf+" "+Arrays.toString(mf.hlf)+"\n"+mf.nrt+" "+Arrays.toString(mf.hrt));break;}
    	}
	    	//StdOut.println(Arrays.toString(op));
    }
}
//maybe it's time to learn debug with breakpoint and trans to intellij idea...
