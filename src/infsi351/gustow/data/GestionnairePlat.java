package infsi351.gustow.data;

import java.util.Hashtable;

public class GestionnairePlat {
	private Hashtable<Long,Plat> map;
	private long n;
	
	public GestionnairePlat(){
		map=new Hashtable<Long,Plat>();
		n=0;
	}
	
	public void testBourrin() {
		Plat p1=new Plat("opera", 200, "", "a");
		Plat p2=new Plat("salut", 20, "", "aaaaaa");
		Plat p3=new Plat("bbbbbbb", 230, "", "cccccccc");
		
		this.put(p1);
		this.put(p2);
		this.put(p3);
	}

	public Plat get(long i) {
		return map.get((Long) i);
	}

	public void put(Plat p) {
		p.setId(n);
		map.put((Long) n, p);
		n++;

	}
}
