package everything.philipchoi;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;


import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class openUp extends Fragment {
    View view;
    TextView textView;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        (getActivity()).setTitle("Home");
        view = inflater.inflate(R.layout.openup, container, false);
         textView = view.findViewById(R.id.txtView);
         DateFormat df = new SimpleDateFormat("h:mm a");
         String date = df.format(Calendar.getInstance().getTime());
         String time = date.split(":")[0];
         String aorp = date.split(" ")[1];
         if(Integer.parseInt(time)>0&&Integer.parseInt(time)<5&&aorp.equals("p.m.")){
             textView.setText("Good Afternoon!");
         }
         if(Integer.parseInt(time)>=5&&aorp.equals("p.m.")){
             textView.setText("Good Evening!");
         }
         if(Integer.parseInt(time)>=5&&aorp.equals("a.m.")){
            textView.setText("Good Morning!");
        }
        if(Integer.parseInt(time)<5&&aorp.equals("a.m.")){
            textView.setText("Good Morning, it's Early!");
        }

         return view;
    }
}
