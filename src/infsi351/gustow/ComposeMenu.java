package infsi351.gustow;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;

public class ComposeMenu extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, 
                                WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.activity_compose_menu);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_compose_menu, menu);
		return true;
	}

	public void selectFormule(View view) {
		LinearLayout menuFormule = (LinearLayout) findViewById(R.id.menu_formule);
		menuFormule.setLayoutParams(new LinearLayout.LayoutParams(100, ViewGroup.LayoutParams.MATCH_PARENT));
		LinearLayout menuPlatEntree = (LinearLayout) findViewById(R.id.menu_plat_entree);
		LinearLayout menuPlatPlat = (LinearLayout) findViewById(R.id.menu_plat_plat);
		LinearLayout menuPlatDessert = (LinearLayout) findViewById(R.id.menu_plat_dessert);


		
		switch(view.getId()) {
			case R.id.button_formule_1:
				//TODO
				break;

			case R.id.button_formule_2:
				//TODO
				break;
		}
		
		for(int i=0; i<3; i++) {
			Button b=new Button(this);
			b.setText(Integer.toString(i));
			menuPlatEntree.addView(b);
		}
		for(int i=0; i<3; i++) {
			Button b=new Button(this);
			b.setText(Integer.toString(i));
			menuPlatPlat.addView(b);
		}
		for(int i=0; i<3; i++) {
			Button b=new Button(this);
			b.setText(Integer.toString(i));
			menuPlatDessert.addView(b);
		}
	}
}
