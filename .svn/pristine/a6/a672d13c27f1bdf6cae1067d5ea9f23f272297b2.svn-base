package pl.kamsoft.nfz.parser;

import java.io.IOException;
import java.util.List;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.playerony.libraryV2.model.Author;

public class AuthorParser {
	public static List<Author> getAuthors(String json) {
		try {
			ObjectMapper mapper = new ObjectMapper();
			
			List<Author> authors = mapper.readValue(json, new TypeReference<List<Author>>(){});
			
			return authors;
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
