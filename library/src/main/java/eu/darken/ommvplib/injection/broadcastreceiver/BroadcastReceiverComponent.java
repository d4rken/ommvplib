package eu.darken.ommvplib.injection.broadcastreceiver;

import android.content.BroadcastReceiver;
import android.support.annotation.Nullable;

import dagger.android.AndroidInjector;

public interface BroadcastReceiverComponent<ReceiverT extends BroadcastReceiver> extends AndroidInjector<ReceiverT> {
    abstract class Builder<ReceiverT extends BroadcastReceiver, ComponentT extends BroadcastReceiverComponent<ReceiverT>>
            extends AndroidInjector.Builder<ReceiverT> {
        public abstract ComponentT build();

        @Override
        public void seedInstance(@Nullable ReceiverT instance) {

        }
    }
}
