package helper;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import android.os.AsyncTask;
import android.util.Log;

public class WebRequest extends AsyncTask<String, Void, String> 
{

    
    protected void onPreExecute() 
    {
        //progressBar.setVisibility(View.VISIBLE);
        //responseView.setText("");
    }

	@Override

    protected String doInBackground(String... urls) 
    {


        try {
            int index=Integer.parseInt(urls[0]);
            
            
            URL url=new URL(Var.apiurl+Var.querys[index]);
            
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            try {
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
                StringBuilder stringBuilder = new StringBuilder();
                String line;
                while ((line = bufferedReader.readLine()) != null) {
                    stringBuilder.append(line).append("\n");
                }
                bufferedReader.close();
                return stringBuilder.toString();
            }
            finally{
                urlConnection.disconnect();
            }
        }
        catch(Exception e) 
        {
            Log.e("ERROR", e.getMessage(), e);
            return null;
        }
    }

    protected void onPostExecute(String response) 
    {
    	
    	
        if(response == null) 
        {
            response = "THERE WAS AN ERROR";
        }
        Var.apiResponse=response;
        JSONParser.parseInfo();
        
    }

	
}