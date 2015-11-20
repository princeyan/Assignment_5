package simpledatabase;
import java.util.ArrayList;

public class Projection extends Operator{
	
	ArrayList<Attribute> newAttributeList;
	private String attributePredicate;


	public Projection(Operator child, String attributePredicate){
		
		this.attributePredicate = attributePredicate;
		this.child = child;
		newAttributeList = new ArrayList<Attribute>();
	
	}
	
	
	/**
     * Return the data of the selected attribute as tuple format
     * @return tuple
     */
	@Override
	public Tuple next(){
		Tuple tuple  = child.next();
		String compare;
		ArrayList<Attribute> Attributes = new ArrayList<Attribute>();

		if(tuple != null){
			for(int i = 0 ; i < tuple.getAttributeList().size(); i++){
				compare =tuple.getAttributeList().get(i).getAttributeName();
				if (compare.equals(attributePredicate)){
					Attributes.add(tuple.getAttributeList().get(i));
					
				}					
			}
			return new Tuple(Attributes);
		}
		return null;
	}
	
	/**
     * The function is used to get the attribute list of the tuple
     * @return attribute list
     */
	public ArrayList<Attribute> getAttributeList(){
		return child.getAttributeList();
	}

}