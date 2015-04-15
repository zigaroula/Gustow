package infsi351.gustow;

import infsi351.gustow.data.Cart;
import infsi351.gustow.data.Formule;
import infsi351.gustow.data.GestionnairePlat;
import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

public class CheckCart extends Activity {

	private Cart cart;
	private GestionnairePlat g;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_check_cart);

		g = new GestionnairePlat(this);
		g.testBourrin();
		cart = new Cart();
		cart.testBourrin();
		
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


		for (Formule f : cart.getMenus()) {
			TextView text = new TextView(this);
			text.setText("formule " + f.getNom() + "\n   "
					+ g.get(f.getEntrees().get(0)).getNom() + "\n   "
					+ g.get(f.getPlats().get(0)).getNom() + "\n   "
					+ g.get(f.getDesserts().get(0)).getNom()
					);
			itemList.addView(text);
			
			//un peu sale...
			LinearLayout separator=new LinearLayout(this);
			separator.setBackgroundColor(Color.BLACK);
			separator.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,2));
			itemList.addView(separator);
		}

		for (int id : cart.getPlats()) {
			TextView text = new TextView(this);
			text.setText(g.get(id).getNom());
			
			//un peu sale...
			LinearLayout separator=new LinearLayout(this);
			separator.setBackgroundColor(Color.BLACK);
			separator.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,2));
			itemList.addView(text);
			itemList.addView(separator);
		}
		
		
	}

}
