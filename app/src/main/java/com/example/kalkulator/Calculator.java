package com.example.kalkulator;

import static java.lang.Character.isDigit;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.mariuszgromada.math.mxparser.*;

public class Calculator extends AppCompatActivity {
    static private final String DEFAULT_VALUE = "0";
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator);
        textView = findViewById(R.id.result);

        addButtonListener(findViewById(R.id.p4), textView);
        addButtonListener(findViewById(R.id.p5), textView);
        addButtonListener(findViewById(R.id.p6), textView);
        addButtonListener(findViewById(R.id.p7), textView);
        addButtonListener(findViewById(R.id.p8), textView);
        addButtonListener(findViewById(R.id.p9), textView);
        addButtonListener(findViewById(R.id.p10), textView);
        addButtonListener(findViewById(R.id.p11), textView);
        addButtonListener(findViewById(R.id.p12), textView);
        addButtonListener(findViewById(R.id.p13), textView);
        addButtonListener(findViewById(R.id.p14), textView);
        addButtonListener(findViewById(R.id.p15), textView);
        addButtonListener(findViewById(R.id.p16), textView);
        addButtonListener(findViewById(R.id.p17), textView);
        addButtonListener(findViewById(R.id.p19), textView);

        Button delButton = findViewById(R.id.p1);
        Button delAllButton = findViewById(R.id.p2);
        Button changeSignButton = findViewById(R.id.p3);
        Button resButton = findViewById(R.id.p18);

        Button backToMenu = findViewById(R.id.p20);
        backToMenu.setOnClickListener(view ->
        {
            Intent intent = new Intent(Calculator.this, MainActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            finish();
        });

        delButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CharSequence text = textView.getText();
                int size = text.length();
                if (!text.equals(DEFAULT_VALUE) && text.length() > 1) {
                    textView.setText(text.subSequence(0, size - 1));
                } else {
                    textView.setText(DEFAULT_VALUE);
                }
            }
        });

        delAllButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                textView.setText(DEFAULT_VALUE);
            }
        });

        changeSignButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String text = textView.getText().toString();
                if (isDigit(text.charAt(text.length()-1))) {
                    for(int i=1; i<=text.length(); i++)
                    {
                        if((!isDigit(text.charAt(text.length()-i))&&text.charAt(text.length()-i)!='.') || i==text.length())
                        {
                            if(text.charAt(text.length()-i)=='-')
                            {
                                if(i<text.length())
                                {
                                    if(!isDigit(text.charAt(i-1)))
                                    {
                                        String newString = text.substring(0,text.length()-i);
                                        newString += text.substring(text.length()-i+1);
                                        textView.setText(newString);
                                        break;
                                    }
                                    else
                                    {
                                        String newString = text.substring(0,text.length()-i);
                                        newString += "+";
                                        newString += text.substring(text.length()-i);
                                        textView.setText(newString);
                                        break;
                                    }
                                }
                                else
                                {
                                    String newString = text.substring(1);
                                    textView.setText(newString);
                                    break;
                                }
                            }
                            else
                            {
                                if(i==text.length())
                                {
                                    String newString ="-";
                                    newString+=text;
                                    textView.setText(newString);
                                    break;
                                }
                                else
                                {
                                    String newString = text.substring(0,text.length()-i+1);
                                    newString += '-';
                                    newString += text.substring(text.length()-i+1);
                                    textView.setText(newString);
                                    break;
                                }
                            }
                        }
                    }
                } else {
                    Toast.makeText(getApplicationContext(), "Nie można zmienić znaku wyrażenia!", Toast.LENGTH_SHORT).show();
                }

            }
        });

        resButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String text = textView.getText().toString();
                if (isDigit(text.charAt(text.length() - 1))) {
                    Expression e = new Expression(text);
                    String res = Double.toString(e.calculate());
                    textView.setText(res);
                } else {
                    Toast.makeText(getApplicationContext(), "Nie można wyliczyć wyrażenia!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putCharSequence("key_text", textView.getText());
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        textView.setText(savedInstanceState.getCharSequence("key_text", DEFAULT_VALUE));
    }

    private boolean isSingleNumber(String text) {
        for (int i = text.length() - 1; i >= 0; i--) {
            if (!isDigit(text.charAt(i)) && text.charAt(i) != '.') {
                return i == 0 && text.charAt(i) == '-';
            }
        }
        return true;
    }

    private void addButtonListener(Button button, TextView result) {
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (result.getText().toString().equals(DEFAULT_VALUE) && isDigitClick(button)) {
                    result.setText(button.getText());
                } else {
                    if (isCorrect(result, button)) {
                        result.append(button.getText());
                    }
                }
            }
        });
    }

    private boolean isDigitClick(Button button) {
        if (button == findViewById(R.id.p4)) return false;
        if (button == findViewById(R.id.p8)) return false;
        if (button == findViewById(R.id.p12)) return false;
        if (button == findViewById(R.id.p16)) return false;
        if (button == findViewById(R.id.p19)) return false;
        return true;
    }


    private boolean isCorrect(TextView result, Button button) {
        String text = result.getText().toString();
        if (isDigitClick(button)) return true;
        if (isDotClick(button)) {
            return isCorrectDotClick(text);
        }
        if (isCorrectOperation(text)) {
            if (changeOperation(text)) {
                String newText = text.substring(0, text.length() - 1) + button.getText();
                result.setText(newText);
                return false;
            }
            return true;
        } else {
            Toast.makeText(getApplicationContext(), "Błąd operacji!", Toast.LENGTH_SHORT).show();
            return false;
        }
    }

    private boolean changeOperation(String text) {
        char ch = text.charAt(text.length() - 1);
        return ch == '*' || ch == '/' || ch == '-' || ch == '+';
    }

    private boolean isCorrectOperation(String text) {
        char ch = text.charAt(text.length() - 1);
        return ch != '.';
    }

    private boolean isCorrectDotClick(String text) {
        for (int i = text.length() - 1; i >= 0; i--) {
            char ch = text.charAt(i);
            if (!isDigit(ch)) {
                if (i == text.length() - 1) return false;
                else return ch != '.';
            }
        }
        return true;
    }

    private boolean isDotClick(Button button) {
        return (button == findViewById(R.id.p4));
    }
}