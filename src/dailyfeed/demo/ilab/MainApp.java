
package dailyfeed.demo.ilab;



import helper.Var;
import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainApp extends Activity {
	
	static public Context ctx;
	static public SharedPreferences preferences;

	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		preferences = PreferenceManager.getDefaultSharedPreferences(this);
		setContentView(R.layout.app);
		ctx=this;Var.context=this;
		
		
		final Button start=(Button) findViewById(R.id.but2);
		final Button otherapps=(Button) findViewById(R.id.b2);
			OnClickListener click=new OnClickListener() {
			
			@Override
			public void onClick(View v) 
			{
				if(v==start)
				{
					
					start() ;
					
				}
				if(v==otherapps)
				{
					otherapps();
				}
				
			}
		};
		start.setOnClickListener(click);
		otherapps.setOnClickListener(click);
		Var.init();
		
	}
	
	
	
	
	
	
	
	public static void start() 
	{
		Intent i = new Intent(MainApp.ctx, QueryApp.class);
     	((Activity) MainApp.ctx).startActivityForResult(i, 12);
     	 
		
	}
	
	public static void otherapps() 
	{ 
		Intent i;
		   
			    i = new Intent(Intent.ACTION_VIEW);
			    i.setData(Uri.parse("https://play.google.com/store/apps/developer?id=ILab"));
				((Activity) ctx).startActivityForResult(i, 12);
			
	}


	
	public static void savebookmaek(int i)
	{

	if(Var.Bookmark!=null)
	{
		if(Var.Bookmark.contains(","+i))
			return;
	}
	
	loadbookmark();
	Editor e=MainApp.preferences.edit();
	e.putString("BookMark",Var.Bookmark+","+i);
	e.commit();
	loadbookmark();
	}
	
	
	public static void loadbookmark()
	{
    Var.Bookmark=  MainApp.preferences.getString("BookMark","");
	}





	
	 
	}
	

