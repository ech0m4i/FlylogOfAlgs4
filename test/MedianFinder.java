package test;
import algs4.*;
import java.util.Arrays;

public class MedianFinder {
    //dual heap solution.
    private int[] hlf;
    private int[] hrt;
    private int nlf;
    private int nrt;
    /*private int sig=-1;
    private int sigva;
    private int[] sigvb;*/
    public MedianFinder() {
        hlf=new int[1];hrt=new int[1];
        hlf[0]=hrt[0]=-1;
        nlf=nrt=0;
    }
    public void addNum(int num) {
        //StdOut.println("@addNum "+num);
        if(nlf==hlf.length) ds(nlf,true);
        if(nrt==hrt.length) ds(nrt,false);
        if(num>hrt[0]) { 
            hrt[nrt]=num;
            swim(hrt,nrt,false);
            ++nrt;}//big heap.->little
        else { 
            hlf[nlf]=num;
            swim(hlf,nlf,true);
            ++nlf;}//little heap.->big
        int pv=-2;//sig -2.
        if(Math.abs(nlf-nrt)<=1) return;
        else if(nlf-nrt>1) {
            pv=popm(hlf,--nlf,true);
            moveto(pv,false);
        }
        else {
            pv=popm(hrt,--nrt,false);
            moveto(pv,true);
        }
    }
    private void moveto(int pv,boolean isbig) {
        int rnoln=isbig?nlf:nrt;
        int[] raola=isbig?hlf:hrt;
        if(rnoln==raola.length) ds(rnoln,isbig);
        raola[rnoln]=pv;
        swim(raola,rnoln,isbig);
        //isbig?++nlf:++nrt;
        if(isbig) ++nlf;
        else ++nrt;
    }
    private void swim(int[] a,int id,boolean ifbig) {
        /*if(sigva==id&&sigvb==a) sig++;
        else {sigva=0;sigvb=new int[0];sig=-1;}
        if(sig!=-1) return;*///can not got this...
        //StdOut.println("starting @swim"+(ifbig?" hlf":" hrt")+" "+id+" "+ifbig+" "+Arrays.toString(hlf)+" "+Arrays.toString(hrt));
        if(ifbig) {
            while(id>0&&a[id]>a[(id+1)/2-1]) {exch(a,id,(id+1)/2-1);id=(id+1)/2-1;}   
        }
        else {
            while(id>0&&a[id]<a[(id+1)/2-1]) {exch(a,id,(id+1)/2-1);id=(id+1)/2-1;}
        }
    }
    private int popm(int[] a,int id,boolean ifbig) {
        int m=a[0];
        exch(a,0,id);a[id--]=-1;sinkopt(a,0,id,ifbig);
        return m;
    }
    private void sinkopt(int[] a,int id,int len,boolean ifbig) {
        int tid;
        int tv;
        if(ifbig) {
            tid=id;
            while(2*tid+1<=len) {
                tv=2*tid+1;
                if(tv+1<=len&&a[tv]<a[tv+1]) tv++;
                exch(a,tid,tv);
                tid=tv;
            }
                //StdOut.println(tid+" ");
                swim(a,tid,true);
        }
        else {
            tid=id;
            while(2*tid+1<=len) {
                tv=2*tid+1;
                if(tv+1<=len&&a[tv]>a[tv+1]) tv++;
                exch(a,tid,tv);
                tid=tv;
            }
                swim(a,tid,false); 
        }
    }
    private void ds(int len,boolean isbig) {
        int[] temp=new int[len*2];
        for(int i=len;i<len*2;i++) temp[i]=-1;//sig -1.temp[i] not temp[len]...stupid
        System.arraycopy(isbig?hlf:hrt,0,temp,0,len);
        //(isbig?hrt:hlf)=temp;//illegal?
        if(isbig) {hlf=temp;}
        else {hrt=temp;}
        //StdOut.println("@ds "+Arrays.toString(isbig?hlf:hrt));
    }
    public double findMedian() {
        //StdOut.println("@findMedian lf: "+Arrays.toString(hlf)+" rt: "+Arrays.toString(hrt)+"\nmid:");
        if(nrt==nlf) return (double)(hrt[0]+hlf[0])/2;
        else if(nrt==nlf+1) return hrt[0];
        else if(nrt+1==nlf) return hlf[0];
        else return 0.0;//hack?
    }
	/*//insert solution.
    private int n;
    private static int[] arr;
    public MedianFinder() {
    	arr=new int[1];
        arr[0]=-1;
    	n=0;
    }

    public void addNum(int num) {
        //StdOut.println(n+" "+arr.length);
    	if(n==arr.length) dz(n);
        //StdOut.println(arr.length);
        arr[n++]=num;
        int p=n-1;
    	while(p>0&&arr[p]<arr[p-1]) { exch(arr,p,p-1);--p; }
        //StdOut.println(Arrays.toString(arr));
    }

    public void dz(int t) {
        //StdOut.println(Arrays.toString(a)+" at dz()");
    	int[] temp=new int[2*t];
    	System.arraycopy(arr,0,temp,0,t);
        //StdOut.println(Arrays.toString(temp)+" at dz() "+temp.length);
    	arr=temp;
        //StdOut.println(Arrays.toString(a)+" at dz()");
        //StdOut.println(Arrays.toString(arr)+" at dz()");
    }
    
    public double findMedian() {
    	return n%2==0?((arr[n/2-1]+arr[n/2])/2):arr[n/2];
    }*/
    public void exch(int[] a,int v,int w) {
        //StdOut.println("v: "+v+" w: "+w); //need 'ref val'(a[]) got.
        int temp=a[v];a[v]=a[w];a[w]=temp;
        //StdOut.println(Arrays.toString(arr)+" @exch "+v+" "+w);
    }
    /*public static void main(String[] args) {
        //int k=StdIn.readInt();
        int[] it={-1,-2,-3,-4,-5};
        //{6,10,2,6,5,0,6,3,1,0,0};
    	MedianFinder mf=new MedianFinder();
    	for(int i=0;i<it.length;i++) {
	    	mf.addNum(it[i]);                
            //StdRandom.uniform(0,100));
	    	StdOut.println(mf.findMedian());
    	}
    }*/
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
    public static void main(String[] args) {
        //int k=StdIn.readInt();
        int[] it={155,66,114,0,60,73,109,26,154,0,107,75,9,57,53,6,85,151,12,110,64,103,42,103,126,3,88,142,79,88,147,47,134,27,82,95,26,124,71,79,130,91,131,67,64,16,60,156,9,65,21,66,49,108,80,17,159,24,90,79,31,79,113,39,54,156,139,8,90,19,10,50,89,77,83,13,3,71,52,21,50,120,159,45,22,69,144,158,19,109,52,50,51,62,20,22,71,95,47,12,21,32,17,130,109,8,61,13,48,107,14,122,62,54,70,96,11,141,129,157,136,41,40,78,141,16,137,127,19,70,15,16,65,96,157,111,87,95,52,42,12,60,17,20,63,56,37,129,67,129,106,107,133,80,8,56,72,81,143,90,};
        double[] ans={155.00000,110.50000,114.00000,90.00000,66.00000,69.50000,73.00000,69.50000,73.00000,69.50000,73.00000,74.00000,73.00000,69.50000,66.00000,63.00000,66.00000,69.50000,66.00000,69.50000,66.00000,69.50000,66.00000,69.50000,73.00000,69.50000,73.00000,74.00000,75.00000,77.00000,79.00000,77.00000,79.00000,77.00000,79.00000,80.50000,79.00000,80.50000,79.00000,79.00000,79.00000,80.50000,82.00000,80.50000,79.00000,79.00000,79.00000,79.00000,79.00000,77.00000,75.00000,74.00000,73.00000,74.00000,75.00000,74.00000,75.00000,74.00000,75.00000,77.00000,75.00000,77.00000,79.00000,77.00000,75.00000,77.00000,79.00000,77.00000,79.00000,77.00000,75.00000,74.00000,75.00000,76.00000,77.00000,76.00000,75.00000,74.00000,73.00000,72.00000,71.00000,72.00000,73.00000,72.00000,71.00000,71.00000,71.00000,72.00000,71.00000,72.00000,71.00000,71.00000,71.00000,70.00000,69.00000,68.00000,69.00000,70.00000,69.00000,68.00000,67.00000,66.50000,66.00000,66.50000,67.00000,66.50000,66.00000,66.00000,66.00000,66.00000,66.00000,66.00000,66.00000,65.50000,66.00000,66.00000,66.00000,66.00000,66.00000,66.50000,67.00000,66.50000,66.00000,66.50000,67.00000,66.50000,67.00000,68.00000,67.00000,68.00000,67.00000,66.50000,66.00000,66.50000,67.00000,68.00000,69.00000,69.50000,69.00000,68.00000,67.00000,66.50000,66.00000,66.00000,66.00000,65.50000,65.00000,65.50000,66.00000,66.00000,66.00000,66.50000,67.00000,67.00000,67.00000,66.50000,67.00000,67.00000,67.00000,68.00000,67.00000};
        MedianFinder mf=new MedianFinder();
        double[] op=new double[it.length];
        for(int i=0;i<it.length;i++) {
            mf.addNum(it[i]);
            op[i]=mf.findMedian();
            if(op[i]!=ans[i]) {
                StdOut.println("i: "+i+" ans["+i+"] is "+ans[i]+" but val in op["+i+"] is "+op[i]+
                    "\n"+Arrays.toString(mf.hlf)+"\n"+Arrays.toString(mf.hrt)); break;
            }
            if(!mf.detectheap()){ StdOut.println(mf.nlf+" "+Arrays.toString(mf.hlf)+"\n"+mf.nrt+" "+Arrays.toString(mf.hrt));break;}
        }
    }
}

/**
 * Your MedianFinder object will be instantiated and called as such:
 * MedianFinder obj = new MedianFinder();
 * obj.addNum(num);
 * double param_2 = obj.findMedian();
 */
//[[],[6],[],[10],[],[2],[],[6],[],[5],[],[0],[],[6],[],[3],[],[1],[],[0],[],[0],[]]//how to implement this init?