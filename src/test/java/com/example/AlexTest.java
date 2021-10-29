package com.example;

import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class AlexTest {

    private Alex alex;

    @Before
    public void alexInit() throws Exception{
        alex = new Alex();
    }

    @Test
    public void getFriendsReturnsListOfFriends() {
        List<String> actualListOfFriends = alex.getFriends();
        List<String> expectedListOfFriends = List.of("зебра Марти", "бегимотиха Глория", "жираф Мелман");
        assertEquals(expectedListOfFriends, actualListOfFriends);
    }

    @Test
    public void getPlaceOfLivingReturnsNewYorkZooString() {
        String actualPlaceOfLiving = alex.getPlaceOfLiving();
        String expectedPlaceOfLiving = "Нью-Йоркский зоопарк";
        assertEquals(expectedPlaceOfLiving, actualPlaceOfLiving);
    }

    @Test
    public void getKittensAlwaysReturnsZero() {
        int actualKittensCount = alex.getKittens();
        int expectedKittensCount = 0;
        assertEquals(expectedKittensCount, actualKittensCount);
    }
}