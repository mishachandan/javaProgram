import java.util.Comparator;


public class MyComparator implements Comparator< FactKey> {

	@Override
	public int compare(FactKey o1, FactKey o2) {
		
		if(o1.getKey()>o2.getKey()){
			return -1;
		}else if(o1.getKey()==o2.getKey()){
			return 0;
		}else
			return 1;
	}
	

}
