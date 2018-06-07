package com.dustnfox.lunchpool.to;

import java.time.LocalDate;
import java.util.Collection;

public class Pool {
    private LocalDate date;
    private Collection<Option> optionList;

    public Pool(LocalDate date, Collection<Option> optionList) {
        this.date = date;
        this.optionList = optionList;
    }
}
