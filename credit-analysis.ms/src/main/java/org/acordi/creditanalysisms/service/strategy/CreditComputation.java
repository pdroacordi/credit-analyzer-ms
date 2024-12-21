package org.acordi.creditanalysisms.service.strategy;

import org.acordi.creditanalysisms.domain.Proposal;
public interface CreditComputation {

    int compute(Proposal proposal);
}
