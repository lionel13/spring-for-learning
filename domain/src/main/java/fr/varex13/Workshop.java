package fr.varex13;

import java.util.Objects;
import java.util.UUID;

public class Workshop {
    private final UUID id;
    private final String label;
    private final Integer duration;

    private Workshop(final Workshop.WorkshopBuilder workshopBuilder) {
        this.id = workshopBuilder.id;
        this.label = workshopBuilder.label;
        this.duration = workshopBuilder.duration;
    }

    public static Workshop.WorkshopBuilder workshopBuilder() {
        return new Workshop.WorkshopBuilder();
    }

    public UUID getId() {
        return id;
    }

    public String getLabel() {
        return label;
    }

    public Integer getDuration() {
        return duration;
    }


    public static final class WorkshopBuilder {
        private UUID id;
        private String label;
        private Integer duration;

        public WorkshopBuilder id(final UUID id) {
            this.id = id;
            return this;
        }

        public WorkshopBuilder label(final String label) {
            this.label = label;
            return this;
        }

        public WorkshopBuilder duration(final Integer duration) {
            this.duration = duration;
            return this;
        }

        public Workshop build() {
            if (Objects.isNull(id)) {
                throw new IllegalArgumentException("id ne doit pas être null");
            }
            if (Objects.isNull(label)) {
                throw new IllegalArgumentException("label ne doit pas être null");
            }
            if (Objects.isNull(duration)) {
                throw new IllegalArgumentException("duration ne doit pas être null");
            }
            return new Workshop(this);
        }
    }
}
