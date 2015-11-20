package simpledatabase;

import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Table extends Operator{
	private BufferedReader br = null;
	private boolean getAttribute = false;
	private Tuple tuple;

	
	public Table(String from){
		this.from = from;
		
		//Create buffer reader
		try{
			br = new BufferedReader(new InputStreamReader(getClass().getResourceAsStream("/datafile/"+from+".csv")));
		}
		catch (Exception e) {
			e.printStackTrace();
		} 
		
	}

	
	/**
     * Create a new tuple and return the tuple to its parent.
     * Set the attribute list if you have not prepare the attribute list
     * @return the tuple
     */
	@Override
	public Tuple next(){
		String col1 , col2 , col3;
		try{
			if(getAttribute == false){ 	
				col1 = br.readLine();
				col2 = br.readLine();
				col3 = br.readLine();
				tuple = new Tuple(col1,col2,col3);
				tuple.setAttributeName();
				tuple.setAttributeType();
				tuple.setAttributeValue();
				getAttribute = true;
				return tuple;
			}
		}
		catch (IOException e) {
			e.printStackTrace();
		}	
		
		try {
			if ((col3 = br.readLine()) != null ){
				tuple.col2 = col3.split(",");
				tuple.setAttributeValue();
				return tuple;
			}
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	

	/**
     * The function is used to get the attribute list of the tuple
     * @return the attribute list
     */
	public ArrayList<Attribute> getAttributeList(){
		return tuple.getAttributeList();
	}
	
}