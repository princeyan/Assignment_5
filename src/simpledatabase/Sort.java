package simpledatabase;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Sort extends Operator{
	
	private ArrayList<Attribute> newAttributeList;
	private String orderPredicate;
	ArrayList<Tuple> tuplesResult;
	int position = 0;
	int tuplePosition = 0;

	
	public Sort(Operator child, String orderPredicate){
		this.child = child;
		this.orderPredicate = orderPredicate;
		newAttributeList = new ArrayList<Attribute>();
		tuplesResult = new ArrayList<Tuple>();
		
		while (true) {
			Tuple tuple = child.next();
			if (tuple == null) break;
			tuplesResult.add(tuple);
		}

		
		Tuple min = new Tuple(tuplesResult.get(0).getAttributeList());
		
		//for (Tuple cur : tuplesResult){
			for (Attribute a : min.getAttributeList()){
				if (a.attributeName.equals(orderPredicate))
					position = min.getAttributeList().indexOf(a);
			}
			/*if ((cur.getAttributeValue(position).toString()).compareTo(min.getAttributeValue(position).toString()) <= 0){
				min = new Tuple(cur.attributeList);
			}*/
		//}
		for (int i = 0; i < tuplesResult.size(); i++){
			if (tuplesResult.get(i).getAttributeValue(0).equals(min.getAttributeValue(0)))
				tuplePosition = i;
		}

		Collections.sort(tuplesResult, new Comparator<Tuple>() {
		@Override
		public int compare(Tuple tupleX, Tuple tupleY)
		{
			return String.valueOf(tupleX.getAttributeValue(position)).compareTo(String.valueOf(tupleY.getAttributeValue(position)));
		}

		});
		
	}
	
	
	/**
     * The function is used to return the sorted tuple
     * @return tuple
     */
	@Override
	public Tuple next(){
		ArrayList<Tuple> tuplesTemp = new ArrayList<Tuple>();

		while (tuplesResult.size() > 0){
			tuplesTemp = new ArrayList<Tuple>();
			Tuple min = new Tuple(tuplesResult.get(0).getAttributeList());

			tuplesResult.remove(tuplePosition);
			tuplesTemp.add(min);
			return tuplesTemp.get(0);
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