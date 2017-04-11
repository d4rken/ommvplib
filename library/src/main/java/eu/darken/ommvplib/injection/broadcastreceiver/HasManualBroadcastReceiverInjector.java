package eu.darken.ommvplib.injection.broadcastreceiver;


import android.content.BroadcastReceiver;

import dagger.android.HasBroadcastReceiverInjector;
import eu.darken.ommvplib.injection.ManualInjector;

public interface HasManualBroadcastReceiverInjector extends HasBroadcastReceiverInjector {
    @Override
    ManualInjector<BroadcastReceiver> broadcastReceiverInjector();
}
