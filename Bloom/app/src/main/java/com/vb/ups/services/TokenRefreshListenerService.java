package com.vb.ups.services;

import android.content.Intent;

import com.google.android.gms.iid.InstanceIDListenerService;
import com.vb.ups.services.RegistrationService;

/**
 * Created by berna on 11/5/2016.
 */

public class TokenRefreshListenerService extends InstanceIDListenerService {
    @Override
    public void onTokenRefresh() {
        Intent i = new Intent(this, RegistrationService.class);
        startService(i);
    }
}