package helper;
import android.content.Context;
 
public class Toast  
{
	
	public static final int LENGTH_LONG = android.widget.Toast.LENGTH_LONG;
	public static final int LENGTH_SHORT= android.widget.Toast.LENGTH_SHORT;
	static android.widget.Toast temp;

public static android.widget.Toast makeText(Context ctx, String text, int dur) 
{
  	if(temp!=null)
	{ temp.cancel();
    }
  	temp=  android.widget.Toast.makeText(ctx, text, dur);
    return temp;
}

}
