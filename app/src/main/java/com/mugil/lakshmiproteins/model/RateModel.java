package com.mugil.lakshmiproteins.model;

import java.io.Serializable;

/**
 * Created by Administrator on 02-01-2018.
 */

public class RateModel implements Serializable {

    private String chicken_rate;
    private String egg_rate;

    public String getChicken_rate() {
        return chicken_rate;
    }

    public void setChicken_rate(String chicken_rate) {
        this.chicken_rate = chicken_rate;
    }

    public String getEgg_rate() {
        return egg_rate;
    }

    public void setEgg_rate(String egg_rate) {
        this.egg_rate = egg_rate;
    }
}
