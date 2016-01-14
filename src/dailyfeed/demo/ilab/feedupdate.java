package dailyfeed.demo.ilab;

import helper.Feed;
import helper.Var;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.os.AsyncTask;
import android.util.Log;

public class feedupdate extends AsyncTask<String, Void, String> 
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
        
        try {
			ParseFeed();
		} catch (JSONException e) {
			
			
		}
    }

    
    

    private static void ParseFeed()throws JSONException 
    {
  	  
  	  JSONObject object = new JSONObject(Var.apiResponse);
  	  JSONArray array=object.getJSONArray(Feed.keys[0]);
  	  
  	  
  	    int i=0;
  	    
  	    while(i<array.length())
		{
			
			Feed feed=new Feed();
			JSONObject object1 = new JSONObject();
			object1=array.getJSONObject(i);
			feed.title=object1.get(Feed.keys[1]).toString();
			feed.source=object1.get(Feed.keys[2]).toString();
			feed.category=object1.get(Feed.keys[3]).toString();
			feed.imageurl=object1.get(Feed.keys[4]).toString();
			feed.imageurl=object1.get(Feed.keys[4]).toString();
			feed.content=object1.get(Feed.keys[5]).toString();
			feed.contenturl=object1.get(Feed.keys[6]).toString();
			
			ReaderApp.titlelist+=","+feed.title;
	        i++;
	  		
		}
	  	  
  	
  	  
      }

    
    
    public static void runonuithread( )
	{
		((Activity) MainApp.ctx).runOnUiThread(new Runnable() 
		{
		    public void run() 
		    {
		    	ReaderApp.ticker.setText(ReaderApp.titlelist);
		    		
		    }
		    	
		});
		
	}


	
	 
    
    
    
	
}