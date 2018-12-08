package luongvo.com.mycalculator;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.annotation.IntegerRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
class heat{
    static int level1 = Color.LTGRAY;
    static int level2= Color.GREEN;
    static int level3= Color.YELLOW;
}
public class MainActivity extends AppCompatActivity {
    heat color;

    int totalClicks=0;
    int button_0 =0;
    int button_1 =0;    int button_9 =0;
    int button_2 =0;    int button_d =0;
    int button_3 =0;    int button_m =0;
    int button_4 =0;    int button_a =0;
    int button_5 =0;    int button_s =0;
    int button_6 =0;    int button_c =0;
    int button_7 =0;    int button_eq =0;
    int button_8 =0;
    Button button0;
    Button button1;
    Button button2;
    Button button3;
    Button button4;
    Button button5;
    Button button6;
    Button button7;
    Button button8;
    Button button9;
    Button buttonAdd;
    Button buttonSubtract;
    Button buttonMul;
    Button buttonDiv;
    Button buttonClear;
    Button buttonEqual;
    Button buttonSet;
    String result;
    String tmp;
    String operator;
    TextView resultTextView;
    boolean clearClicked = true;
    public int get_Color(int num)
    {   float res = 0;
        if(totalClicks != 0)
            res =  ((float)num /  (float)totalClicks) * 100;
        Log.d("Color", "get_Color: " + res);

        if(res>5 && res <=15)
            return heat.level1;
        if(res>15 && res <=35)
            return heat.level2;
        if(res>35)
            return heat.level3;
        return Color.GRAY;
    }
    void getTotal()
    {   totalClicks = button_0 + button_1 + button_9 + button_2 + button_d + button_3 + button_m + button_4
            + button_a + button_5 + button_s + button_6 + button_c + button_7 + button_eq + button_8;
    }
    public void coloring(){
        getTotal();
        button0.setBackgroundColor(get_Color(button_0));
        button1.setBackgroundColor(get_Color(button_1));
        button2.setBackgroundColor(get_Color(button_2));
        button3.setBackgroundColor(get_Color(button_3));
        button4.setBackgroundColor(get_Color(button_4));
        button5.setBackgroundColor(get_Color(button_5));
        button6.setBackgroundColor(get_Color(button_6));
        button7.setBackgroundColor(get_Color(button_7));
        button8.setBackgroundColor(get_Color(button_8));
        button9.setBackgroundColor(get_Color(button_9));
        buttonAdd.setBackgroundColor(get_Color(button_a));
        buttonSubtract.setBackgroundColor(get_Color(button_s));
        buttonMul.setBackgroundColor(get_Color(button_m));
        buttonDiv.setBackgroundColor(get_Color(button_d));
        buttonClear.setBackgroundColor(get_Color(button_c));
        buttonEqual.setBackgroundColor(get_Color(button_eq));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initControl();
        initControlListener();
    }

    private void initControlListener() {
        buttonSet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buttonSet = (Button) findViewById(R.id.buttonSet);
                buttonSet.setOnClickListener (new View.OnClickListener() {
                    public void onClick(View v) {
                        Intent i = new Intent(getApplicationContext(),NewActivity.class);
                        startActivity(i);
                    }
                });
            }
        });
        button0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onNumberButtonClicked("0");
            }
        });
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onNumberButtonClicked("1");
            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onNumberButtonClicked("2");
            }
        });
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onNumberButtonClicked("3");
            }
        });
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onNumberButtonClicked("4");
            }
        });
        button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onNumberButtonClicked("5");
            }
        });
        button6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onNumberButtonClicked("6");
            }
        });
        button7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onNumberButtonClicked("7");
            }
        });
        button8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onNumberButtonClicked("8");
            }
        });
        button9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onNumberButtonClicked("9");
            }
        });
        buttonClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClearButtonClicked();
            }
        });
        buttonSubtract.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                onOperatorButtonClicked("-");
            }
        });
        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onOperatorButtonClicked("+");
            }
        });
        buttonMul.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onOperatorButtonClicked("X");
            }
        });
        buttonDiv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onOperatorButtonClicked("/");
            }
        });
        buttonEqual.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onEqualButtonClicked();
            }
        });

    }

    private void onEqualButtonClicked() {
        float res = 0;
        button_eq++;
        coloring();
        try {
            float number = Integer.valueOf(tmp);
            float number2 = Integer.valueOf(resultTextView.getText().toString());
            switch (operator) {
                case "+":
                    res = number + number2;
                    break;
                case "/":
                    res = number / number2;
                    break;
                case "-":
                    res = number - number2;
                    break;
                case "X":
                    res = number * number2;
                    break;
            }
            result = String.valueOf(res);
            resultTextView.setText(result);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void onOperatorButtonClicked(String operator) {
        tmp = resultTextView.getText().toString();
        clearClicked = false;
        onClearButtonClicked();
        this.operator = operator;
        switch (this.operator) {
            case "+":
                button_a++;
                break;
            case "/":
                button_d++;
                break;
            case "-":
                button_s++;
                break;
            case "X":
                button_m++;
                break;
        }
        coloring();
    }

    private void onClearButtonClicked() {
        result = "";
        resultTextView.setText("");
        if(clearClicked)
        button_c++;
        clearClicked = true;
        coloring();
    }

    private void onNumberButtonClicked(String pos) {
        result = resultTextView.getText().toString();
        result = result + pos;
        resultTextView.setText(result);
        switch (pos) {
            case "0":
                button_0++;
                Log.d("Ababa", "onNumberButtonClicked: "+ button_0);
                break;
            case "1":
                button_1++;
                break;
            case "2":
                button_2++;
                break;
            case "3":
                button_3++;
                break;
            case "4":
                button_4++;
                break;
            case "5":
                button_5++;
                break;
            case "6":
                button_6++;
                break;
            case "7":
                button_7++;
                break;
            case "8":
                button_8++;
                break;
            case "9":
                button_9++;
                break;
        }
        coloring();
    }

    private void initControl() {
        buttonSet = (Button)findViewById(R.id.buttonSet);
        button0 = (Button)findViewById(R.id.button0);
        button1 = (Button)findViewById(R.id.button1);
        button2 = (Button)findViewById(R.id.button2);
        button3 = (Button)findViewById(R.id.button3);
        button4 = (Button)findViewById(R.id.button4);
        button5 = (Button)findViewById(R.id.button5);
        button6 = (Button)findViewById(R.id.button6);
        button7 = (Button)findViewById(R.id.button7);
        button8 = (Button)findViewById(R.id.button8);
        button9 = (Button)findViewById(R.id.button9);

        buttonAdd = (Button)findViewById(R.id.buttonAdd);
        buttonClear = (Button)findViewById(R.id.buttonClear);
        buttonSubtract = (Button)findViewById(R.id.buttonSub);
        buttonMul = (Button)findViewById(R.id.buttonMul);
        buttonDiv = (Button)findViewById(R.id.buttonDiv);
        buttonEqual = (Button)findViewById(R.id.buttonEqual);

        resultTextView = (TextView)findViewById(R.id.text_view_result);
        coloring();
    }
}
