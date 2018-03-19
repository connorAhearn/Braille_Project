package src.tests;

import org.junit.Test;
import src.enamel.ScenarioCreator;

public class testScenarioCreator {

	@Test
	public void testInit() {
		
		ScenarioCreator creator = new ScenarioCreator();
		creator.launch(creator.getClass());
	}
}
