package ebps.gerraughtywj.switchadventure;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Adventure extends Activity {

    public static Button btnYes, btnNo;
    public static TextView textField;
    public static String place;

    public static void Switch() {
        switch (place) {
            case Prompts.doorway:
                textField.setText(Prompts.doorwayPrompt);
                btnYes.setText(Prompts.kitchen);
                btnNo.setText(Prompts.upstairs);
                break;
            case Prompts.kitchen:
                textField.setText(Prompts.kitchenPrompt);
                btnYes.setText(Prompts.fridge);
                btnNo.setText(Prompts.cabinet);
                break;
            case Prompts.upstairs:
                textField.setText(Prompts.upstairsPrompt);
                btnYes.setText(Prompts.bedroom);
                btnNo.setText(Prompts.bathroom);
                break;
            case Prompts.fridge:
                textField.setText(Prompts.fridgePrompt);
                btnYes.setText(Prompts.yes);
                btnNo.setText(Prompts.no);
                break;
            case Prompts.cabinet:
                textField.setText(Prompts.cabinetPrompt);
                btnYes.setText(Prompts.yes);
                btnNo.setText(Prompts.no);
                break;
            case Prompts.bedroom:
                textField.setText(Prompts.bedroomPrompt);
                btnYes.setText(Prompts.yes);
                btnNo.setText(Prompts.no);
                break;
            case Prompts.bathroom:
                textField.setText(Prompts.bathroomPrompt);
                btnYes.setText(Prompts.yes);
                btnNo.setText(Prompts.no);
                break;
            case Prompts.done:
                btnYes.setText(Prompts.again);
                btnNo.setText(Prompts.finish);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adventure);
        btnYes = (Button) findViewById(R.id.buttonYes);
        btnNo = (Button) findViewById(R.id.buttonNo);
        textField = (TextView) findViewById(R.id.textFieldText);
        place = Prompts.doorway;
        Switch();
    }

    public void btnYesClicked(View v) {
        if (place.equals(Prompts.fridge)) {
            textField.setText(Prompts.fridgeYes);
            place = Prompts.done;
            Switch();
        } else if (place.equals(Prompts.cabinet)) {
            textField.setText(Prompts.cabinetYes);
            place = Prompts.done;
            Switch();
        } else if (place.equals(Prompts.bedroom)) {
            textField.setText(Prompts.bedroomYes);
            place = Prompts.done;
            Switch();
        } else if (place.equals(Prompts.bathroom)) {
            textField.setText(Prompts.bathroomYes);
            place = Prompts.done;
            Switch();
        } else if (place.equals(Prompts.done)) {
            place = Prompts.doorway;
            Switch();
        } else {
            place = btnYes.getText().toString();
            Switch();
        }
    }

    public void btnNoClicked(View v) {
        if (place.equals(Prompts.fridge)) {
            textField.setText(Prompts.fridgeNo);
            place = Prompts.done;
            Switch();
        } else if (place.equals(Prompts.cabinet)) {
            textField.setText(Prompts.cabinetNo);
            place = Prompts.done;
            Switch();
        } else if (place.equals(Prompts.bedroom)) {
            textField.setText(Prompts.bedroomNo);
            place = Prompts.done;
            Switch();
        } else if (place.equals(Prompts.bathroom)) {
            textField.setText(Prompts.bathroomNo);
            place = Prompts.done;
            Switch();
        } else if (place.equals(Prompts.done)) {
            finish();
        } else {
            place = btnNo.getText().toString();
            Switch();
        }
    }
}
