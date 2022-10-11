package com.example.pat_1;

import java.util.ArrayList;

public class RepoStorage {

    public ArrayList<RepoValues> temp_repo = new ArrayList<RepoValues>();
    public ArrayList<RepoValues> light_repo = new ArrayList<RepoValues>();
    public ArrayList<RepoValues> humid_repo = new ArrayList<RepoValues>();

    public RepoValues max_temp = new RepoValues("NULL", -10000, 0);
    public RepoValues min_temp = new RepoValues("NULL", 10000, 0);;
    public RepoValues max_light = new RepoValues("NULL", -10000, 0);;
    public RepoValues min_light = new RepoValues("NULL", 10000, 0);;
    public RepoValues max_humid = new RepoValues("NULL", -10000, 0);;
    public RepoValues min_humid = new RepoValues("NULL", 10000, 0);;

    /**
    public RepoArray(String time_inp, float value_inp, long time_millis_inp) {
        RepoValues first_val = new RepoValues(time_inp, value_inp, time_millis_inp);
        temp_repo.add(first_val);
    }
     */


}
