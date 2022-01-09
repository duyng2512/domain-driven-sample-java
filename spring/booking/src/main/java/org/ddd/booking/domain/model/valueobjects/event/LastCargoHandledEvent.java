package org.ddd.booking.domain.model.valueobjects.event;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Embeddable;
import javax.persistence.Transient;

@Embeddable
@Getter
@Setter
public class LastCargoHandledEvent {
    private Integer handlingEventId;

    @Transient // not to be persisted in the database
    private String handlingEventType;

    @Transient // not to be persisted in the database
    private String handlingEventVoyage;

    @Transient // not to be persisted in the database
    private String handlingEventLocation;

    public static final LastCargoHandledEvent EMPTY = new LastCargoHandledEvent();

    public LastCargoHandledEvent() {
    }

    public LastCargoHandledEvent(Integer handlingEventId, String handlingEventType,
                                 String handlingEventVoyage, String handlingEventLocation) {
        this.handlingEventId = handlingEventId;
        this.handlingEventType = handlingEventType;
        this.handlingEventVoyage = handlingEventVoyage;
        this.handlingEventLocation = handlingEventLocation;
    }
}