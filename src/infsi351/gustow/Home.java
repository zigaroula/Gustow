package infsi351.gustow;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;

public class Home extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, 
                                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        Intent intent = getIntent();
        String language = intent.getStringExtra(MainActivity.LANGUAGE);
        System.out.println(language);
		setContentView(R.layout.activity_home);
		LinearLayout page2 = (LinearLayout) findViewById(R.id.page2);
		if (language.equals("FR")) {
			page2.setBackgroundResource(R.drawable.page2);
		} else if (language.equals("ES")) {
			page2.setBackgroundResource(R.drawable.page2_spa);
		} else {
			page2.setBackgroundResource(R.drawable.page2_eng);
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_home, menu);
		return true;
	}
	
	public void selectMenu(View View) {
		Intent intent = new Intent(this, ComposeMenu.class);
		startActivity(intent);
	}
	
	public void selectCarte(View View) {
		Intent intent = new Intent(this, CarteMenu.class);
		startActivity(intent);
	}

}
