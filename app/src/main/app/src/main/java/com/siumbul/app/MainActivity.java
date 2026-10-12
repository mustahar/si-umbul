package com.siumbul.app;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.webkit.CookieManager;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        webView = new WebView(this);
        setContentView(webView);

        // Efek Fade-In (dari gelap ke terang saat aplikasi dibuka)
        AlphaAnimation fadeIn = new AlphaAnimation(0.0f, 1.0f);
        fadeIn.setDuration(800); // Durasi transisi 0.8 detik
        webView.startAnimation(fadeIn);

        // Konfigurasi Pengaturan Web
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setDomStorageEnabled(true); // Menyimpan sesi & password login
        webSettings.setDatabaseEnabled(true);
        
        // Fitur Zoom 2 jari (Multi-touch zoom)
        webSettings.setBuiltInZoomControls(true);
        webSettings.setDisplayZoomControls(false);
        webSettings.setSupportZoom(true);

        // Mengaktifkan Cookie agar password otomatis tersimpan
        CookieManager.getInstance().setAcceptCookie(true);
        CookieManager.getInstance().setAcceptThirdPartyCookies(webView, true);

        webView.setWebViewClient(new WebViewClient());
        webView.loadUrl("https://jibas.masmu.sch.id/meterair/login.php");
    }

    // Fungsi Tombol Kembali (Back Button): Kembali ke halaman sebelumnya di web, 
    // dan keluar aplikasi jika sudah di halaman paling awal.
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && webView.canGoBack()) {
            webView.goBack();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
