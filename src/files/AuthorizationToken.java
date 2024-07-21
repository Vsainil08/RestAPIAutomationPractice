package files;

public class AuthorizationToken {
	
	
	public static String Token() {
		
		return " Basic dmlzaGFsMDgxMjk2QGdtYWlsLmNvbTpBVEFUVDN4RmZHRjA1WngyUnQzOHFWc"
				+ "FphMkNkXzlzdGNud3FvV0l4M3lQaGtWLUQ3cEVHbThNNlphRWgyNWNvMDNTZGF2YjM"
				+ "5VktGSVRoeWFWQWxYaFVKZ1RfcW12eG1IaHYzU1pJaWdUaVdhdEhBaHoxR"
				+ "EhfRzQ5MkhGZXIyR2I1QThTSVM2ZnhZUmU1OFBNYXNydXRyTzVuVDRsaUlBNHFkbUpmU0VlaEFMcWh6cDc4ZHloRWs9QjNFM0Y2OUU=";
	}
	
	public static String Body()
	{
		return "{\r\n"
				+ "    \"fields\": {\r\n"
				+ "       \"project\":\r\n"
				+ "       {\r\n"
				+ "          \"key\": \"SCRUM\"\r\n"
				+ "       },\r\n"
				+ "       \"summary\": \"Original\",\r\n"
				+ "       \"description\": \"Lesson Learnt\",\r\n"
				+ "       \"issuetype\": {\r\n"
				+ "          \"name\": \"Bug\"\r\n"
				+ "       }\r\n"
				+ "   }\r\n"
				+ "}";
	}

}
