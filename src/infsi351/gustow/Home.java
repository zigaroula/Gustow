package infsi351.gustow;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;

public class Home extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_home, menu);
		return true;
	}
	
	public void selectMenu(View View) {
		//Intent intent = new Intent(this, ComposeMenu.class);
		//startActivity(intent);
	}
	
	public void selectCarte(View View) {
		//Intent intent = new Intent(this, ComposeCarte.class);
		//startActivity(intent);
	}

}
