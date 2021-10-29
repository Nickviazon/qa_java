package com.example;

import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.runners.Enclosed;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(Enclosed.class)
public class FelineTest {

    @RunWith(Parameterized.class)
    public static class ParametrizedTest {

        private Feline feline;

        @Before
        public void felineInit() {
            feline = new Feline();
        }

        @Parameterized.Parameter
        public int kittensCount;

        @Parameterized.Parameter(1)
        public int expectedKittensCount;


        @Parameterized.Parameters(name = "{index} feline.getKittens({0}) -> kittensCount={1}")
        public static Object[][] felineKittensCountData() {
            return new Object[][] {
                    {0, 0},
                    {3, 3},
                    {-3, -3}  // под вопросом, по хорошему в методе логическая ошибка
            };
        }

        @Test
        public void getKittensReturnsSubmittedKittensCount() {
            int actualNumberOfKittens = feline.getKittens(kittensCount);
            assertEquals(expectedKittensCount, actualNumberOfKittens);
        }
    }

    public static class NotParameterizedTest {

        private Feline feline;

        @Before
        public void felineInit() {
            feline = new Feline();
        }

        @Test
        public void eatMeatReturnsPredatorsFood() throws Exception {
            List<String> actualListOfFood = feline.eatMeat();
            List<String> expectedListOfFood = List.of("Животные", "Птицы", "Рыба");
            assertEquals(expectedListOfFood, actualListOfFood);
        }

        @Test
        public void getFamilyReturnsFelineFamilyString() {
            String actualFamily = feline.getFamily();
            assertEquals("Кошачьи", actualFamily);
        }

        @Test
        public void getKittensReturnsOneKitten () {
            int actualNumberOfKittens = feline.getKittens();
            int expectedNumberOfKittens = 1;
            assertEquals(expectedNumberOfKittens, actualNumberOfKittens);
        }
    }
}