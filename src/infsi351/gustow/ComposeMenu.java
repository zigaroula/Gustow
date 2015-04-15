package infsi351.gustow;

import infsi351.gustow.data.Formule;
import infsi351.gustow.data.GestionnairePlat;
import infsi351.gustow.data.Plat;
import android.app.Activity;
import android.os.Bundle;
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
		LinearLayout menuPlatEntree = (LinearLayout) findViewById(R.id.menu_plat_entree);
		LinearLayout menuPlatPlat = (LinearLayout) findViewById(R.id.menu_plat_plat);
		LinearLayout menuPlatDessert = (LinearLayout) findViewById(R.id.menu_plat_dessert);


		menuFormule.setLayoutParams(new LinearLayout.LayoutParams(100,
					ViewGroup.LayoutParams.MATCH_PARENT));

		GestionnairePlat g = new GestionnairePlat(getApplicationContext());
		g.testBourrin();

		Formule formule=new Formule();

		switch (view.getId()) {
		case R.id.button_formule_1:
			//TODO
			formule.testBourrin();
			break;

		case R.id.button_formule_2:
			//TODO
			break;
		}

		for(LinearLayout layout : new LinearLayout[]{menuPlatEntree, menuPlatPlat, menuPlatDessert}) {
			layout.removeAllViews();
		}
		
		for (int i : formule.getEntrees()) {
			Plat p = g.get(i);

			Button b = new Button(this);
			b.setText(p.getNom() + "\n" + p.getDescription());
			menuPlatEntree.addView(b);
		}
		for (int i : formule.getPlats()) {
			Plat p = g.get(i);

			Button b = new Button(this);
			b.setText(p.getNom() + "\n" + p.getDescription());
			menuPlatPlat.addView(b);
		}
		for (int i : formule.getDesserts()) {
			Plat p = g.get(i);

			Button b = new Button(this);
			b.setText(p.getNom() + "\n" + p.getDescription());
			menuPlatDessert.addView(b);
		}
	}
}
