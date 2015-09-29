package ca.mcgill.ecse429.conformancetest.generator;

public class GenerateTests {

	public static void help(){
		System.out.println("Usage: " + GenerateTests.class.getSimpleName() + " stateModel.xml");
	}
	public static void main(String[] args) {
		if(args.length != 1){
			System.err.println("ERROR: No argument for xml file provided");
			help();
			return;
		}
		if (args[0].equals("--help") || args[0].equals("-h")){
			help();
			return;
		}
		
		new Generator(args[0]).generate();
	}

}
