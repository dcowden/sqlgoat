package com.triage.injection;

import com.triage.config.Configuration;
import com.triage.rulehelpers.DynamicSqlException;
import com.triage.rulehelpers.ProbableSqlInjectionException;
import com.triage.sqlparse.SqlParse;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.testng.log4testng.Logger;

/**
 * Stores hashed sql allocation sites, to detect dynamic sql 
 * This simple version has several issues:
 *    1.  it is unbounded. real systems would be at risk of running out of memory
 *    2. it uses a static map. Not sure if this is the right strategy
 *    3. it may not be all that fast-- need to make sure this is as fast as it can be
 * 
 * 
 * Overall Strategy:
 *    (1) Determine if the sql being run is actually dynamic. If it is not,let it go
 *    (2) if the sql is dynamic:
 *          (a) does it appear to be an injection attempt?
 * @author dcowden
 */
public class SqlTracker {
    
    public SqlTracker(){
        this.config = Configuration.active;
    }
    
    public SqlTracker(Configuration config){
        this.config = config;
    }
    private static final Logger log = Logger.getLogger(SqlTracker.class);
    protected Configuration config = null;
    
    //key is the siteID, value is the statement prepared.
    private static final Map<String,String> sqlStatements = new ConcurrentHashMap<>();
    
    public boolean statementLooksLikeInjection(String sqlStatement){
       return SqlParse.isSQLi(sqlStatement);
    }
    
    public void checkSql(String stackFrame, String sqlStatement){
        if ( isStatementDynamic(stackFrame,sqlStatement)){
            if ( config.isAllowDynamicSql()){
                if ( statementLooksLikeInjection(sqlStatement)){
                   throw new ProbableSqlInjectionException(sqlStatement,stackFrame);
                }                
            }
            else{
                throw new DynamicSqlException(sqlStatement,stackFrame);
            }
        }
    }
    public boolean isStatementDynamic(String stackFrame, String sqlStatement){
        String siteId = computeSiteIdFromStackFrame(stackFrame);
        if ( siteContainsStatement(siteId,sqlStatement)){
            return true;
        }
        else{
            storeStatement(siteId,sqlStatement);
            return false;
        }

    }
    
    protected void storeStatement(String siteId, String sqlStatement){
        sqlStatements.put(siteId,sqlStatement);
        debug("Storing siteId='" + siteId + "', statement='" + sqlStatement + "'");
      
    }
    
    protected boolean siteContainsStatement(String siteId, String sqlStatement){
        debug("Checking sql statement='" + sqlStatement + "'");
        if ( sqlStatements.containsKey(siteId)){
            if ( ! sqlStatements.get(siteId).equals(sqlStatement)){
                //same allocation site, but different statement! probably
                //dynamic sql!
                return true;
            }
        }
        return false;
    }
    
    public String computeSiteIdFromStackFrame(String stackFrame){
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] digestedBytes = md.digest(stackFrame.getBytes()); //watch out--using platform default encoding
            String siteId = new BigInteger(1,digestedBytes).toString(16); //MD5 hash in hex
            
            debug("Computed ID='" + siteId +"'");
            debug("StackTrace = '" + stackFrame + "\n'");
            return siteId;
        } catch (NoSuchAlgorithmException ex) {
            throw new RuntimeException(ex);
        }
        
    }    
    
    protected void debug(String message){
       System.out.println(message);
    }
}
