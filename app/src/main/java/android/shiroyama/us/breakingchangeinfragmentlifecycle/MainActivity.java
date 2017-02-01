package android.shiroyama.us.breakingchangeinfragmentlifecycle;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.container, FragmentA.newInstance(), FragmentA.class.getSimpleName())
                .commit();
    }

    public static class FragmentA extends LifecycleFragment {
        public FragmentA() {
        }

        public static FragmentA newInstance() {
            return new FragmentA();
        }

        @Nullable
        @Override
        public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
            View view = inflater.inflate(R.layout.fragment_a, container, false);
            view.findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    getFragmentManager()
                            .beginTransaction()
                            .setAllowOptimization(true)
                            .replace(R.id.container, FragmentB.newInstance(), FragmentB.class.getSimpleName())
                            .commit();
                }
            });
            return view;
        }
    }

    public static class FragmentB extends LifecycleFragment {
        public FragmentB() {

        }

        public static FragmentB newInstance() {
            return new FragmentB();
        }

        @Nullable
        @Override
        public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
            View view = inflater.inflate(R.layout.fragment_b, container, false);
            view.findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    getFragmentManager()
                            .beginTransaction()
                            // default is "disabled" as of 25.1.1
                            // .setAllowOptimization(false)
                            .replace(R.id.container, FragmentA.newInstance(), FragmentA.class.getSimpleName())
                            .commit();
                }
            });
            return view;
        }
    }

    public abstract static class LifecycleFragment extends Fragment {
        public LifecycleFragment() {
        }

        @Override
        public void onActivityCreated(@Nullable Bundle savedInstanceState) {
            super.onActivityCreated(savedInstanceState);
            getActivity().setTitle(getTag());
        }

        @Override
        public void onStart() {
            super.onStart();
            Log.d(getTag(), "onStart()");
        }

        @Override
        public void onResume() {
            super.onResume();
            Log.d(getTag(), "onResume()");
        }

        @Override
        public void onPause() {
            Log.d(getTag(), "onPause()");
            super.onPause();
        }

        @Override
        public void onStop() {
            Log.d(getTag(), "onStop()");
            super.onStop();
        }
    }
}
