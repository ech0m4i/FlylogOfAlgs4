/*trapwithstack.java*/
package test;
import algs4.*;
import java.util.Arrays;
public class trapwithstack {
    private static int[] flow;
    public static int trap(int[] h) {
        if(h==null||h.length<3) return 0;
        int n=h.length;
        int res=0;
        int cnt=0;
        int tempa,tempb,cntemp;
        flow=new int[1];
        int lenf;
        for(int i=0;i<n;++i) {//using stack storage index of h[].
            lenf=flow.length;
            if(cnt==lenf) rs(lenf<<1);            
            if(cnt==0||h[i]<h[flow[cnt-1]]) flow[cnt++]=i;
            //StdOut.println("start for "+cntemp+" judgea "+h[flow[cntemp]]+" judgeb "+(cntemp-1>=0?h[flow[cntemp-1]]:-1));
            else {
            	flow[cnt++]=i;
            	cntemp=cnt-1;
            	while(cntemp>1&&h[flow[cntemp]]>h[flow[cntemp-1]]) {
	                tempa=h[flow[cntemp-2]]-h[flow[cntemp-1]];StdOut.println(tempa+" a");
	                tempb=h[flow[cntemp]]-h[flow[cntemp-1]];StdOut.println(tempb+" b");
	                res+=(tempa<tempb?tempa:tempb)*
	                    (flow[cntemp]-flow[cntemp-2]-1);
	                    //(flow[cntemp-1]-flow[cntemp-2]));
	                flow[cntemp-1]=flow[cntemp];
	                flow[cntemp]=-1;
	                --cntemp;
	                --cnt;
	                StdOut.println("res "+res+" flow "+Arrays.toString(flow));
            	}
	            if(cntemp==1&&h[flow[1]]>h[flow[0]]) {
	            	flow[0]=flow[1];
	            	--cnt;
	            	flow[1]=-1;
	            }
            }
            StdOut.println("@outer res "+res+" flow "+Arrays.toString(flow)+" cnt "+cnt);
            //if(cnt==(lenf>>2)) rs(lenf>>2);
        }
        return res;
    }
    /*public static int trap(int[] h) {
        if(h==null||h.length<3) return 0;
        int n=h.length;
        int res=0;
        int cnt=0;
        flow=new int[1];//build stack.
        //oops it's using index...
        int lenf;
        for(int i=0;i<n;++i) {
            lenf=flow.length;
            if(cnt==lenf) rs(lenf<<1);
            if(cnt>0&&h[i]>h[flow[cnt-1]]) {
                if(h[i]<h[flow[0]]) {
                    flow[cnt++]=i;
                    for(int k=cnt-1;k>0&&h[flow[k]]>h[flow[k-1]];--k) {
                            int temp=flow[k];flow[k]=flow[k-1];flow[k-1]=temp;
                    }             
                } else {
                    for(int j=flow[cnt-1];j>flow[0];--j) {
                        //--cnt;
                        res+=h[flow[0]]-h[j];
                        //j=-1;
                        //if(cnt>0&&(lenf>>2)==cnt) rs(lenf>>2);
                    }
                    cnt=1;
                    flow[0]=i;
                }
            } else { 
            	flow[cnt++]=i;
            }
        }
        return res;
    }*/
    private static void rs(int len) {
        int[] temp=new int[len];
        System.arraycopy(flow,0,temp,0,len>>1);
        for(int s=len>>1;s<len;++s) temp[s]=-1;
        flow=temp;
    }
    public static void main(String[] args) {
    	int[] a={4,2,0,3,2,5};
    	//{4,2,3};
    	//{0,1,0,2,1,0,1,3,2,1,2,1};
    	StdOut.println(trap(a));
	}
}
