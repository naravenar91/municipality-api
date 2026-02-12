package cl.aravena.domain.common.exception;

public class BusinessRuleException extends DomainException {

    // Opcional: Puedes añadir un código de error específico para el frontend
    private final String businessCode;

    public BusinessRuleException(String message) {
        super(message);
        this.businessCode = "BUSINESS_RULE_VIOLATION";
    }

    public BusinessRuleException(String message, String businessCode) {
        super(message);
        this.businessCode = businessCode;
    }

    public String getBusinessCode() {
        return businessCode;
    }
}