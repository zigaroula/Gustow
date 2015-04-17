package infsi351.gustow;

import infsi351.gustow.data.Formule;
import infsi351.gustow.data.Globals;
import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

public class CheckCart extends Activity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_check_cart);

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

		for (Formule f : Globals.cart.getFormules()) {
			TextView text = new TextView(this);
			text.setText(f.getId() + "\n   "
					+ Globals.plats.get(f.getEntree()).getNom() + "\n   "
					+ Globals.plats.get(f.getPlat()).getNom() + "\n   "
					+ Globals.plats.get(f.getDessert()).getNom());
			itemList.addView(text);

			// un peu sale...
			LinearLayout separator = new LinearLayout(this);
			separator.setBackgroundColor(Color.BLACK);
			separator.setLayoutParams(new LinearLayout.LayoutParams(
					ViewGroup.LayoutParams.MATCH_PARENT, 2));
			itemList.addView(separator);
		}

		for (int id : Globals.cart.getPlats()) {
			TextView text = new TextView(this);
			text.setText(Globals.plats.get(id).getNom());

			// un peu sale...
			LinearLayout separator = new LinearLayout(this);
			separator.setBackgroundColor(Color.BLACK);
			separator.setLayoutParams(new LinearLayout.LayoutParams(
					ViewGroup.LayoutParams.MATCH_PARENT, 2));
			itemList.addView(text);
			itemList.addView(separator);
		}

	}

}
