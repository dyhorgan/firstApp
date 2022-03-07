//package com.example.numad22sp_dylanhorgan;
//
//import android.os.AsyncTask;
//import android.os.Bundle;
//import android.util.Log;
//import android.view.View;
//import android.widget.EditText;
//import android.widget.TextView;
//
//import androidx.appcompat.app.AppCompatActivity;
//
//import org.json.JSONException;
//import org.json.JSONObject;
//
//import java.io.IOException;
//import java.io.InputStream;
//import java.net.HttpURLConnection;
//import java.net.MalformedURLException;
//import java.net.ProtocolException;
//import java.net.URL;
//import java.util.Scanner;
//
//
//public class WebServiceActivity extends AppCompatActivity {
//
//    private static final String TAG = "WebServiceActivity";
//
//    private EditText mURLEditText;
//    private TextView mTitleTextView;
//
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_web_service);
//
//        mURLEditText = (EditText)findViewById(R.id.URL_editText);
//        mTitleTextView = (TextView)findViewById(R.id.result_textview);
//
//    }
//
//    public void callWebserviceButtonHandler(View view){
//        PingWebServiceTask task = new PingWebServiceTask();
//        task.execute(mURLEditText.getText().toString()); // This is a security risk.  Don't let your user enter the URL in a real app.
//
//    }
//
//
//
//    private class PingWebServiceTask  extends AsyncTask<String, Integer, String[]> {
//
//        @Override
//        protected void onProgressUpdate(Integer... values) {
//            Log.i(TAG, "Making progress...");
//        }
//
//        @Override
//        protected String[] doInBackground(String... params) {
//
//
//            String[] results = new String[2];
//            URL url = null;
//            try {
//
////                url = new URL("https://jsonplaceholder.typicode.com/posts/1");
//                url = new URL(params[0]);
//
//                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
//                conn.setRequestMethod("GET");
//                conn.setDoInput(true);
//
//                conn.connect();
//
//                // Read response.
//                InputStream inputStream = conn.getInputStream();
//                final String resp = convertStreamToString(inputStream);
//
////                JSONArray jArray = new JSONArray(resp);    // Use this if your web service returns an array of objects.  Arrays are in [ ] brackets.
//                JSONObject jObject = new JSONObject(resp);
//                String jTitle = jObject.getString("title");
//                String jBody = jObject.getString("body");
//                results[0] = jTitle;
//                results[1] = jBody;
//                return results;
//
//            } catch (MalformedURLException e) {
//                Log.e(TAG,"MalformedURLException");
//                e.printStackTrace();
//            } catch (ProtocolException e) {
//                Log.e(TAG,"ProtocolException");
//                e.printStackTrace();
//            } catch (IOException e) {
//                Log.e(TAG,"IOException");
//                e.printStackTrace();
//            } catch (JSONException e) {
//                Log.e(TAG,"JSONException");
//                e.printStackTrace();
//            }
//            results[0] = "Something went wrong";
//            return(results);
//        }
//
//        @Override
//        protected void onPostExecute(String... s) {
//            super.onPostExecute(s);
//            TextView result_view = (TextView)findViewById(R.id.result_textview);
//            result_view.setText(s[0]);
//        }
//    }
//
//
//    /**
//     * Helper function
//     * @param is
//     * @return
//     */
//    private String convertStreamToString(InputStream is) {
//        Scanner s = new Scanner(is).useDelimiter("\\A");
//        return s.hasNext() ? s.next().replace(",", ",\n") : "";
//    }
//
//
//}
