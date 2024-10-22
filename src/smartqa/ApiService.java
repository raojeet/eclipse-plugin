package smartqa;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import org.json.JSONObject;

public class ApiService {

    // Method to perform login
    public static JSONObject login(String hostUrl, String username, String password, String pytestDir, String pythonFile) throws Exception {
        // Prepare the URL for the login API
        @SuppressWarnings("deprecation")
		URL url = new URL(hostUrl);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        
        // Configure the connection
        connection.setRequestMethod("POST");
        connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
        connection.setDoOutput(true);

        // Prepare the request body
        String urlParameters = "username=" + username
                + "&password=" + password
                + "&pytestDir=" + pytestDir
                + "&pythonFile=" + pythonFile;

        byte[] postData = urlParameters.getBytes(StandardCharsets.UTF_8);

        // Send the request
        try (DataOutputStream wr = new DataOutputStream(connection.getOutputStream())) {
            wr.write(postData);
        }

        // Get the response
        int responseCode = connection.getResponseCode();
        if (responseCode != HttpURLConnection.HTTP_OK) {
            throw new Exception("Failed to log in: HTTP error code " + responseCode);
        }

        // Read the response
        BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        String inputLine;
        StringBuilder content = new StringBuilder();
        while ((inputLine = in.readLine()) != null) {
            content.append(inputLine);
        }

        // Close connections
        in.close();
        connection.disconnect();

        // Parse the response as JSON
        JSONObject jsonResponse = new JSONObject(content.toString());
        
        // Check if access token is present
        if (!jsonResponse.has("access_token")) {
            throw new Exception("Failed to retrieve access token");
        }

        return jsonResponse;
    }
}
