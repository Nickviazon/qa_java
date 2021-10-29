package com.example;

import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.runners.Enclosed;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(Enclosed.class)
public class AnimalTest {

    @RunWith(Parameterized.class)
    public static class ParameterizedTest {

        @Parameterized.Parameter
        public String animalKind;

        @Parameterized.Parameter(1)
        public List<String> expectedFood;

        @Parameterized.Parameters(name = "{index}: animal.getFood({0}) -> 1")
        public static Object[][] getFoodData() {
            return new Object[][] {
                    {"Травоядное", List.of("Трава", "Различные растения")},
                    {"Хищник", List.of("Животные", "Птицы", "Рыба")},
            };
        }

        private Animal animal;

        @Before
        public void animalInit() {
            animal = new Animal();
        }

        @Test
        public void getFoodReturnsAnimalKindsFood() throws Exception {
            List<String> actualAnimalKindsFood = animal.getFood(animalKind);
            assertEquals(expectedFood, actualAnimalKindsFood);
        }

    }

    public static class NotParameterizedTest {

        private Animal animal;

        @Before
        public void animalInit() {
            animal = new Animal();
        }

        @Test
        public void getFoodWithUnknownKindThrowsException() {
            assertThrows(Exception.class, () -> animal.getFood("foo"));
        }

        @Test
        public void getFamilyReturnsDefinitionOfFamiliesString() {
            String actual = animal.getFamily();
            String expected = "Существует несколько семейств: заячьи, беличьи, мышиные, кошачьи, псовые, медвежьи, куньи";
            assertEquals(expected, actual);
        }
    }


}