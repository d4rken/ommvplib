package eu.darken.ommvplib.example.pager.shifting;

import dagger.Module;
import dagger.Provides;

@Module
public class ShiftingModule {

    private final String initialText;

    public ShiftingModule(String initialText) {
        this.initialText = initialText;
    }

    @Provides
    @ShiftingScope
    Shifter provideCounter() {
        return new Shifter(initialText);
    }

}
