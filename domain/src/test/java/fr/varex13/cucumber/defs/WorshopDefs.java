package fr.varex13.cucumber.defs;

import fr.varex13.prestation.Workshop;
import fr.varex13.prestation.outputport.WorkshopRepository;
import io.cucumber.java.Before;
import io.cucumber.java.DataTableType;
import io.cucumber.java.en.Given;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;
import java.util.UUID;

public class WorshopDefs {

    @Autowired
    private WorkshopRepository workshopRepository;

    @Before
    public void before() {
        workshopRepository.all().clear();
    }

    @Given("des ateliers existent:")
    public void desAteliersExistent(final List<Workshop> workshops) {
        workshops.forEach(workshopRepository::add);
    }

    @DataTableType
    public Workshop workshopEntry(final Map<String, String> entry) {
        return Workshop.workshopBuilder()
                .id(UUID.fromString(entry.get("id")))
                .label(entry.get("label"))
                .duration(Integer.valueOf(entry.get("duration")))
                .build();
    }

}
