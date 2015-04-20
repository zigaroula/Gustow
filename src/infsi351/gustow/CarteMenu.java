package infsi351.gustow;

import infsi351.gustow.data.Formule;
import infsi351.gustow.data.Globals;
import infsi351.gustow.data.Plat;
import infsi351.gustow.data.TypePlat;

import java.util.EnumMap;
import java.util.EnumSet;
import java.util.Map;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

public class CarteMenu extends Activity {

	private Formule currentFormule;
	private Formule buildingFormule;
	
	private int couleurCourante;

	// les rubriques à afficher dans l'ordre
	private final EnumSet<TypePlat> rubriques = EnumSet.of(TypePlat.Entree,
			TypePlat.PlatPrincipal, TypePlat.Dessert);

	// LinearLayout où on affiche la liste des plats par entree/plat/dessert
	private Map<TypePlat, LinearLayout> PlatLists;

	// FrameLayout où on affiche plus d'infos sur le plat sélectionné
	private Map<TypePlat, FrameLayout> PlatFrames;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		couleurCourante = 0;
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.activity_carte_menu);
		

		LinearLayout menuTypePlat = (LinearLayout) findViewById(R.id.menu_typeplat);
		for (final TypePlat type : TypePlat.values()) {
			final int couleurTypePlat = couleurCourante;
			Button b = buttonTypePlat(type);
			menuTypePlat.addView(b);
			b.setOnClickListener(new View.OnClickListener() {
				public void onClick(View v) {
					setTypePlat(type, couleurTypePlat);
				}
			});
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_compose_menu, menu);
		return true;
	}

	public void setTypePlat(TypePlat type, final int couleur) {
		LinearLayout carteTypePlat = (LinearLayout) findViewById(R.id.carte_typeplat);
		LinearLayout menuTypePlat = (LinearLayout) findViewById(R.id.menu_typeplat);
		menuTypePlat.setLayoutParams(new LinearLayout.LayoutParams(100,
				ViewGroup.LayoutParams.MATCH_PARENT));
		carteTypePlat.setBackgroundColor(Globals.couleurs.get(couleur));
		
		LinearLayout produitsCarte = (LinearLayout) findViewById(R.id.produits_carte);
		produitsCarte.removeAllViews();
		
		TextView blankSpace = (TextView) findViewById(R.id.blank);
		blankSpace.setTextSize(TypedValue.COMPLEX_UNIT_SP, 10);
		
		TextView titreMenu = (TextView) findViewById(R.id.titre_carte);
		titreMenu.setText(type.toString());
		Typeface tf = Typeface.createFromAsset(getAssets(), "fonts/SnellRoundhand.ttc");
		titreMenu.setTypeface(tf, Typeface.BOLD);
		titreMenu.setTextSize(TypedValue.COMPLEX_UNIT_SP, 50);
		
		int childcount = menuTypePlat.getChildCount();
		for (int i = 0; i < childcount; i++) {
			View v = menuTypePlat.getChildAt(i);
			((Button) v).setText("");
		}
		
		for (Plat p : Globals.plats.getPlatsByType(type)) {
			Button b = buttonPlat(p);
			final int id = p.getId();
			final TypePlat t = type;

			b.setOnClickListener(new View.OnClickListener() {
				public void onClick(View v) {
					addToCart(id);
				}
			});

			produitsCarte.addView(b);
		}
		
		Button buttonViewCart = (Button) findViewById(R.id.button_carte_view_cart);
		Typeface buttonTF = Typeface.createFromAsset(getAssets(), "fonts/SnellRoundhand.ttc");
		buttonViewCart.setTypeface(buttonTF);
		buttonViewCart.setTextSize(TypedValue.COMPLEX_UNIT_SP, 30);
	}

	private void addToCart(int idPlat) {
		Globals.cart.add(idPlat);
	}
	
	public void confirm(View view) {
		Formule f=new Formule();
		f.copyFormule(buildingFormule);
		Globals.cart.add(f);
		
		Intent intent = new Intent(this, CheckCart.class);
		startActivity(intent);
	}
	
	public Button buttonPlat(Plat p) {
		Button b = new Button(this);
		Typeface tf = Typeface.createFromAsset(getAssets(), "fonts/Bodoni 72.ttc");
		b.setText(p.getNom());
		b.setTypeface(tf);
		b.setBackgroundColor(Color.TRANSPARENT);
		b.setGravity(Gravity.CENTER);
		return b;
	}
	
	public Button buttonFormule(Formule f) {
		Button b = new Button(this);
		b.setText(f.getNom());
		Typeface tf = Typeface.createFromAsset(getAssets(), "fonts/SnellRoundhand.ttc");
		b.setTypeface(tf);
		b.setBackgroundColor(Color.TRANSPARENT);
		return b;
	}
	
	public Button buttonTypePlat(TypePlat type) {
		Button b = new Button(this);
		b.setText(type.toString());
		Typeface tf = Typeface.createFromAsset(getAssets(), "fonts/SnellRoundhand.ttc");
		b.setTypeface(tf);
		LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
				LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
		params.weight = 1.0f;
		b.setLayoutParams(params);
		b.setTextSize(TypedValue.COMPLEX_UNIT_SP, 50);
		b.setBackgroundColor(Globals.couleurs.get(couleurCourante));
		couleurCourante = (couleurCourante + 1) % Globals.couleurs.size();
		return b;
	}
	
	public void viewCart(View v) {
		Intent intent = new Intent(this, CheckCart.class);
		startActivity(intent);
	}
	
}
