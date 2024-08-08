package org.example.parser.command.car;

import org.example.application.ApplicationState;
import org.example.application.controller.CarController;
import org.example.in.parser.command.car.FilterCarCommand;
import org.example.core.responsesAndRequestes.car.FilterCarRequest;
import org.example.core.responsesAndRequestes.car.ShowCarResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class FilterCarCommandTest {

    private CarController controller;
    private ApplicationState state;

    @BeforeEach
    void setUp() {
        controller = mock(CarController.class);
        state = mock(ApplicationState.class);
    }

    @Test
    @DisplayName("Should successfully filter cars when valid filters are provided")
    void filterCars_testSuccess_whenValidFiltersAreProvided() throws Exception {
        String[] tokens = {"command", "filter", "--carBrand", "Brand", "--carModel", "Model", "--releaseYear", "2020-01-01", "--condition", "New", "--price", "35000"};
        FilterCarCommand command = new FilterCarCommand(controller, tokens);

        Date releaseYear = new SimpleDateFormat("yyyy-MM-dd").parse("2020-01-01");
        List<ShowCarResponse> expectedCars = Collections.singletonList(new ShowCarResponse());
        when(controller.getFilterCars(any(FilterCarRequest.class))).thenReturn(expectedCars);

        command.execute(state);

        verify(controller).getFilterCars(any(FilterCarRequest.class));
    }

    @Test
    @DisplayName("Should throw IllegalArgumentException when unknown flag is provided")
    void filterTest_shouldThrowIllegalArgumentException_whenUnknownFlag() {
        String[] tokens = {"command", "filter", "--carBrand", "Model", "--unknownFlag", "value"};
        FilterCarCommand command = new FilterCarCommand(controller, tokens);

        Exception exception = assertThrows(IllegalArgumentException.class, () -> command.execute(state));

        assertEquals("Unknown flag: --unknownFlag", exception.getMessage());
    }

    @Test
    @DisplayName("Should throw IllegalArgumentException when invalid date format is provided")
    void filterTest_shouldThrowIllegalArgumentException_whenUnknownInvalidDateFormat() {
        String[] tokens = {"command", "filter", "--carBrand", "Model", "--releaseYear", "invalid-date"};
        FilterCarCommand command = new FilterCarCommand(controller, tokens);

        Exception exception = assertThrows(IllegalArgumentException.class, () -> command.execute(state));

        assertEquals("Invalid date format. Use yyyy-MM-dd.", exception.getMessage());
    }

    @Test
    @DisplayName("Should execute without filters and return results")
    void filterTest_shouldExecuteWithoutFilters_whenNoFilterCriteria() throws Exception {
        String[] tokens = {"command", "filter"};
        FilterCarCommand command = new FilterCarCommand(controller, tokens);

        List<ShowCarResponse> expectedCars = Collections.singletonList(new ShowCarResponse());
        when(controller.getFilterCars(any(FilterCarRequest.class))).thenReturn(expectedCars);

        command.execute(state);

        verify(controller).getFilterCars(any(FilterCarRequest.class));
    }
}
