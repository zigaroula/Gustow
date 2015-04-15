package infsi351.gustow;

import infsi351.gustow.data.Formule;
import infsi351.gustow.data.GestionnairePlat;
import infsi351.gustow.data.Plat;
import infsi351.gustow.data.TypePlat;

import java.util.EnumMap;
import java.util.EnumSet;
import java.util.Map;

import android.app.Activity;
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

public class ComposeMenu extends Activity {

	private GestionnairePlat g;
	private int currentFormuleId;

	// les rubriques à afficher dans l'ordre
	private final EnumSet<TypePlat> rubriques = EnumSet.of(TypePlat.Entree,
			TypePlat.PlatPrincipal, TypePlat.Dessert);

	// LinearLayout où on affiche la liste des plats par entree/plat/dessert
	private Map<TypePlat, LinearLayout> PlatLists;

	// FrameLayout où on affiche plus d'infos sur le plat sélectionné
	private Map<TypePlat, FrameLayout> PlatFrames;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.activity_compose_menu);

		g = new GestionnairePlat(getApplicationContext());
		g.testBourrin(); // TEST

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

	public void selectFormule(View view) {
		if (view.getId() == currentFormuleId)
			return;

		LinearLayout menuFormule = (LinearLayout) findViewById(R.id.menu_formule);
		menuFormule.setLayoutParams(new LinearLayout.LayoutParams(100,
				ViewGroup.LayoutParams.MATCH_PARENT));

		Formule formule = new Formule();

		switch (view.getId()) {
		case R.id.button_formule_1:
			// TODO
			formule.testBourrin();
			break;

		case R.id.button_formule_2:
			// TODO
			break;
		}

		for (TypePlat type : rubriques) {

			LinearLayout layout = PlatLists.get(type);
			layout.removeAllViews();
			FrameLayout frame = PlatFrames.get(type);
			frame.removeAllViews();

			// pour chaque plat, on cree le bouton
			for (int i : formule.getPlatsOfType(type)) {
				Plat p = g.get(i);

				Button b = new Button(this);
				b.setText(p.getNom());

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
		Plat p = g.get(idPlat);

		// remplit la frame
		frame.removeAllViews();

		TextView name = new TextView(this);
		name.setBackgroundResource(R.drawable.doge);
		name.setText(p.getNom());

		frame.addView(name);
	}
}
