package summon;

import com.intuit.karate.junit5.Karate;

public class SummonFeature {
	@Karate.Test
	Karate testSummon() {
		return Karate.run("summon").relativeTo(getClass());
	}
}
