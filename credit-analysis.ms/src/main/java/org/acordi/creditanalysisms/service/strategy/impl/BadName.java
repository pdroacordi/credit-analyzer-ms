package org.acordi.creditanalysisms.service.strategy.impl;

import org.acordi.creditanalysisms.constants.ConstantMessage;
import org.acordi.creditanalysisms.exceptions.StrategyException;
import org.acordi.creditanalysisms.service.strategy.CreditComputation;
import org.acordi.creditanalysisms.domain.Proposal;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.Random;

@Order(1)
@Component
public class BadName implements CreditComputation {
    @Override
    public int compute(Proposal proposal) {
        //I actually wanted to use an API to consult the user's
        // credit situation.
        //Didn't find one, though, so let's just randomize it.
        if( isBadName() ){
            throw new StrategyException(String.format(ConstantMessage.BAD_NAME, proposal.getUser().getName()));
        }
        return 100;
    }

    private boolean isBadName(){
        return new Random().nextBoolean();
    }
}
