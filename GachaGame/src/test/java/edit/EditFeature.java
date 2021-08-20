package edit;

import com.intuit.karate.junit5.Karate;

public class EditFeature {
	@Karate.Test
	Karate testLogin() {
		return Karate.run("edit").relativeTo(getClass());
	}	
}
