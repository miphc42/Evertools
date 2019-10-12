package everything.philipchoi;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;


import java.util.ArrayList;

public class mainCalculator extends Fragment {
    View view;
    private TextView current, answer;
    private Button zero, one, two, three, four, five, six, seven, eight, nine;
    private Button divide, multiply, add, subtract, delete, equals, decimal;
    private int operand, index;
    private String now;
    ArrayList<Integer> numbers = new ArrayList<>();
    ArrayList<String> functions = new ArrayList<>();
    String  x[] = new String[100];
    @SuppressLint("ResourceType")
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view =  inflater.inflate(R.layout.calculator_main, container, false);
        now = "";
        zero = view.findViewById(R.id.zero);
        one = view.findViewById(R.id.one);
        two = view.findViewById(R.id.two);
        three = view.findViewById(R.id.three);
        four = view.findViewById(R.id.four);
        five = view.findViewById(R.id.five);
        six = view.findViewById(R.id.six);
        seven = view.findViewById(R.id.seven);
        eight = view.findViewById(R.id.eight);
        nine = view.findViewById(R.id.nine);
        divide = view.findViewById(R.id.divide);
        multiply = view.findViewById(R.id.multiply);
        subtract = view.findViewById(R.id.minus);
        add = view.findViewById(R.id.add);
        equals = view.findViewById(R.id.equals);
        delete = view.findViewById(R.id.cancel);
        decimal = view.findViewById(R.id.decimal);
        current = view.findViewById(R.id.text);
        answer = view.findViewById(R.id.answer);
        onClick();
        return view;
    }
    public void onClick(){
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                current.setText("");
                answer.setText("");
                now = "";
                numbers.clear();
                functions.clear();
            }
        });
        one.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                now += "1";
                numbers.add(1);
                current.setText(now);
            }
        });
        two.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                now += "2";
                numbers.add(2);
                current.setText(now);
            }
        });
        three.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                now += "3";
                numbers.add(3);
                current.setText(now);
            }
        });
        four.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                now += "4";
                numbers.add(4);
                current.setText(now);
            }
        });
        five.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                now += "5";
                numbers.add(5);
                current.setText(now);
            }
        });
        six.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                now += "6";
                numbers.add(6);
                current.setText(now);
            }
        });
        seven.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                now += "7";
                numbers.add(7);
                current.setText(now);
            }
        });
        eight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                now += "8";
                numbers.add(8);
                current.setText(now);
            }
        });
        nine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                now += "9";
                numbers.add(9);
                current.setText(now);
            }
        });
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                operand = 0;
              //  numbers.add(now);
                now+= " + ";
                functions.add("+");
                current.setText(now);

            }
        });
        subtract.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                operand = 1;
                now+=" - ";
                functions.add("-");
                current.setText(now);

            }
        });
        multiply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                operand = 2;
                now+=" x ";
                functions.add("x");
                current.setText(now);

            }
        });
        divide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                operand = 3;
                now+=" / ";
                functions.add("/");
                current.setText(now);

            }
        });
        equals.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                operand = 4;

                int count = 0;
                int t = numbers.size();
                int all = numbers.get(0);
                for(int c = 0; c < numbers.size(); c++) {
                    if (c + 1 == numbers.size()) {
                        break;
                    }
                    if (functions.get(c).equals("x")) {
                        all *= numbers.get(c + 1);
                    }
                    if (functions.get(c).equals("+")) {
                        System.out.println("PLUS");
                        all += numbers.get(c+1);
                    }
                    if (functions.get(c).equals("-")) {
                        all -= numbers.get(c+1);
                    }
                    if (functions.get(c).equals("/")) {
                        all /= numbers.get(c+1);
                    }
                    System.out.println("ALL" + " " + c + "  " + all);
                }
                answer.setText(Integer.toString(all));
                functions.clear();
                numbers.clear();
                all = 0;

                /*
                for(int i =0; i < now.length(); i++){
                    if(functions.get(i)=="x"){
                        all = 1;
                        x = now.split("x");
                        for(int c = 0; c < x.length; c++){
                            if(x[c].contains("-"){
                                x[c].split("-");
                            }
                        }
                    }

                }
                */
                /*
                try {
                    x = now.split(" p ");
                    operand = Integer.parseInt(x[0]) + Integer.parseInt(x[1]);
                    answer.setText(Integer.toString(operand));
                }catch(Exception e){
                }
                try {
                    x = now.split(" - ");
                    operand = Integer.parseInt(x[0]) - Integer.parseInt(x[1]);
                    answer.setText(Integer.toString(operand));
                }catch(Exception e){

                }
                try {
                    x = now.split(" x ");

                    operand = Integer.parseInt(x[0]) * Integer.parseInt(x[1]);
                    answer.setText(Integer.toString(operand));
                }catch(Exception e){

                }
                try {
                    x = now.split(" / ");
                    operand = Integer.parseInt(x[0]) / Integer.parseInt(x[1]);
                    answer.setText(Integer.toString(operand));
                }catch(Exception e){

                }
                */
            }
        });
        decimal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            }
        });
    }
}
