package com.rizn.wonderland.android;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;
import com.rizn.wonderland.AdTutorial;
import com.rizn.wonderland.AdsInterface;
import com.rizn.wonderland.Sfx;
import com.rizn.wonderland.Wonderland;

public class AndroidLauncher extends AndroidApplication implements AdsInterface {

    private static final String INTERSTITIAL_UNIT_ID = "ca-app-pub-9323566212516578/8441743844";
    InterstitialAd interstitialAd;

	@Override
	protected void onCreate (Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();
		config.useAccelerometer = false;
		config.useCompass = false;
		config.useWakelock = true;
		initialize(new Wonderland(this), config);
        //initialize(new AdTutorial(this), config);
        setupAds();
	}

    private void setupAds() {
        interstitialAd = new InterstitialAd(this);
        interstitialAd.setAdUnitId(INTERSTITIAL_UNIT_ID);

        AdRequest.Builder builder = new AdRequest.Builder();
        AdRequest ad = builder.build();
        interstitialAd.loadAd(ad);
    }

    @Override
    public void showInterstitialAd(final Runnable then) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (then != null) {
                    interstitialAd.setAdListener(new AdListener() {
                        @Override
                        public void onAdClosed() {
                            Gdx.app.postRunnable(then);
                            AdRequest.Builder builder = new AdRequest.Builder();
                            AdRequest ad = builder.build();
                            interstitialAd.loadAd(ad);
                        }
                    });
                }
                interstitialAd.show();
            }
        });
    }

    @Override
    public boolean isWifiConnected() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo ni = cm.getNetworkInfo(ConnectivityManager.TYPE_WIFI);

        return (ni != null && ni.isConnected());
    }

    @Override
    protected void onPause () {
        super.onPause();
        Sfx.disposeMusic();
    }
}
