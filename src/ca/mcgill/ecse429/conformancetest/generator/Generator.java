package ca.mcgill.ecse429.conformancetest.generator;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import ca.mcgill.ecse429.conformancetest.generator.tree.Node;
import ca.mcgill.ecse429.conformancetest.generator.tree.RoundTripPathTree;
import ca.mcgill.ecse429.conformancetest.statemodel.State;
import ca.mcgill.ecse429.conformancetest.statemodel.StateMachine;
import ca.mcgill.ecse429.conformancetest.statemodel.Transition;
import ca.mcgill.ecse429.conformancetest.statemodel.persistence.PersistenceStateMachine;

public class Generator {
	private StringBuilder testSuite;
	private StateMachine sm;
	private ArrayList<StringBuilder> testCases;
	private String sourceFile;
	
	public Generator(String xmlFile, String sourceFile){
		PersistenceStateMachine.loadStateMachine(xmlFile);
		this.testSuite = new StringBuilder();
		this.testCases = new ArrayList<StringBuilder>();
		this.sourceFile= sourceFile;
	}
	
	public void generate(){
		this.sm = StateMachine.getInstance();
		RoundTripPathTree tree = new RoundTripPathTree(this.sm);
		buildTestCases(tree.getRoot());
//		System.out.println(testSuite.toString());
		exportToFile();
	}
	
	private void exportToFile(){
	    try {
	    	File sourceFile = new File(this.sourceFile);
//		    if(sourceFile.exists()){
//		    	int i = 1;
//		    	while(!sourceFile.createNewFile()){
//		    		sourceFile = new File("C:/temp/Test" + i + ".java");
//		    		i++;
//		    	}
//		    }
	    	FileWriter writer = new FileWriter(sourceFile);
			writer.write(testSuite.toString());
			writer.close();
//		
//		    JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
//		    StandardJavaFileManager fileManager = compiler.getStandardFileManager(null, null, null);
//
//			fileManager.setLocation(StandardLocation.CLASS_OUTPUT,
//                    Arrays.asList(new File("C:/temp")));
//			
//			compiler.getTask(null, fileManager, null, null, null,
//		               fileManager.getJavaFileObjectsFromFiles(Arrays.asList(sourceFile)))
//		            .call();
//		    fileManager.close();
		    
	    } catch (IOException e) {
			e.printStackTrace();
		}
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
//				verifyCurrentState(sb, t.getFrom());
				setConditions(s, t);
				performNextTransition(s, t);
				verifyTransition(s, t);
				generateTestCases(s, n.moves.get(x));
			});
		}
		final int x = n.moves.size() - 1;
		n.moves.get(x).trans.ifPresent(t -> {
//			verifyCurrentState(sb, t.getFrom());
			setConditions(sb, t);
			performNextTransition(sb, t);
			verifyTransition(sb, t);
			generateTestCases(sb, n.moves.get(x));
		});
	}
	
	private void verifyCurrentState(StringBuilder s, State t){
		indent(s, 2); s.append("assertEquals(\"" + t.getName() + "\", klazz.getStateFullName());\n");
	}
	
	private void setConditions(StringBuilder s, Transition t){
		if(t.getCondition().length() > 0){
			String cond = t.getCondition();
			if (cond.matches("^get.*\\(\\)$")){
				indent(s, 2); s.append("klazz.set" + cond.substring(3, cond.length() - 1) + "true);\n");
			} else if (cond.matches("^!get.*\\(\\)$")){
				indent(s, 2); s.append("klazz.set" + cond.substring(4, cond.length() - 1) + "false);\n");
			} else if (cond.matches(".+[<>=]=[ 0-9]+")){
				String[] split = cond.split("[<>=]=");
				String method = "klazz.set" + split[0].trim().substring(0, 1).toUpperCase() + split[0].trim().substring(1);
				String value = split[1].trim();
				indent(s, 2); s.append(method + "(" + value + ");\n");				
			} else if (cond.matches(".+>[^=].+")){
				String[] split = cond.split(">");
				String method = "klazz.set" + split[0].trim().substring(0, 1).toUpperCase() + split[0].trim().substring(1);
				String value = split[1].trim();
				indent(s, 2); s.append(method + "(" + value + " + 1);\n");
			} else if (cond.matches(".+<[^=].+")){
				String[] split = cond.split(">");
				String method = "klazz.set" + split[0].trim().substring(0, 1).toUpperCase() + split[0].trim().substring(1);
				String value = split[1].trim();
				indent(s, 2); s.append(method + "(" + value + " - 1);\n");
			} else {
				indent(s, 2); s.append("// COND: " + cond + "\n");
			}
		}
	}
	
	private void verifyTransition(StringBuilder s, Transition t){
		verifyCurrentState(s, t.getTo());
		String action = t.getAction();
//		if(action.length() > 0){
//			String[] actions = action.split(";");
//			for (String a : actions){
//				if (a.length() > 0){
//					String[] act = a.trim().split(" ?= ?");
//					if(act.length != 2){
//						String x = "";
//						for (String y : act){
//							x = x + ", " + y;
//						}
//						throw new RuntimeException("ERROR: " + x);
//					} else {
//						String method = act[0].substring(0, 1).toUpperCase() + act[0].substring(1);
//						String arg = act[1];
//						if (arg.trim().matches(act[0].trim() + " ?[+-] ?[0-9]+")){
//							arg = arg.replace(act[0].trim(), "klazz.get" + method + "()");
//						} 
//						
//						indent(s, 2); s.append("// klazz.set" + method + "(" + arg + ");\n");
//					}
//				}
//			}
//		}

//		if (condition.length() > 0){
//			indent(s, 2); s.append("Condition: " + condition + "\n");
//			indent(s, 2); s.append("Event: " + event + "\n");
//			indent(s, 2); s.append("Action: " + action + "\n");
//		}
	}
	
	private void performNextTransition(StringBuilder s, Transition t){
		if(t.getEvent().length() > 0){
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
