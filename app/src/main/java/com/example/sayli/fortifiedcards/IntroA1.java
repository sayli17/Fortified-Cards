package com.example.sayli.fortifiedcards;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.heinrichreimersoftware.materialintro.app.IntroActivity;
import com.heinrichreimersoftware.materialintro.app.NavigationPolicy;
import com.heinrichreimersoftware.materialintro.app.OnNavigationBlockedListener;
import com.heinrichreimersoftware.materialintro.slide.SimpleSlide;

public class IntroA1 extends IntroActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        setFullscreen(true);

        super.onCreate(savedInstanceState);

        /* Enable/disable skip button */
        setSkipEnabled(true);

        /* Enable/disable finish button */
        setFinishEnabled(true);

        addSlide(new SimpleSlide.Builder()
                .title("Welcome to Fortified Cards")
                .description("Make sure to register your IMEI number with your bank")
                .image(R.drawable.bank)
                .background(R.color.intro1)
                .backgroundDark(R.color.colorPrimaryDark)
                .build());

        addSlide(new SimpleSlide.Builder()
                .title("Please note")
                .description("1) To find your IMEI number, dial *#06# in your smartphone or else go to Settings " +
                        "> About phone > IMEI number.\n2) If you have dual-sim phone or multiple phones, register all " +
                        "the IMEI numbers with your bank.\n3) Incase of lost/stolen credit/debit card or your phone, " +
                        "contact 1800-xxx-xxxx immediately.")
                .image(R.drawable.warning)
                .background(R.color.intro2)
                .backgroundDark(R.color.colorPrimaryDark)
                .build());

        addSlide(new SimpleSlide.Builder()
                .title("Scan the QR Code present on your credit/debit card.")
                .image(R.drawable.cscan)
                .background(R.color.intro3)
                .backgroundDark(R.color.colorPrimaryDark)
                .build());

        addSlide(new SimpleSlide.Builder()
                .title("Scan Vendor's QR Code.")
                .image(R.drawable.cscan1)
                .background(R.color.intro4)
                .backgroundDark(R.color.colorPrimaryDark)
                .build());
    }
}
