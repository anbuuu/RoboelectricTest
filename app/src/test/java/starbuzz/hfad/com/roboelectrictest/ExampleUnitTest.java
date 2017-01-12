package starbuzz.hfad.com.roboelectrictest;

import org.apache.maven.model.Build;
import org.json.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(RobolectricTestRunner.class)
@Config(constants = BuildConfig.class, sdk = 25)
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() throws Exception {
        assertEquals(4, 2 + 2);
    }

    @Test
    public void testActivity() throws Exception {
        //assertTrue(Robolectric.setupActivity(MainActivity.class) != null);
        // Need to call the Service API to get the JSON
        HttpURLConnection urlConnection = null;

        try {
            URL url = new URL("https://dl.dropboxusercontent.com/u/746330/facts.json");
            urlConnection = (HttpURLConnection) url.openConnection();

        } catch (Exception ex) {

        }
        // Initiate GET Request
        urlConnection.setRequestMethod("GET");

        int statusCode = urlConnection.getResponseCode();

        // Check if the status Code is Ok,
        if (statusCode == 200) {

            BufferedReader r = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
            StringBuilder response = new StringBuilder();
            String line;
            while ((line = r.readLine()) != null) {
                response.append(line);
            }
            JSONObject jsonResponse = new JSONObject(response.toString());

            assertEquals(jsonResponse.optString("title"), "About Canada");
        }
    }
}