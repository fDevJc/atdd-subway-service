package nextstep.subway.line.domain;

import nextstep.subway.BaseEntity;
import nextstep.subway.station.domain.Station;

import javax.persistence.*;
import java.util.List;

@Entity
public class Line extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String name;
    private String color;
    private double extraFare;

    @Embedded
    private Sections sections = new Sections();

    public Line() {
    }

    public Line(String name, String color) {
        this.name = name;
        this.color = color;
    }

    public Line(String name, String color, Station upStation, Station downStation, int distance, double extraFare) {
        this.name = name;
        this.color = color;
        sections.addLineStation(this, upStation, downStation, distance);
        this.extraFare = extraFare;
    }

    public void update(Line line) {
        this.name = line.getName();
        this.color = line.getColor();
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getColor() {
        return color;
    }

    public List<Station> getStations() {
        return sections.getStations();
    }

    public double getExtraFare() {
        return extraFare;
    }

    public void addLineStation(Station upStation, Station downStation, int distance) {
        sections.addLineStation(this, upStation, downStation, distance);
    }

    public void removeLineStation(Station station) {
        sections.removeLineStation(this, station);
    }
}
