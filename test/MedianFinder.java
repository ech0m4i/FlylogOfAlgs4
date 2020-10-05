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
        else if(nrt-nlf>1) {
            pv=popm(hrt,--nrt,false);
            moveto(pv,true);
        }
        else return;
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
        if(sig!=-1) return;*/
        //StdOut.println("starting @swim"+(ifbig?" hlf":" hrt")+" "+id+" "+ifbig+" "+Arrays.toString(hlf)+" "+Arrays.toString(hrt));
        if(ifbig) {
            while(id>0&&a[id]>a[id/2]) {exch(a,id,id/2);id=id/2;}   
        }
        else {
            while(id>0&&a[id]<a[id/2]) {exch(a,id,id/2);id=id/2;}
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
    public static void main(String[] args) {
        //int k=StdIn.readInt();
        int[] it={-1,-2,-3,-4,-5};
        //{6,10,2,6,5,0,6,3,1,0,0};
    	MedianFinder mf=new MedianFinder();
    	for(int i=0;i<it.length;i++) {
	    	mf.addNum(it[i]);                
            //StdRandom.uniform(0,100));
	    	StdOut.println(mf.findMedian());
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