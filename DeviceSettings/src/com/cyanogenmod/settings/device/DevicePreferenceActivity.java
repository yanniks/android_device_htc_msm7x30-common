/*
 * Copyright (C) 2011 The CyanogenMod Project
 * Copyright (C) 2013 Mustaavalkosta
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.cyanogenmod.settings.device;

import android.content.Context;
import android.os.Bundle;
import android.preference.CheckBoxPreference;
import android.preference.Preference;
import android.preference.PreferenceFragment;
import android.preference.PreferenceScreen;
import com.cyanogenmod.settings.device.R;

public class DevicePreferenceActivity extends PreferenceFragment {

    public static final String KEY_FAST_CHARGE = "fast_charge";
    public static final String KEY_SWEEP2WAKE = "sweep2wake";

    private Context context;
    private CheckBoxPreference mFastCharge;
    private CheckBoxPreference mSweep2Wake;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        addPreferencesFromResource(R.xml.preferences);
        context = getActivity().getApplication();

        mFastCharge = (CheckBoxPreference) findPreference(KEY_FAST_CHARGE);
        mFastCharge.setChecked(FastCharge.isEnabled());
        mFastCharge.setEnabled(FastCharge.isSupported());

        mSweep2Wake = (CheckBoxPreference) findPreference(KEY_SWEEP2WAKE);
        mSweep2Wake.setChecked(Sweep2Wake.isEnabled());
        mSweep2Wake.setEnabled(Sweep2Wake.isSupported());
    }

    @Override
    public boolean onPreferenceTreeClick(PreferenceScreen preferenceScreen, Preference preference) {
        if (preference == mFastCharge) {
            if (mFastCharge.isChecked())
                FastCharge.enable(context);
            else
                FastCharge.disable(context);
            return true;
        } else if (preference == mSweep2Wake) {
            if (mSweep2Wake.isChecked())
                Sweep2Wake.enable(context);
            else
                Sweep2Wake.disable(context);
            return true;
        }
        return super.onPreferenceTreeClick(preferenceScreen, preference);
    }
}
