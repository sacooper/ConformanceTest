package ca.mcgill.ecse429.conformancetest.generator.tree;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import ca.mcgill.ecse429.conformancetest.statemodel.State;
import ca.mcgill.ecse429.conformancetest.statemodel.Transition;

public class Node {
	public State state;
	public Optional<Transition> trans;
	public List<Node> moves;
	
	public Node(State state, Optional<Transition> trans){
		this.state = state;
		this.trans = trans;
		this.moves = new ArrayList<Node>();
	}
	
	private String getDepth(int i){
		StringBuilder s = new StringBuilder();
		for (int x = 0; x < i; x++){
			s.append(' ');
		}
		return s.toString();
	}
	
	public String toString(){
		return this.toString(0);
	}
	
	public String toString(int i){
		StringBuilder s = new StringBuilder();
		if (this.trans.isPresent()){
			String t = 
					trans.get().getAction() + ", " + 
					trans.get().getCondition() + ", " +
					trans.get().getEvent();
			
			s.append(getDepth(i) + "-" + state.getName() + ": " + t + "\n");
		} else {
			s.append(getDepth(i) + "-" + state.getName() + "\n");
		}
		
		for (Node n : moves){
			s.append(n.toString(i+ 1));
		}
		return s.toString();
	}
}