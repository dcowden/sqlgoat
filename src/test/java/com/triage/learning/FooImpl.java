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
public class FooImpl implements Foo{

    @Override
    public String speak() {
        return Foo.RESPONSE;
    }
    
}
