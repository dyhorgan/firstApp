package com.example.numad22sp_dylanhorgan;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputLayout;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URI;
import java.net.URL;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;

import cz.msebera.android.httpclient.Header;

public class ServiceActivity extends AppCompatActivity {
    Button submit;
    TextView input;
    TextView resultArea;
    ImageView img;
    HashMap<String, String> teamMap = new HashMap<>();
    private static String TAG = "WebServiceActivity";


  public void callWebserviceButtonHandler(View view){
    PingWebServiceTask task = new PingWebServiceTask();
    task.execute(input.getText().toString()); // This is a security risk.  Don't let your user enter the URL in a real app.
  }

  @Override
    protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_service);

    teamMap.put("Heat", "3435");
    teamMap.put("Hornets", "3430");
    teamMap.put("Warriors", "3428");
    teamMap.put("Spurs", "3429");
    teamMap.put("Hawks","3423");
    teamMap.put("Celtics", "3422");
    teamMap.put("Rockets", "3412");
    teamMap.put("Grizzlies", "3415");
    teamMap.put("Bulls", "3409");
    teamMap.put("Timberwolves", "3426");
    teamMap.put("Pelicans", "5539");
    teamMap.put("Jazz", "3434");
    teamMap.put("Wizards", "3431");
    teamMap.put("Bucks", "3410");
    teamMap.put("Magic", "3437");
    teamMap.put("Lakers", "3427");
    teamMap.put("Nuggets", "3417");
    teamMap.put("Knicks", "3421");
    teamMap.put("Kings","3413");
    teamMap.put("Nets", "3436");
    teamMap.put("Suns", "3416");
    teamMap.put("76ers", "3420");
    teamMap.put("Clippers", "3425");
    teamMap.put("Blazers", "3414");
    teamMap.put("Raptors", "3433");
    teamMap.put("Pacers","3419");
    teamMap.put("Cavaliers", "3432");
    teamMap.put("Pistons", "3424");
    teamMap.put("Mavericks", "3411");
    teamMap.put("Thunder", "3418");

    Button submitButton = findViewById(R.id.serviceSubmit);
    TextView serviceInput = findViewById(R.id.serviceUserInput);
    TextView resultView = findViewById(R.id.resultTextView);
    input = serviceInput;
    submit = submitButton;
    resultArea = resultView;


    submit.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        callWebserviceButtonHandler(input);

      }
    });
  }



    private class PingWebServiceTask  extends AsyncTask<String, Integer, String[]> {

        @Override
        protected void onProgressUpdate(Integer... values) {
            Log.i(TAG, "Loading...");
            View load = findViewById(R.id.loadingGif);
            load.setVisibility(View.VISIBLE);
        }

        @Override
        protected String[] doInBackground(String... params) {

          publishProgress();
          try {
            Thread.sleep(3000);
          } catch (InterruptedException e) {
            e.printStackTrace();
          }
            String[] results = new String[7];
            URL url = null;

            try {

                url = new URL( ("https://www.balldontlie.io/api/v1/players/" + params[0]));

                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setRequestMethod("GET");
                conn.setDoInput(true);

                conn.connect();


                // Read response.
                InputStream inputStream = conn.getInputStream();
                final String resp = convertStreamToString(inputStream);

//                JSONArray jArray = new JSONArray(resp);    // Use this if your web service returns an array of objects.  Arrays are in [ ] brackets.
                JSONObject jObject = new JSONObject(resp);
                String first = jObject.getString("first_name");
                String last = jObject.getString("last_name");
              String feet = jObject.getString("height_feet");
              String inches = jObject.getString("height_inches");
              String position = jObject.getString("position");
              JSONObject teamObj = jObject.getJSONObject("team");
              String teamName = teamObj.getString("name");
              String teamFullName = teamObj.getString("full_name");
                results[0] = first;
                results[1] = last;
                results[2] = feet;
                results[3] = inches;
                results[4] = position;
                results[5] = teamName;
                results[6] = teamFullName;

                return results;

            } catch (MalformedURLException e) {
                Log.e(TAG,"MalformedURLException");
                e.printStackTrace();
            } catch (ProtocolException e) {
                Log.e(TAG,"ProtocolException");
                e.printStackTrace();
            } catch (IOException e) {
                Log.e(TAG,"IOException");
                e.printStackTrace();
            } catch (JSONException e) {
                Log.e(TAG,"JSONException");
                e.printStackTrace();
            }
            results[0] = "Something went ";
            results[1] = "wrong";
            results[2] = "!";
            results[3] = "!";
            results[4] = "!";
            results[5] = "!";
            results[6] = "!";
            return(results);
        }

        @Override
        protected void onPostExecute(String... s) {
            super.onPostExecute(s);
          View load = findViewById(R.id.loadingGif);
          load.setVisibility(View.INVISIBLE);
          resultArea.setText(s[0] + " " + s[1] +  " " + s[2] +" foot " + s[3] + " " + s[4] + " " + s[6]);


          img = findViewById(R.id.teamImage);
          String picURL = "https://sportteamslogo.com/api?key=1f2223fa3b924590bef2d6ea69a52fb0&size=big&tid=" + teamMap.get(s[5]);
          Picasso.get().load(picURL).into(img);
        }
    }


    /**
     * Helper function
     * @param is
     * @return
     */
    private String convertStreamToString(InputStream is) {
        Scanner s = new Scanner(is).useDelimiter("\\A");
        return s.hasNext() ? s.next().replace(",", ",\n") : "";
    }


}
