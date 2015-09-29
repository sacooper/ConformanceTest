package ca.mcgill.ecse429.conformancetest.statemodel.persistence;

import java.util.Iterator;

import ca.mcgill.ecse429.conformancetest.statemodel.State;
import ca.mcgill.ecse429.conformancetest.statemodel.StateMachine;
import ca.mcgill.ecse429.conformancetest.statemodel.Transition;

public class PersistenceStateMachine {

	private static void initializeXStream(String filename) {
		PersistenceXStream.setFilename(filename);
		PersistenceXStream.setAlias("machine", StateMachine.class);
		PersistenceXStream.setAlias("state", State.class);
		PersistenceXStream.setAlias("transition", Transition.class);
	}

	public static void loadStateMachine(String filename) {
		StateMachine sm = StateMachine.getInstance();
		PersistenceStateMachine.initializeXStream(filename);
		StateMachine sm2 = (StateMachine) PersistenceXStream.loadFromXMLwithXStream();
		if (sm2 != null) {
			// unfortunately, this creates a second StateMachine object, even though it is a singleton
			// copy loaded model into singleton instance of StateMachine, because this will be used throughout the application
			sm.setClassName(sm2.getClassName());
			sm.setPackageName(sm2.getPackageName());
			sm.setStartState(sm2.getStartState());
			Iterator<State> sIt = sm2.getStates().iterator();
			while (sIt.hasNext())
				sm.addState(sIt.next());
			Iterator<Transition> tIt = sm2.getTransitions().iterator();
			while (tIt.hasNext())
				sm.addTransition(tIt.next());
		}
	}
	
	public static void saveStateMachine(String filename) {
		StateMachine sm = StateMachine.getInstance();
		PersistenceStateMachine.initializeXStream(filename);
		PersistenceXStream.saveToXMLwithXStream(sm);
	}

}
