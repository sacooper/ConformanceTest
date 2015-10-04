package ca.mcgill.ecse429.conformancetest.generator.tree;

import java.util.HashSet;
import java.util.Optional;
import java.util.stream.Collectors;

import ca.mcgill.ecse429.conformancetest.statemodel.State;
import ca.mcgill.ecse429.conformancetest.statemodel.StateMachine;

public class RoundTripPathTree {	
	private Node root;
	private StateMachine sm;
	private HashSet<State> seen;
	
	/**
	 * Create a new RoundTripPathTree
	 * @param sm	State Machine to generate from
	 */
	public RoundTripPathTree(StateMachine sm){
		this.root = new Node(sm.getStartState(), Optional.empty());
		this.sm = sm;
		seen = new HashSet<State>();
		buildTree(root);
	}
	
	private void buildTree(Node start){
		seen.add(start.state);
		start.moves = this.sm.getTransitions()
				.stream()
				.filter(t -> t.getFrom().equals(start.state))
				.map(t -> new Node(t.getTo(), Optional.of(t)))
				.collect(Collectors.toList());
		
		start.moves.stream()
				.filter(s -> !seen.contains(s.state))
				.forEach(this::buildTree);
	}
	
	/**
	 * Get the root of the tree
	 * @return	The Node of the root of the tree
	 */
	public Node getRoot(){
		return root;
	}
	
}
