function fn() {
	var env = karate.env; // get the java system property 'karate.env'
	
	if (!env) {
		env = 'dev'; // a custom default
	}

	var config = {
		loginUrl: 'http://localhost:8080/users',
		michaelInventory: 'http://localhost:8080/users/Michael/inventory',
		gachas: 'http://localhost:8080/gachas'
	}
	
	// don't waste time waiting for a server I didn't start
	karate.configure('connectTimeout', 5000);
	karate.configure('readTimeout', 5000);
	return config;
}