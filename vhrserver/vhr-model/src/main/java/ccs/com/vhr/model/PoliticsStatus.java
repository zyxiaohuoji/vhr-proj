package ccs.com.vhr.model;

import java.util.Objects;

public class PoliticsStatus {

    private Integer id;

    private String name;

    public PoliticsStatus() {
    }

    public PoliticsStatus(String name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PoliticsStatus politicsStatus = (PoliticsStatus) o;
        return Objects.equals(name, politicsStatus.name);
    }
}