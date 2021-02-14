package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import org.mariuszgromada.math.mxparser.*;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private EditText display;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        display = findViewById(R.id.input);
        display.setShowSoftInputOnFocus(false);

        display.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (getString(R.string.display).equals(display.getText().toString()))
                    display.setText("");

            }
        });

    }

    public void updateText(String strToAdd){
        String oldstr = display.getText().toString();
        int cursorpos = display.getSelectionStart();
        String leftstr = oldstr.substring(0,cursorpos);
        String rightstr = oldstr.substring(cursorpos);
        if (getString(R.string.display).equals(display.getText().toString())){
            display.setText(strToAdd);
            display.setSelection(cursorpos + 1);
        }
        else
        {
            display.setText(String.format("%s%s%s",leftstr,strToAdd,rightstr));
            display.setSelection(cursorpos + 1);
        }


    }

    public void butonuzero(View view){
        updateText("0");
    }

    public void butonuunu(View view){
        updateText("1");
    }

    public void butonudoi(View view){
        updateText("2");
    }

    public void butonutrei(View view){
        updateText("3");
    }

    public void butonupatru(View view){
        updateText("4");
    }

    public void butonucinci(View view){
        updateText("5");
    }

    public void butonusase(View view){
        updateText("6");
    }

    public void butonusapte(View view){
        updateText("7");
    }

    public void butonuopt(View view){
        updateText("8");
    }

    public void butonunoua(View view){
        updateText("9");
    }

    public void butonuplus(View view){
        updateText("+");
    }

    public void butonuminus(View view){
        updateText("-");
    }

    public void butonuinmultire(View view){
        updateText("×");
    }

    public void butonuimpartire(View view){
        updateText("÷");
    }

    public void butonuplusminus(View view){
        updateText("-");
    }

    public void butonuparanteze(View view){
        int cursorpos = display.getSelectionStart();
        int parantezedeschise = 0;
        int parantezeinchise = 0;
        int textlen = display.getText().length();

        for (int i=0;i < cursorpos;i++) {
            if (display.getText().toString().substring(i, i + 1).equals("(")) {
                parantezedeschise += 1;
            }
            if (display.getText().toString().substring(i, i + 1).equals(")")) {
                parantezeinchise += 1;
            }
        }
            if (parantezedeschise == parantezeinchise  || display.getText().toString().substring(textlen -1,textlen).equals("(")){
                updateText("(");
                display.setSelection(cursorpos + 1);
            }
            else if (parantezeinchise < parantezedeschise  &&  !display.getText().toString().substring(textlen -1,textlen).equals("(")){
                updateText(")");
            }
            display.setSelection(cursorpos + 1);
        }


    public void butonuexponent(View view){
        updateText("^");
    }

    public void butonuclear(View view){
        display.setText("");
    }

    public void butonupunct(View view){
        updateText(".");
    }

    public void butonuegal(View view){
        String expresie = display.getText().toString();

        expresie = expresie.replaceAll("÷", "/");
        expresie = expresie.replaceAll("×","*");

        Expression exp = new Expression(expresie);

        String rezultat = String.valueOf(exp.calculate());

        display.setText(rezultat);
        display.setSelection(rezultat.length());
    }

    public void butonbackspace(View view) {
        int cursorpos = display.getSelectionStart();
        int textLen = display.getText().length();

        if (cursorpos != 0 && textLen != 0) {
            SpannableStringBuilder selection = (SpannableStringBuilder) display.getText();
            selection.replace(cursorpos - 1, cursorpos, "");
            display.setText(selection);
            display.setSelection(cursorpos - 1);
        }
    }
}