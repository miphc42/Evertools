package everything.philipchoi;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class utilityCalculator extends Fragment {
    EditText ea, eb, ec, emax, emin, estep;
    TextView roots;
    View view;
    Button button;
    String[] arrayy;
    private double a,b,c,first,second,step;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.equationsolvecalc, container, false);
        ea = view.findViewById(R.id.a);
        eb = view.findViewById(R.id.b);
        ec = view.findViewById(R.id.c);
        emax = view.findViewById(R.id.max);
        emin = view.findViewById(R.id.min);
        estep = view.findViewById(R.id.min);
        roots = view.findViewById(R.id.roots);

        button = view.findViewById(R.id.calculate);
        int m =0;


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               // arrayy = editText.getText().toString().split(" ");
                a = Double.parseDouble(ea.getText().toString());
                b = Double.parseDouble(eb.getText().toString());
                c = Double.parseDouble(ec.getText().toString());
                first = Double.parseDouble(emax.getText().toString());
                second = Double.parseDouble(emin.getText().toString());
                step = Double.parseDouble(estep.getText().toString());
                double tracker1 = (Math.abs((second-first)/step))+1;
                int tracker = (int)tracker1;
                double array[] = new double[tracker];
                double array1[] = new double[tracker];
                double array2[] = new double[tracker];
                double limit =0;
                for (int count = 0 ; count<=Math.abs((second-first)/step); count++){
                    double y = a*Math.pow((first+limit),2)+b*(first+limit)+c;
                    array[count] = y;
                    array1[count] = first+limit;
                    array2[count] = first+limit;
                    limit += step;
                }
                double max = array[0];
                double maxx = array1[0];
                for (int counter = 1; counter<array.length;counter++){
                    if(array[counter]>=max){
                        max = array[counter];
                        maxx = array1[counter];
                    }
                }
                double min = array[0];
                double minx = array2[0];
                for (int ct = 1; ct<array.length;ct++){
                    if(array[ct]<min){
                        min = array[ct];
                        minx = array2[ct];
                    }
                }
                System.out.println();
                System.out.println();
                double e1 = a*Math.pow((-minx),2)+b*(-minx)+c;
                double e2 = a*Math.pow((-maxx),2)+b*(-maxx)+c;
                if (minx!=0&Math.round(min) == Math.round(e1)&(Math.round(-minx)==second|Math.round(-minx)==first)) {
                    System.out.printf(("Maximum value when x = %.2f\t f(%.2f) = %.2f\n\nMinimum value when x = \u00B1 %.2f\t f(%.2f) = %.2f, f(%.2f) = %.2f"), maxx, maxx, max, Math.abs(minx), minx,min, -minx, min);

                    String root = String.format("Maximum value when x = %.2f\t f(%.2f) = %.2f\n\nMinimum value when x = \u00B1 %.2f\t f(%.2f) = %.2f, f(%.2f) = %.2f", maxx, maxx, max, Math.abs(minx), minx,min, -minx, min);
                    roots.setText(String.format("Maximum value when x = %.2f\t f(%.2f) = %.2f\n\nMinimum value when x = \u00B1 %.2f\t f(%.2f) = %.2f, f(%.2f) = %.2f", maxx, maxx, max, Math.abs(minx), minx,min, -minx, min));
                }
                else if (maxx!=0&Math.round(max) == Math.round(e2)&(Math.round(-maxx)==second|Math.round(-maxx)==first)){
                    System.out.printf("Maximum value when x = \u00B1 %.2f\t f(%.2f) = %.2f, f(%.2f) = %.2f\n\nMinimum value when x = %.2f\t f(%.2f) = %.2f", Math.abs(maxx), maxx, max, -maxx, max, minx, minx,min);
                    roots.setText(String.format("Maximum value when x = \u00B1 %.2f\t f(%.2f) = %.2f, f(%.2f) = %.2f\n\nMinimum value when x = %.2f\t f(%.2f) = %.2f", Math.abs(maxx), maxx, max, -maxx, max, minx, minx,min));
                }
                else{
                    System.out.printf("Maximum value when x = %.2f\t f(%.2f) = %.2f\nMinimum value when x = %.2f\t f(%.2f) = %.2f", maxx, maxx, max, minx, minx,min);
                }
            }
        });


        return view;
    }
}
