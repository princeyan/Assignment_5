package simpledatabase;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Sort extends Operator{
	
	private ArrayList<Attribute> newAttributeList;
	private String orderPredicate;
	ArrayList<Tuple> tuplesResult;
	private ArrayList<String> St = new ArrayList<String>();
	int position = -1;
	int count = 0;
	
	public Sort(Operator child, String orderPredicate){
		this.child = child;
		this.orderPredicate = orderPredicate;
		newAttributeList = new ArrayList<Attribute>();
		tuplesResult = new ArrayList<Tuple>();
	}
	
	/**
     * The function is used to return the sorted tuple
     * @return tuple
     */
	@Override
	public Tuple next(){
		while(true){
			Tuple tuple = child.next();
			if(tuple != null){
				tuplesResult.add(tuple);
			}
			else{
				for(Attribute a:tuplesResult.get(0).getAttributeList()){
					St.add(a.getAttributeName());
				}
				position = St.indexOf(orderPredicate);
				 if(position != -1){
						for(int x = 1; x < tuplesResult.size(); x++){
							Tuple temp = tuplesResult.get(x);
							String st = tuplesResult.get(x).getAttributeValue(position).toString();
							int j = x - 1;
							while(j >= 0 && tuplesResult.get(j).getAttributeValue(position).toString().compareTo(st) > 0){
								tuplesResult.set(j + 1, tuplesResult.get(j));
								j--;
							}
							tuplesResult.set(j + 1, temp);
						
					}
					
				}
				break;
			}
		}
		if(position == -1){
			if(count < tuplesResult.size()){
				return tuplesResult.get(count++);
			}
			else{
				return null;
			}
		}
		else if(count < tuplesResult.size()){
				return tuplesResult.get(count++);
		}
		else{
			return null;
		}
		
		
	}
	
	/**
     * The function is used to get the attribute list of the tuple
     * @return attribute list
     */
	public ArrayList<Attribute> getAttributeList(){
		return child.getAttributeList();
	}

}