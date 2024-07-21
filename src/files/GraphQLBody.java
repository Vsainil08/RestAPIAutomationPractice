package files;

public class GraphQLBody {
	
	public static String Mutation()
	{
	String body="{\"query\": \r\n"
			+ "\"mutation($locationName:String!,$characterName:String!,$episodeName:String!)\\n{\\n  createLocation(location: {name:$locationName, type: \\\"West\\\", dimension: \\\"048\\\"}) {\\n    id\\n  }\\n  createCharacter(character: {name:$characterName, type: \\\"Agent\\\", status: \\\"Resting\\\", species: \\\"Alpha\\\", gender: \\\"Godly\\\", image: \\\"Villian\\\", originId: 11496, locationId: 11496}) {\\n    id\\n  }\\n  createEpisode(episode: {name:$episodeName, air_date: \\\"Unknown 2020\\\", episode: \\\"20\\\"}) {\\n    id\\n  }\\n  deleteLocations(locationIds: [11500]) {\\n    locationsDeleted\\n  }\\n}\\n\",\r\n"
			+ "\"variables\"\r\n"
			+ ": \r\n"
			+ "{\"locationName\": \"India\", \"characterName\": \"Jokes\", \"episodeName\": \"The Witcherant\"}}";
		
		return body;
	}
	
	public static String Query()
	{
	String query= "{\"query\"\r\n"
			+ ": \r\n"
			+ "\"query ($characterid: Int!, $locationid: Int!,$episodeid:Int!) {\\n  character(characterId: $characterid) {\\n    name\\n    gender\\n    status\\n    id\\n  }\\n  location(locationId: $locationid) {\\n    name\\n    dimension\\n    }\\n  \\n  episode(episodeId:$episodeid)\\n  {\\n    name\\n    air_date\\n    episode\\n  }\\n}\\n\",\r\n"
			+ "\"variables\"\r\n"
			+ ": \r\n"
			+ "{\"characterid\": 7402, \"locationid\": 11507, \"episodeid\": 9017}}";
		
		return query;
	}

}
