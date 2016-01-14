package helper;

import java.util.ArrayList;
import java.util.List;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import dailyfeed.demo.ilab.MainApp;
import dailyfeed.demo.ilab.QueryApp;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.view.View;

public class Var 
{
	
	public static Context context;
	public static String networkfailmessage="Sorry internet not working on this Device";
	public static CharSequence ratemsg="Rate this";
	
	public static String apiurl="https://dailyhunt.0x10.info/api/dailyhunt?type=json&query=";
	public static String [] querys={ "list_news", "api_hits"};
	public static String apihits;
	public static String apiResponse;
	public static int cat=0;
	
	public static List<Feed> feedlist=new  ArrayList<Feed>();
	public static List<Feed> tempfeedlist=new  ArrayList<Feed>();
	
	
	
	public static enum QueryIndex { News,ApiHits}
	
	static  public void init()
	{
		isloaded=0;
		sourcelist="";
		titlelist="";
		
		MainApp.loadbookmark();
		DisplayImageOptions defaultOptions = new DisplayImageOptions.Builder().cacheInMemory(true).cacheOnDisk(true).build();
	    ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(MainApp.ctx).defaultDisplayImageOptions(defaultOptions).build();
		ImageLoader.getInstance().init(config);
		if(checkNetworkState(true))
        refresh();
		
    }
	
	
	static public void refresh()
	{
		
		if(QueryApp.pb!=null)
		  if(QueryApp.pb.getVisibility()!=View.VISIBLE)
			  QueryApp.pb.setVisibility(View.VISIBLE);
		
		isloaded=1;
	
		new WebRequest().execute(""+QueryIndex.ApiHits.ordinal());
		new WebRequest().execute(""+QueryIndex.News.ordinal());
		
		
	}
	
	public static int index;
	public static int isloaded;
	public static int selected=-1;
	public static String seldate;
	public static String selectedcity;
	public static String Bookmark="";
	public static int Sourcecount=0;
	public static int nossource;
	public static String sourcelist="";
	public static String titlelist="";
	public static String catagorylist="All";
	public static int current;
	public static Feed feed;
	
	
	
	static  public void sort( )
	{
	/*	Collections.sort(feedlist, new Comparator<Feed>()
				{
					@Override
					public int compare(Feed lhs, Feed rhs) 
					{
						
						if(Var.index==0)
						{
							return lhs.compareTo(rhs.distance);
							
						}
						if(Var.index==2)
						{
							return lhs.compareTo(rhs.departureTime);
							
						}
						return lhs.compareTo(rhs.departureTime);
						
					}
		         }
		       );*/		

 }
	
	

	 public  static boolean  checkNetworkState(boolean  flag)
	 {
	    ConnectivityManager connMgr = (ConnectivityManager) Var.context.getSystemService(Var.context.CONNECTIVITY_SERVICE);
	    NetworkInfo networkInfo = connMgr.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
	    
	    if (networkInfo != null && networkInfo.isConnected()) 
	    {
	    	return true;
	    } 
	    
	    else 
	    {networkInfo=connMgr.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
	    
	    if (networkInfo != null && networkInfo.isConnected()) 
	    {
	    	return true;
	    }
	    	
	    	
	    }
	    	
	    if(flag)
	    {
	    Toast.makeText(MainApp.ctx, Var.networkfailmessage, Toast.LENGTH_SHORT).show();
	    }
	    return false;
	    
	}
	
	
	
	
	

	

}
