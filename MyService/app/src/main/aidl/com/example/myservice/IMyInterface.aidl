// IMyInterface.aidl
package com.example.myservice;

// Declare any non-default types here with import statements

interface IMyInterface {
    /**
     * Demonstrates some basic types that you can use as parameters
     * and return values in AIDL.
     */
//    int messages(int data);

    void setValue(int data);
    int getValue();
}