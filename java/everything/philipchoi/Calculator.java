package everything.philipchoi;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Calculator extends Fragment {

    private BottomNavigationView topNavigationView;
    private FrameLayout frameLayout;
    Fragment f;
    View view;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        (getActivity()).setTitle("Calculator");
        view = inflater.inflate(R.layout.calculator, container, false);
        topNavigationView = view.findViewById(R.id.main_bar);
        frameLayout = view.findViewById(R.id.framerLayout);
        getChildFragmentManager().beginTransaction().replace(R.id.framerLayout,
                new mainCalculator()).commit();
        topNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.one:
                       // topNavigationView.setItemBackgroundResource(R.color.colorAccent);
                        getChildFragmentManager().beginTransaction().replace(R.id.framerLayout,
                                new mainCalculator()).commit();
                        return true;
                    case R.id.two:
                        getChildFragmentManager().beginTransaction().replace(R.id.framerLayout,
                                new utilityCalculator()).commit();
                        return true;
                    case R.id.three:
                        getChildFragmentManager().beginTransaction().replace(R.id.framerLayout,
                                new graphingCalculator()).commit();
                        return  true;
                    case R.id.four:
                        getChildFragmentManager().beginTransaction().replace(R.id.framerLayout,
                                new conversionCalculator()).commit();
                        return  true;
                        default:
                         return false;
                }
            }
        });
        return view;
    }
}
