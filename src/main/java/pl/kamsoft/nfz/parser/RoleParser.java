package pl.kamsoft.nfz.parser;

import java.io.IOException;
import java.util.List;

import org.springframework.web.client.RestClientException;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.playerony.libraryV2.model.Role;

public class RoleParser {
	public static List<Role> getRoles(String json) {
		try {
			ObjectMapper mapper = new ObjectMapper();
			
			List<Role> roles = mapper.readValue(json, new TypeReference<List<Role>>(){});
			
			return roles;
		} catch (JsonGenerationException e) {
			throw new RestClientException("Some problems by json generation", e);
		} catch (JsonMappingException e) {
			throw new RestClientException("Some problems by json mapping", e);
		} catch (IOException e) {
			throw new RestClientException("Other problem", e);
		}
	}
}
