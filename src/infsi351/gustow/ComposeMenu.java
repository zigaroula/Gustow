package infsi351.gustow;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

public class ComposeMenu extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
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
		LinearLayout menuPlat = (LinearLayout) findViewById(R.id.menu_plat);
		menuFormule.setLayoutParams(new LinearLayout.LayoutParams(300, ViewGroup.LayoutParams.MATCH_PARENT));
		menuPlat.setLayoutParams(new LinearLayout.LayoutParams(300, ViewGroup.LayoutParams.MATCH_PARENT));

		switch(view.getId()) {
			case R.id.button_formule_1:
				//TODO
				break;
			case R.id.button_formule_2:
				//TODO
				break;
		}
	}
}
