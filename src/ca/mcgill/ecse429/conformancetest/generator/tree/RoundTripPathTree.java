package ca.mcgill.ecse429.conformancetest.generator.tree;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

import ca.mcgill.ecse429.conformancetest.statemodel.State;
import ca.mcgill.ecse429.conformancetest.statemodel.StateMachine;

public class RoundTripPathTree {
	private class Node {
		public State state;
		public List<Node> moves;
		
		public Node(State state){
			this.state = state;
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
			s.append(getDepth(i) + "-" + state.getName() + "\n");
			if(moves != null){
				for (Node n : moves){
					s.append(n.toString(i+ 1));
				}
			}	
			return s.toString();
		}
	}
	
	private Node root;
	private StateMachine sm;
	private HashSet<State> seen;
	
	public RoundTripPathTree(StateMachine sm){
		this.root = new Node(sm.getStartState());
		this.sm = sm;
		seen = new HashSet<State>();
		buildTree(root);
		System.out.println(root.toString());
	}
	
	public void buildTree(Node start){
		seen.add(start.state);
		start.moves = this.sm.getTransitions()
				.stream()
				.filter(t -> t.getFrom().equals(start.state))
				.map(t -> new Node(t.getTo()))
				.collect(Collectors.toList());
		
		start.moves
				.stream()
				.filter(s -> !seen.contains(s.state))
				.forEach(this::buildTree);
	}
	
}
