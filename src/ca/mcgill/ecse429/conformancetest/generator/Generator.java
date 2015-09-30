package ca.mcgill.ecse429.conformancetest.generator;
import java.util.ArrayList;

import ca.mcgill.ecse429.conformancetest.generator.tree.Node;
import ca.mcgill.ecse429.conformancetest.generator.tree.RoundTripPathTree;
import ca.mcgill.ecse429.conformancetest.statemodel.*;
import ca.mcgill.ecse429.conformancetest.statemodel.persistence.*;

public class Generator {
	private StringBuilder testSuite;
	private StateMachine sm;
	
	private ArrayList<StringBuilder> testCases;
	
	public Generator(String xmlFile){
		PersistenceStateMachine.loadStateMachine(xmlFile);
		testSuite = new StringBuilder();
		testCases = new ArrayList<StringBuilder>();
	}
	
	public void generate(){
		this.sm = StateMachine.getInstance();
		RoundTripPathTree tree = new RoundTripPathTree(this.sm);
		buildTestCases(tree.getRoot());
		System.out.println(testSuite.toString());
	}
	
	private void initializeTestCases(Node root, String className){		
		testSuite.append("package " + sm.getPackageName() + ";\n");
		testSuite.append("import " + sm.getPackageName() + ".*;\n");
		testSuite.append("import static org.junit.Assert.*;\n");
		testSuite.append("import org.junit.Before;\n");
		testSuite.append("import org.junit.Test;\n\n");
		testSuite.append("public class " + className + "Test " + "{\n");
		
		indent(1); testSuite.append("private " + className + " klazz;\n\n");
		indent(1); testSuite.append("@Before\n");
		indent(1); testSuite.append("public void setUp() throws Exception {\n");
		indent(2); testSuite.append("klazz = new " + className + "();\n");
		
		if(!root.moves.isEmpty()){
			root.moves.get(0).trans.ifPresent(t -> this.verifyTransition(testSuite, t));
		}
		
		indent(1); testSuite.append("}\n");
	}
	
	private void finalizeTestCases(){
		for (int i = 0; i < testCases.size(); i++){
			indent(1); testSuite.append("@Test\n");
			indent(1); testSuite.append("public void testCase" + i + "() {\n");
			testSuite.append(testCases.get(i).toString());
			indent(1); testSuite.append("}\n\n");
		}
		
		testSuite.append("}");
	}
	
	private void buildTestCases(Node root){
		String className = sm.getClassName().substring(0, sm.getClassName().indexOf('.'));
		initializeTestCases(root, className);
		
		StringBuilder sb = new StringBuilder();
		testCases.add(sb);
		if(!root.moves.isEmpty()){
			generateTestCases(sb, root.moves.get(0));
		}
		finalizeTestCases();
	}
	
	private void generateTestCases(StringBuilder sb, Node n){
		if(n.moves.isEmpty()){
			return;
		}
		
		for(int i = 0; i < n.moves.size() - 1; i++){
			StringBuilder s = new StringBuilder();
			testCases.add(s);
			s.append(sb.toString());
			final int x = i;
			n.moves.get(i).trans.ifPresent(t -> {
				performNextTransition(s, t, n.moves.get(x));
				verifyTransition(s, t);
				generateTestCases(s, n.moves.get(x));
			});
		}
		final int x = n.moves.size() - 1;
		n.moves.get(x).trans.ifPresent(t -> {
			verifyCurrentState(sb, t.getFrom());
			performNextTransition(sb, t, n.moves.get(x));
			verifyTransition(sb, t);
			generateTestCases(sb, n.moves.get(x));
		});
	}
	
	private void verifyCurrentState(StringBuilder s, State t){
		indent(s, 2); s.append("assert(klazz.getStateFullName().equals(\"" + t.getName() + "\"));\n");
	}
	
	private void verifyTransition(StringBuilder s, Transition t){
		verifyCurrentState(s, t.getTo());
//		indent(s, 2); 
		String condition = t.getCondition();
		String event = t.getEvent();
		String action = t.getAction();

//		if (condition.length() > 0){
//			indent(s, 2); s.append("Condition: " + condition + "\n");
//			indent(s, 2); s.append("Event: " + event + "\n");
//			indent(s, 2); s.append("Action: " + action + "\n");
//		}
	}
	
	private void performNextTransition(StringBuilder s, Transition t, Node n){
		if(t.getAction().length() > 0){
			indent(s, 2); s.append("klazz." + t.getEvent() + "();\n");
		}
	}
	
	private void indent(StringBuilder test, int depth){
		for (int i = 0; i < depth; i++){
			test.append("  ");
		}
	}
	
	private void indent(int depth){
		indent(testSuite, depth);
	}
}
