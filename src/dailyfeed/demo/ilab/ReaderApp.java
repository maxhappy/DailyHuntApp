
package dailyfeed.demo.ilab;


import helper.Var;
import helper.WebRequest;
import helper.Var.QueryIndex;

import com.nostra13.universalimageloader.core.ImageLoader;







import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.WebView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

 





 

public class ReaderApp extends Activity 
{
	
	 
	
	public static boolean Loaded;
	static Context ctx;
	public static String text="";
	private static ImageView im;
	private static ProgressBar pb;
	private static TextView tex;
	public static String titlelist;
	private long lasttime=0;
	static TextView ticker;

	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.reader);
		ctx=this;
		
		
		
		
		final ImageButton b1=(ImageButton) ((Activity) ctx).findViewById(R.id.but1);
		final ImageButton b3=(ImageButton) ((Activity) ctx).findViewById(R.id.share);
		final ImageButton b4=(ImageButton) ((Activity) ctx).findViewById(R.id.bookmark);
		
		final ImageView link=(ImageView)((Activity) ctx).findViewById(R.id.linki);
		final TextView textv=(TextView)((Activity) ctx).findViewById(R.id.linkt);
		final TextView textt=(TextView)((Activity) ctx).findViewById(R.id.titlet);
		
		 ticker=(TextView)((Activity) ctx).findViewById(R.id.ticker);
		 ticker.setText("");
		
		textv.setText(Var.feed.contenturl);
		textt.setText(Var.feed.title+" under "+Var.feed.category+" by "+Var.feed.source);
		
		
		final ImageView ima=(ImageView)((Activity) ctx).findViewById(R.id.imageView1);
        OnClickListener click=new OnClickListener() 
         {
			
			@Override
			public void onClick(View v) 
			{
			
				if(v==b1)
				{
		            finish();
					
				}
             	
            	if(v==ima)
             	{
            		im=(ImageView) ((Activity) ctx).findViewById(R.id.readmore);
            		im.setVisibility(View.GONE);
             		
             	}
            	
            	if(v==b3)
            	{
            		share();
            	}
            	
            	if(v==b4)
            	{
            		bookmark();
            	}
            	
            	if(v==textv||v==link)
            	{
            		webactivity();
            	}
				
			}

		
		};
		
		
		b1.setOnClickListener(click);
		b3.setOnClickListener(click);
		b4.setOnClickListener(click);
		link.setOnClickListener(click);
		textv.setOnClickListener(click);
		ima.setOnClickListener(click);
		
		
		
		
		
		
		load();
		final Runnable run=new Runnable() 
		{
		 public void run() 
		 { timerthread(); 
		 }
		};
		
		//Thread th=new  Thread(run);
		//th.start();
		
	}

	private void timerthread() 
	{
		
		
		while(true)
		{if(System.currentTimeMillis()-lasttime>30*1000)
		 {
	
			final Runnable run=new Runnable() 
			{
			 public void run() 
			 {
				 new feedupdate().execute(""+QueryIndex.News.ordinal());
				 
			 }
			};
			
			runOnUiThread(run);
			
		
		 }
	    }
  }

	private void load() 
	{
	lasttime=System.currentTimeMillis();
	im=(ImageView) ((Activity) ctx).findViewById(R.id.readmore);
	ImageLoader.getInstance().displayImage(Var.feed.imageurl,im);
	//pb=(ProgressBar)((Activity) ctx).findViewById(R.id.progressBar1);
	//pb.setVisibility(View.VISIBLE);
	
	WebView web=(WebView)((Activity) ctx).findViewById(R.id.webView1);
	web.loadUrl(Var.feed.contenturl);
	
	
	}

	public static void init_and_refreshView() 
	{
		
		im=(ImageView) ((Activity) ctx).findViewById(R.id.readmore);
		ImageLoader.getInstance().displayImage(Var.feed.imageurl,im);
		pb=(ProgressBar)((Activity) ctx).findViewById(R.id.progressBar1);
		pb.setVisibility(View.GONE);
	
	}
	
	
	
	
	
	
	private void webactivity() 
	{
		
		Intent i = new Intent(Intent.ACTION_VIEW);
		i.setData(Uri.parse(Var.feed.contenturl));
		startActivity(i);
		
	}

	private void bookmark() 
	{
		int i=0;
		while(i<Var.feedlist.size())
		{if(Var.feed.title.equals(Var.titlelist.split("❥")[i]))
				break;
		else
			i++;
		}
		if(i<Var.feedlist.size())
		{
			if(Var.Bookmark.contains(","+i))
			{
				Toast.makeText(ctx,"Article already bookmarked",Toast.LENGTH_LONG).show();
			}
			else
				{
				Toast.makeText(ctx,"Article  bookmarked",Toast.LENGTH_LONG).show();
		 		MainApp.savebookmaek(i);
			}
			
		}
	}

	private void share() 
	{
		
		Intent sharingIntent = new Intent(Intent.ACTION_SEND);
		sharingIntent.setType("text/html");
		sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT,Var.feed.title+"Read on "+Var.feed.contenturl);
		startActivity(Intent.createChooser(sharingIntent,"Share using"));
		
	}
	
	
	
	
	
	
	
	
	 
}
	

