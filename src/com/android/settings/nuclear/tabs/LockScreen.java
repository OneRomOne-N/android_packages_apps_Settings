/*
 NuclearTeam 2017'
 */

package com.android.settings.nuclear.tabs;

import android.app.ActivityManager;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.FragmentManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.os.SystemProperties;
import android.os.UserHandle;
import android.os.UserManager;
import android.provider.Settings;
import android.support.v14.preference.SwitchPreference;
import android.support.v7.preference.Preference;
import android.support.v7.preference.Preference.OnPreferenceChangeListener;
import android.support.v7.preference.Preference.OnPreferenceClickListener;
import android.support.v7.preference.PreferenceGroup;
import android.support.v7.preference.PreferenceScreen;
import android.text.TextUtils;
import android.util.Log;

import com.android.internal.logging.MetricsProto.MetricsEvent;
import com.android.internal.widget.LockPatternUtils;
import com.android.settings.R;
import com.android.settings.OwnerInfoSettings;
import com.android.settings.SettingsPreferenceFragment;
import com.android.settings.Utils;

import static android.provider.Settings.System.SCREEN_OFF_TIMEOUT; 


public class LockScreen extends SettingsPreferenceFragment implements
        Preference.OnPreferenceChangeListener {
    private static final String TAG = "LockScreen";
    private static final String KEY_OWNER_INFO_SETTINGS = "owner_info_settings";
    private static final int MY_USER_ID = UserHandle.myUserId();


    private Preference mOwnerInfoPref;
    private LockPatternUtils mLockPatternUtils;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        addPreferencesFromResource(R.xml.lockscreen);

        mLockPatternUtils = new LockPatternUtils(getContext());

        mOwnerInfoPref = (Preference) findPreference(KEY_OWNER_INFO_SETTINGS);
        mOwnerInfoPref.setOnPreferenceClickListener(new OnPreferenceClickListener() {
                @Override
                public boolean onPreferenceClick(Preference preference) {
                    OwnerInfoSettings.show(LockScreen.this);
                    return true;
                }   
        });        
    }

    @Override
    protected int getMetricsCategory() {
        return MetricsEvent.NUCLEAR;
    }

    @Override
    public void onResume() {
        super.onResume();

        updateOwnerInfo();
                
    }
    
    @Override
    public void onPause() {
        super.onPause();
    }

    public void updateOwnerInfo() {
        mOwnerInfoPref.setSummary(mLockPatternUtils.isOwnerInfoEnabled(MY_USER_ID)
                            ? mLockPatternUtils.getOwnerInfo(MY_USER_ID)
                            : getString(R.string.owner_info_settings_summary));
    }

    public boolean onPreferenceChange(Preference preference, Object objValue) {
        final String key = preference.getKey();
        return true;
    }

}
