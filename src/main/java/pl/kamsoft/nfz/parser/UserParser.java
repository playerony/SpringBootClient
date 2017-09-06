package pl.kamsoft.nfz.parser;

import java.io.IOException;
import java.util.List;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.playerony.libraryV2.model.User;

public class UserParser {
	public static List<User> getUsers(String json) {
		try {
			ObjectMapper mapper = new ObjectMapper();
			
			List<User> users = mapper.readValue(json, new TypeReference<List<User>>(){});
			
			return users;
		} catch (JsonGenerationException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return null;
	}
}
