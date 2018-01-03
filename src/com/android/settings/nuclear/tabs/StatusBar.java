package com.android.settings.nuclear.tabs;

import android.app.ActivityManager;
import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.provider.Settings;
import android.preference.Preference;
import android.preference.PreferenceScreen;
import android.preference.PreferenceCategory;
import android.preference.Preference.OnPreferenceChangeListener;
import android.preference.SwitchPreference;
import android.telephony.TelephonyManager;
import android.telephony.SubscriptionManager;

import com.android.internal.logging.MetricsProto.MetricsEvent;
import com.android.settings.R;
import com.android.settings.SettingsPreferenceFragment;


public class StatusBar extends SettingsPreferenceFragment implements
        Preference.OnPreferenceChangeListener {
    private static final String TAG = "StatusBar";
    private static final String SIM_EMPTY_SWITCH = "no_sim_cluster_switch";
    private SubscriptionManager mSm;


    private SwitchPreference mNoSims;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        addPreferencesFromResource(R.xml.statusbar);

        mNoSims = (SwitchPreference) findPreference(SIM_EMPTY_SWITCH);
        mSm = (SubscriptionManager) getSystemService(getContext().TELEPHONY_SUBSCRIPTION_SERVICE);

        if (mNoSims != null) { 
            if (!TelephonyManager.getDefault().isMultiSimEnabled() || mSm.getActiveSubscriptionInfoCount() <= 0){
                getPreferenceScreen().removePreference(mNoSims);
            }
        }
    }

    @Override
    protected int getMetricsCategory() {
        return MetricsEvent.APPLICATION;
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    public boolean onPreferenceChange(Preference preference, Object objValue) {
        final String key = preference.getKey();
        return true;
    }
}