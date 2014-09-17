
package com.triage.learning.rulehelpers;

import com.triage.learning.Foo;
import com.triage.learning.FooWrapper;

/**
 *
 * @author dcowden
 */
public class FooHelper {
    public FooWrapper wrap(Foo original){
        return new FooWrapper(original);
    }
}
