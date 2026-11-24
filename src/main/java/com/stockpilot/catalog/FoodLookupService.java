package com.stockpilot.catalog;
import org.springframework.stereotype.Service;
@Service
class FoodLookupService {
    private final FoodGateway gateway;
    FoodLookupService(FoodGateway gateway) { this.gateway = gateway; }
    FoodProduct lookup(String barcode) {
        if (barcode == null || !barcode.matches("[0-9]{8,14}")) throw new InvalidBarcodeException();
        return gateway.lookup(barcode);
    }
}
class InvalidBarcodeException extends RuntimeException {
    InvalidBarcodeException() { super("Barcode must contain 8 to 14 digits"); }
}
class FoodNotFoundException extends RuntimeException {
    FoodNotFoundException() { super("No food product found for this barcode"); }
}
class FoodProviderException extends RuntimeException {
    FoodProviderException() { super("Food product service is unavailable or returned an invalid response"); }
}
