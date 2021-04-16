package com.practice.consumer.model;

import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Builder
public class RestaurantDto {

    private Long         id;
    private String       name;
    private Integer      stars;
    private Set<DishDto> dishes;
    private Set<ChefDto> chefs;
    
}
