package simpledatabase;
import java.util.ArrayList;

public class Join extends Operator{

	private ArrayList<Attribute> newAttributeList;
	private String joinPredicate;
	ArrayList<Tuple> tuples1;

	
	//Join Constructor, join fill
	public Join(Operator leftChild, Operator rightChild, String joinPredicate){
		this.leftChild = leftChild;
		this.rightChild = rightChild;
		this.joinPredicate = joinPredicate;
		newAttributeList = new ArrayList<Attribute>();
		tuples1 = new ArrayList<Tuple>();
		
	}

	
	/**
     * It is used to return a new tuple which is already joined by the common attribute
     * @return the new joined tuple
     */
	//The record after join with two tables
	@Override
	public Tuple next(){
		Tuple rightTuple;
		Tuple leftTuple;
		while (true) {
			leftTuple = leftChild.next();
			ArrayList<Attribute> AttributeList = new ArrayList<Attribute>();
			if (leftTuple == null){
				break;
			}
			else {
				for (Attribute leftTP : leftTuple.attributeList) {
					Attribute temp = new Attribute();
					temp.attributeName = leftTP.attributeName;
					temp.attributeType  = leftTP.attributeType;
					temp.attributeValue = leftTP.attributeValue;
					AttributeList.add(temp);
				}
				tuples1.add(new Tuple(AttributeList));
			}
		}
		while ((rightTuple = rightChild.next()) != null) {
			for (Attribute rightTA : rightTuple.attributeList){
				for (Tuple temp : tuples1){
					for (Attribute tupleTemp : temp.attributeList){
						if (rightTA.getAttributeValue().equals(tupleTemp.attributeValue)){
							newAttributeList = new ArrayList<Attribute>();
							for (Attribute tuples1At : temp.attributeList){ 
								newAttributeList.add(tuples1At);							
							}
							for (Attribute rightTA2 : rightTuple.attributeList){
								if (rightTA2.getAttributeValue().equals(tupleTemp.attributeValue));
								else newAttributeList.add(rightTA2);
							}
							return new Tuple(newAttributeList);
						}
					}
				}
			}
		}
		return null;
	}
	
	
	/**
     * The function is used to get the attribute list of the tuple
     * @return attribute list
     */
	public ArrayList<Attribute> getAttributeList(){
		if(joinPredicate.isEmpty())
			return child.getAttributeList();
		else
			return(newAttributeList);
	}

}