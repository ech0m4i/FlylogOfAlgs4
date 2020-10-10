/*tailrecbitmultiply.java*/
package test;
import algs4.*;
public class tailrecbitmultiply {
	private static int w=0;
	//private static int[] w=new int[1];
	public static int Multiply(int a,int b) {
		//currying.
		return tr(a,b,0);
		//return dc(a,b);
	}
	private static int dc(int a,int b) {
		//d.& c.
		if(a<b) return dc(b,a);
		if(b==0) {return a;}
		if((b&1)==0) return dc(a,b>>1)+dc(a,b>>1);
		else if(b>1&&((b&1)==1)) return dc(a,b>>1)+dc(a,b-(b>>1));
		else return dc(a,0);//wtf it's correct? (...what happened(read again after about 2 days))
	}
	/*public static int solution(int a,int b,int t) {
		//dc with tr. no way they can't exist at one time.
        if(a<b) return solution(b,a);
        //StdOut.println(a+" above "+b+" "+t[0]);
        if(b==0) {
        	//StdOut.println(a+" "+b+" "+t[0]);
        	return 0;
        }
        else if((b&1)==0) {
        	//StdOut.println("=0");
        	//t[0]+=a;
        	solution(a,b>>1);
        	solution(a,b>>1);
        }
        else if(b>1&&((b&1)==1)) {
        	//StdOut.println("=1");
        	//t[0]+=a;
        	solution(a,b>>1);
        	solution(a,b-(b>>1));
        }
        else {
        	w+=a;
        	//t[0]+=a;
        	solution(a,0);
        }
        return w;
        //return t[0];
    }*/
	private static int tr(int a,int b,int t) {
		//tail recursion.
        if(a<b) return tr(b,a,t);
        if(b==0) return t;
        return tr(a,b-1,t+a);
	}
	public static void main(String[] args) {
		StdOut.println(Multiply(100,25));
	}
}
//so what's usefulness of this implementation? the "for" can also do this. -> got it, 2 way diff just.