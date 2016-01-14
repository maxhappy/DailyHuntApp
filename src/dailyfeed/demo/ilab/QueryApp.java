
package dailyfeed.demo.ilab;

import dailyfeed.demo.ilab.R.drawable;
import helper.Feed;
import helper.Var;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.LinearGradient;
import android.net.http.SslCertificate;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.WebView.FindListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;


public class QueryApp extends Activity 
{
	
	
	public static  Context ctx;
	public static ProgressBar pb;
	static ListView listview;
	static TextView info;
	static TextView selc;
	
	static ImageView header;
	private static AutoCompleteTextView input;
	private static Spinner spinner;
	private static boolean refresh;
	private static ImageButton b1,b2;
	private static LinearLayout searbar;
	private static boolean filter=false;
	protected static int page=0;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		ctx=this;
		
		
		init_and_refreshView();
		
		
		
	}
	
	
	
	protected static void loadbookmark() 
	{
		filter=true;
		
		Var.tempfeedlist.clear();	
		  int i=0;
		  while (i<Var.feedlist.size())
		  { 
			  if(Var.Bookmark.contains(""+i))
			  Var.tempfeedlist.add(Var.feedlist.get(i));
			  i++;
			  
		  }
		
	}


	
	
	protected static void filter(String feed) 
	{
		
		filter=true;
		
		
		
		  Var.tempfeedlist.clear();	
		  int i=0;
		  while (i<Var.feedlist.size())
		  { 
			  if(Var.feedlist.get(i).source.contains(feed))
			  Var.tempfeedlist.add(Var.feedlist.get(i));
			  i++;
			  
			  
		  }
		
		  if(Var.tempfeedlist.size()==0)
		  {   i=0;
			  while (i<Var.feedlist.size())
			  { 
				  if(Var.feedlist.get(i).title.contains(feed))
				  Var.tempfeedlist.add(Var.feedlist.get(i));
				  i++;
			  }
			  
			  
		  }
		
		
		
		
		
		
	}
	
	
	
	public static void refresh()
	{
	  Var.tempfeedlist.clear();	
	  int i=0;
	  while (i<Var.feedlist.size())
	  { 
		  if(Var.cat==0)
		  Var.tempfeedlist.add(Var.feedlist.get(i));
		  else
		  {
			  if(Var.feedlist.get(i).category.equals(Var.catagorylist.split(",")[Var.cat]))
			  Var.tempfeedlist.add(Var.feedlist.get(i));
			  
		  }
		  i++;
	  }
	  
	  
	  
	  
		
	}
	
	
	
	
	
	
	
	public static void init_and_refreshView()
	{
		if(ctx==null)
		return;
        
		
		
		ProgressBar pb=(ProgressBar) ((Activity) ctx).findViewById(R.id.progressBar1);
		if(Var.isloaded!=2&&pb.getVisibility()!=View.VISIBLE)
		pb.setVisibility(View.VISIBLE);
		else if(Var.isloaded==2)
		{
		
	    pb.setVisibility(View.GONE);
	    
	    if(!filter)
		refresh();
		    
		
		
	    header=(ImageView) ((Activity) ctx).findViewById(R.id.header);
		
		
		searbar=(LinearLayout)((Activity) ctx).findViewById(R.id.searchbar);
		info=(TextView) ((Activity) ctx).findViewById(R.id.info);
		info.setText("Total FeedSorce:"+Var.nossource+"and Api Hits:"+Var.apihits );
		
		
		  
		
		    spinner=(Spinner) ((Activity) ctx).findViewById(R.id.categ);
		    ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(ctx,android.R.layout.simple_spinner_item,Var.catagorylist.split(","));
		 	dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		 	
		 	if(refresh!=true)
		 	spinner.setAdapter(dataAdapter);
		 	spinner.setOnItemSelectedListener(new  OnItemSelectedListener() 
		 	{
				@Override
				public void onItemSelected(AdapterView<?> parent, View view,
						int position, long id) {
					if(position!=Var.cat)
					{Var.cat=position;
					
					refresh=true;
					init_and_refreshView();
					}
					
				}

				@Override
				public void onNothingSelected(AdapterView<?> parent) 
				{
					// TODO Auto-generated method stub
					
				}
			});		 
		
		
		
		input = (AutoCompleteTextView) ((Activity) ctx).findViewById(R.id.mat);
	    input.setSelected(false);
	    input.setActivated(false);
		
	    final String temp=Var.titlelist+","+Var.sourcelist;
	    
	    input.setAdapter(new ArrayAdapter<String>(ctx, android.R.layout.simple_dropdown_item_1line,temp.split(",")));
		
	    TextWatcher watcher=new TextWatcher() {
			
			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				
				
			}
			
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void afterTextChanged(Editable s) {
                     if(temp.contains(s.toString()))
                     filter(s.toString()) ;
                     init_and_refreshView();
				
			}
		};
		input.addTextChangedListener(watcher);	    
	    
	    
	    
		
		
		OnItemClickListener listhandler=new OnItemClickListener() 
		{

            
			@Override
			public void onItemClick(AdapterView<?> parent, View view,int position, long id) 
			{
			    Var.feed=new Feed();	
				Var.feed=Var.tempfeedlist.get(position);
    			Intent i = new Intent(ctx, ReaderApp.class);
    	     	((Activity) ctx).startActivityForResult(i, 12);
				
			}

        };
		
		
		
		
		
		
		
		listview=(ListView) ((Activity) ctx).findViewById(R.id.listView);
		ViewAdapter adapter = new ViewAdapter(ctx, Var.tempfeedlist);
		listview.setAdapter(adapter);
		if(listview.getOnItemClickListener()==null)
		listview.setOnItemClickListener(listhandler);

		
		
		
		
		b1=(ImageButton) ((Activity) ctx).findViewById(R.id.but2);
		b2=(ImageButton) ((Activity) ctx).findViewById(R.id.b2);
		
        OnClickListener click=new OnClickListener() 
         {
			
			@Override
			public void onClick(View v) 
			{
			
				if(page==0)
				{
				if(v==b1)
				{
					 refresh=false;
					 Var.init();
					 init_and_refreshView();
					
				}
             	if(v==b2)
             	{
             	
             		spinner.setVisibility(View.GONE);
             		b2.setVisibility(View.GONE);
             		
             		header.setImageResource(drawable.booktop);
             		b1.setImageResource(drawable.bak);
             		searbar.setVisibility(View.GONE);
             		
             	    loadbookmark();
             	    page=1;
             	}
				}
				else if(page==1)
				{
					
					
					if(v==b1)
					{
						filter=false;
						spinner.setVisibility(View.VISIBLE);
	             		b2.setVisibility(View.VISIBLE);
	             		searbar.setVisibility(View.VISIBLE);
	             		
	             		header.setImageResource(drawable.home);
	             		b1.setImageResource(drawable.refresh);
	             		
	             		
	             	    init_and_refreshView();
						page=0;
	             	}
					
				}
				
				
				
			}
		};
		
		
		b1.setOnClickListener(click);
		b2.setOnClickListener(click);
		
		
		
		
		
	 }
	}


	
	
	
}
		