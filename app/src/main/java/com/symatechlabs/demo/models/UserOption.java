package com.symatechlabs.demo.models;

import java.util.ArrayList;
import java.util.List;

/**
 * user option is a wrapper for a single entry that is added when the + button is clicked
 */
public class UserOption {
    private List<Region> regions=new ArrayList<>();
    //whether the user has completed selection the  entire region and its data
    //update this from the recycler view when the callback is triggered
    private boolean completed=false;

    public List<Region> getRegions() {
        return regions;
    }

    public void setRegions(List<Region> regions) {
        this.regions = regions;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }
}
