package pl.kamsoft.nfz.parser;

import java.io.IOException;
import java.util.List;

import org.springframework.web.client.RestClientException;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonParseException;
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
			throw new RestClientException("Some problems by json generation", e);
		} catch (JsonMappingException e) {
			throw new RestClientException("Some problems by json mapping", e);
		} catch (IOException e) {
			throw new RestClientException("Other problem", e);
		}
	}
}
