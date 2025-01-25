package com.cyberpsy.controller;


public class MyErrorController extends RuntimeException  {
    
    public String handleError() {
        //do something like logging
        return "error";
    }

    

}
