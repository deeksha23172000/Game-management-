package com.deeksha.alternatives;

import javax.enterprise.inject.Alternative;

@Alternative
public class KidGameAnnouncer implements GameAnnouncer {
    @Override
    public String announce() {
        return "Playing games is fun!";
    }
}
