package controller;

import entity.bike.StandardBike;
import entity.station.Station;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RentBikeControllerTest {
private RentBikeController rentBikeController;
@BeforeEach
    void setUp() throws Exception{
    rentBikeController= new RentBikeController();
}


    @ParameterizedTest
    @CsvSource({
            "true",
            "false",
            "false"
    })

   void testCheckAvailableBike( boolean expected){
    StandardBike bike= new StandardBike();
    Station station=new Station();
    bike.setStation(station);
    boolean valid= rentBikeController.checkAvailableBike(bike);
    assertEquals(expected,valid);

}
}
