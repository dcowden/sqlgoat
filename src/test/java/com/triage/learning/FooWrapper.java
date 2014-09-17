/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.triage.learning;

/**
 *
 * @author dcowden
 */
public class FooWrapper implements Foo{
    protected Foo wrapped = null;
    
    public FooWrapper(Foo foo){
        this.wrapped = foo;
    }
    @Override
    public String speak() {
        return Foo.WRAPPED_RESPONSE;
    }
    
}
