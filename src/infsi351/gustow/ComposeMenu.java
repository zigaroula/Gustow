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
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TableRow;
import android.widget.TextView;

public class ComposeMenu extends Activity {

	private Formule currentFormule;
	private Formule buildingFormule;

	// les rubriques à afficher dans l'ordre
	private final EnumSet<TypePlat> rubriques = EnumSet.of(TypePlat.Entree,
			TypePlat.PlatPrincipal, TypePlat.Dessert);

	// LinearLayout où on affiche la liste des plats par entree/plat/dessert
	private Map<TypePlat, LinearLayout> PlatLists;

	// FrameLayout où on affiche plus d'infos sur le plat sélectionné
	private Map<TypePlat, FrameLayout> PlatFrames;
	
	
	private int couleurCourante;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		couleurCourante = 0;
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.activity_compose_menu);
		

		LinearLayout menuFormule = (LinearLayout) findViewById(R.id.menu_formule);
		for (final Formule f : Globals.plats.getFormules()) {
			final int couleurFormule = couleurCourante;
			
			Button b = buttonFormule(f);

			b.setOnClickListener(new View.OnClickListener() {
				public void onClick(View v) {
					selectFormule(f, couleurFormule);
				}
			});

			menuFormule.addView(b);
		}
		
		buildingFormule=new Formule();

		PlatLists = new EnumMap<TypePlat, LinearLayout>(TypePlat.class);
		PlatLists.put(TypePlat.Entree,
				(LinearLayout) findViewById(R.id.menu_plat_entree));
		PlatLists.put(TypePlat.PlatPrincipal,
				(LinearLayout) findViewById(R.id.menu_plat_plat));
		PlatLists.put(TypePlat.Dessert,
				(LinearLayout) findViewById(R.id.menu_plat_dessert));

		PlatFrames = new EnumMap<TypePlat, FrameLayout>(TypePlat.class);
		PlatFrames.put(TypePlat.Entree,
				(FrameLayout) findViewById(R.id.frame_entree));
		PlatFrames.put(TypePlat.PlatPrincipal,
				(FrameLayout) findViewById(R.id.frame_plat));
		PlatFrames.put(TypePlat.Dessert,
				(FrameLayout) findViewById(R.id.frame_dessert));
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_compose_menu, menu);
		return true;
	}

	public void selectFormule(Formule f, int couleur) {
		if (currentFormule == f)
			return;

		currentFormule = f;
		buildingFormule.copyFormule(f);
		
		LinearLayout menuFormule = (LinearLayout) findViewById(R.id.menu_formule);
		menuFormule.setLayoutParams(new LinearLayout.LayoutParams(100,
				ViewGroup.LayoutParams.MATCH_PARENT));
		
		LinearLayout menuPlat = (LinearLayout) findViewById(R.id.menu_plat);
		menuPlat.setBackgroundColor(Globals.couleurs.get(couleur));

		for (TypePlat type : rubriques) {
			
			LinearLayout layout = PlatLists.get(type);
			layout.removeAllViews();
			FrameLayout frame = PlatFrames.get(type);
			frame.removeAllViews();

			// pour chaque plat, on cree le bouton
			for (int i : f.getPlatsOfType(type)) {
				Plat p = Globals.plats.get(i);
				Button b=buttonPlat(p);
				
				final int id = p.getId();
				final TypePlat t = type;

				b.setOnClickListener(new View.OnClickListener() {
					public void onClick(View v) {
						setFrame(id, t);
					}
				});

				layout.addView(b);
			}
		}
	}

	private void setFrame(int idPlat, TypePlat typePlat) {
		// selectionne la frame à modifier
		FrameLayout frame = PlatFrames.get(typePlat);
		Plat p = Globals.plats.get(idPlat);

		// remplit la frame
		frame.removeAllViews();

		TextView name = new TextView(this);
		String path = p.getPathImage();
		int id = getResources().getIdentifier(path, "drawable",getApplicationContext().getPackageName());
		name.setBackgroundResource(id);
		name.setText(p.getNom());

		frame.addView(name);

		buildingFormule.setPlatOfType(typePlat, idPlat);
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
		return b;
	}
	
	public Button buttonFormule(Formule f) {
		Button b = new Button(this);
		b.setText(f.getNom());
		Typeface tf = Typeface.createFromAsset(getAssets(), "fonts/SnellRoundhand.ttc");
		b.setTypeface(tf);
		LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
			    LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
		params.weight = 1.0f;
		b.setLayoutParams(params);
		b.setBackgroundColor(Globals.couleurs.get(couleurCourante));
		couleurCourante = (couleurCourante+1)%Globals.couleurs.size();
		return b;
	}
}
