package com.material.francisco.voz;

import java.util.ArrayList;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

    TextView txtviewSpeech2Text;

    private static final int RECOGNIZE_SPEECH_ACTIVITY = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtviewSpeech2Text = (TextView) findViewById(R.id.txt_view_speech_to_text);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {
            case RECOGNIZE_SPEECH_ACTIVITY:

                if (resultCode == RESULT_OK && null != data) {

                    // Acomoda el Speech en un ArrayList
                    ArrayList<String> speech = data
                            .getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);

                    // Obtiene el Speech de la Posción 0 siendo el más Preciso
                    String strSpeech2Text = speech.get(0);

                    txtviewSpeech2Text.setText(strSpeech2Text);
                }

                break;
            default:

                break;
        }

    }

    public void onClickImgBtnHablar(View v) {

        // Intent del Reconocimiento de Voz
        Intent intentActionRecognizeSpeech = new Intent(
                RecognizerIntent.ACTION_RECOGNIZE_SPEECH);

        // Configura el Lenguaje (Español-México)
        intentActionRecognizeSpeech.putExtra(
                RecognizerIntent.EXTRA_LANGUAGE_MODEL, "es-MX");

        // Inicia la Actividad
        try {
            startActivityForResult(intentActionRecognizeSpeech,
                    RECOGNIZE_SPEECH_ACTIVITY);
        } catch (ActivityNotFoundException a) {
            Toast.makeText(getApplicationContext(),
                    "¡Opps! Tú dispositivo no soporta Speech to Text",
                    Toast.LENGTH_SHORT).show();
        }

    }

}