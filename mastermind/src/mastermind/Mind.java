package mastermind;

public class Mind {
	String[] tips = new String[100];
	String[] ergs = new String[100];
	String tip = new String();
	String code = new String("HGEF");
	String erg = new String();
	int counter = 0;
	
	public void start() {
		// TODO Auto-generated method stub
		do {
		boolean ok;
		do {
			tip = generiereTip(tip);
			ok = true;
			for(int j=0;j<counter;j++) {
				// wenn der aktuelle Tip richtig sein soll müssen bei den bisherigen Tips die gleichen
				// Ergebnisse rauskommen wenn man diese gegen diesen Tip validiert. Falls nicht, neuen Tip generieren
				if (!bewerteTip(tip,tips[j]).equals(ergs[j])) {
					ok = false;
					break;
				}
			}
		} while (!ok) ;
		erg = bewerteTip(code,tip);
		tips[counter] = tip;
		ergs[counter] = erg;
		counter++;
		System.out.println(tip+" "+erg);
		} while (counter<100 && !tip.equals("ZZZZ") && !erg.equals("ssss"));
		
		if(erg.equals("ssss")) System.out.printf(":-) Geloest in #Schritten: %d",counter); else System.out.println("Leider verloren");
	}

 	/**
 	 * @param tip2
 	 * @return
 	 */
	private String bewerteTip(String code, String tip) {
		// TODO Auto-generated method stub
		char[] e = new char[4];
		char[] c = new char[4];
		char[] t = new char[4];
		int counter = 0;

		c = code.toCharArray();
		t = tip.toCharArray();

		for(int i=0;i<4;i++)
		{
			if (c[i]==t[i]) {
				e[counter] = 's'; // genau gleich --> schwarzer Stift
				c[i] = 'x'; // diese Stelle aus dem Code löschen
				counter++;
			}
		}
		code = new String(c); 
		for(int i=0;i<4;i++)
		{
			int pos = code.indexOf(t[i]);
			if (pos>-1) {
				e[counter]= 'w'; // kommt vor aber an anderer Stelle (sonst wäre es schon weggeXt)
				c[pos] = 'x'; // diese Stelle aus dem Code löschen
				code = new String(c); // aktualisertes Array übernehmen
				counter++;
			}
		} 
		return new String(e);
	}

	private String generiereTip(String tip) {
		char[] t = new char[4];
		
		if (tip.isEmpty()) return new String("AAAA");
		
		t = tip.toCharArray();
		
		t[3]++; // letzten Buchstaben um eins erhöhen
		for (int i=3;i>-1;i--) {
			if (t[i] > 'Z') {
				if (i==0) break;
				t[i] = 'A';
				t[i-1]++;
			}			
		}
		return new String(t);
		
	}

}
 