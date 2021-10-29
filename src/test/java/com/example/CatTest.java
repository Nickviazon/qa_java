package com.example;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class CatTest {

    @Mock private Feline felineMock;

    private Cat cat;

    @Before
    public void catInit() {
        cat = new Cat(felineMock);
    }

    @Test
    public void getSoundReturnsMeowString() {
        String actual = cat.getSound();
        assertEquals("Мяу", actual);
    }

    @Test
    public void getFoodReturnsPredatorsFood() throws Exception {
        Mockito.when(felineMock.eatMeat()).thenReturn(List.of("Животные", "Птицы", "Рыба"));
        List<String> catsActualFood = cat.getFood();
        List<String> catsExpectedFood = List.of("Животные", "Птицы", "Рыба");
        Mockito.verify(felineMock).eatMeat();
        assertEquals(catsActualFood, catsExpectedFood);
    }
}