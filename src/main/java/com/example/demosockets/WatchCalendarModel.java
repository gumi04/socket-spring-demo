package com.example.demosockets;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
public class WatchCalendarModel {
    private String email;
    private String tipo;

    @Override
    public String toString() {
        return super.toString();
    }
}
