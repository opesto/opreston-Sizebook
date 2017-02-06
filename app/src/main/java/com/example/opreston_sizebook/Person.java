package com.example.opreston_sizebook;

import java.util.Date;
import java.util.Locale;

/**
 * Created by olivier on 2017-02-04.
 */

public class Person {
    private String personMessage;
    private String date;
    private String name;
    private Double neck;
    private Double bust;
    private Double chest;
    private Double waist;
    private Double hip;
    private Double inseam;
    private String comment;

    public Person(String name) {

        this.setName(name);
    }

    public String getDate() {

        return date;
    }
    public void setDate(String date) {

        this.date = date;
    }

    public String getName() {

        return name;
    }

    public void setName(String name) {

        this.name = name;

    }

    public Double getNeck() {

        return neck;
    }

    public void setNeck(Double neck) {

        this.neck = neck;
    }

    public Double getBust() {

        return bust;
    }

    public void setBust(Double bust) {

        this.bust = bust;
    }

    public Double getChest() {

        return chest;
    }

    public void setChest(Double chest) {

        this.chest = chest;
    }

    public Double getWaist() {

        return waist;
    }

    public void setWaist(Double waist) {

        this.waist = waist;
    }

    public Double getHip() {

        return hip;
    }

    public void setHip(Double hip) {

        this.hip = hip;
    }

    public Double getInseam() {

        return inseam;
    }

    public void setInseam(Double inseam) {

        this.inseam = inseam;
    }

    public String getComment() {

        return comment;
    }

    public void setComment(String comment) {

        this.comment = comment;
    }

    public String toStringView() {
        personMessage = String.format("Name: %s\n", name);

        if (date != null) {
            personMessage = personMessage + "date: " + date;
        }
        if (neck != null) {
            personMessage = personMessage + String.format("| neck: %.1f ", neck);
        }
        if (bust != null) {
            personMessage = personMessage + String.format("| bust: %.1f ", bust);
        }
        if (chest != null) {
            personMessage = personMessage + String.format("| chest: %.1f", chest);
        }
        if (waist != null) {
            personMessage = personMessage + String.format("| waist: %.1f", waist);
        }
        if (hip != null) {
            personMessage = personMessage + String.format("| hip: %.1f", hip);
        }
        if (inseam != null) {
            personMessage = personMessage + String.format("| inseam: %.1f", inseam);
        }
        if (comment != null) {
            personMessage = personMessage + "| comment: " + comment;
        }
        return personMessage;
    }
}

