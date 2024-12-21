package org.acordi.creditanalysisms.service.strategy.impl;

import org.acordi.creditanalysisms.domain.Proposal;
import org.acordi.creditanalysisms.service.strategy.CreditComputation;
import org.springframework.stereotype.Component;

@Component
public class PaymentTermLessThanTenYears implements CreditComputation {
    @Override
    public int compute(Proposal proposal) {
        return isPaymentTermLessThanTenYears(proposal.getPaymentTerm()) ? 80 : 0;
    }

    private boolean isPaymentTermLessThanTenYears(int paymentTerm){
        return paymentTerm < (12 * 10);
    }
}
