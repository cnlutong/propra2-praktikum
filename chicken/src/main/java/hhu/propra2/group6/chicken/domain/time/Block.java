package hhu.propra2.group6.chicken.domain.time;

import java.time.LocalDateTime;

public class Block {

    private final LocalDateTime localDateTime;
    private final Long id;
    private String color;

    public Block(LocalDateTime localDateTime, String color, Long id) {
        this.localDateTime = localDateTime;
        this.color = color;
        this.id = id;
    }


    public LocalDateTime getLocalDateTime() {
        return localDateTime;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Long getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Block{" +
                "localDateTime=" + localDateTime +
                ", color='" + color + '\'' +
                ", id=" + id +
                '}';
    }

}
