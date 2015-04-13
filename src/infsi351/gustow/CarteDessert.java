package infsi351.gustow;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;

public class CarteDessert extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_carte_dessert);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_carte_dessert, menu);
		return true;
	}

	public void retourCarte(View sender){
    	Intent intent = new Intent(CarteDessert.this, CarteMenu.class);
    	startActivity(intent);
	}
}
