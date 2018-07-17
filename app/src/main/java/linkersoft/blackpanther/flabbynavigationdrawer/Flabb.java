package linkersoft.blackpanther.flabbynavigationdrawer;

import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import static android.view.View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN;
import static android.view.View.SYSTEM_UI_FLAG_LAYOUT_STABLE;

public class Flabb extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.flabb);


        if (Build.VERSION.SDK_INT >= 21){
            setTheme(R.style.flabby);
            getWindow().setStatusBarColor(0x40FFFFFF);
            getWindow().getDecorView().setSystemUiVisibility(SYSTEM_UI_FLAG_LAYOUT_STABLE | SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
        }
    }
}
