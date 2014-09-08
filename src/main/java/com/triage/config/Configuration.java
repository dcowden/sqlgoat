package com.triage.config;

/**
 * To be effective, we need to make sure to load this in the parent ( system ) class loader!!!!
 * @author dcowden
 */
public class Configuration {
    
    public static Configuration active = new Configuration();
    
    private boolean allowDynamicSql = false;

    public boolean isAllowDynamicSql() {
        return allowDynamicSql;
    }

    public void setAllowDynamicSql(boolean allowDynamicSql) {
        this.allowDynamicSql = allowDynamicSql;
    }
    
}
