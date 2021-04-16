package com.practice.consumer.model;

import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
@EqualsAndHashCode
@Builder
@Slf4j
@JsonIgnoreProperties(ignoreUnknown=true)
public class ChefDto {

    private Long               id;
    private String             name;
    private Set<RestaurantDto> restaurants;

}
