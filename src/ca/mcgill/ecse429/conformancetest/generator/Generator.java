package ca.mcgill.ecse429.conformancetest.generator;
import ca.mcgill.ecse429.conformancetest.generator.tree.RoundTripPathTree;
import ca.mcgill.ecse429.conformancetest.statemodel.*;
import ca.mcgill.ecse429.conformancetest.statemodel.persistence.*;

public class Generator {
	public Generator(String xmlFile){
		PersistenceStateMachine.loadStateMachine(xmlFile);
	}
	
	public void generate(){
		StateMachine aStateMachine = StateMachine.getInstance();
		RoundTripPathTree tree = new RoundTripPathTree(aStateMachine);
		
	}
}
