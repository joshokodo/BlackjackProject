package com.skilldistillery.cards.common;

public enum Rank {

    TWO(2, "2"), THREE(3, "3"), FOUR(4, "4"), FIVE(5, "5"), SIX(6, "6"), SEVEN(7, "7"), EIGHT(8, "8"), NINE(9, "9"),
    TEN(10, "10"), JACK(10, "J"), QUEEN(10, "Q"), KING(10, "K"), HIGH_ACE(11, "A"), LOW_ACE(1, "A");

    Rank(int value, String stringValue) {
	this.value = value;
	this.stringValue = stringValue;

    }

    private int value;
    private String stringValue;

    public int getValue() {
	return value;
    }

    public String toString() {
	return stringValue;
    }

}
