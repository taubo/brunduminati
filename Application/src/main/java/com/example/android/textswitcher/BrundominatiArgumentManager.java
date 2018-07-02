package com.example.android.textswitcher;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.widget.EditText;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class BrundominatiArgumentManager implements ArgumentManager {
    private String argumentsFileName = "argument_list.txt";
    private String baseEssayArguments[] = {
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
            "Protestantesimo > Cattolicesimo",
            "Le sfide della globalizzazione porteranno alla prossima evoluzione della razza umana; sono tali sfide che corregono l'attitudine, " +
                    "tipica dei paesi occidentali del Sud, di oziare",
            "Senza la globalizzazione non riusciremmo mai ad affrontare con successo i Tartari",
            "Il superamento del concetto di famiglia, ci consentirà di raggiungiere nuove vette della collaborazione tra persone e popoli",
            "Bergman è sopravvalutato",
    };

    private ArrayList<Argument> arguments;

    public BrundominatiArgumentManager() {
        arguments = new ArrayList<>();
    }

    @Override
    public void add(Argument argument) {
        arguments.add(argument);
    }

    @Override
    public void delete(int index) {
        if (!arguments.isEmpty())
            arguments.remove(index);
    }

    @Override
    public ArrayList<Argument> getAll() {
        return arguments;
    }

    public Argument get(int index) {
        if (arguments.isEmpty())
            return null;
        return arguments.get(index);
    }

    public int getElemNumber() {
        return arguments.size();
    }

    public void deleteFile(Context context) {
        String directory = context.getFilesDir().getAbsolutePath();
        File file = new File(directory, argumentsFileName);
        file.delete();
    }

    public void load(Context context) {
        File directory = context.getFilesDir();
        File file = new File(directory, argumentsFileName);
        if (!file.exists()) {
            Log.d("ArgumentManager", "file not present");
            try {
                FileOutputStream outFile = new FileOutputStream(file);
                for (String str : baseEssayArguments) {
                    str = str + "\n";
                    outFile.write(str.getBytes());
                    Argument arg = new Argument(str);
                    this.add(arg);
                }
                outFile.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            Log.d("ArgumentManager", "file present");
            String line;
            try {
                BufferedReader reader = new BufferedReader(new FileReader(file));
                while ((line = reader.readLine()) != null) {
                    this.add(new Argument(line));
                }
                reader.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void save(Context context) {
        String directory = context.getFilesDir().getAbsolutePath();
        File file = new File(directory, argumentsFileName);

        try {
            FileOutputStream outFile = new FileOutputStream(file);
            for (Argument arg : arguments) {
                Log.d("BrundominatiArgManager", arg.getText());
                String str = arg.getText() + "\n";
                outFile.write(str.getBytes());
            }
            outFile.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
