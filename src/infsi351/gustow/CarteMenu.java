package infsi351.gustow;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;

public class CarteMenu extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_carte_menu);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_carte_menu, menu);
		return true;
	}

	public void carte_e(View sender){
    	Intent intent = new Intent(CarteMenu.this, CarteEntree.class);
    	startActivity(intent);
    }
    
    public void carte_p(View sender){
    	Intent intent = new Intent(CarteMenu.this, CartePlat.class);
    	startActivity(intent);
    }
    
    public void carte_d(View sender){
    	Intent intent = new Intent(CarteMenu.this, CarteDessert.class);
    	startActivity(intent);
    }
}
