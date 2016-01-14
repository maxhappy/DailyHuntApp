package helper;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import dailyfeed.demo.ilab.MainApp;
import dailyfeed.demo.ilab.QueryApp;
import android.app.Activity;
import android.app.Application;
import android.view.View;


public class JSONParser 
{
	static JSONObject jsonobjcet;
	
	
	
	static public void parseInfo()
	{
		
		
		
		
		
		try{
			 ParseFeed();
			}
		 catch (JSONException e) 
		 {
			 
			  try{
					ParseApiHits();
				 }
				 catch (JSONException e1)
				 {
					
				 }
				 
			 }
			 
		 }
		
		
		
		




	
      private static void ParseFeed()throws JSONException 
      {
    	  
    	  JSONObject object = new JSONObject(Var.apiResponse);
    	  JSONArray array=object.getJSONArray(Feed.keys[0]);
    	  
    	  
    	  int i=0;
    	  Var.feedlist.clear();
      	  Var.nossource=0;
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
  			Var.feedlist.add(feed);
  			
  			if(!Var.sourcelist.contains(feed.source))
  			{
  				Var.sourcelist+=","+feed.source;
  				Var.nossource++;
  			}
  			
  			
  			if(!Var.catagorylist.contains(feed.category))
  			{	Var.catagorylist+=","+feed.category;
  			}
  			
  			
  			
  			Var.titlelist+="❥"+feed.title;
  			
    	 i++;
  	  		
		}
  	  	  
  		Var.titlelist=Var.titlelist.substring(1);
        Var.sourcelist=Var.sourcelist.substring(1);
  		Var.index=0;
  	    Var.isloaded=2;
  	    runonuithread();
    	  
        }

	 
	 

	private static void ParseApiHits() throws JSONException 
	{
			jsonobjcet=new JSONObject(Var.apiResponse);
			Var.apihits=jsonobjcet.get("api_hits").toString();
		
    }
	
	

	public static void runonuithread( )
	{
		((Activity) MainApp.ctx).runOnUiThread(new Runnable() 
		{
		    public void run() 
		    {
		    	
		    	QueryApp.init_and_refreshView();
		    	
		    		
		    }
		    	
		});
		
	}


	



	
	
	
	
	
	
	
	
	
	
	
	
	

}