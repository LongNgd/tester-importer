package energy.at.testerimporter.common.enums;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum AssetKind {
    BreakerAirBlastBreaker("Breaker air blast breaker"),
    BreakerBulkOilBreaker("Breaker bulk oil breaker"),
    BreakerInsulatingStackAssembly("Breaker insulating stack assembly"),
    BreakerMinimumOilBreaker("Breaker minimum oil breaker"),
    BreakerSF6DeadTankBreaker("Breaker SF6 dead tank breaker"),
    BreakerSF6LiveTankBreaker("Breaker SF6 live tank breaker"),
    BreakerTankAssembly("Breaker tank assembly"),
    Other("Other"),
    Transformer("Transformer"),
    TransformerTank("Transformer tank");

    @JsonValue
    private final String value;
}
