package com.example.pat_1;

import java.util.ArrayList;

public class RepoStorage {

    public ArrayList<RepoValues> temp_repo = new ArrayList<RepoValues>();
    public ArrayList<RepoValues> light_repo = new ArrayList<RepoValues>();
    public ArrayList<RepoValues> humid_repo = new ArrayList<RepoValues>();

    public RepoValues max_temp = new RepoValues("NULL", -1000000000, 0);
    public RepoValues min_temp = new RepoValues("NULL", 1000000000, 0);;
    public RepoValues max_light = new RepoValues("NULL", -1000000000, 0);;
    public RepoValues min_light = new RepoValues("NULL", 1000000000, 0);;
    public RepoValues max_humid = new RepoValues("NULL", -1000000000, 0);;
    public RepoValues min_humid = new RepoValues("NULL", 1000000000, 0);;

}
