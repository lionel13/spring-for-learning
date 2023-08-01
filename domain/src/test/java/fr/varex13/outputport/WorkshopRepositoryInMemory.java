package fr.varex13.outputport;

import fr.varex13.prestation.Workshop;

import java.util.LinkedHashSet;
import java.util.Set;

public class WorkshopRepositoryInMemory implements WorkshopRepository {

    private final Set<Workshop> workshops = new LinkedHashSet<>();

    @Override
    public Set<Workshop> all() {
        return workshops;
    }

    @Override
    public void add(Workshop workshop) {
        workshops.add(workshop);
    }
}
