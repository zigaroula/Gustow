package infsi351.gustow;

import infsi351.gustow.data.Formule;
import infsi351.gustow.data.Globals;
import infsi351.gustow.data.Plat;
import infsi351.gustow.data.TypePlat;
import android.app.Activity;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

public class CheckCart extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.activity_check_cart);

		TextView titre = (TextView) findViewById(R.id.title_cart);
		Typeface tf = Typeface.createFromAsset(getAssets(), "fonts/SnellRoundhand.ttc");
		titre.setTypeface(tf);
		displayCart();

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_check_cart, menu);
		return true;
	}

	private void displayCart() {
		LinearLayout itemList = (LinearLayout) findViewById(R.id.item_list);
		itemList.removeAllViews();

		for (final Formule f : Globals.cart.getFormules()) {
			TextView text = new TextView(this);
			text.setText(f.getNom()
					+ "\n   " + Globals.plats.get(f.getEntree()).getNom()
					+ "\n   " + Globals.plats.get(f.getPlat()).getNom()
					+ "\n   " + Globals.plats.get(f.getDessert()).getNom());
			itemList.addView(text);
			
			Button b = new Button(this);
			b.setText("rm");

			b.setOnClickListener(new View.OnClickListener() {
				public void onClick(View v) {
					rmFormule(f);
				}
			});
			
			itemList.addView(b);
			itemList.addView(separator());
		}

		for (final int id : Globals.cart.getPlats()) {
			TextView text = new TextView(this);
			text.setText(Globals.plats.get(id).getNom());
			itemList.addView(text);
			Button b = new Button(this);
			b.setText("rm");

			b.setOnClickListener(new View.OnClickListener() {
				public void onClick(View v) {
					rmPlat(id);
				}
			});
			
			itemList.addView(b);
			itemList.addView(separator());
		}
		itemList.addView(separator());

	}

	private LinearLayout separator() {
		// un peu sale...
		LinearLayout separator = new LinearLayout(this);
		separator.setBackgroundColor(Color.BLACK);
		separator.setLayoutParams(new LinearLayout.LayoutParams(
				ViewGroup.LayoutParams.MATCH_PARENT, 2));
		return separator;
	}
	
	private void rmFormule(Formule f) {
		Globals.cart.rm(f);
		displayCart();
	}

	private void rmPlat(int idPlat) {
		Globals.cart.rm(idPlat);
		displayCart();
	}
}
