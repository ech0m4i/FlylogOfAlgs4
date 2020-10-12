/*mergeiv.java*/
package test;
import java.util.Arrays;
public class mergeiv { 
	/*class JmpRec extends RuntimeException {

	}*/
	private static int[][] getres;
	public static int[][] mergeplanb(int[][] arrmpb) {
		int n=arrmpb.length;
		int mergeremaincnt=n;
		if(arrmpb==null||n<=1) return arrmpb;
		qc(arrmpb,0,n-1);
		System.out.println(Arrays.deepToString(arrmpb));
		int bemergenow=0;
		for(int i=1;i<n;++i) {
			if(arrmpb[i][0]<=arrmpb[bemergenow][1]) {
				if(arrmpb[bemergenow][1]<arrmpb[i][1]) arrmpb[bemergenow][1]=arrmpb[i][1];
				arrmpb[i][0]=-1;
				--mergeremaincnt;
			} else bemergenow=i;
		}
		int[][] temparr=new int[mergeremaincnt][2];
		int tempindex=0;
		for(int i=0;i<n;++i) {
			if(arrmpb[i][0]>0) System.arraycopy(arrmpb[i],0,temparr[tempindex++],0,2);
		}
		return temparr;
	}
    public static int[][] merge(int[][] itv) {
    	//int[] sortindexzero=new int[n];
    	//for(int i=0;i<n;++i) sortindexzero[i]=intervals[i][0];
    	//qc(sortindexzero,0,n-1);
    	qc(itv,0,itv.length-1);
    	//System.out.println(Arrays.deepToString(itv));
    	try {
    		updatemerge(itv);
    	} catch(RuntimeException e) {
    		return getres;
    	}
    	return new int[0][];//for compile passing...
    }
    private static int[][] updatemerge(int[][] intervals) throws RuntimeException {
    	int n=intervals.length;
    	if(n==1) { getres=intervals; throw new RuntimeException(); }
    	int judgereturncnt=n;
    	int idxcnt=n;
        for(int i=0;i<n;++i) {
            if(i+1<n&&intervals[i][1]>=intervals[i+1][0]) {
                if(intervals[i+1][1]>intervals[i][1]) intervals[i][1]=intervals[i+1][1];
                intervals[i+1][0]=-1;
                --idxcnt;
                i+=2;
                continue;
            } else if(i+2<=n) --judgereturncnt;
        }
        if(judgereturncnt==1) {
        	getres=intervals;
        	throw new RuntimeException();
        }
        int[][] temp=new int[idxcnt][2];
        int idxtemp=0;
        for(int i=0;i<n;++i) {
            if(intervals[i][0]>-1) {
                temp[idxtemp][0]=intervals[i][0];
                temp[idxtemp][1]=intervals[i][1];
                ++idxtemp;
            }
        }
        //System.out.println("af tempopr "+Arrays.deepToString(temp));
        //return temp;
        return updatemerge(temp);
    }
    private static void ins(int[][] arrins,int lf,int rt) {
    	//System.out.println(Arrays.toString(arrins));
    	for(int i=lf;i<=rt;++i) {
    		for(int j=i;j>lf;--j) {
	    		if(arrins[j][0]<arrins[j-1][0]) exch(arrins,j,j-1);
	    		else break;
    		}
    	}
    }
    //private static int errorcnt=0;
    private static void qc(int[][] arr,int lf,int rt) {
    	//if(lf>=rt) return;
    	if(lf+15>rt) { ins(arr,lf,rt); return; }
    	int tvcut=arr[lf][0];
    	int tvlf=lf,tvrt=rt+1;
    	//System.out.println(Arrays.toString(arr)+" "+tvlf+" "+tvrt); ++errorcnt; 
    	while(true) {
    		//if(errorcnt<8) { System.out.println(tvlf+" "+tvrt); ++errorcnt; }
	    	while(arr[++tvlf][0]<tvcut) { if(tvlf+1==tvrt) break; }
	    	//System.out.println(tvlf+" - whiled 1st - "+tvrt);
	    	while(arr[--tvrt][0]>tvcut) {}
	    	//System.out.println(tvlf+" - whiled 2rd - "+tvrt);
	    	if(tvlf>=tvrt) break;
	    	exch(arr,tvlf,tvrt);
	    }
    	exch(arr,lf,tvrt);
    	qc(arr,lf,tvrt-1);
    	qc(arr,tvrt+1,rt);
    }
    private static void exch(int[][] arrexch,int v,int w) {
    	int[] temp=arrexch[v];arrexch[v]=arrexch[w];arrexch[w]=temp;
    }
    private static boolean vertifymerged(int[][] arrvm) {
    	for(int i=1;i<arrvm.length;++i)
    		if(arrvm[i][0]<=arrvm[i-1][1]) return false;
    	return true;
    }
    public static void main(String[] args) {
    	int n=10;
    	int[][] a=
    	new int[n][2];
    	for(int i=0;i<n;++i) {
    		a[i][0]=(int)(Math.random()*100.0);
    		a[i][1]=(int)(Math.random()+2)*a[i][0];
    	}
    	//{{1,3},{0,4},{2,5}};
    	//{{8,10},{15,18},{1,3},{3,6}};
    	int[][] mergeresult=
    	//mergeplanb(a);
    	merge(a);
    	System.out.println(vertifymerged(mergeresult)?Arrays.deepToString(mergeresult):" damaged!");
    }
}