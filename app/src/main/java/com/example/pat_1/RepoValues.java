package com.example.pat_1;

public class RepoValues implements java.io.Serializable{
    protected String time;
    protected float value;
    protected long time_millis;

    public RepoValues (String time_inp, float value_inp, long time_millis_inp){
        time = time_inp;
        value = value_inp;
        time_millis = time_millis_inp;
    }
}
