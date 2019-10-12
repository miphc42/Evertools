package everything.philipchoi;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.DataPointInterface;
import com.jjoe64.graphview.series.LineGraphSeries;
import com.jjoe64.graphview.series.OnDataPointTapListener;
import com.jjoe64.graphview.series.PointsGraphSeries;
import com.jjoe64.graphview.series.Series;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;

public class graphingCalculator extends Fragment {
    private static final String TAG = "MainActivity";
    PointsGraphSeries<DataPoint> xySeries;
    ArrayList<Double> xs = new ArrayList<>();
    ArrayList<Double> ys = new ArrayList<>();
    ArrayList<Double> eqs = new ArrayList<>();
    double t2;
    double t;
    int slope2;
    int slope;
    double operator;
    private Button btnAddPt;

    private EditText mX,mY, eq;

    GraphView mScatterPlot;
    View view;
    //make xyValueArray global
    private ArrayList<XYValue> xyValueArray;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.graphingcalc, container, false);
        btnAddPt = (Button) view.findViewById(R.id.button);
        mX = (EditText) view.findViewById(R.id.editText);
        mY = (EditText) view.findViewById(R.id.editext2);
        eq = (EditText) view.findViewById(R.id.editText3);
        mScatterPlot = (GraphView) view.findViewById(R.id.graph);
        xyValueArray = new ArrayList<>();
     //   eqs = new ArrayList<>();

        init();
        return view;
    }
    private void init(){

        //declare the xySeries Object
        xySeries = new PointsGraphSeries<>();
        btnAddPt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.println("AAAAAA");
                String equation = eq.getText().toString();
                InputMethodManager mgr = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                mgr.hideSoftInputFromWindow(eq.getWindowToken(), 0);

                if(!eq.getText().toString().contains("sin")) {
                    slope2 = 0;
                    slope = Integer.parseInt(eq.getText().toString().split("x")[0]);
                }
                    try {
                        slope2 = Integer.parseInt(eq.getText().toString().split("-")[1].split("x")[0]);
                        System.out.println("A" + slope2);
                    } catch (Exception e) {
                        try {
                            slope2 = Integer.parseInt((eq.getText().toString().split("\\+")[1]).split("x")[0]);
                            //slope2 = Integer.parseInt("AAAAAAA".split(" {3}y {3} ")[0]);
                            System.out.println("B" + slope2);
                        } catch (Exception l) {
                            slope2 = 0;
                        }
                    }
                    System.out.println("SLOPEEEEEEEEEEEEEE" + slope2);
                    System.out.println("SLOPE @@@@@@@@@" + slope2);
                    try {
                        operator = Integer.parseInt(eq.getText().toString().split("\\+")[1].split("\\+")[1]);
                    } catch (Exception e) {
                        try {
                            operator = Integer.parseInt(eq.getText().toString().split("\\+")[1]);
                        } catch (Exception t) {
                            try {
                                operator = -(Integer.parseInt(eq.getText().toString().split("-")[1].split("-")[1]));
                            } catch (Exception p) {
                                try {
                                    operator = -(Integer.parseInt(eq.getText().toString().split("-")[1]));
                                } catch (Exception d) {
                                    operator = 0;
                                }
                            }
                        }
                    }
                    int exponent = 0;
                    try {
                        exponent = Integer.parseInt(eq.getText().toString().split("\\^")[1].split("\\+")[0]);
                    } catch (Exception e) {
                        try {
                            exponent = Integer.parseInt(eq.getText().toString().split("\\^")[1].split("-")[0]);
                        }catch (Exception g){
                            e.printStackTrace();
                        }
                        }
                int counter = -7;
                int counterTrig = -7;
                System.out.println(equation);
                t = 0;
                t2 = 0;
                /*
                if(equation.equals("sinx")){
                    for(int i = 0; i < 40; i+=1){
                        System.out.println(counter);
                        t = Math.sin(counterTrig);
                        if(t<100) {
                            t2 = counter;
                            ys.add(t);
                            xs.add(t2);
                            System.out.println(xs);
                            counter+=1;
                        }else{break;}
                    }
                }*/
                if(equation.contains("^")&&exponent==2){
                    System.out.println("A VALUE"+slope);
                    System.out.println("B VALUE"+slope2);
                    System.out.println("C VALUE"+operator);
                    for(int i = 0; i < 14; i+=1){
                        System.out.println(counter);
                        t = slope*Math.pow(counter, exponent)+(slope2*counter)+operator;
                        if(t<100) {
                            t2 = counter;
                            ys.add(t);
                            xs.add(t2);
                            System.out.println(xs);
                            counter+=1;
                        }else{break;}
                    }
                }
                else if(equation.contains("^")&&exponent>2){
                    for(int i = 0; i < 40; i+=1){
                        System.out.println(counter);
                        t = slope*Math.pow(counter, exponent);
                        if(t<100) {
                            t2 = counter;
                            ys.add(t);
                            xs.add(t2);
                            System.out.println(xs);
                            counter+=1;
                        }else{break;}
                    }
                }
                else {
                    for (int i = 0; i < 40; i += 1) {
                        t = slope * i;
                        if (t < 50) {
                            t2 = i;
                            ys.add(t);
                            xs.add(t2);
                        } else {
                            break;
                        }
                    }
                }
                xyValueArray.clear();
                if(!mX.getText().toString().equals("") && !mY.getText().toString().equals("") ) {
                    for (int i = 0; i < xs.size(); i++){
                        double x = xs.get(i);
                        double y = ys.get(i);
                    Log.d(TAG, "onClick: Adding a new point. (x,y): (" + x + "," + y + ")");
                    xyValueArray.add(new XYValue(x, y));
                }
                    init();
                }else {
                    toastMessage("You must fill out both fields!");
                }
            }
        });

        //little bit of exception handling for if there is no data.
        if(xyValueArray.size() != 0){
            createScatterPlot();
        }else{
            Log.d(TAG, "onCreate: No data to plot.");
        }
    }
    LinkedHashMap<Double, Double> xys  = new LinkedHashMap<>();
    private void createScatterPlot() {

        Log.d(TAG, "createScatterPlot: Creating scatter plot.");
        //sort the array of xy values
        xyValueArray = sortArray(xyValueArray);
       // System.out.println(xyValueArray);
       // xs.add(Double.parseDouble(mX.getText().toString()));
      //  ys.add(Double.parseDouble(mY.getText().toString()));
        //xys.put(Double.parseDouble(mX.getText().toString()), Double.parseDouble(mY.getText().toString()));
        GraphView graph = (GraphView) view.findViewById(R.id.graph);
        DataPoint[] dataPoints = new DataPoint[xs.size()]; // declare an array of DataPoint objects with the same size as your list
        mScatterPlot.removeAllSeries();
        for (int i = 0; i < xs.size(); i++) {
            System.out.println("X"+"  "+i+"\t"+xs.get(i));
            System.out.println("Y"+"  "+i+"\t"+ys.get(i));
            // add new DataPoint object to the array for each of your list entries
            dataPoints[i] = new DataPoint(xs.get(i), ys.get(i)); // not sure but I think the second argument should be of type double
        }
        /*
        dataPoints[xs.size()] = new DataPoint(98, 98);
        dataPoints[xs.size()+1] = new DataPoint(98, -98);
        dataPoints[xs.size()+2] = new DataPoint(-98, -98);
        dataPoints[xs.size()+3] = new DataPoint(-98, 98);*/
        LineGraphSeries<DataPoint> series = new LineGraphSeries<>(dataPoints);
        /*
        LineGraphSeries<DataPoint> series = new LineGraphSeries<>(new DataPoint[] {
                // new DataPoint(x, y),
                new DataPoint(2, 3),
                new DataPoint(Double.parseDouble(mX.getText().toString()), Double.parseDouble(mY.getText().toString())),
                //   new DataPoint(3, 2),
                //  new DataPoint(4, 6)
        });
       */
        series.setOnDataPointTapListener(new OnDataPointTapListener() {
            @Override
            public void onTap(Series series, DataPointInterface dataPoint) {
                Toast.makeText(getActivity(), ""+dataPoint, Toast.LENGTH_SHORT).show();
            }
        });
        mScatterPlot.addSeries(series);
        //add the data to the series
        for(int i = 0;i <xyValueArray.size(); i++){
            try{
                double x = xyValueArray.get(i).getX();
                double y = xyValueArray.get(i).getY();
                xySeries.appendData(new DataPoint(x,y),true, 1000);
            }catch (IllegalArgumentException e){
                Log.e(TAG, "createScatterPlot: IllegalArgumentException: " + e.getMessage() );
            }
        }
        //set some properties
        xySeries.setShape(PointsGraphSeries.Shape.POINT);
        xySeries.setColor(Color.BLUE);
        xySeries.setSize(15f);

        //set Scrollable and Scaleable
        mScatterPlot.getViewport().setScalable(true);
        mScatterPlot.getViewport().setScalableY(true);
        mScatterPlot.getViewport().setScrollable(true);
        mScatterPlot.getViewport().setScrollableY(true);
      //  graph.getViewport().setScrollable(true);
     //   graph.getViewport().setScrollableY(true);
        //set manual x bounds
        System.out.println("MAXXXXXXX"+Collections.max(ys));
        mScatterPlot.getViewport().setYAxisBoundsManual(true);
        mScatterPlot.getViewport().setMaxY(Collections.max(ys)+10);
        mScatterPlot.getViewport().setMinY(Collections.min(ys)-10);

        //set manual y bounds
        mScatterPlot.getViewport().setXAxisBoundsManual(true);
        mScatterPlot.getViewport().setMaxX(Collections.max(xs)+10);
        mScatterPlot.getViewport().setMinX(Collections.min(xs)-10);
       // mScatterPlot.getViewport().setScrollable(true);
       // mScatterPlot.getViewport().setScrollableY(true);
       // mScatterPlot.getViewport().
        xs.clear();
        ys.clear();
        mScatterPlot.addSeries(xySeries);
    }

    /**
     * Sorts an ArrayList<XYValue> with respect to the x values.
     * @param array
     * @return
     */
    private ArrayList<XYValue> sortArray(ArrayList<XYValue> array){
        /*
        //Sorts the xyValues in Ascending order to prepare them for the PointsGraphSeries<DataSet>
         */
        int factor = Integer.parseInt(String.valueOf(Math.round(Math.pow(array.size(),2))));
        int m = array.size() - 1;
        int count = 0;
        Log.d(TAG, "sortArray: Sorting the XYArray.");


        while (true) {
            m--;
            if (m <= 0) {
                m = array.size() - 1;
            }
            Log.d(TAG, "sortArray: m = " + m);
            try {
                //print out the y entrys so we know what the order looks like
                //Log.d(TAG, "sortArray: Order:");
                //for(int n = 0;n < array.size();n++){
                //Log.d(TAG, "sortArray: " + array.get(n).getY());
                //}
                double tempY = array.get(m - 1).getY();
                double tempX = array.get(m - 1).getX();
                if (tempX > array.get(m).getX()) {
                    array.get(m - 1).setY(array.get(m).getY());
                    array.get(m).setY(tempY);
                    array.get(m - 1).setX(array.get(m).getX());
                    array.get(m).setX(tempX);
                } else if (tempX == array.get(m).getX()) {
                    count++;
                    Log.d(TAG, "sortArray: count = " + count);
                } else if (array.get(m).getX() > array.get(m - 1).getX()) {
                    count++;
                    Log.d(TAG, "sortArray: count = " + count);
                }
                //break when factorial is done
                if (count == factor) {
                    break;
                }
            } catch (ArrayIndexOutOfBoundsException e) {
                Log.e(TAG, "sortArray: ArrayIndexOutOfBoundsException. Need more than 1 data point to create Plot." +
                        e.getMessage());
                break;
            }
        }
        return array;
    }
    private void toastMessage(String message){
        Toast.makeText(getContext(),message, Toast.LENGTH_SHORT).show();
    }
}
