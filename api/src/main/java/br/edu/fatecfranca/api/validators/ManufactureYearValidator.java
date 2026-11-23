package br.edu.fatecfranca.api.validators;

import java.time.Year;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import br.edu.fatecfranca.api.dtos.CarRequest;

public class ManufactureYearValidator extends RequestValidator<CarRequest> {

   private static final long MIN_YEAR = 1900;

   public ManufactureYearValidator(RequestValidator<CarRequest> next) {
       super(next);
   }

   @Override
   protected void check(CarRequest request, Long carId) {
       Long year = request.yearManufacture();

       if (year == null) {
           return;
       }

       long currentYear = Year.now().getValue();

       if (year < MIN_YEAR || year > currentYear) {
           throw new ResponseStatusException(
               HttpStatus.BAD_REQUEST,
               "O ano de fabricação deve estar entre " + MIN_YEAR + " e " + currentYear + "."
           );
       }
   }
}
