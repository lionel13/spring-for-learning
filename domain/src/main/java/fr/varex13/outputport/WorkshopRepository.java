package fr.varex13.outputport;

import fr.varex13.prestation.Workshop;

import java.util.Set;

public interface WorkshopRepository {
    Set<Workshop> all();

    void add(Workshop workshop);
}
