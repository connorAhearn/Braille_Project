package src.tests;

import org.junit.Test;

<<<<<<< HEAD
import FactoryScenarios.AudioFiles.ScenarioCreator;
=======
import src.enamel.*;
>>>>>>> branch 'master' of https://github.com/connorAhearn/Braille_Project

public class testScenarioCreator {

	@Test
	public void testInit() {
		
		ScenarioCreator creator = new ScenarioCreator();
		creator.launch(creator.getClass());
	}
}
