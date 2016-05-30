package org.aleajactarest;

import com.google.inject.Binder;
import com.google.inject.Module;
import com.google.inject.Provides;
import org.aleajactarest.assembly.CustomDiceRollResultAssembly;
import org.aleajactarest.assembly.DiceRollResultAssembly;
import org.aleajactarest.parser.DiceNotationParser;

public class AleaJactaRestModule implements Module {

    @Override
    public void configure(Binder binder) {

    }

    @Provides
    public DiceRollResultAssembly assembly() {
        return new DiceRollResultAssembly();
    }

    @Provides
    public DiceNotationParser parser() {
        return new DiceNotationParser();
    }

    @Provides
    public CustomDiceRollResultAssembly customAssembly() {
        return new CustomDiceRollResultAssembly();
    }
}
