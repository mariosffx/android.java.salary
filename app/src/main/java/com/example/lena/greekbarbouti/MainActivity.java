package com.example.lena.greekbarbouti;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {
    private String[] arraySpinner;
    private String[] arraySpinner2;

    @Override 
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Αρχικοποίηση των spinners
        this.arraySpinner = new String[] { "Υ.Ε.", "Δ.Ε.", "Τ.Ε.", "Π.Ε.", "M.Sc.", "Ph.D." };
        Spinner s = (Spinner) findViewById(R.id.spinner);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, arraySpinner);
        s.setAdapter(adapter);
        
        this.arraySpinner2 = new String[] { "Υπάλληλος", "Προϊστάμενος", "Τομεάρχης", "Υποδιευθυντής", "Διευθυντής" };
        Spinner s2 = (Spinner) findViewById(R.id.spinner2);
        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, arraySpinner2);
        s2.setAdapter(adapter2);

        //Αρχικοποίηση των Edit Texts προς αποφυγή Run Time Errors

    }
//
//        Button clickButton = (Button) findViewById(R.id.btnOnClick);
//        clickButton.setOnClickListener( new OnClickListener() {
//

//        @Override
        public void onClick (View v){
            int VasMisthos = 0, epidGamou, epidTameiou, epidHY, epidKin, epThesis = 0, epidTek, minesMisthodosias = 12;
            double xronoEpid, mApod, kratIKA, etApod = 0, FMY, forAllil, synKrat, miniaiosFMY, minForAllil, kathPlir;

            //Διάβασμα έτους πρόσληψης και μετατροπή σε ακέραιο
            EditText etosTxt = (EditText) findViewById(R.id.editText2);
            int etos = Integer.parseInt(etosTxt.getText().toString());

            //Διάβασμα αριθμού τέκνων και μετατροπή σε ακέραιο
            EditText teknaTxt = (EditText) findViewById(R.id.editText3);
            int arTek = Integer.parseInt(teknaTxt.getText().toString());

            // Υπολογισμός Επιδόματος Γάμου
            final CheckBox checkGamos = (CheckBox) findViewById(R.id.checkBox2);

            findViewById(R.id.checkBox2);
            if (checkGamos.isChecked()) {
                epidGamou = 59;
            } else {
                epidGamou = 0;
            }

            // Υπολογισμός Επιδόματος Τέκνων
            switch (arTek) {
                case 1:
                    epidTek = 45;
                    break;
                case 2:
                    epidTek = 80;
                    break;
                case 3:
                    epidTek = 115;
                    break;
                case 4:
                    epidTek = 201;
                    break;
                default:
                    epidTek = arTek * 50;
            }

            // Υπολογισμός Επιδόματος Ταμείου
            final CheckBox checkTameio = (CheckBox) findViewById(R.id.checkBox4);

            if (checkTameio.isChecked()) {
                epidTameiou = 250;
            } else {
                epidTameiou = 0;
            }

            // Υπολογισμός Επιδόματος H/Y
            final CheckBox checkHY = (CheckBox) findViewById(R.id.checkBox3);
            if (checkHY.isChecked()) {
                epidHY = 120;
            } else {
                epidHY = 0;
            }

            // Υπολογισμός Κινήτρου Αποδοτικότητας
            final CheckBox checkKin = (CheckBox)
                    findViewById(R.id.checkBox5);
            if (checkKin.isChecked()) {
                epidKin = 90;
            } else {
                epidKin = 0;
            }

            //Διάβασμα Κλάδου από το spinner και υπολογισμός Βασικού Μισθού
            final Spinner spKlados = (Spinner)
                    findViewById(R.id.spinner);
            String ss = spKlados.getSelectedItem().toString();

            switch (ss) {
                case "Υ.Ε.":
                    VasMisthos = 530;
                    break;
                case "Δ.Ε.":
                    VasMisthos = 600;
                    break;
                case "Τ.Ε.":
                    VasMisthos = 680;
                    break;
                case "Π.Ε.":
                    VasMisthos = 800;
                    break;
                case "M.Sc.":
                    VasMisthos = 950;
                    break;
                case "Ph.D.":
                    VasMisthos = 1100;

            }
            //Διάβασμα Θέσης από το spinner και υπολογισμός Επιδόματος Θέσης
            final Spinner spThesi = (Spinner) findViewById(R.id.spinner2);
            String ss1 = spThesi.getSelectedItem().toString();

            switch (ss1) {
                case "Υπάλληλος":
                    epThesis = 0;
                    break;
                case "Προϊστάμενος":
                    epThesis = 110;
                    break;
                case "Τομεάρχης":
                    epThesis = 180;
                    break;
                case "Υποδιευθυντής":
                    epThesis = 220;
                    break;
                case "Διευθυντής":
                    epThesis = 300;
            }

            //Υπολογισμός Τρέχοντος Έτους και Χρονοεπιδόματος
            Calendar calendar = Calendar.getInstance();
            int curYear = calendar.get(Calendar.YEAR);
            xronoEpid = ((curYear - etos) / 2) * (VasMisthos * 0.02);

            //Υπολογισμός και Εμφάνιση Μικτών Αποδοχών
            mApod = VasMisthos + epidGamou + epidHY + epidKin + epidTameiou + epidTek + epThesis + xronoEpid;

            TextView mapodTV = (TextView) findViewById(R.id.textView8);
            mapodTV.setText(String.format("%.2f €", mApod));

            //Υπολογισμός Κρατήσεων ΙΚΑ - ΕΤΕΑΜ
            final CheckBox checkPalaios = (CheckBox) findViewById(R.id.checkBox);
            if (checkPalaios.isChecked()) {
                kratIKA = mApod * 0.185;

                // Οι παλαιοί ασφαλισμένοι έχουν κρατήσεις +3%
            } else {
                kratIKA = mApod * 0.155;
            }

            // Υπολογισμός ΦΜΥ
            final RadioButton radio12misth = (RadioButton) findViewById(R.id.radioButton);

            //Έλεγχος μηνών μισθοδοσίας
            final RadioButton radio14misth = (RadioButton) findViewById(R.id.radioButton2);
            if (radio12misth.isChecked()) {
                minesMisthodosias = 12;
            }

            if (radio14misth.isChecked()) {
                minesMisthodosias = 14;
            }

            etApod = mApod * minesMisthodosias;

            if (etApod <= 20000)
                FMY = etApod * 0.22;
            else if ((etApod > 20000) && (etApod <= 30000))
                FMY = (20000 * 0.22) + ((etApod - 20000) * 0.29);
            else if ((etApod > 30000) && (etApod <= 40000))
                FMY = (20000 * 0.22) + (10000 * 0.29) + ((etApod - 30000) * 0.37);
            else
                FMY = (20000 * 0.22) + (10000 * 0.29) + (10000 * 0.37) + ((etApod - 40000) * 0.45);

            miniaiosFMY = ((FMY - 2100) / minesMisthodosias) - (((FMY - 2100) / minesMisthodosias) * 0.015);

            if (miniaiosFMY < 0)
                miniaiosFMY = 0;

            //Υπολογισμός Φόρου Αλληλεγγύης
            if (etApod <= 12000)
                forAllil = 0;
            else if ((etApod > 12000) && (etApod <= 20000))
                forAllil = etApod * 0.022;
            else if ((etApod > 20000) && (etApod <= 30000))
                forAllil = etApod * 0.05;
            else if ((etApod > 30000) && (etApod <= 40000))
                forAllil = etApod * 0.065;
            else if ((etApod > 40000) && (etApod <= 65000))
                forAllil = etApod * 0.075;
            else if ((etApod > 65000) && (etApod <= 250000))
                forAllil = etApod * 0.09;
            else forAllil = etApod * 0.1;
            minForAllil = forAllil / minesMisthodosias;

            //Υπολογισμός και Εμφάνιση Συνόλου Κρατήσεων
            synKrat = kratIKA + miniaiosFMY + minForAllil;
            TextView sKratTV = (TextView) findViewById(R.id.textView9);
            sKratTV.setText(String.format("%.2f €", synKrat));

            //Υπολογισμός Καθαρού Πληρωτέου
            kathPlir = mApod - synKrat;
            TextView kathPlirTV = (TextView) findViewById(R.id.textView10);
            kathPlirTV.setText(String.format("%.2f €", kathPlir));
        }

}