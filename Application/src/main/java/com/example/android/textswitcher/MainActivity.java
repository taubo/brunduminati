/*
 * Copyright 2013 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.android.textswitcher;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;
import android.widget.TextSwitcher;
import android.widget.TextView;
import android.widget.ViewSwitcher.ViewFactory;

import java.util.ArrayList;
import java.util.Random;

/**
 * This sample shows the use of the {@link android.widget.TextSwitcher} View with animations. A
 * {@link android.widget.TextSwitcher} is a special type of {@link android.widget.ViewSwitcher} that animates
 * the current text out and new text in when
 * {@link android.widget.TextSwitcher#setText(CharSequence)} is called.
 */
public class MainActivity extends Activity {
    private TextSwitcher mSwitcher;
    private int mCounter = 0;
    private Random r = new Random();
    private BrundominatiArgumentManager argsManager = new BrundominatiArgumentManager();
    private String essayArguments[] = {
            "Il pareggio di bilancio deve essere raggiunto al fine di non violare una legge naturale",
            "La parita' di genere sara' raggiunta solo quando le donne avranno piu' diritti degli uomini",
            "Marx preferiva il capitale",
            "Il ritorno del Fiorino e la Lira Tron come scenario post-Europeo",
            "In Svezia si lavora un giorno in meno e si guadagna come se si lavorasse tre giorni di più grazie ad un corredo genetico e culturale che " +
                    "privilegia la qualità alla quantità",
            "In Arabia Saudita hanno recentemente applicato un'aggressiva politica di supporto alla parità di genere, pare infatti " +
                    "che il progressismo saudita si sia spinto fino al punto di concedere alle donne la possibilità di uscire di casa " +
                    "per un'ora al giorno senza la supervisione del marito (solo se maggiori di 10 anni). Cosa dovremmo imparare, noi " +
                    "occidentali da un così chiaro esempio di eliminazione del pregiudizio di genere?",
            "I pakora e i samosa salveranno il pianeta",
            "Se 2 + 2 = 5, allora io sono il Papa",
            "Magdi Allam, quando parla di Islam, non sà ciò che dice",
            "Le politiche per il turismo andrebbero pensate in modo integrato con altre politiche rivolte all'accoglienza degli stranieri",
            "I migranti ci offrono uno stile di vita che presto sarà lo stile di vita di tutti noi",
            "Sia vero il fatto che la VW ha conquistato il mercato dell'auto semplicemente perché ha integrato la linea di assemblaggio" +
                    " con la blockchain, come si distinguono i paraurti di una A3 a metano da quelli della merkel?",
            "Noi occidentali, avendo perso di vista i valori fondanti della spiritualità, dovrebbemmo guardare con ammirazione alla purezza ideologica dell'Islam" +
                    " e fare tutto ciò che è in nostro potere per diffondere la parola del profeta Maometto nelle nostre comunità",
            "La fine del QE coinciderà con la fine della pacchia",
            "Il caso base dei fagioli ricorsivi sommergibili Sean Connery, è costituito dalle balene sul Sole",
            "Più Europa, perché il quantitativo attuale si è dimostrato insufficiente a curarci",
            "Un'economia globale a cambio fisso è necessaria al fine di limitare il crescente impatto della finanza cattiva",
            "Protestantesimo > Cattolicesi  mo",
            "Le sfide della globalizzazione porteranno alla prossima evoluzione della razza umana; sono tali sfide che corregono l'attitudine, " +
                    "tipica dei paesi occidentali del Sud, di oziare",
            "Senza la globalizzazione non riusciremmo mai ad affrontare con successo i Tartari",
            "Il superamento del concetto di famiglia, ci consentirà di raggiungiere nuove vette della collaborazione tra persone e popoli",
            "Bergman è sopravvalutato",
    };

    private void launchManager() {
        final Intent intent = new Intent(this, ManagerActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onResume() {
        super.onResume();
        argsManager.load(getApplicationContext());
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sample_main);

        // Get the TextSwitcher view from the layout
        mSwitcher = (TextSwitcher) findViewById(R.id.switcher);

        // BEGIN_INCLUDE(setup)
        // Set the factory used to create TextViews to switch between.
        mSwitcher.setFactory(mFactory);

        /*
         * Set the in and out animations. Using the fade_in/out animations
         * provided by the framework.
         */
        Animation in = AnimationUtils.loadAnimation(this,
                android.R.anim.fade_in);
        Animation out = AnimationUtils.loadAnimation(this,
                android.R.anim.fade_out);
        mSwitcher.setInAnimation(in);
        mSwitcher.setOutAnimation(out);
        // END_INCLUDE(setup)

        argsManager.load(getApplicationContext());
        final ArrayList<Argument> args = argsManager.getAll();
        for (Argument arg : args) {
            Log.d("MainActivity", "" + arg.getText());
        }

        /*
         * Setup the 'next' button. The counter is incremented when clicked and
         * the new value is displayed in the TextSwitcher. The change of text is
         * automatically animated using the in/out animations set above.
         */
        ImageButton nextButton = (ImageButton) findViewById(R.id.next_essay);
        nextButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                /*
                mCounter = r.nextInt(essayArguments.length);
                mSwitcher.setText(essayArguments[mCounter]);
                mCounter %= essayArguments.length;
                */
                String arg = argsManager.get(mCounter).getText();
                mCounter = r.nextInt(argsManager.getElemNumber());
                mSwitcher.setText(arg);
                Log.d("MainActivity", "index elem " + mCounter + "/" + argsManager.getElemNumber() + " " + arg);
            }
        });

        nextButton.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                launchManager();
                return true;
            }
        });

        // Set the initial text without an animation
        // mSwitcher.setCurrentText(String.valueOf(mCounter));

    }

    // BEGIN_INCLUDE(factory)
    /**
     * The {@link android.widget.ViewSwitcher.ViewFactory} used to create {@link android.widget.TextView}s that the
     * {@link android.widget.TextSwitcher} will switch between.
     */
    private ViewFactory mFactory = new ViewFactory() {

        @Override
        public View makeView() {

            // Create a new TextView
            TextView t = new TextView(MainActivity.this);
            t.setGravity(Gravity.TOP | Gravity.CENTER_HORIZONTAL);
            t.setTextAppearance(MainActivity.this, android.R.style.TextAppearance_Large);
            return t;
        }
    };
    // END_INCLUDE(factory)
}
