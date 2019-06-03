package tree;


import java.util.ArrayList;
import java.util.HashMap;

public class Tree {
	int x;
	Tree l;
	Tree r;
		
	static ArrayList<Integer> currentPath = new ArrayList<Integer>();
	static HashMap<String, Integer> myHash = new HashMap<String, Integer>();
	static ArrayList<String> visiblePath = new ArrayList<String>();
	
	public Tree(int x, Tree l, Tree r){
		this.x = x;
		this.l = l;
		this.r = r;
	}
	
	public Tree(int x){
		this.x = x;
		this.l = null;
		this.r = null;
	}
	
	public Tree getL() {
		return this.l;
	}
	
	public Tree getR() {
		return this.r;
	}
	
	public int getX() {
		return this.x;
	}
	
	public void setX(int x) {
		this.x = x;
	}
	
	public void setL(Tree l) {
		this.l = l;
	}
	
	public void setR(Tree r) {
		this.r = r;
	}
	
	String getCurrentPath(){
		
		//This function returns the description of current path
		String s = "";
		for(int i : currentPath){
			s += Integer.toString(i);
		}
		return s;
	}
		
	void makeValuePathHashMap(Tree t){
		
		/*
		* This function travel recursively the tree, make a HashMap who containning "path - value" of the element
		* and make a arrayList of "visibles paths"
		*/
		
		try {
			if(t != null){
				//System.out.println(t.getX() + "\tCurrent path is: " + getCurrentPath());
				myHash.put(getCurrentPath(), t.getX());
				
				if(t.getL() != null){
					currentPath.add(0);
					t.makeValuePathHashMap(t.getL());
					currentPath.remove(currentPath.size()-1);

					
					if(t.getR() != null){
						currentPath.add(1);
						t.makeValuePathHashMap(t.getR());
						currentPath.remove(currentPath.size()-1);

					}
				}else if(t.getR() != null){
					currentPath.add(1);
					t.makeValuePathHashMap(t.getR());
					currentPath.remove(currentPath.size()-1);
				}else {
					visiblePath.add(getCurrentPath());
				}
				
				
				
			}
		} catch (Exception e) {
			System.err.println(e);
		}
		
	}
	
	public String getVisibleNodes(Tree t){
		
		/*
		 * This function returns the result.
		 * It find the visible element on each path using visiblePaths arraylist
		 */
		
		Tree localT = t;
		ArrayList<Integer> visibleNodes = new ArrayList<Integer>();
		ArrayList<Integer> valuesOnThePath = new ArrayList<Integer>();
		
		int root = t.getX();
		valuesOnThePath.add(root);
		
		try{
			for(String s : visiblePath){
				
				for(int j=0; j<s.length(); j++){
					char c = s.charAt(j);
					
					if(c == '0'){
						localT = localT.getL();
						valuesOnThePath.add(localT.getX());
					}else if(c == '1'){
						localT = localT.getR();
						valuesOnThePath.add(localT.getX());
					}
				}
				
				//Find the max value on the path
				int max = root;
				for(int i=0; i<valuesOnThePath.size(); i++){
					if(valuesOnThePath.get(i)>max){
						max = valuesOnThePath.get(i);
					}
				}
				
				if(max != root){
					visibleNodes.add(max);
				}
				
				valuesOnThePath.clear();
				localT = t;
				
			}
		
		}catch(Exception e){
			
		}
		
		String stringVisibleNodes = Integer.toString(root) + " ";
		
		for(int i : visibleNodes){
			stringVisibleNodes += Integer.toString(i) + " ";
		}
		
		return "There are " + (visibleNodes.size()+1) + " visibles nodes:" + stringVisibleNodes;
		
	}
	
	public static void main(String[] args) {
		Tree t = new Tree(3,
				new Tree(7,new Tree(10), new Tree(12)), 
				new Tree(6, new Tree(5, new Tree(20), null), new Tree(9, null, new Tree(4))));
				
		t.makeValuePathHashMap(t);
		
		/*
		 * 
		for(String i : myHash.keySet()){
			System.out.println(myHash.get(i) + "\tCurrent path is: " + i);
		}
		
		for(String s : visiblePath){
			System.out.println(s);
		}
		*/
		System.out.println(t.getVisibleNodes(t));
		
		
		
	}
	
}


















