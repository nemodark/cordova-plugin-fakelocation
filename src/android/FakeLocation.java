package com.richardreal.fakelocation;

// import org.apache.cordova.CallbackContext;
// import org.apache.cordova.CordovaPlugin;

import com.getcapacitor.JSObject;
import com.getcapacitor.Plugin;
import com.getcapacitor.PluginCall;
import com.getcapacitor.PluginMethod;
import com.getcapacitor.annotation.CapacitorPlugin;

import android.content.Context;
import android.location.Location;
import android.os.Build;
import android.provider.Settings;

import org.json.JSONArray;
import org.json.JSONException;

@CapacitorPlugin(name = "FakeLocation")
public class FakeLocation extends Plugin {
    @PluginMethod
    public boolean execute(String action, JSONArray data, Context context, Location location, PluginCall call) throws JSONException {
        boolean isMock = false;
        JSObject result = new JSObject();
        if (action.equals("check")) {
            if (Build.VERSION.SDK_INT >= 18) {
                isMock = location.isFromMockProvider();
            } else {
                isMock = Settings.Secure.getString(context.getContentResolver(), Settings.Secure.ALLOW_MOCK_LOCATION).equals(0);
            }

            result.put("is_mock", isMock);

            call.resolve(result);
            return true;
        }
        return false;
    }
}
