package com.practice.consumer.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

@JsonDeserialize
public enum Unit {
    KG, PIECE, LITER;
}
