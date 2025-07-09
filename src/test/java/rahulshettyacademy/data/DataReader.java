package rahulshettyacademy.data;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;


public class DataReader {
	// DataReader class is responsible for reading data from JSON files and converting it into a suitable format for testing.
	public List<HashMap<String, String>> getJsonDataToMap() throws IOException {
		
		String jsonContent = FileUtils.readFileToString(new File(System.getProperty("user.dir") + "\\src\\test\\java\\rahulshettyacademy\\data\\PurchaseOrder.json"),StandardCharsets.UTF_8);
		// Convert the JSON content to a HashMap or any other data structure as needed.
		// For example, you can use a JSON library like Jackson or Gson to parse the JSON content.
		// Example using Jackson: 	
		ObjectMapper objectMapper = new ObjectMapper();
		List<HashMap<String, String>> data = objectMapper.readValue(jsonContent, new TypeReference<List<HashMap<String, String>>>(){});
		// This will read the JSON file and convert it into a List of HashMaps, where each HashMap represents a row in the JSON file.
		return data; // Return the list of HashMaps containing the data from the JSON file.
	}

}
