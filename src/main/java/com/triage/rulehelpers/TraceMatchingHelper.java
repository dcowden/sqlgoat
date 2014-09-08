package com.triage.rulehelpers;

import com.triage.config.Configuration;
import com.triage.injection.SqlTracker;
import org.jboss.byteman.rule.Rule;
import org.jboss.byteman.rule.exception.ThrowException;
import org.jboss.byteman.rule.helper.Helper;

/**
 * This class contains byteman specific stuff.
 * The idea is that this class would be what is replaced if we went with 
 * another solution besides byteman
 * @author dcowden
 */
public class TraceMatchingHelper extends Helper {
    public TraceMatchingHelper(Rule rule){
        super(rule);
    }
    public void testSql(String sqlStatement){
        SqlTracker tracker = new SqlTracker(Configuration.active);
        String stackFrame = this.formatStack();
        //you can only throw byteman exceptions out of helpers.
        //otherwise, byteman throws a 'misconfigured handler' exception
        //but if you throw a ThrowException, Byteman knows to deliver
        //the exception to the caller
        //https://developer.jboss.org/thread/178055
        try{
            tracker.checkSql(stackFrame, sqlStatement);
        }
        catch ( BadSqlException bse ){
            throw new ThrowException(bse);
        }
    }
}
