package infsi351.gustow;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

public class MainActivity extends Activity {
	
	public final static String LANGUAGE = "infsi351.gustow.LANGUE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, 
                                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
    
    public void selectLanguage(View view) {
    	Intent intent = new Intent(this, Home.class);
    	String language = "";
    	switch (view.getId()) {
    	case R.id.button_fr:
    		language = "FR";
    		break;
    	case R.id.button_en:
    		language = "EN";
    		break;
    	case R.id.button_es:
    		language = "ES";
    		break;
    	}
    	intent.putExtra(LANGUAGE, language);
    	startActivity(intent);
    }
    
}
