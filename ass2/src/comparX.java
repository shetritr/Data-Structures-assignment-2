import java.util.Comparator;

public class comparX implements Comparator {

	@Override
	public int compare(Object o1, Object o2) {
		
		return ((Point)o1).getX()-((Point)o2).getX();
	}

}
